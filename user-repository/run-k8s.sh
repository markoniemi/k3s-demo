cd user-repository
k3d image import markoniemi/user-repository:latest
kubectl apply -f user-repository.yaml
#kubectl rollout restart deployment/user-repository
