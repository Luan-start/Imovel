package com.exemple.imovel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.imovel.entities.Imovel;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Long>{

}