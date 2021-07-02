echo install user-application
#import from file
#k3d image import user-application/target/jib-image.tar
k3s ctr images import user-application/target/jib-image.tar
docker load -i user-application/target/jib-image.tar
kubectl apply -k user-application/k8s/base
#kubectl delete pods,svc,deploy,ing,cm,rs -l app=user-repository
#kubectl rollout restart deployment/user-application
