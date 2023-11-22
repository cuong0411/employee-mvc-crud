package org.cuong.employeemvccrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
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
    }
}
