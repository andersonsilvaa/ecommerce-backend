package com.project.ecommerce.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> lstCategoria, Pageable pageRequest);

}
