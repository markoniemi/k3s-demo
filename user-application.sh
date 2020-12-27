echo install user-application
k3d image import markoniemi/user-application:latest
kubectl apply -k user-application/k8s/base
#kubectl delete pods,svc,deploy,ing,cm,rs -l app=user-repository
#kubectl rollout restart deployment/user-application
