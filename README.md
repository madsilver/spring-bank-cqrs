### Create Docker Network - springbankNet 
We will put all our containers in the same network.
```
docker network create --attachable -d overlay springbankNet
```

### Axon Platform
```
docker run -d --name axon-server \
-p 8024:8024 -p 8124:8124 \
--network springbankNet \
axoniq/axonserver:latest
```
Once installed, check if running:
http://localhost:8024/

### MongoDB
```
docker run -it -d --name mongo-container \
-p 27017:27017 --network springbankNet \
--restart always \
-v mongodb_data_container:/data/db \
mongo:latest
```
Download Client Tools ? Robo 3T:
https://robomongo.org/download

### MySQL
``` 
docker run -it -d --name mysql-container \
-p 3306:3306 --network springbankNet \
-e MYSQL_ROOT_PASSWORD=springbankRootPsw \
--restart always \
-v mysql_data_container:/var/lib/mysql  \
mysql:latest
```
Client tools in Docker ? Adminer:
```
docker run -it -d --name adminer \
-p 8080:8080 --network springbankNet \
-e ADMINER_DEFAULT_SERVER=mysql-container \
--restart always adminer:latest
```


