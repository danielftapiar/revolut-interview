module "eks_managed_node_group" {
  source = "terraform-aws-modules/eks/aws//modules/eks-managed-node-group"

  name            = "system"
  cluster_name    = local.cluster_name
  cluster_version = module.eks.cluster_version

  # vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.private_subnets

  // The following variables are necessary if you decide to use the module outside of the parent EKS module context.
  // Without it, the security groups of the nodes are empty and thus won't join the cluster.
  cluster_primary_security_group_id = module.eks.cluster_primary_security_group_id
  # cluster_security_group_id         = module.eks.node_security_group_id
  cluster_auth_base64 = module.eks.cluster_certificate_authority_data
  min_size            = 1
  max_size            = 1
  desired_size        = 1

  instance_types = ["t3.medium"]
  capacity_type  = "SPOT"

  tags = local.tags
}

resource "kubernetes_config_map" "aws_auth" {
  metadata {
    name      = "aws-auth"
    namespace = "kube-system"
  }
  data = {
    mapRoles = <<YAML
- groups:
  - system:bootstrappers
  - system:nodes
  rolearn: ${module.eks_managed_node_group.iam_role_arn}
  username: system:node:{{EC2PrivateDNSName}}
YAML
    mapUsers = <<YAML
- userarn: userarn
  username: user
  groups:
    - system:masters
YAML
  }

  depends_on = [
    module.eks_managed_node_group
  ]
}
