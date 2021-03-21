set -e
echo install user-repository
#import from file
k3d image import user-repository/target/jib-image.tar
kubectl apply -k user-repository/k8s/dev
#kubectl rollout restart deployment/user-repository
