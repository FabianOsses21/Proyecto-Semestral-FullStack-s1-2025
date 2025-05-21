package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public String addProveedor(Proveedor proveedor){
        return proveedorRepository.addProveedor(proveedor);

    }

    public String deleteProveedor(int id){
        proveedorRepository.removeProveedor(id);
        return "Informacion borrada de manera correcta";
    }

    public String getAllProveedores(){
        return proveedorRepository.getAllProveedores();
    }

    public String getProveedorById(int id){
        return proveedorRepository.getProveedor(id);
    }

    public String updateProveedor(int id, Proveedor proveedor){
        return proveedorRepository.updateProveedor(id, proveedor);
    }
}
