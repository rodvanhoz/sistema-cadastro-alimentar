package com.rvr.sistemacadastroalimentar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvr.sistemacadastroalimentar.entities.Alimentos;

public interface AlimentosRepository extends JpaRepository<Alimentos, Integer> {

}
