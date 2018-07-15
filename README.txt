git URL: https://github.com/sanjibpramanick/transaction-service
Pre-Requisite: Java8, Maven

Use spring-boot:run to deploy the application on the integrated tomcat server.

The default running port is 8080

Access swagger with the URL: http://<server>:<ip>/swagger-ui.html

Debug Logs Location: <project-base-dir>/logs/debug-logs/
Localhost Access Logs Location: <project-base-dir>/logs/access-logs/logs/

Use the following command to run as a service

nohup mvn clean install spring-boot:run &

Access Test Report here: <project-base-dir>/target/surefire-reports/ExtentSummaryReport.html
