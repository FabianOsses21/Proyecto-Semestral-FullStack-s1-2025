package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedor(int id) {
        return proveedorRepository.findById(id).get();
    }

    public Proveedor addProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void removeProveedor(int id) {
        proveedorRepository.deleteById(id);
    }

    public Proveedor updateProveedor(int id, Proveedor proveedor) {
        Proveedor pr =  proveedorRepository.findById(id).get();
        pr.setProductos(proveedor.getProductos());
        pr.setProveEmail(proveedor.getProveEmail());
        pr.setProveRut(proveedor.getProveRut());
        pr.setProveNombre(proveedor.getProveNombre());
        proveedorRepository.save(pr);
        return pr;
    }
}
