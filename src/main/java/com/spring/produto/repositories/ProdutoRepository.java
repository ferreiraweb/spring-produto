package com.spring.produto.repositories;

import com.spring.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByNome(String nome);

}
