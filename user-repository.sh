echo install user-repository
k3d image import markoniemi/user-repository:latest
kubectl apply -k user-repository/k8s/dev
#kubectl rollout restart deployment/user-repository
