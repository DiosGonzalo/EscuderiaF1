package com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepositorio extends JpaRepository<Coche,Long> {
}
