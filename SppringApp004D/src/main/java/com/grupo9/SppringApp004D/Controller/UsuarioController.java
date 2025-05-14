package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public String getAllUsuarios(){

        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id){

        return usuarioService.getUserById(id);
    }

    @PostMapping
    public String addUser(@RequestBody Usuario usuario){

        return usuarioService.addUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id){

        return usuarioService.deleteUsuario(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @RequestBody Usuario usuario){
        return usuarioService.updateUsuario(id, usuario);
    }
}
