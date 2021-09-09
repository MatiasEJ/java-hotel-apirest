package com.hotel.model.service.impl;

import com.hotel.model.dao.UsuarioDao;
import com.hotel.model.entity.Usuario;
import com.hotel.model.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {
    
    @Autowired
    UsuarioDao usuarioDao;
    
    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findUsuarioByUsername(s);
        if (usuario == null) {
            log.error("No existe el usuario en el sistema");
            throw new UsernameNotFoundException("No existe el usuario en el sistema");
        }
        List<GrantedAuthority> authorities;
        authorities = usuario.getRoles().stream()
                             .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                             .peek(rol -> {
                                 log.info("Rol ->" + rol.getAuthority());
                             })
                             .collect(Collectors.toList());
        
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }
    
    
    @Override
    public Usuario findByUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
}
