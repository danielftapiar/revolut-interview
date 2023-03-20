provider "aws" {
  profile = "poc"
  region  = "us-east-1"
}


provider "kubernetes" { // only usable when running terraform import at end of deployment terraform import kubernetes_config_map.aws_auth kube-system/aws-auth
  host                   = module.eks.cluster_endpoint
  cluster_ca_certificate = base64decode(module.eks.cluster_certificate_authority_data)
  # token                  = module.eks.aws_eks_cluster_auth.default.token

  exec {
    api_version = "client.authentication.k8s.io/v1beta1"
    command     = "aws"
    # This requires the awscli to be installed locally where Terraform is executed
    args = ["eks", "get-token", "--cluster-name", module.eks.cluster_name]
  }
}
