package com.spring.produto.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.produto.domain.Categoria;
import com.spring.produto.dtos.CategoriaDto;
import com.spring.produto.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CategoriaService service;

    private Categoria categoria;

    @BeforeEach
    void setup() {
        categoria = new Categoria("Gelados");
    }

    @Test
    void save_deve_salvar_categoria() throws Exception {
        /* given */
        CategoriaDto dto = CategoriaDto.categoriaToDto(categoria);
        when(service.save(any(CategoriaDto.class))).thenReturn(dto);
        /* when */
                ResultActions response = mvc.perform(MockMvcRequestBuilders
                        .post("/api/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto))
                );

        /* then */

        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value(dto.nome()));
    }

    @Test
    void findAll_deve_retornar_lista_de_categorias() throws Exception {

        /* given */
        List<CategoriaDto> dtos = List.of(CategoriaDto.categoriaToDto(categoria));
        when(service.findAll()).thenReturn(dtos);

        /* when */
        ResultActions response = mvc.perform(MockMvcRequestBuilders
                .get("/api/categorias"));

        /* then */
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(dtos.size()));
    }


}
