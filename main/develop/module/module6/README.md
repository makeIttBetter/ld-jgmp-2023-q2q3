Maven
1. cd main/develop/module/builders
2. mvn clean install
3. java -jar admin/target/admin-1.0-SNAPSHOT.jar
4. copy web/target/web-1.0-SNAPSHOT.war /path/to/tomcat/webapps/
5. Then start Tomcat, usually with a command like ./catalina.sh run or ./startup.sh in the bin directory of your Tomcat installation


Gradle
1. cd main/develop/module/builders
2. gradle build
3. java -jar admin/build/libs/admin-1.0-SNAPSHOT.jar
4. copy web/build/libs/web-1.0-SNAPSHOT.war /path/to/tomcat/webapps/
5. Then start Tomcat, usually with a command like ./catalina.sh run or ./startup.sh in the bin directory of your Tomcat installation
