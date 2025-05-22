package com.grupo9.SppringApp004D.Repository;
import com.grupo9.SppringApp004D.Model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
// Pasar codigo a Service, Pasarlo a interface y extends JpaRepository<Usuario,Integer>
@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {

    }

    public String getAllUsuarios() {
        String output = " ";
        for (Usuario temp: usuarios) {
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
        for (Usuario temp: usuarios) {
            if (temp.getId() == id) {
                output = "Id del usuario: " + temp.getId() + "\n";
                output += "Nombre del usuario: " + temp.getNombre() + "\n";
                output += "password del usuario: " + temp.getPassword() + "\n";
                output += "email del usuario: " + temp.getEmail() + "\n";
                output += "direccion del usuario: " + temp.getDireccion() + "\n";
                return output;
            }
        }
        return "No existe el usuario";
    }

    //Agregar usuario
    public String addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        return "Usuario agregado con exito!";
    }

    public String removeUsuario(int id) {
        for (Usuario temp: usuarios) {
            if (temp.getId() == id) {
                usuarios.remove(temp);
                return "Usuario eliminado con exito!";
            }
        }
        return "No existe el usuario";
    }

    public String updateUsuario(int id, Usuario usuario) {
        int index =0;
        for (Usuario temp: usuarios) {
            if (temp.getId() == id) {

                index= usuarios.indexOf(temp);
            }
        }
        if (index == -1) {
            return "No existe el usuario";
        }else{
            usuarios.set(index, usuario);
            return "Usuario actualizado con exito!";
        }
    }

}
