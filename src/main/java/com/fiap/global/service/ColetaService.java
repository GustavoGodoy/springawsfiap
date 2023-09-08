package com.fiap.global.service;

import com.fiap.global.model.Coleta;
import com.fiap.global.repository.ColetaRepository;
import com.fiap.global.repository.UsuarioRepository;
import com.fiap.global.request.ColetaDTO;
import com.fiap.global.response.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class ColetaService {

    @Autowired
    ColetaRepository coletaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<ResponseDTO> findAllColetas(){
        var coletas = coletaRepository.findAll();

        var coletasResponse = new ArrayList<ResponseDTO>();

        if(!coletas.isEmpty()) {
            coletas.forEach(coleta -> {
                coletasResponse.add(new ResponseDTO(coleta));
            });
        }

        return coletasResponse;
    }

    public Coleta salvar(ColetaDTO coletaDTO) throws Exception {

        System.out.println(coletaDTO.getUsuario());

        var user = usuarioRepository.findByLogin(coletaDTO.getUsuario());

        if (user.isEmpty()) {
            System.err.println("Usuario não encontrado" + coletaDTO.getUsuario());
            throw new Exception("User not found" + coletaDTO.getUsuario());
        }

       return coletaRepository.save(new Coleta(user.get(), coletaDTO.getAlimento(), coletaDTO.getData()));
    }
}
