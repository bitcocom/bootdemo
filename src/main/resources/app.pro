server.port=8081

# Thymeleaf ??
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.cache=false
spring.thymeleaf.suffix=.html

# JDBC ??
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.url=jdbc:mysql://localhost:3306/books
spring.datasource.password=12345

# JPA ??
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create
# spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true