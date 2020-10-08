kubectl delete ingressroute user-application-ingress
kubectl delete service user-application
kubectl delete configmap user-application
kubectl delete deployment user-application

kubectl delete service user-repository
kubectl delete secret user-repository
kubectl delete deployment user-repository
