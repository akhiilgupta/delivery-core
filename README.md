To up the service go to the service directory and run following commands [maven should be installed]:
1. mvn clean install
2. java -jar target/delivery-core-0.0.1-SNAPSHOT.jar

Service will run on the server http://localhost:9080

User following curls to test the endpoints:

1. To update the status of the delivery:
curl --location --request PUT 'http://localhost:9080/v1/delivery/11312964' \
--header 'Content-Type: application/json' \
--data-raw '{
	"profile_id": "1121131",
	"status": "DELIVERED"
}'

2. To check the status of the delivery:
curl --location --request GET 'http://localhost:9080/v1/delivery/11312964'

3. To create the delivery for a given order id:
curl --location --request POST 'http://localhost:9080/v1/delivery/' \
--header 'Content-Type: application/json' \
--data-raw '{
	"order_id": "1121131",
	"address": "address",
	"phone": "7699092812"
}'

