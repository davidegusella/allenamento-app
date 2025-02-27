package com.palestra.allenamentoapp.controller;

import com.palestra.allenamentoapp.service.AllenamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetController {

    @Autowired
    private AllenamentoService allenamentoService;

    // Endpoint per testare manualmente il reset dei flag
    @PostMapping("/reset-weekly")
    public String resetWeeklyFlags() {
        allenamentoService.resetCompletamentoSettimanale();
        return "Completamento settimanale resettato con successo.";
    }
}

