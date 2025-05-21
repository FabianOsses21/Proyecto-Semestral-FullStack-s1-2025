package com.grupo9.SppringApp004D.Repository;

import com.grupo9.SppringApp004D.Model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository


public class ProveedorRepository {
    private List<Proveedor> proveedores = new ArrayList<>();

    public ProveedorRepository(){

    }

    public String getAllProveedores(){
        String output = "  ";
        for (Proveedor temp: proveedores){
            output += output + ("Id del Proveedor" + temp.getId() + "\n");
            output += "Nombre del Proveedor (Nombre y Apellido)" + temp.getProveNombre() + "\n";
            output += "Direccion de correo" + temp.getProveEmail() + "\n";
            output += "Producto seleccionado" + temp.getProducto() + "\n";
            output += "Nombre de la tienda" + temp.getTiendaNombre() + "\n";
        }
        if(output.isEmpty()){
            return "No se encontro el provvedor o no esta escrito";
        }else{
            return output;
        }
    }

    public String getProveedor(int id){
        String output = " ";
        for (Proveedor temp: proveedores){
            if(temp.getId() == id){
                output += "Id del Proveedor" + temp.getId() + "\n";
                output += "Nombre del Proveedor (Nombre y Apellido)" + temp.getProveNombre() + "\n";
                output += "Direccion de correo" + temp.getProveEmail() + "\n";
                output += "Producto seleccionado" + temp.getProducto() + "\n";
                output += "Nombre de la tienda" + temp.getTiendaNombre() + "\n";
                return output;
            }
        }
        return "No existe el proveedor o no esta escrito";
    }

    public String addProveedor(Proveedor proveedor){
        proveedores.add(proveedor);
        return "Informacion agregada con exito";
    }

    public String removeProveedor(int id){
        for (Proveedor temp: proveedores){
            if(temp.getId() == id){
                proveedores.remove(temp);
                return "Informacion eliminada con exito";
            }
        }
        return "No existe la informacion o no esta redactada";
    }

    public String updateProveedor(int id, Proveedor proveedor){
        int index = 0;
        for (Proveedor temp: proveedores){
            if (temp.getId() == id){
                index = proveedores.indexOf(temp);
            }
        }
        if (index != -1){
            return "No existe la informacion";
        }else{
            proveedores.set(index, proveedor);
            return "Informacion actualizada con exito";
        }

    }
}
