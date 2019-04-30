# Mobile Subscription Management Systems

This is a basic fully functional Rest Api to Manage Mobile Services
 
## System Main features consist in the following:


### Management
- Msisdn Registration (Newly created Msisdn) 
- Msisdn Provisioning (Provisioning Msisdn including assigning type of subscription (Prepaid or Postpaid) and assigning User and Owner) 
- Msisdn DeProvisioning (Subscription is de-provisioned ie no service, can be assigned to another owner (still available in the database without any owner))
- Msisdn Termination (This functionality simulates msisdn port outs, can be re-provisioned. (At thi stage msisdn is purged from the database))
- Msisdn Subscription Update (This functionality allow the action to change subscription from Prepaid to Postpaid and vice versa))
- Msisdn Subscription Update Owner/User (This functionality allow the action to change owner/user))
### Querying
- List all Msisdns details 
- Get Msisdn details by msisdn
- Get Msisdn details by entityId
- List all Msisdn details matching search 

### Historical Event Querying
- List all historical events for MSISDN (including pre terminations and post terminations)
- List all historical events for EntityId (From start till termination)

## Generic Functionality

- Global exception handling
- Fully event driven using axon framework

## Some Technologies used
- Java
- Spring Boot 
- Axon Framework
- Swagger
- JPA
- MySql (Can be plugged to any Relational and Non Relational Databases)
- Orika
- Lombok
- Docker


## Prerequisites prior running the application
- Docker is required to run the application

## Running The Application
- Go in the project folder and from the terminal execute mvn clean install 
- Execute docker-compose build and soon after docker-compose up 
- docker-compose up start MySQL DB on 3007(externally and 3006 internally) and will run inside another docker container
- All APIs can be accessed using http://localhost:8087/ and swagger documentation can be accessed from http://localhost:8087/swagger-ui.html


## Contact me
If you require any additional information please feel free to contact me on christmagro@gmail.com
