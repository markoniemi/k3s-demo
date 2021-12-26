#!/bin/bash
set -e

echo copy config.yaml
sudo mkdir -p /etc/rancher/k3s
sudo cp cluster/config.yaml /etc/rancher/k3s/config.yaml
sudo chmod a+r /etc/rancher/k3s/config.yaml

echo copy registries.yaml
sudo cp cluster/registries.yaml /etc/rancher/k3s/

echo install k3s
sudo curl -sfL https://get.k3s.io | sh -
#sudo curl -sfL https://get.k3s.io | sh -s - --docker

echo link  .kube/config to k3s.yaml
ln -sf /etc/rancher/k3s/k3s.yaml $HOME/.kube/config

