package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Resenia;
import com.grupo9.SppringApp004D.Repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReseniaService {
    @Autowired
    ReseniaRepository reseniaRepository;

    public String getAllResenias() {
        String output = "";
        for (Resenia temp: reseniaRepository.findAll()) {
            output += "Id de reseña: " + temp.getId() + "\n";
            output += "Id del producto: " + temp.getProducto().getId() + "\n";
            output += "Comentario: " + temp.getComentario() + "\n";
            output += "Calificacion: " + temp.getCalificacion() + "\n";
            output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen reseñas";
        } else {
            return output;
        }
    }

    public String getResenia(int id){
        String output = "";
        if (reseniaRepository.existsById(id)) {
            Resenia buscado = reseniaRepository.findById(id).get();
            output = "Id de reseña: " + buscado.getId() + "\n";
            output += "Id del producto: " + buscado.getProducto().getId() + "\n";
            output += "Comentario: " + buscado.getComentario() + "\n";
            output += "Calificacion: " + buscado.getCalificacion() + "\n";
            output += "Id del usuario: " + buscado.getUsuario().getId() + "\n";
            return output;
        }
        else {
            return "No existe la reseña con id: " + id;
        }
    }
    public String addResenia(Resenia resenia) {
        reseniaRepository.save(resenia);
        return "Reseña añadida con éxito";
    }
    public String updateResenia(int id, Resenia resenia) {
        if (reseniaRepository.existsById(id)) {
            Resenia buscado = reseniaRepository.findById(id).get();
            buscado.setComentario(resenia.getComentario());
            buscado.setCalificacion(resenia.getCalificacion());
            reseniaRepository.save(buscado);
            return "Reseña actualizada con éxito";
        } else {
            return "No se encontró la reseña con id: " + id;
        }
    }
    public String deleteResenia(int id) {
        if (reseniaRepository.existsById(id)) {
            Resenia buscado = reseniaRepository.findById(id).get();
            reseniaRepository.delete(buscado);
            return "Reseña eliminada con éxito";
        } else {
            return "No se encontró la reseña con id: " + id;
        }
    }
    public String getReseniasByIdProducto(int idProducto) {
        String output = "";
        for (Resenia temp: reseniaRepository.findAll()) {
            if (temp.getProducto().getId() == idProducto) {
                output += "Id del producto: " + temp.getProducto().getId() + "\n";
                output += "Comentario: " + temp.getComentario() + "\n";
                output += "Calificacion: " + temp.getCalificacion() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
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
        for (Resenia temp: reseniaRepository.findAll()) {
            if (temp.getUsuario().getId() == idUsuario) {
                output += "Id de reseña: " + temp.getId() + "\n";
                output += "Id del producto: " + temp.getProducto().getId() + "\n";
                output += "Comentario: " + temp.getComentario() + "\n";
                output += "Calificacion: " + temp.getCalificacion() + "\n";
                output += "Id del usuario: " + temp.getUsuario().getId() + "\n";
            }
        }
        if (!output.isEmpty()){
            return output;
        }else {
            return "No se encontraron reseñas de algún usuario con id: "+ idUsuario;
        }
    }
}
