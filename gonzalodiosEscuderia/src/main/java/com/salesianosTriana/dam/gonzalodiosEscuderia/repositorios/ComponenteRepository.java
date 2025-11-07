package com.salesianosTriana.dam.gonzalodiosEscuderia.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianosTriana.dam.gonzalodiosEscuderia.modelos.Componente;


@Repository
public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    
    @Query("SELECT c FROM Componente c LEFT JOIN FETCH c.coche WHERE c.id = :id")
    Optional<Componente> findByIdWithCoche(@Param("id") Long id);
}
