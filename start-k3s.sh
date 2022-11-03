echo start k3s
#sudo systemctl enable k3s
#sudo systemctl start k3s
sudo k3s server > /dev/null 2>&1 & 
sleep 5
sudo chmod a+r /etc/rancher/k3s/k3s.yaml

#docker volume create local_registry
#docker container run -d --name registry.local -v local_registry:/var/lib/registry --restart always -p 5000:5000 registry:2
