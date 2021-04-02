# k3s Traefik Consul demo

## Prerequisites

1. install docker
2. install kubectl
3. install k3d > 3.4.0
4. install lens (optional)
5. install skaffold (optional)
  
## Create cluster, install Traefik, Consul, Portainer

    ./create_cluster.sh

### Verify installation

    kubectl get pod --all-namespaces

1. Start Lens, add ~/.kube/config to Lens.
2. Wait until all pods are running

## Build services

    ./build.sh
    
## Install applications

    ./user-repository.sh
    ./user-application.sh
    
If something goes wrong, just delete the cluster and start again.

    k3d cluster delete k3s-default
    
## Verify installation

user-application: [http://localhost/users](http://localhost/users) login with admin/admin
user-repository: [http://localhost:8082/api/rest/users/v1]([http://localhost:8082/api/rest/users/v1])
