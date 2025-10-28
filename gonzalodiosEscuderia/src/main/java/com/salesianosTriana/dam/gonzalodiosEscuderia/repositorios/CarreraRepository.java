package com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}
