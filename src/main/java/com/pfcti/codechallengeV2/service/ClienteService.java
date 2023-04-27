package com.pfcti.codechallengeV2.service;

import com.pfcti.codechallengeV2.dto.ClienteDto;
import com.pfcti.codechallengeV2.model.Cliente;
import com.pfcti.codechallengeV2.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClienteService {
    private ClienteRepository clienteRepository;

    private ClienteDto fromClienteToClienteDto(Cliente cliente){
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }
    public void insertarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setCedula(clienteDto.getCedula());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setPaisNacimiento(clienteDto.getPaisNacimiento());
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setEstado(clienteDto.getEstado());
        clienteRepository.save(cliente);
    }

    public List<ClienteDto> obtenerClientes() {
        List<ClienteDto> clienteDtos = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();
        clientes.forEach(cliente -> {
            clienteDtos.add(fromClienteToClienteDto(cliente));
        });
        return clienteDtos;
    }

    public ClienteDto obtenerCliente (int clienteId) {
        Cliente cliente =
                clienteRepository.findById(clienteId).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNombre (cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setCedula(cliente.getCedula());
        clienteDto.setTelefono(cliente.getTelefono());
        return clienteDto;
    }

    public void  actualizarDatosContactoCliente (ClienteDto clienteDto) {
        Cliente cliente =
                clienteRepository.findById(clienteDto.getId()).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        cliente.setId((clienteDto.getId()));
        cliente.setPaisResidencia(clienteDto.getPaisResidencia());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        clienteRepository.save(cliente);
    }

    public void desactivarCliente(ClienteDto clienteDto){
        Cliente cliente =
                clienteRepository.findById(clienteDto.getId()).orElseThrow(()->
                {throw new RuntimeException("No existe");});
        cliente.setId((clienteDto.getId()));
        cliente.setEstado(false);
        clienteRepository.save(cliente);
    }

}
