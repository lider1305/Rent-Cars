Project web app for renting cars.
--------------------------------------------------------------------------------------------
This application is developed using frameworks MAVEN, HIBERNATE, SPRING, MYSQL, APACHE TOMCAT
--------------------------------------------------------------------------------------------
The application consists of 4 modules entities, dao, service, web
All the dependencies spelled out in the main pom.xml in root folder.
--------------------------------------------------------------------------------------------
The functional part of the application is divided into three access levels
----------------------------
1) the access level - guest
Any user may view the information available in the database cars and test them for the
possibility of reservation for the selected period of time of time.There is a possibility
of selection of auto according to the following parameters such as Manufacturer, Car model,
Body Type, Fuel Type, transmission Type, Value, Year of issue. It is also possible to sort the
received parameters.
----------------------------
2) the access level - registered user
After registering and logging in, the user can change their credentials. May make an order for
the rental of the chosen car. It is possible to view your orders, edit and delete them until
the administrator will not process the order and its status corresponds to ”ACCEPTED”. After
changing the status, the order becomes inactive to change or removal.
----------------------------
3) the access level - administrator
At this access level is accessible only to the system administrator. It is possible to add
new cars to process orders, access to registered users, and orders.
--------------------------------------------------------------------------------------------
USED VERSIONS
--------------------------------------------------------------------------------------------
Maven: 3.3.9
Hibernate: 4.3.6.Final
Spring: 4.3.1.RELEASE
MYSQL 5.6
JDK 1.8.92
Tomcat 8.0.36

----------------------------------------------------------------------------------------------
INSTALLATION
----------------------------------------------------------------------------------------------
1. MYSQL http://dev.mysql.com/
Database settings you can find on the way /dao/src/main/resources/config.properties
Change settings:
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/cars_hibernate
jdbc.username="login"
jdbc.password="password"
Dump of database is in the root /dump_work_database.sql

Database settings for TESTS you can find on the way /dao/src/test/resources/config-test.properties
                                                   /service/src/test/resources/config-test.properties
You also need to change the settings for main database
Dump of database is in the root /dump_test_database.sql

2.MAVEN http://maven.apache.org/
Open the file settings.xml in your maven(...\maven3.3.9\conf\settings.xml)
and write inside the tag <servers>

<server>
<id>TomcatServer</id>
<username>admin</username>
<password>password</password>
</server>

3.APACHE TOMCAT http://tomcat.apache.org/

Open the file tomcat-users.xml in your tomcat(...\apache-tomcat-8.0.36\conf\tomcat-users.xml)
and write inside the tag <tomcat-users>

<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui,manager-script" />
</tomcat-users>

4. Check registration of the environment variables
Use command line: write command "set"
Check whether the registered variables:
-CATALINA_HOME
-JAVA_HOME
-M2_HOME
-PATH (TOMCAT,MAVEN,MYSQL,JDK)

Windows:
For registration -CATALINA_HOME:
GO My Computer ->Select Properties->Advanced setting->Environment Variables
Under System Variables, click New.
Enter the variable name as CATALINA_HOME.
Enter the variable value as the installation path for the Tomcat.
Click OK.
Set Tomcat Path under system variable
PATH= ..\apache-tomcat 8.0.36

For registration -JAVA_HOME:
You must do the same actions as for -CATALINA_HOME:
the variable name as JAVA_HOME
PATH= C:\Program Files\Java\jdk1.8.92

For registration -M2_HOME:
You must do the same actions as for -CATALINA_HOME:
the variable name as M2_HOME
PATH= ..\apache-maven-3.3.9

5. RUN apache tomcat(...\apache-tomcat-8.0.36\bin\startup.bat)

6. RUN MAVEN
mvn install tomcat7:deploy

7. Open browser
In the browser type the path: http://127.0.0.1:8080/Rent-Cars

---------------------------------------------------------------------------
DEFAULT USER:
login: vanu@mail.ru
password: 1234
--------------------
DEFAULT ADMIN
login: admin@mail.ru
password: 1234
-----------------------------------------------------------------------------
