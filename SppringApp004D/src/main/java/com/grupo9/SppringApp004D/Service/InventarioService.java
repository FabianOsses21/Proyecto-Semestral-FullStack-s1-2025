package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo9.SppringApp004D.Model.Inventario;
@Service
public class InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;

    public String getAllInventario() {
        return inventarioRepository.getAllInventario();
    }
    public String getInventario(int idProducto,int idTienda) {
        return inventarioRepository.getInventario(idProducto,idTienda);
    }
    public String addInventario(Inventario inventario) {
        return inventarioRepository.addInventario(inventario);
    }
    public String updateInventario(int idProducto, int idTienda,Inventario inventario) {
        return inventarioRepository.updateInventario(idProducto, idTienda,inventario);
    }
    public String deleteInventario(int idProducto, int idTienda) {
        return inventarioRepository.deleteInventario(idProducto, idTienda);
    }
    public String getInventarioByTienda(int idTienda) {
        return inventarioRepository.getInventarioByTienda(idTienda);
    }
    public String getInventarioByProducto(int idProducto) {
        return inventarioRepository.getInventarioByProducto(idProducto);
    }
}
