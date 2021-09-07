package com.hotel.auth;

import com.hotel.controller.EmpleadoUri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String LOGIN = "/login";
    public static final String ERRORS_403 = "/errors/403";
    public static final String INDEX = "index";
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEnconder() {
        return new BCryptPasswordEncoder();
    
    }
    
    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userDetailsService)
               .passwordEncoder(passwordEnconder());
    }
    
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/empleados")
            .permitAll()
            .anyRequest().authenticated();
        
    }
    
    
}
