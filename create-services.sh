k3d image import markoniemi/user-repository:latest
kubectl apply -f user-repository/user-repository.yaml

k3d image import markoniemi/user-application:latest
kubectl apply -f user-application/user-application.yaml
kubectl apply -f user-application/user-application-ingress.yaml
#kubectl port-forward service/user-application 8081:8083
