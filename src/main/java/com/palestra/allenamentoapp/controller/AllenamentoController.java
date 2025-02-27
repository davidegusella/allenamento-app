package com.palestra.allenamentoapp.controller;

import com.palestra.allenamentoapp.model.Allenamento;
import com.palestra.allenamentoapp.service.AllenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allenamenti")
public class AllenamentoController {
    @Autowired
    private AllenamentoService allenamentoService;

    @GetMapping
    public List<Allenamento> getAllAllenamenti() {
        return allenamentoService.getAllAllenamenti();
    }

    @GetMapping("/{id}")
    public Allenamento getAllenamentoById(@PathVariable Long id) {
        return allenamentoService.getAllenamentoById(id);
    }

    @PostMapping
    public Allenamento createAllenamento(@RequestBody Allenamento allenamento) {
        return allenamentoService.saveAllenamento(allenamento);
    }

    @DeleteMapping("/{id}")
    public void deleteAllenamento(@PathVariable Long id) {
        allenamentoService.deleteAllenamento(id);
    }

    @PutMapping("/{id}")
    public Allenamento updateAllenamento(@PathVariable Long id, @RequestBody Allenamento allenamento) {
        return allenamentoService.updateAllenamento(id, allenamento);
    }
}
