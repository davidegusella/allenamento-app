package com.palestra.allenamentoapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.palestra.allenamentoapp.model.Allenamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "allenamento")
public class Esercizio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int serie;
    private int ripetizioni;
    private boolean completato;
    private String descrizione;

    // Campo per salvare il percorso del file
    private String fileUrl;

    @JsonBackReference  // Evita la serializzazione ricorsiva
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "allenamento_id")
    private Allenamento allenamento;
}
