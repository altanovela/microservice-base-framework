# Microservice Base Framework

[![Java](https://img.shields.io/badge/Java-1.8.0-red.svg?style=plastic)](https://www.oracle.com/java/technologies/)
[![Maven](https://img.shields.io/badge/Maven-3.5.3-purple.svg?style=plastic)](https://maven.apache.org)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR7-green.svg?style=plastic)](https://spring.io/projects/spring-cloud)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.3.2.RELEASE-green.svg?style=plastic)](https://spring.io/projects/spring-boot)
[![My Batis](https://img.shields.io/badge/MyBatis-3.5.1-red.svg?style=plastic)](https://mybatis.org/mybatis-3/)

Microservice Base Framework ini di buat sebagai acuan untuk mengembangkan framework aplikasi berbasis Java.
Dibuat dengan mempertimbangkan :

- Stateless Application
- Centralized Authentication Service
- Easy to Scale Up
- Support Complex Native Query
- Support Cache (on progress, less priority, modular implementation depend on the service, centralized by using same Cache Provider.)
- Support Multitenancy 
- Support Basic Authorization for All Public Request (Non Login user)
- ...

## Technology Stack
- Spring Cloud Gateway
- Spring Cloud Security
- Spring Cloud Discovery Service (Eureka)
- Spring Boot
- Sprng Data JPA
- Feign Client
- MyBatis

## Architecture Diagram
![](https://gitlab.playcourt.id/riobastian/spring-cloud-architecture/-/raw/master/.res/architecture-diagram.jpg)

## Package Structure

| Application | Project Name | Port | Notes |
| ------------ | ------------ | ------------ | ------------ |
| Spring Cloud Gateway | core-gateway | 8101 | Application Server, used as API Gateway |
| Spring Cloud Security | core-security | 8102 | Application Server, used as Central Authentication |
| Discovery Service (Eureka) | core-discovery | 8103 | Application Server, used as Service Colaborator |
| Application : Member | app-member | 8001 | Application Server, Service Consumer |
| Application : BPJS (sample) | app-bpjs | 8002 | Application Server, Service Consumer |
| Service : Member | service-member-api | | Embedded Library, Supporting Service Producer |
| | service-member-impl | 60101 | Application Server, Service Producer |
| Library | lib-util | | Embedded Library, Common use Function |

## Pre Preparation
```
Created Database & Assign User:
mysql> create database db_member;
mysql> create user 'umember'@'%' identified with mysql_native_password by '123qwe';
mysql> grant all privileges on db_member.* to 'umember'@'%' with grant option;
mysql> flush privileges;

Run Application and Insert Initialize Client Data :
INSERT INTO db_member.oauth_client_details (client_id, access_token_validity, additional_information, authorities, autoapprove, client_secret, authorized_grant_types, web_server_redirect_uri, refresh_token_validity, resource_ids, `scope`) VALUES ('mobile-apps', 900, NULL, 'password,refresh_token,client_credentials,authorization_code', NULL, '$2y$12$qAD6hUSq9FOuvum4XKCBf.5o3/ZtOniJ4pYocfnZoLRvFVtrKRjCu', NULL, NULL, 2592000, NULL, 'read,write');

Client Id     : mobile-apps
Client Secret : rahasia12345
```

## Build & Run
```
1. Git Clone
$ git clone http://gitlab.playcourt.id/riobastian/spring-cloud-architecture.git

2. Compile Supported Library 
$ cd ${PROJECT_BASE}/lib-util/
$ mvn -e clean install
$ cd ${PROJECT_BASE}/service-member-api/
$ mvn -e clean install

3. Run Discovery Service (Eureka)
$ cd ${PROJECT_BASE}/core-discovery/
$ mvn -e clean spring-boot:run

4. Run Service : Member
$ cd ${PROJECT_BASE}/service-member-impl/
$ mvn -e clean spring-boot:run

5. Run Spring Cloud Security
$ cd ${PROJECT_BASE}/core-security/
$ mvn -e clean spring-boot:run

6. Run Application : Member
$ cd ${PROJECT_BASE}/core-security/
$ mvn -e clean spring-boot:run

7. Run Spring Cloud Gateway
$ cd ${PROJECT_BASE}/core-gateway/
$ mvn -e clean spring-boot:run
```

## Sample API
#### Register New Member
Request URL (POST) : 
```
http://localhost:8101/auth/member/register
```
Request Header :
```
Authorization: Basic ZmluYm94LW1vYmlsZS1hcHBzOnBhc3N3b3Jk
Content-Type: application/json
```
*Notes ```Basic Base64Encoder.encode(client-id:client-secret)```

Request Body :
```
{
	"email":"rio.bastian@metranet.co.id",
	"username":"rio.bastian",
	"password":"Password123",
	"image":"http://here.iam/rio.bastian.jpeg"
}
```
Response :
```
{
    "id": 43,
    "username": "rio.bastian",
    "email": "rio.bastian@metranet.co.id",
    "password": "$2a$10$zPLVUKhZvUVfApUbaAsY5euArKCYwzfoTxAJ.bkXyJp5Rl8iCfSzW",
    "type": "BUYER",
    "image": "http://here.iam/rio.bastian.jpeg",
    "status": "ACTIVE"
}
```
#### Login and Get Token
Request URL (POST) : 
```
http://localhost:8101/oauth/token
```
Request Header :
```
Authorization: Basic ZmluYm94LW1vYmlsZS1hcHBzOnBhc3N3b3Jk
Content-Type: application/x-www-form-urlencoded
```
*Notes ```Basic Base64Encoder.encode(client-id:client-secret)```

Request Body :
```
grant_type=password
username=rio.bastian
password=Password123
```
Response :
```
{
    "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJmaW5ib3gtc2VjIiwiZXhwIjoxNTk5NTg2Mjc0LCJ1c2VyX25hbWUiOiJyaW8uYmFzdGlhbiIsImF1dGhvcml0aWVzIjpbIkJVWUVSIl0sImNsaWVudF9pZCI6ImZpbmJveC1tb2JpbGUtYXBwcyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.sMMGxbRwzkdN8ZbY52c2CFH9ShIBStfTVlCQ69UrgPiiykpLS_BubXF1TVbepklt5agLJSZZ-7nFvPqQTCa_crxpX2wobJ6VL75feD-H_Md24tu6lVec2S-0eYIL360p7GGU0bMSAQCmZx7AsnVXdWnAkZkfG1JZoVm3xpn8rUVBZEAjEmBDj3NyenhjQG74RGB6szQWaAlmq32iEQvR7clHIPVSu87Jh06DuwKPASfCQiPC7YTxj67sXcZObv2e0kIvTdSRf5uFBwUdKuvhY3CguFXYCduS7R_4P5KjnbAE-P3LkaC3BuP_1kOqA2VGcyK7Y8KLb_fIw1gqTI02eA",
    "token_type": "bearer",
    "expires_in": 19999,
    "scope": "read write",
    "iss": "finbox-sec"
}
```
#### Get Member Info (based on Token)
Request URL (GET) : 
```
http://localhost:8101/api/member/info
```
Request Header :
```
Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJmaW5ib3gtc2VjIiwiZXhwIjoxNTk5NTg2Mjc0LCJ1c2VyX25hbWUiOiJyaW8uYmFzdGlhbiIsImF1dGhvcml0aWVzIjpbIkJVWUVSIl0sImNsaWVudF9pZCI6ImZpbmJveC1tb2JpbGUtYXBwcyIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.sMMGxbRwzkdN8ZbY52c2CFH9ShIBStfTVlCQ69UrgPiiykpLS_BubXF1TVbepklt5agLJSZZ-7nFvPqQTCa_crxpX2wobJ6VL75feD-H_Md24tu6lVec2S-0eYIL360p7GGU0bMSAQCmZx7AsnVXdWnAkZkfG1JZoVm3xpn8rUVBZEAjEmBDj3NyenhjQG74RGB6szQWaAlmq32iEQvR7clHIPVSu87Jh06DuwKPASfCQiPC7YTxj67sXcZObv2e0kIvTdSRf5uFBwUdKuvhY3CguFXYCduS7R_4P5KjnbAE-P3LkaC3BuP_1kOqA2VGcyK7Y8KLb_fIw1gqTI02eA
```
Response :
```
{
    "id": 43,
    "username": "rio.bastian",
    "email": "rio.bastian@metranet.co.id",
    "password": "$2a$10$zPLVUKhZvUVfApUbaAsY5euArKCYwzfoTxAJ.bkXyJp5Rl8iCfSzW",
    "type": "BUYER",
    "image": "http://here.iam/rio.bastian.jpeg",
    "status": "ACTIVE"
}
```

## Contributors
| Name | Email | Role |
| ------------ | ------------ | ------------ |
| Rio Bastian | rio.bastian@metranet.co.id | Authors |
