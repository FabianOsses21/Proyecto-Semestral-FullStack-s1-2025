package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Cupones;
import com.grupo9.SppringApp004D.Service.CuponesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cupon")

public class CuponesController {

    @Autowired
    CuponesService cuponesService;

    @GetMapping
    public String getAllCupones(){
        return cuponesService.getAllCupones();
    }

    @GetMapping("/{id}")
    public String getCuponById(@PathVariable int id){
        return cuponesService.getCuponesById(id);
    }

    @PostMapping
    public String addCupon(@RequestBody Cupones cupones){
        return cuponesService.addCupones(cupones);
    }

    @DeleteMapping("/{id}")
    public String deleteCuponById(@PathVariable int id){
        return cuponesService.deleteCupones(id);
    }

    @PutMapping("/{id}")
    public String updateCupon(@PathVariable int id, @RequestBody Cupones cupones){
        return cuponesService.updateCupon(id, cupones);
    }

}
