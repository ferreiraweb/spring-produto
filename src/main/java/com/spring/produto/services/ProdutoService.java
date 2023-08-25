package com.spring.produto.services;

import com.spring.produto.domain.Categoria;
import com.spring.produto.domain.Produto;
import com.spring.produto.dtos.ProdutoDto;
import com.spring.produto.exceptions.CategoriaNotFoundException;
import com.spring.produto.exceptions.ProdutoNomeMustBeNotRepeatedException;
import com.spring.produto.repositories.CategoriaRepository;
import com.spring.produto.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public ProdutoDto save(ProdutoDto dto) throws Exception {

        if (dto.categoria_id().isEmpty()) {
            throw  new CategoriaNotFoundException("O Id da Categoria deve ser informado.");
        }

        Optional<Categoria> categoria = categoriaRepository.findById(dto.categoria_id().get());

        if (categoria.isEmpty()) {
            throw  new CategoriaNotFoundException("Categoria não econtrada pelo id informado");
        }

        Optional<Produto> produtoByNome = repository.findProdutoByNome(dto.nome());

        if (produtoByNome.isPresent()) {
            throw new ProdutoNomeMustBeNotRepeatedException("O nome do produto já existe na base de dados");
        }

        Produto produto = ProdutoDto.dtoToProduto(dto, categoria.get());

        Produto savedProduto = repository.save(produto);

        return ProdutoDto.produtoToDto(savedProduto);
    }
}
