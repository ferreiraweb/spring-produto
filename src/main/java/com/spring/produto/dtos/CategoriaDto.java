package com.spring.produto.dtos;

import com.spring.produto.domain.Categoria;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CategoriaDto(
        Optional<Long> id,
        @NotNull(message = "O nome da categoria deve ser informado")
        @Size(min = 5, max = 30, message = "O nome da categoria deve ter entre 5 e 30 caracteres")
        String nome
) {

    public static Categoria dtoToCategoria(CategoriaDto dto) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(dto, categoria);
        return categoria;
    }

    public static CategoriaDto categoriaToDto(Categoria categoria) {

        CategoriaDto dto = new CategoriaDto(
                Optional.ofNullable(categoria.getId()),
                categoria.getNome()
        );

        return dto;
    }

    public static List<CategoriaDto> listCategoriaToListDto(List<Categoria> categorias) {

        List<CategoriaDto> dtos = new ArrayList<CategoriaDto>();
        categorias.forEach(categoria -> dtos.add(categoriaToDto(categoria)));
        return dtos;
    }
}
