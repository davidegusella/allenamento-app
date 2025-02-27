package com.palestra.allenamentoapp.service;

import com.palestra.allenamentoapp.model.Esercizio;
import com.palestra.allenamentoapp.repository.EsercizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EsercizioService {

    @Autowired
    private EsercizioRepository esercizioRepository;

    // Metodo per recuperare tutti gli esercizi
    public List<Esercizio> getAllEsercizi() {
        return esercizioRepository.findAll();
    }

    // Metodo per recuperare un esercizio tramite id
    public Esercizio getEsercizioById(Long id) {
        // Controllo se l'esercizio esiste, se non esiste ritorno un'eccezione con messaggio personalizzato
        return esercizioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio con id " + id + " non trovato."));
    }

    // Metodo per salvare un nuovo esercizio
    public Esercizio saveEsercizio(Esercizio esercizio) {
        // Controllo se l'esercizio ha un allenamento associato
        if (esercizio.getAllenamento() == null || esercizio.getAllenamento().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Allenamento associato mancante o non valido.");
        }

        // Salvataggio dell'esercizio
        return esercizioRepository.save(esercizio);
    }

    // Metodo per cancellare un esercizio tramite id
    public void deleteEsercizio(Long id) {
        // Verifica se l'esercizio esiste
        esercizioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio con id " + id + " non trovato."));

        // Cancella l'esercizio
        esercizioRepository.deleteById(id);
    }

    // Metodo per aggiornare il flag di completamento dell'esercizio
    public Esercizio updateEsercizio(Long id, Esercizio esercizio) {
        // Recupera l'esercizio esistente
        Esercizio existingEsercizio = esercizioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Esercizio con id " + id + " non trovato."));

        // Aggiorna il flag completato
        existingEsercizio.setCompletato(esercizio.isCompletato());

        // Salva l'esercizio aggiornato
        return esercizioRepository.save(existingEsercizio);
    }
}
