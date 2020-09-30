# k3s Traefik Consul demo

## Prerequisites

1. install docker
2. install kubectl
3. install k3d
    
## Create cluster, install Traefik, Consul, Portainer

    ./create_cluster.sh


1. Connect to local Kubernetes, set Endpoint URL: kubernetes.default.svc

### Verify installation

    kubectl get pod --all-namespaces

Wait until all pods are running

    kubectl port-forward $(kubectl get pods --selector "app.kubernetes.io/name=traefik" --output=name) 8080:9000
    kubectl -n consul port-forward service/consul-ui 8500:80

Portainer: [http://localhost:9000](http://localhost:9000)
Consul: [http://localhost:8500](http://localhost:8500)
Traefik: [http://localhost:8080](http://localhost:8080)
