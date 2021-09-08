package com.hotel.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET,"/api/empleados").permitAll()
            
            //            .antMatchers(API_CLIENTES+"/{id}").permitAll()
            //            .antMatchers("/api/facturas/**").permitAll()
            ////            .antMatchers(HttpMethod.GET, API_CLIENTES + "/{id}").hasAuthority("ROL_USER")
            //            .antMatchers(HttpMethod.POST, API_CLIENTES + "/upload").hasAnyAuthority("ROL_USER", "ROL_ADMIN")
            //            .antMatchers(HttpMethod.POST, API_CLIENTES).hasAuthority("ROL_ADMIN")
            //            .antMatchers("/api/clientes/**").hasAuthority("ROL_ADMIN")
            .anyRequest().authenticated();
        
    }
    
}
