k3d cluster create k3s-default --k3s-server-arg "--no-deploy=traefik" -p 80:80@loadbalancer -p 8500:8500@loadbalancer -p 9000:9000@loadbalancer -p 8080:8080@loadbalancer -p 8082:8082@loadbalancer -p 8081:8081@loadbalancer
k3d kubeconfig merge k3s-default --switch-context

helm repo add traefik https://containous.github.io/traefik-helm-chart
helm repo update
helm install traefik traefik/traefik
#kubectl port-forward $(kubectl get pods --selector "app.kubernetes.io/name=traefik" --output=name) 9000:9000

kubectl create namespace portainer
kubectl apply -n portainer -f https://raw.githubusercontent.com/portainer/k8s/master/deploy/manifests/portainer/portainer-lb.yaml
kubectl apply -f portainer-ingress.yaml

#kubectl -n kube-system patch deployment traefik --type="merge" -p "$(cat traefik-deployment-patch.yaml)" -o yaml
#kubectl -n kube-system patch service traefik --type="strategic" -p "$(cat traefik-service-patch.yaml)" -o yaml
#kubectl apply -f traefik-ingress.yaml
#kubectl apply -f traefik-deployment.yaml
#kubectl apply -f traefik-ingress.yaml
helm repo add hashicorp https://helm.releases.hashicorp.com
kubectl create namespace consul
helm install consul hashicorp/consul --set global.name=consul --namespace consul -f helm-consul-values.yaml
kubectl apply -f consul-ingress.yaml
#kubectl -n consul port-forward service/consul-ui 8886:80
