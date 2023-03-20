locals {
  cluster_name    = "staging"
  cluster_version = "1.23"
  vpc_name        = "staging-k8s"
  region          = "us-east-1"
  account_id      = "001066253573"

  tags = {
    env       = "staging"
    terraform = "true"
  }
}
