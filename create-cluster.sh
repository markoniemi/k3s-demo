#!/bin/bash
set -e

echo create user
kubectl apply -f cluster/default-user-role.yaml

echo add repos
# postgres
helm repo add bitnami https://charts.bitnami.com/bitnami
# docker registry
#helm repo add twuni https://helm.twun.io
helm repo update

echo install postgresql in cluster
helm install postgresql bitnami/postgresql -f cluster/postgresql.yaml

#echo install docker registry
#helm install docker-registry twuni/docker-registry -f cluster/docker-registry.yaml
