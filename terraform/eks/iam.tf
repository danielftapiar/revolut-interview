resource "aws_iam_role_policy_attachment" "external_dns_policy_attach" {
  role       = module.eks_managed_node_group.iam_role_name
  policy_arn = "arn:aws:iam::aws:policy/AmazonRoute53FullAccess"
}

resource "aws_iam_role" "access_cluser" {
  name = "kube-access-developers"

  # inline_policy {
  #   name = "AmazonEKSViewNodesAndWorkloadsPolicy"

  #   policy = data.aws_iam_policy_document.eks_access.json
  # }

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action = "sts:AssumeRole"
        Effect = "Allow"
        Sid    = ""
        Principal = {
          AWS = "${local.account_id}"
        }
      },
    ]
  })

}



resource "aws_iam_policy" "amazon_eks_view_nodes_and_workloads_policy" {
  name        = "AmazonEKSViewNodesAndWorkloadsPolicy"
  path        = "/"
  description = "Policy to Allow Access into the cluster"

  policy = data.aws_iam_policy_document.eks_access.json
}

resource "aws_iam_policy" "amazon_eks_assume_eks_admin_policy" {
  name        = "AmazonEKSAssumeEKSAdminPolicy"
  path        = "/"
  description = "Policy to allow users to read and write to EKS Cluster"

  policy = data.aws_iam_policy_document.admin_policy.json
}

resource "aws_iam_group" "developers" {
  name = "developers"
  path = "/"
}

resource "aws_iam_role_policy_attachment" "eks_developer_attachment" {
  role       = aws_iam_role.access_cluser.name
  policy_arn = aws_iam_policy.amazon_eks_view_nodes_and_workloads_policy.arn
}

resource "aws_iam_role_policy_attachment" "eks_developer_eks_access_attachment" {
  role       = aws_iam_role.access_cluser.name
  policy_arn = aws_iam_policy.amazon_eks_assume_eks_admin_policy.arn
}

resource "aws_iam_group_policy_attachment" "eks_admin_policy_attachment" {
  group      = aws_iam_group.developers.name
  policy_arn = aws_iam_policy.amazon_eks_assume_eks_admin_policy.arn
}

resource "aws_iam_group_policy_attachment" "eks_view_nodes_and_workloads_policy" {
  group      = aws_iam_group.developers.name
  policy_arn = aws_iam_policy.amazon_eks_view_nodes_and_workloads_policy.arn
}


resource "aws_iam_group_policy_attachment" "cloudshell_full_access" {
  group      = aws_iam_group.developers.name
  policy_arn = "arn:aws:iam::aws:policy/AWSCloudShellFullAccess"
}
# # # check
# aws sts assume-role \
#   --role-arn arn:aws:iam::001066253573:role/eks-admin \
#   --role-session-name manager-session \
