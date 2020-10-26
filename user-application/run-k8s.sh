cd user-application
k3d image import markoniemi/user-application:latest
kubectl apply -f user-application.yaml
#kubectl rollout restart deployment/user-application
