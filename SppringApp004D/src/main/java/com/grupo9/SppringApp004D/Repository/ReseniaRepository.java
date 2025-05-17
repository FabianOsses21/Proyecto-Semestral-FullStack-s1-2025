package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Resenia;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ReseniaRepository {
    private ArrayList<Resenia> listaResenias = new ArrayList<>();
    public ReseniaRepository() {
    }

    public String getAllResenias() {
        String output = "";
        for (Resenia temp: listaResenias) {
            output += "Id del producto: " + temp.getIdProducto() + "\n";
            output += "Comentario: " + temp.getComentario() + "\n";
            output += "Calificacion: " + temp.getCalificacion() + "\n";
            output += "Id del usuario: " + temp.getIdUsuario() + "\n";
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reseñas de algún producto";
        }
    }

    public String getResenia(int idProducto, int idUsuario){
        String output = "";
        for (Resenia temp: listaResenias) {
            if (temp.getIdProducto() == idProducto && temp.getIdUsuario() == idUsuario) {
                output = "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Comentario: " + temp.getComentario() + "\n";
                output += "Calificacion: " + temp.getCalificacion() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
                return output;
            }
        }
        return "No se encontró la reseña del producto con id: " + idProducto + " del el usuario con id: "+ idUsuario;
    }
    public String addResenia(Resenia resenia) {
        listaResenias.add(resenia);
        return "Reseña añadida con éxito";
    }
    public String updateResenia(int idProducto, int idUsuario, Resenia resenia) {
        for (int i = 0; i < listaResenias.size(); i++) {
            if (listaResenias.get(i).getIdProducto() == idProducto && listaResenias.get(i).getIdUsuario() == idUsuario) {
                listaResenias.set(i, resenia);
                return "Reseña modificada con éxito";
            }
        }
        return "No se encontró la reseña con id de Producto: " + idProducto + " y id de Usuario: "+ idUsuario;
    }
    public String deleteResenia(int idProducto, int idUsuario) {
        for (int i = 0; i < listaResenias.size(); i++) {
            if (listaResenias.get(i).getIdProducto() == idProducto && listaResenias.get(i).getIdUsuario() == idUsuario) {
                listaResenias.remove(i);
                return "Reseña eliminada con éxito";
            }
        }
        return "No se encontró la reseña con id de Producto: " + idProducto + " y id de Usuario: "+ idUsuario;
    }
    public String getReseniasByIdProducto(int idProducto) {
        String output = "";
        for (Resenia temp: listaResenias) {
            if (temp.getIdProducto() == idProducto) {
                output += "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Comentario: " + temp.getComentario() + "\n";
                output += "Calificacion: " + temp.getCalificacion() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reseñas de algún producto con id: "+ idProducto;
        }
    }
    public String getReseniasByIdUsuario(int idUsuario) {
        String output = "";
        for (Resenia temp: listaResenias) {
            if (temp.getIdUsuario() == idUsuario) {
                output += "Id del producto: " + temp.getIdProducto() + "\n";
                output += "Comentario: " + temp.getComentario() + "\n";
                output += "Calificacion: " + temp.getCalificacion() + "\n";
                output += "Id del usuario: " + temp.getIdUsuario() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;

        }else {
            return "No se encontraron reseñas de algún producto con id de usuario: "+ idUsuario;
        }
    }
}
