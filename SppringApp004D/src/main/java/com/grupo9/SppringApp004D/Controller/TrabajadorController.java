package com.grupo9.SppringApp004D.Controller;


import com.grupo9.SppringApp004D.Model.Trabajador;
import com.grupo9.SppringApp004D.Service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    TrabajadorService trabajadorService;

    @GetMapping
    public String getAllTrabajadores(){
        return trabajadorService.getAllTrabajadores();
    }

    @GetMapping("/{id}")
    public String getTrabajadorById(@PathVariable int id){
        return trabajadorService.getTrabajador(id);
    }

    @PostMapping
    public String addTrabajador(@RequestBody Trabajador trabajador){
        return trabajadorService.addTrabajador(trabajador);
    }

    @DeleteMapping("/{id}")
    public String deleteTrabajadorById(@PathVariable int id){
        return trabajadorService.removeTrabajador(id);
    }

    @PutMapping("/{id}")
    public String updateTrabajador(@PathVariable int id, @RequestBody Trabajador trabajador){
        return trabajadorService.updateTrabajador(id, trabajador);
    }
}
