terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
    }
  }

#   backend "s3" {
#     encrypt        = true
#     bucket         = "revolut-terraform-state-poc"
#     dynamodb_table = "terraform-state-lock"
#     key            = "staging/us-east-1/cluster/eks"
#     region         = "us-east-1"
#     profile        = "revolut"
#   }
}
