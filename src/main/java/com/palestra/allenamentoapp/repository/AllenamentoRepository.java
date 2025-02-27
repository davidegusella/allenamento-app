package com.palestra.allenamentoapp.repository;

import com.palestra.allenamentoapp.model.Allenamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllenamentoRepository extends JpaRepository<Allenamento, Long> {
}
