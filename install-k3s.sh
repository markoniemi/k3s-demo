#!/bin/bash
set -e

echo copy config.yaml
sudo mkdir -p /etc/rancher/k3s
sudo cp cluster/config.yaml /etc/rancher/k3s/config.yaml
sudo chmod a+r /etc/rancher/k3s/config.yaml

# get ip_address
#bind_address=`ip route get 8.8.8.8 | head -1 | awk '{print $7}'`
#echo ${bind_address}
# set ip_address to config file
#sudo sed -i "s/bind-address.*/bind-address: ${bind_address}/" /etc/rancher/k3s/config.yaml

echo install k3s
sudo curl -sfL https://get.k3s.io | sh -

# TODO wait for the k3s.yaml to update
echo copy k3s.yaml to .kube/config
mkdir -p $HOME/.kube
cp /etc/rancher/k3s/k3s.yaml $HOME/.kube/config
