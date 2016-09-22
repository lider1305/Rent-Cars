Проект веб приложения по аренде автомобилей.
В проекте использовались следующие технологии MAVEN, HIBERNATE, SPRING, MYSQL, APACHE TOMCAT

Используемые версии:
Maven: 3.3.9
Hibernate: 4.3.6.Final
Spring: 4.3.1.RELEASE
MYSQL 5.6
JDK 1.8.92
Tomcat 8.0.36


Сборка и запуск:

Настройки базы данных лежат в библиотеке dao-1.0-SNAPSHOT.jar (config.properties)
Настройки базы данных для тестов /dao/src/test/resources/config-test.properties
Dump базы данных dump_work_database.sql
Dump базы для тестов dump_test_database.sql

Запустить Apache Tomcat указаной выше версии:
Произвести настройку файла tomcat-users.xml (..\apache-tomcat-8.0.36\conf\tomcat-users.xml)

<role rolename="manager-gui"/>
	<role rolename="manager-script"/>
	<user username="admin" password="password" roles="manager-gui,manager-script" />
</tomcat-users>

Произвести настройку Maven файл settings.xml (..\apache-maven-3.3.9\conf\settings.xml)

<server>
<id>TomcatServer</id>
<username>admin</username>
<password>password</password>
</server>

Для сборки проекта использовать Maven указаной выше версии:
mvn install tomcat7:deploy

В браузере указать путь к проету:
http://127.0.0.1:8080/Rent-Cars
