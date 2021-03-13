set -e
./create-cluster.sh
kubectl wait --for=condition=ready --timeout=3600s pod -l app.kubernetes.io/instance=traefik
./user-repository.sh
./user-application.sh
