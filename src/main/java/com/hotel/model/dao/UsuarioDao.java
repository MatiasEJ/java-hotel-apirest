package com.hotel.model.dao;

import com.hotel.model.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
    //Por nombre de metodo
    Usuario findByUsername(String username);
    // Usuario findByUsernameAndEmail(String username,String email);
    
    @Query("select u from Usuario u where u.username=?1")
    Usuario findUsuarioByUsername(String username);
}
