## J HIPSTER Tutorial
JHipster includes the Yeoman , SpringBoot and AngularJS.

### Prerequest
1 . NodeJS
<br> 2 .Yarn
<br> 3 . WebPack
<br> 4 . Yeoman

### Install
Quick setup
1 . Install Java 8 from the Oracle website.
<br> 2 .Install Node.js from the Node.js website (prefer an LTS 64-bit version)
<br> 3 .Install Yarn from the Yarn website
<br> 4 .If you want to use the JHipster Marketplace, install Yeoman: yarn global add yo
<br> 5 .Install JHipster: yarn global add generator-jhipster

### Create JHipster App 
<br> 1 . yo jhipster
<br> 2 . Select the Monolithic Application 
``` 
  Application files will be generated in folder: /home/alpermulayim/IdeaProjects/JBlogJhipster
  Which *type* of application would you like to create? (Use arrow keys)
  ❯ Monolithic application (recommended for simple projects) 
  Microservice application 
  Microservice gateway 
  JHipster UAA server (for microservice OAuth2 authentication) 
```

<br> 3 . Give the application name
```
What is the base name of your application? (JBlogJhipster) JBlog
```
<br> 4 . Set the package of the application
<br> 5 . Select the JHipster Registry Control <No>  
```
  Do you want to use the JHipster Registry to configure, monitor and scale your application? (Use arrow keys)
  ❯ No 
  Yes 
  ```
 <br> 6 . Select authentication type JWT
  ```
  Which *type* of authentication would you like to use? (Use arrow keys)
  ❯ JWT authentication (stateless, with a token) 
  OAuth 2.0 / OIDC Authentication (stateful, works with Keycloak and Okta) 
  HTTP Session Authentication (stateful, default Spring Security mechanism) 
```
  <br 7 . Select Database SQL
   ```
  Which *type* of database would you like to use? (Use arrow keys)
 ❯  SQL (H2, MySQL, MariaDB, PostgreSQL, Oracle, MSSQL) 
  MongoDB 
  Cassandra 
  [BETA] Couchbase 
```
   <br> 8 . Select PRoduction MySQL
  ```
  Which *production* database would you like to use? (Use arrow keys)
❯ MySQL 
  MariaDB 
  PostgreSQL 
  Oracle (Please follow our documentation to use the Oracle proprietary driver) 
  Microsoft SQL Server 
  ```
  <br> 9 . Select Disk-Based H2 database 
  ```
  Which *development* database would you like to use? (Use arrow keys)
❯ H2 with disk-based persistence 
  H2 with in-memory persistence 
  MySQL 
  ```
  <br> 10 . 
  ```
  Do you want to use the Spring cache abstraction? (Use arrow keys)
❯ Yes, with the Ehcache implementation (local cache, for a single node) 
  Yes, with the Hazelcast implementation (distributed cache, for multiple nodes) 
  [BETA] Yes, with the Infinispan (hybrid cache, for multiple nodes) 
  No (when using an SQL database, this will also disable the Hibernate L2 cache) 
  ```
  <br> 11 .No Hibernate cache 2 
  ```Do you want to use Hibernate 2nd level cache? (Y/n) n```
  
  <br> 12 . Use Maven 
  ```
   Would you like to use Maven or Gradle for building the backend? (Use arrow keys)
❯ Maven 
  Gradle 
  ```
  <br> 13 . Social Login no 
  ```
  Which other technologies would you like to use? (Press <space> to select, <a> to toggle all, <i> to inverse sel
ection)
❯◯ Social login (Google, Facebook, Twitter)
 ◯ Search engine using Elasticsearch
 ◯ WebSockets using Spring Websocket
 ◯ API first development using swagger-codegen
 ◯ Asynchronous messages using Apache Kafka
```

<br> 14 . Framework Angular 5 
```
Which *Framework* would you like to use for the client? (Use arrow keys)
❯ Angular 5 
  AngularJS 1.x 
```

<br> 15 . SAAS no 
``` Would you like to enable *SASS* support using the LibSass stylesheet preprocessor? No```
<br> 16 . Interrnalization Support No 
<br> 17 . Marketplace No 

```

