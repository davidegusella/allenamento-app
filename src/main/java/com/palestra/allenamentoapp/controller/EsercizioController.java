package com.palestra.allenamentoapp.controller;

import com.palestra.allenamentoapp.model.Esercizio;
import com.palestra.allenamentoapp.service.EsercizioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/esercizi")
public class EsercizioController {

    @Autowired
    private EsercizioService esercizioService;

    @GetMapping
    public List<Esercizio> getAllEsercizi() {
        return esercizioService.getAllEsercizi();
    }

    @GetMapping("/{id}")
    public Esercizio getEsercizioById(@PathVariable Long id) {
        return esercizioService.getEsercizioById(id);
    }

    @PostMapping
    public Esercizio createEsercizio(@RequestBody Esercizio esercizio) {
        return esercizioService.saveEsercizio(esercizio);
    }

    @DeleteMapping("/{id}")
    public void deleteEsercizio(@PathVariable Long id) {
        esercizioService.deleteEsercizio(id);
    }

    // Aggiornamento flag di completamento dell'esercizio
    @PutMapping("/{id}")
    public Esercizio updateEsercizio(@PathVariable Long id, @RequestBody Esercizio esercizio) {
        return esercizioService.updateEsercizio(id, esercizio);
    }
}
