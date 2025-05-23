package com.grupo9.SppringApp004D.Service;


import com.grupo9.SppringApp004D.Model.Usuario;
import com.grupo9.SppringApp004D.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String getAllUsuarios() {
        String output = "";
        for (Usuario temp: usuarioRepository.findAll()) {
            output += "Id del usuario: " + temp.getId() + "\n";
            output += "Nombre del usuario: " + temp.getNombre() + "\n";
            output += "password del usuario: " + temp.getPassword() + "\n";
            output += "email del usuario: " + temp.getEmail() + "\n";
            output += "direccion del usuario: " + temp.getDireccion() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontraron usuarios";
        }
        else {
            return output;
        }
    }

    public String getUsuario(int id) {
        String output = "";
            if (usuarioRepository.existsById(id)) {
                Usuario buscado = usuarioRepository.findById(id).get();
                output = "Id del usuario: " +buscado.getId() +"\n";
                output += "Nombre del usuario: " + buscado.getNombre() + "\n";
                output += "password del usuario: " + buscado.getPassword() + "\n";
                output += "email del usuario: " + buscado.getEmail() + "\n";
                output += "direccion del usuario: " + buscado.getDireccion() + "\n";
                return output;
            }
        return "No existe el usuario";
    }

    //Agregar usuario
    public String addUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario agregado con exito!";
    }

    public String removeUsuario(int id) {
            if (usuarioRepository.existsById(id)) {
                Usuario buscado= usuarioRepository.findById(id).get();
                usuarioRepository.delete(buscado);
                return "Usuario eliminado con exito!";
            }
        return "No existe el usuario";
    }

    public String updateUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            Usuario buscado = usuarioRepository.findById(id).get();
            buscado.setNombre(usuario.getNombre());
            buscado.setPassword(usuario.getPassword());
            buscado.setEmail(usuario.getEmail());
            buscado.setDireccion(usuario.getDireccion());
            usuarioRepository.save(buscado);
            return "Usuario actualizado con exito!";
        }
        return "No existe el usuario";
    }
}
