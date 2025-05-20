package com.grupo9.SppringApp004D.Controller;

import com.grupo9.SppringApp004D.Model.Envio;
import com.grupo9.SppringApp004D.Service.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envio")
public class EnvioController {
    @Autowired
    private EnvioService envioService;

    @GetMapping
    public String getAllEnvios() {
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public String getEnvio(@PathVariable int id) {
        return envioService.getEnvio(id);
    }

    @GetMapping("/usuario/{id}")
    public String getEnviosByUsuario(@PathVariable int id) {
        return envioService.getEnviosByUsuario(id);
    }

    @GetMapping("/estado/{estado}")
    public String getEnviosByEstado(@PathVariable String estado) {
        return envioService.getEnviosByEstado(estado);
    }

    @PostMapping
    public String addEnvio(@RequestBody Envio envio) {
        return envioService.addEnvio(envio);
    }

    @PutMapping("/{id}")
    public String updateEnvio(@PathVariable int id, @RequestBody Envio envio) {
        return envioService.updateEnvio(id, envio);
    }

    @DeleteMapping("/{id}")
    public String deleteEnvio(@PathVariable int id) {
        return envioService.deleteEnvio(id);
    }
}
