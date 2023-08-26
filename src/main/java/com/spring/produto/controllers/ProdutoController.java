package com.spring.produto.controllers;


import com.spring.produto.dtos.ProdutoDto;
import static com.spring.produto.exceptions.CustomResponseEntityExceptionHandle.getCustomResponseMessage;
import com.spring.produto.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid ProdutoDto dto, Errors validation) throws Exception {

        if (validation.hasErrors()) {
            var httpStatus = HttpStatus.BAD_REQUEST;
            return ResponseEntity.status(httpStatus)
                    .body(getCustomResponseMessage(validation, httpStatus));
        }

        ProdutoDto savedDto = service.save(dto);

        return ResponseEntity.status(HttpStatus.OK).body(savedDto);
    }




}
