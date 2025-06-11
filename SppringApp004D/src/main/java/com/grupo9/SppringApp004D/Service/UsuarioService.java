package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(int id) {
        return usuarioRepository.findById(id).get();
    }

    //Agregar usuario
    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void removeUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario updateUsuario(int id, Usuario usuario) {
        Usuario us =  usuarioRepository.findById(id).get();
        us.setNombre(usuario.getNombre());
        us.setPassword(usuario.getPassword());
        us.setEmail(usuario.getEmail());
        us.setDireccion(usuario.getDireccion());
        usuarioRepository.save(us);
        return us;
    }
}
