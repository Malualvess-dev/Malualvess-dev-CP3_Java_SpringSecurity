package br.com.fiap.gameapi.repository;

import br.com.fiap.gameapi.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlataformaRepository
        extends JpaRepository<Plataforma, Long> {
}