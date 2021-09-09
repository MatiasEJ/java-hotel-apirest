package com.hotel.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    public static final  String HAB_HABITACIONES = "/hab/habitaciones";
    private static final String API_EMPLEADOS    = "/api/empleados";
    public static final String API_EMPLEADO_ID = "/api/empleado/{id}";
    public static final String API_EMPLEADO = "/api/empleado";
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, API_EMPLEADOS, API_EMPLEADOS + "/**","/api/uploads/img/**").permitAll()
            .antMatchers(HttpMethod.GET, API_EMPLEADO_ID).hasAnyRole("USER", "ADMIN")
            .antMatchers(HttpMethod.POST, API_EMPLEADOS+"/upload/**").hasAnyRole("USER","ADMIN")
            .antMatchers(HttpMethod.POST, API_EMPLEADO).hasRole("ADMIN")
            .antMatchers(HttpMethod.PUT, API_EMPLEADO_ID).hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, API_EMPLEADO_ID).hasRole("ADMIN")
            .antMatchers(API_EMPLEADOS+"/**").hasRole("ADMIN")
            .anyRequest().authenticated();
        
    }
    
}
