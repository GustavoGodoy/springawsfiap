package com.fiap.global.controller;

import com.fiap.global.repository.ColetaRepository;
import com.fiap.global.request.ColetaDTO;
import com.fiap.global.service.ColetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ColetaController {

    @Autowired
    ColetaService coletaService;

    @GetMapping
    public ResponseEntity getAllColetas(){
        return ResponseEntity.ok(coletaService.findAllColetas());
    }

    @PostMapping
    public ResponseEntity postColeta(@RequestBody ColetaDTO coletaDTO) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(coletaService.salvar(coletaDTO));
    }

}
