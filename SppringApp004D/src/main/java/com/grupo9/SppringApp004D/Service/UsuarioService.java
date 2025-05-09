package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String addUsuario(Usuario usuario){
        return usuarioRepository.addUsuario(usuario);
    }

    public String deleteUsuario(int id){
        usuarioRepository.removeUsuario(id);
        return "Usuario eliminado";
    }

    public String getAllUsuarios(){
        return usuarioRepository.getAllUsuarios();
    }

    public String getUserById(int id){
        return usuarioRepository.getUsuario(id);
    }

    public String updateUsuario(int id, Usuario usuario){
        return usuarioRepository.updateUsuario(id, usuario);
    }


}
