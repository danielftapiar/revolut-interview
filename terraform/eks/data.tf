data "aws_caller_identity" "current" {}


data "aws_iam_policy_document" "eks_access" {
  statement {
    sid       = "EksFullAccess"
    effect    = "Allow"
    resources = ["*"]

    actions = ["eks:*"]
  }

  statement {
    sid       = "AllowPassRoleToEks"
    effect    = "Allow"
    resources = ["*"]
    actions   = ["iam:PassRole"]

    condition {
      test     = "StringEquals"
      variable = "iam:PassedToService"
      values   = ["eks.amazonaws.com"]
    }
  }
}

data "aws_iam_policy_document" "admin_policy" {
  statement {
    sid       = "AllowKubeAccessToDevelopers"
    effect    = "Allow"
    resources = ["arn:aws:iam::${local.account_id}:role/kube-access-developers"]
    actions   = ["sts:AssumeRole"]
  }
}
