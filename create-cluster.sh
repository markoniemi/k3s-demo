# create cluster
k3d cluster create k3s-default -i docker.io/rancher/k3s:v1.19.2-k3s1 --servers 2 --k3s-server-arg "--no-deploy=traefik" -p 80:80@loadbalancer -p 8500:8500@loadbalancer -p 9000:9000@loadbalancer -p 8080:8080@loadbalancer -p 8082:8082@loadbalancer -p 8083:8083@loadbalancer
k3d kubeconfig merge k3s-default --switch-context

# create user
kubectl apply -f default-user-role.yaml

# add repos
#helm repo add traefik https://helm.traefik.io/traefik
helm repo add portainer https://portainer.github.io/k8s/
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo add stable https://kubernetes-charts.storage.googleapis.com/
helm repo update

# Nginx
helm install ingress-nginx ingress-nginx/ingress-nginx

# Traefik
#helm install traefik traefik/traefik --set image.tag=v2.3.2
#kubectl apply -f traefik-ingress.yaml

# Portainer
kubectl create namespace portainer
helm install -n portainer portainer portainer/portainer --set service.type=LoadBalancer

# Postgre
helm install postgresql bitnami/postgresql -f postgresql-helm-values.yaml
