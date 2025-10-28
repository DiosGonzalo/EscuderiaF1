package com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long> {
}
