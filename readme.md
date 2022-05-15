# k3s Traefik SpringBoot demo

## Prerequisites

1. install docker
2. install k9s (optional)

## Install k3s

    ./install-k3s.sh
  
## Create cluster, install Traefik, PostGRE

    ./create_cluster.sh

### Verify installation

    kubectl get pod --all-namespaces

or start k9s

## Build services

    ./build.sh
    
## Install applications

    ./user-repository.sh
    ./user-application.sh
    
If something goes wrong, just delete the cluster and start again.

    ./uninstall-k3s.sh
    
## Verify installation

user-application: [http://localhost/users](http://localhost/users) login with admin/admin
user-repository: [http://localhost:8082/api/rest/users/v1]([http://localhost:8082/api/rest/users/v1])
