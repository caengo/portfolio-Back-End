//Clase para crear roles inicialmente

 
package com.TPPortfolio.portfolio.Util;

import com.TPPortfolio.portfolio.Security.Entity.Rol;
import com.TPPortfolio.portfolio.Security.Enums.RolNombre;
import com.TPPortfolio.portfolio.Security.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CrearRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;
    
    @Override
    public void run(String... args) throws Exception {


        Rol rolUser = new Rol (RolNombre.ROLE_USER);
        rolService.save(rolUser);

        
        //Rol rolAdmin = new Rol (RolNombre.ROLE_ADMIN);
        //rolService.save(rolAdmin);
    }
    
}
