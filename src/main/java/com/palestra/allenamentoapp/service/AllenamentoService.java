package com.palestra.allenamentoapp.service;

import com.palestra.allenamentoapp.model.Allenamento;
import com.palestra.allenamentoapp.model.Esercizio;
import com.palestra.allenamentoapp.repository.AllenamentoRepository;
import com.palestra.allenamentoapp.repository.EsercizioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AllenamentoService {

    @Autowired
    private AllenamentoRepository allenamentoRepository;

    @Autowired
    private EsercizioRepository esercizioRepository;

    // Metodo che azzera i flag di completamento ogni settimana
    @Scheduled(cron = "0 0 0 * * MON")  // Esegue ogni lunedì alle 00:00
    public void resetCompletamentoSettimanale() {
        // Reset dei flag degli allenamenti
        List<Allenamento> allenamenti = allenamentoRepository.findAll();
        for (Allenamento allenamento : allenamenti) {
            allenamento.setCompletato(false);
            allenamentoRepository.save(allenamento);  // Salva ogni allenamento con il flag resettato
        }

        // Reset dei flag degli esercizi
        List<Esercizio> esercizi = esercizioRepository.findAll();
        for (Esercizio esercizio : esercizi) {
            esercizio.setCompletato(false);
            esercizioRepository.save(esercizio);  // Salva ogni esercizio con il flag resettato
        }
    }

    // Metodo per recuperare tutti gli allenamenti
    public List<Allenamento> getAllAllenamenti() {
        return allenamentoRepository.findAll();
    }

    // Metodo per recuperare un allenamento esistente tramite id
    public Allenamento getAllenamentoById(Long id) {
        return allenamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenamento con id " + id + " non trovato."));
    }

    // Metodo per aggiornare il flag dell'allenamento
    public Allenamento updateAllenamento(Long id, Allenamento allenamento) {

        // Recupera l'allenamento esistente
        Allenamento existingAllenamento = allenamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenamento non trovato con id " + id));

        // Recupera gli esercizi associati all'allenamento
        List<Esercizio> eserciziAssociati = esercizioRepository.findByAllenamentoId(id);

        // Verifica se tutti gli esercizi sono completati
        boolean tuttiCompletati = eserciziAssociati.stream()
                .allMatch(Esercizio::isCompletato);  // Verifica se tutti gli esercizi sono completati

        // Se non tutti gli esercizi sono completati, non aggiorniamo l'allenamento
        if (!tuttiCompletati) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Non tutti gli esercizi sono completati. Impossibile aggiornare l'allenamento.");
        }

        // Aggiorna i dati dell'allenamento
        existingAllenamento.setNomeGiorno(allenamento.getNomeGiorno());
        existingAllenamento.setCompletato(allenamento.isCompletato());

        return allenamentoRepository.save(existingAllenamento);
    }

    // Metodo per salvare un nuovo allenamento
    public Allenamento saveAllenamento(Allenamento allenamento) {
        return allenamentoRepository.save(allenamento);
    }

    // Metodo per cancellare un allenamento tramite il suo id
    public void deleteAllenamento(Long id) {
        allenamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Allenamento non trovato con id " + id));

        // Cancella l'allenamento
        allenamentoRepository.deleteById(id);
    }
}
