package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value="classpath:hibernate.properties")
// TODO classpath: - не правильно определяет путь для .jar приложения
public class DbConfig {

    @Value( "${hibernate.connection.driver_class}")
    private String driverClass;

    @Value( "${hibernate.connection.url}")
    private String url;

    @Value( "${hibernate.connection.password}")
    private String password;

    @Value( "${hibernate.connection.username}")
    private String username;

    @Bean(name="dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClass);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setUrl(url);

        return ds;
    }

}