#k3d cluster create k3s-default -i docker.io/rancher/k3s:v1.19.2-k3s1 --k3s-server-arg "--no-deploy=traefik" -p 80:80@loadbalancer -p 8500:8500@loadbalancer -p 9000:9000@loadbalancer -p 8080:8080@loadbalancer -p 8082:8082@loadbalancer -p 8083:8083@loadbalancer
k3d cluster create k3s-default --k3s-server-arg "--no-deploy=traefik" -p 80:80@loadbalancer -p 8500:8500@loadbalancer -p 9000:9000@loadbalancer -p 8080:8080@loadbalancer -p 8082:8082@loadbalancer -p 8083:8083@loadbalancer
k3d kubeconfig merge k3s-default --switch-context

helm repo add traefik https://containous.github.io/traefik-helm-chart
helm repo update
helm install traefik traefik/traefik
kubectl apply -f traefik-ingress.yaml
#kubectl port-forward $(kubectl get pods --selector "app.kubernetes.io/name=traefik" --output=name) 8888:9000

#kubectl create namespace portainer
#kubectl apply -n portainer -f https://raw.githubusercontent.com/portainer/k8s/master/deploy/manifests/portainer/portainer-lb.yaml
#kubectl apply -f portainer-ingress.yaml
helm repo add portainer https://portainer.github.io/k8s/
helm repo update
kubectl create namespace portainer
helm install -n portainer portainer portainer/portainer --set service.type=LoadBalancer


#kubectl -n kube-system patch deployment traefik --type="merge" -p "$(cat traefik-deployment-patch.yaml)" -o yaml
#kubectl -n kube-system patch service traefik --type="strategic" -p "$(cat traefik-service-patch.yaml)" -o yaml
#kubectl apply -f traefik-ingress.yaml
#kubectl apply -f traefik-deployment.yaml
#kubectl apply -f traefik-ingress.yaml

helm repo add hashicorp https://helm.releases.hashicorp.com
helm install consul hashicorp/consul --set global.name=consul -f consul-helm-values.yaml
kubectl apply -f consul-ingress.yaml
#kubectl port-forward service/consul-ui 8886:80

helm repo add bitnami https://charts.bitnami.com/bitnami
helm install postgresql bitnami/postgresql -f postgresql-helm-values.yaml

kubectl apply -f default-user-role.yaml
