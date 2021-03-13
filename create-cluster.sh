echo create cluster
k3d cluster create --config k3d-default.yaml

echo create user
kubectl apply -f default-user-role.yaml

echo add repos
#helm repo for traefik 
helm repo add traefik https://helm.traefik.io/traefik
#helm repo for postgresql 
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update

echo install Traefik
helm install traefik traefik/traefik --set image.tag=v2.4.2
kubectl apply -f traefik-ingress.yaml

# Postgre
echo install postgresql in cluster
helm install postgresql bitnami/postgresql -f postgresql-helm-values.yaml
#echo install postgresql outside of cluster
#kubectl apply -f postgresql-external.yaml
#docker-compose up -d postgresql
