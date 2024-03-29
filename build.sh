# build only one module:
# ./build.sh -pl user-repository -am

docker run \
-it --rm \
-v /var/run/docker.sock:/var/run/docker.sock \
-v "$PWD":/usr/src \
-v "$HOME/.m2":/root/.m2 \
-w /usr/src \
maven:3.8-eclipse-temurin-11-alpine \
mvn package -DskipTests=true -P docker "$@"

#docker load -i user-repository/target/jib-image.tar
#docker load -i user-application/target/jib-image.tar
