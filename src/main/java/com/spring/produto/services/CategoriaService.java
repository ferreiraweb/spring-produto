package com.spring.produto.services;

import com.spring.produto.domain.Categoria;
import com.spring.produto.dtos.CategoriaDto;
import com.spring.produto.exceptions.CategoriaNomeMustBeNotRepeatedException;
import com.spring.produto.exceptions.CategoriaNotFoundException;
import com.spring.produto.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public CategoriaDto save(CategoriaDto dto) throws CategoriaNomeMustBeNotRepeatedException {

        Optional<Categoria> savedCategoria = repository.findByNome(dto.nome());

        if (savedCategoria.isPresent()) {
            throw new CategoriaNomeMustBeNotRepeatedException("O nome da categoria já está em uso");
        }

        Categoria categoria = repository.save(CategoriaDto.dtoToCategoria(dto));
        return CategoriaDto.categoriaToDto(categoria);
    }


    public List<CategoriaDto> findAll() {

        List<Categoria> categorias = repository.findAll(Sort.by(Sort.Direction.ASC, "nome"));
        return CategoriaDto.listCategoriaToListDto(categorias);
    }

    public CategoriaDto update(Long id, CategoriaDto dto) throws Exception {

        Categoria categoria = repository.findById(id).orElseThrow(() ->
                new CategoriaNotFoundException("Categoria não encontada"));

        Optional<Categoria> categoriaByNome = repository.findByNome(dto.nome());

        if (categoriaByNome.isPresent()) {
            if (categoriaByNome.get().getId() != id) {
                throw new CategoriaNomeMustBeNotRepeatedException("O nome da categoria alterado já existe na base de dados");
            }
        }

        categoria.setNome(dto.nome());

        Categoria savedCategoria = repository.save(categoria);

        return CategoriaDto.categoriaToDto(categoria);

    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
