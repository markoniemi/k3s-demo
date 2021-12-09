#!/bin/bash
set -e

echo create user
kubectl apply -f cluster/default-user-role.yaml

echo add repos
# postgres
helm repo add bitnami https://charts.bitnami.com/bitnami
# docker registry
#helm repo add twuni https://helm.twun.io
# haproxy
helm repo add haproxy-ingress https://haproxy-ingress.github.io/charts
helm repo update

echo install haproxy
helm install haproxy-ingress haproxy-ingress/haproxy-ingress --namespace kube-system --values cluster/haproxy-values.yaml

echo install postgresql in cluster
helm install postgresql bitnami/postgresql -f cluster/postgresql.yaml

#echo install docker registry
#helm install docker-registry twuni/docker-registry -f cluster/docker-registry.yaml
