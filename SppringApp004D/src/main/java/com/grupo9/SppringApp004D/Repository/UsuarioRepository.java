package com.grupo9.SppringApp004D.Repository;
import com.grupo9.SppringApp004D.Model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private List<Usuario> listaUsuarios = new ArrayList<>();

    public UsuarioRepository() {

    }

    public String getAllUsuarios() {
        String output = " ";
        for (Usuario temp: listaUsuarios) {
            output += "Id del usuario: " + temp.getId() + "\n";
            output += "Nombre del usuario: " + temp.getNombre() + "\n";
            output += "password del usuario: " + temp.getPassword() + "\n";
            output += "email del usuario: " + temp.getEmail() + "\n";
        }
        if(output.isEmpty()){
            return "No existe el usuario";
        }
        else {
            return output;
        }
    }

    public String getUsuario(int id) {
        String output = "";
        for (Usuario temp: listaUsuarios) {
            if (temp.getId() == id) {
                output = "Id del usuario: " + temp.getId() + "\n";
                output += "Nombre del usuario: " + temp.getNombre() + "\n";
                output += "password del usuario: " + temp.getPassword() + "\n";
                output += "email del usuario: " + temp.getEmail() + "\n";
                return output;
            }
        }
        return "No existe el usuario";
    }

    //Agregar usuario
    public String addUsuario(Usuario usuario) {
        listaUsuarios.add(usuario);
        return "Usuario agregado con exito!";
    }

    public String removeUsuario(int id) {
        for (Usuario temp: listaUsuarios) {
            if (temp.getId() == id) {
                listaUsuarios.remove(temp);
                return "Usuario eliminado con exito!";
            }
        }
        return "No existe el usuario";
    }

    public String updateUsuario(int id, Usuario usuario) {
        int index =0;
        for (Usuario temp: listaUsuarios) {
            if (temp.getId() == id) {

                index= listaUsuarios.indexOf(temp);
            }
        }
        if (index == -1) {
            return "No existe el usuario";
        }else{
            listaUsuarios.set(index, usuario);
            return "Usuario actualizado con exito!";
        }
    }

}
