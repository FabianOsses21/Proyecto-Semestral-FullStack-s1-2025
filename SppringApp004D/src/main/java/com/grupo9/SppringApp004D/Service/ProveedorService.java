package com.grupo9.SppringApp004D.Service;

import com.grupo9.SppringApp004D.Model.Proveedor;
import com.grupo9.SppringApp004D.Repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;

    public String getAllProveedores(){
        String output = "";
        for (Proveedor temp : proveedorRepository.findAll()) {
            output += "Id del proveedor: " + temp.getId() + "\n";
            output += "RUT del proveedor: " + temp.getProveRut() + "\n";
            output += "Nombre del proveedor: " + temp.getProveNombre() + "\n";
            output += "Email del proveedor: " + temp.getProveEmail() + "\n";
            output += "Productos del proveedor: " + temp.getProductos() + "\n";
        }
        if (output.isEmpty()) {
            return "No existen proveedores";
        } else {
            return output;
        }
    }
    public String getProveedor(int id){
        String output = "";
        if (proveedorRepository.existsById(id)) {
            Proveedor buscado = proveedorRepository.findById(id).get();
            output = "Id del proveedor: " + buscado.getId() + "\n";
            output += "RUT del proveedor: " + buscado.getProveRut() + "\n";
            output += "Nombre del proveedor: " + buscado.getProveNombre() + "\n";
            output += "Email del proveedor: " + buscado.getProveEmail() + "\n";
            output += "Productos del proveedor: " + buscado.getProductos() + "\n";
            return output;
        } else {
            return "No existe el proveedor con id: " + id;
        }
    }

    public String addProveedor(Proveedor proveedor){
        proveedorRepository.save(proveedor);
        return "Proveedor agregado con exito!";
    }

    public String removeProveedor(int id){
        if (proveedorRepository.existsById(id)) {
            Proveedor buscado = proveedorRepository.findById(id).get();
            proveedorRepository.delete(buscado);
            return "Proveedor eliminado con exito!";
        } else {
            return "No existe el proveedor con id: " + id;
        }
    }

    public String updateProveedor(int id, Proveedor proveedor){
        if (proveedorRepository.existsById(id)) {
            Proveedor buscado = proveedorRepository.findById(id).get();
            buscado.setProveRut(proveedor.getProveRut());
            buscado.setProveNombre(proveedor.getProveNombre());
            buscado.setProveEmail(proveedor.getProveEmail());
            buscado.setProductos(proveedor.getProductos());
            proveedorRepository.save(buscado);
            return "Proveedor actualizado con exito!";
        } else {
            return "No existe el proveedor con id: " + id;
        }
    }
}
