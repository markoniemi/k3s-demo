docker-compose up -d postgresql
k3d image import markoniemi/user-repository:latest
kubectl apply -k user-repository/k8s/external-db
#kubectl rollout restart deployment/user-repository
