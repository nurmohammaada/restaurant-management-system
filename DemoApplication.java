import org.hibernate.Hibernate;
import org.hibernate.boot.model.relational.Database;

spring.application.name=your-application-name
Hibernate DDL auto setting
spring.jpa.hibernate.ddl-auto=update

Database connection properties
spring.datasource.url=jdbc:mysql://localhost:8080/your-database-name
spring.datasource.username=root
spring.datasource.password=your-password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
how SQL queries in the console
spring.jpa.show-sql=true
