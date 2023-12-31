package org.cuong.employeemvccrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    // hard code users and exist in memory
/*    @Bean
    InMemoryUserDetailsManager userDetailsManager() {
        UserDetails ana = User.builder()
                .username("ana")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails topson = User.builder()
                .username("topson")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails ceb = User.builder()
                .username("ceb")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(ana, topson, ceb);
    }*/

    // users in database
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) { // spring will inject datasource
        System.out.println(dataSource); // HikariDataSource

        // use custom table and column names: members and roles
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id = ?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id = ?");
        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(configurer -> {
                configurer.requestMatchers("/assets/**").permitAll();
                configurer.requestMatchers("/about").permitAll();
                configurer.requestMatchers("/contact").permitAll();
                configurer.requestMatchers("/").permitAll();
                configurer.requestMatchers("").permitAll();
                configurer.requestMatchers("/access-denied").permitAll();
//                configurer.requestMatchers("/employees/**").authenticated();
                configurer.requestMatchers("/employees").hasAnyRole("MANAGER", "ADMIN");
                configurer.requestMatchers("/employees/**").hasRole("ADMIN");
            })
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/authenticateTheUser") // no need request mapping required for this
                    .permitAll())
            .logout(LogoutConfigurer::permitAll)
            .exceptionHandling(customizer -> customizer.accessDeniedPage("/access-denied"));
        return http.build();
    }
}
