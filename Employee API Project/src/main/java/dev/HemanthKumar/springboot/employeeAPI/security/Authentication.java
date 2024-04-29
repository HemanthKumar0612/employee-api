package dev.HemanthKumar.springboot.employeeAPI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import javax.sql.DataSource;

@Configuration
public class Authentication {
    @Bean
    public UserDetailsManager detailsManager(DataSource datasource){
        // Creates a User Details Manager Object from data source
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(datasource);
        // Set Users
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        // Set Authorities
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
//                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
//                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
//                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
//                .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
//                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                        .requestMatchers("/**").permitAll()

        );
        // Basic HTTP Auth
        http.httpBasic(Customizer.withDefaults());

        // Disable csrf
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
