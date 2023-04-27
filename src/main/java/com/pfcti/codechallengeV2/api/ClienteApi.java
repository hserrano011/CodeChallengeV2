package com.pfcti.codechallengeV2.api;

import com.pfcti.codechallengeV2.dto.ClienteDto;
import com.pfcti.codechallengeV2.service.ClienteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v2/api/cliente")
public class ClienteApi {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDto buscarCliente(@PathVariable int id){
        log.info("Busqueda de cliente : {}", id);
        return clienteService.obtenerCliente(id); }

    @PostMapping
    public void guardarCliente(@Valid @RequestBody ClienteDto clienteDto){
        log.info("cliente de cliente : {}", clienteDto);
        clienteService.insertarCliente(clienteDto);
    }

    @PutMapping
    public void actualizarDatosContactoCliente(@RequestBody ClienteDto clienteDto){
        log.info("cliente de cliente : {}", clienteDto);
        clienteService.actualizarDatosContactoCliente(clienteDto);
    }

    @GetMapping(value = "/all")
    public List<ClienteDto> buscarTodosClientes(){
        return clienteService.obtenerClientes(); }
/*


    @PutMapping
    public void desactivarCliente(@RequestBody ClienteDto clienteDto){
        log.info("cliente de cliente : {}", clienteDto);
        clienteService.desactivarCliente(clienteDto); }
*/



}
