package com.spring.produto.controllers;


import com.spring.produto.dtos.CategoriaDto;
import com.spring.produto.exceptions.CustomResponseMessage;
import com.spring.produto.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid CategoriaDto dto, Errors validations) {

        if (validations.hasErrors()) {
            var httpStatus = HttpStatus.BAD_REQUEST;

            return ResponseEntity.status(httpStatus)
                    .body(getCustomResponseMessage(validations, httpStatus.toString()));
        }

        CategoriaDto returnedDto = service.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnedDto);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<CategoriaDto> dtos = service.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable(value = "id") Long id,
            @RequestBody @Valid CategoriaDto dto,
            Errors validations) throws Exception {

        if (validations.hasErrors()) {
            var statusHttp = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(statusHttp)
                    .body(getCustomResponseMessage(validations, statusHttp.toString()));
        }

        var savedDto = service.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(savedDto);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

        service.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                new CustomResponseMessage(
                        HttpStatus.OK.toString(),
                        new Date(),
                        "Categoria removida com sucesso",
                        null
                )
        );
    }

    /* ---------------------------------------- */

    private CustomResponseMessage getCustomResponseMessage(Errors validations, String status) {

        String[] erros = validations.getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .toArray(String[]::new);

        return new CustomResponseMessage(
                status,
                new Date(),
                "",
                erros
        );
    }





}
