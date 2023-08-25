package com.spring.produto.dtos;

import com.spring.produto.domain.Categoria;
import com.spring.produto.domain.Produto;
import jakarta.validation.constraints.*;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public record ProdutoDto(

        Optional<Long> id,
        @NotEmpty(message = "O nome do produto deve ser informado")
        @Size(max = 30, message = "O nome do produto deve ter no máximo 30 caracteres")
        String nome,
        @Positive(message = "O valor dever ser maior do que zero")
        BigDecimal valor,
        @NotNull(message = "A data de validade deve ser informada")
        Date validade,

        Optional<Categoria> categoria,
        @NotNull(message = "O Id da categoria deve ser informado, por favor")
        Optional<Long> categoria_id

) {

        public static Produto dtoToProduto(ProdutoDto dto, Categoria categoria) {

                Produto produto = new Produto();
                BeanUtils.copyProperties(dto, produto);
                produto.setCategoria(categoria);
                return produto;
        }

        public static ProdutoDto produtoToDto(Produto produto) {
                ProdutoDto dto = new ProdutoDto(
                       Optional.ofNullable(produto.getId()),
                        produto.getNome(),
                        produto.getValor(),
                        produto.getValidade(),
                        Optional.ofNullable(produto.getCategoria()),
                        Optional.ofNullable(produto.getCategoria().getId())
                );

                return dto;
        }

        public List<ProdutoDto> listProdutoToListDto(List<Produto> produtos) {
                List<ProdutoDto> dtos = new ArrayList<ProdutoDto>();
                produtos.forEach(p -> dtos.add(produtoToDto(p)));

                return dtos;

        }

}
