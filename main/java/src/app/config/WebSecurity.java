package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/edit/**").hasRole("EDITOR").and()
                .logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID").and()
                .formLogin().loginPage("/login").failureUrl("/loginfailed").and()
                .rememberMe();
    }

    @Bean
    public Md5PasswordEncoder encoder() {
        return new Md5PasswordEncoder();
    }
}
