package com.spring.produto.services;

import com.spring.produto.domain.Categoria;
import com.spring.produto.dtos.CategoriaDto;
import com.spring.produto.exceptions.CategoriaNomeMustBeNotRepeatedException;
import com.spring.produto.exceptions.CategoriaNotFoundException;
import com.spring.produto.repositories.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

    @Mock
    private CategoriaRepository repository;

    @InjectMocks
    private CategoriaService service;

    private Categoria categoria;

    @BeforeEach
    void setup() {
        categoria = new Categoria("Graos");
    }

    /* ---------------------------------------------- */

    @Test
    void save_deve_salvar_uma_categoria() {
        /* given */
        when(repository.save(any(Categoria.class))).thenReturn(categoria);

        /* when */
        CategoriaDto dto = service.save(CategoriaDto.categoriaToDto(categoria));

        /* then */
        assertNotNull(dto);
        assertEquals(categoria.getNome(), dto.nome(), () -> "Nome retornado não combina");
    }

    @Test
    void save_deve_lancar_excecao_se_nome_categoria_repetido() {
        /* given */
        CategoriaDto dto = CategoriaDto.categoriaToDto(categoria);
        when(repository.findByNome(anyString())).thenReturn(Optional.ofNullable(categoria));

        /* when */

        assertThrows(CategoriaNomeMustBeNotRepeatedException.class, () -> service.save(dto));

        /* then */

    }

    @Test
    void findAll_deve_retornar_lista_de_categorias() {
        /* given */
        List<Categoria> categorias = List.of(categoria);
        when(repository.findAll()).thenReturn(categorias);
        /* when */
        List<CategoriaDto> dtos = service.findAll();

        /* then */
        assertNotNull(dtos);
        assertEquals(categorias.size(), dtos.size());
    }

    @Test
    void update_deve_editar_um_usuario_existente() throws Exception {
        /* given */
        categoria.setId(1L);
        when(repository.findById(anyLong())).thenReturn(Optional.of(categoria));
        when(repository.findByNome(anyString())).thenReturn(Optional.of(categoria));
        when(repository.save(any(Categoria.class))).thenReturn(categoria);

        /* when */

        CategoriaDto dto =  service.update(categoria.getId(), CategoriaDto.categoriaToDto(categoria));

        /* then */
        assertNotNull(dto);
        assertEquals(dto.nome(), categoria.getNome());
    }

    @Test
    void update_deve_lancar_execao_se_aluno_nao_encontrado() throws Exception {
        /* given */
        categoria.setId(1L);
        when(repository.findById(anyLong())).thenThrow(new CategoriaNotFoundException(""));

        /* when */
        assertThrows(CategoriaNotFoundException.class, () ->
                        service.update(categoria.getId(), CategoriaDto.categoriaToDto(categoria))
                );

    }



}
