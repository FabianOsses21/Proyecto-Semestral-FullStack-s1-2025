
package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Inventario;
import com.grupo9.SppringApp004D.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    InventarioRepository inventarioRepository;

    public List<Inventario> getAllInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario getInventario(int id) {
        return inventarioRepository.findById(id).get();
    }

    public Inventario addInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void deleteInventario(int id) {
        inventarioRepository.deleteById(id);
    }

    public Inventario updateInventario(int id, Inventario inventario) {
        Inventario inv = inventarioRepository.findById(id).get();
        inv.setCantidad(inventario.getCantidad());
        inv.setProducto(inventario.getProducto());
        inv.setTienda(inventario.getTienda());
        inv.setFechaActualizacion(inventario.getFechaActualizacion());
        inventarioRepository.save(inv);
        return inv;
    }

    public List<Inventario> getInventarioByTienda(int idTienda) {
        return inventarioRepository.findAll()
                .stream()
                .filter(i -> i.getTienda().getId() == idTienda)
                .toList();
    }

    public List<Inventario> getInventarioByProducto(int idProducto) {
        return inventarioRepository.findAll()
                .stream()
                .filter(i -> i.getProducto().getId() == idProducto)
                .toList();
    }
}
