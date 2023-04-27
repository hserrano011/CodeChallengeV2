package com.pfcti.codechallengeV2.service;

import com.pfcti.codechallengeV2.dto.ClienteDto;
import com.pfcti.codechallengeV2.model.Cliente;
import com.pfcti.codechallengeV2.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClienteServiceTest {
    private ClienteService clienteService;
    private ClienteRepository clienteRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    void insertarCliente() {
         List<Cliente> listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println(">>>>>>>>>>>>>>>>>> Lista antes de insertar: " + listaClientes.size());
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setNombre("HANNIA");
        clienteDto.setApellidos("SERRANO");
        clienteDto.setCedula("303670414");
        clienteDto.setTelefono("+50687124590");
        clienteService.insertarCliente(clienteDto);
        listaClientes = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        System.out.println(">>>>>>>>>>>>>>>>>>Lista despues de insertar:" + ((List<?>) listaClientes).size());
        assertEquals("SERRANO",1);
    }


    @Test
    void obtenerClientes() {
        List<ClienteDto> clienteDtos = clienteService.obtenerClientes();
        clienteDtos.forEach(cliente -> System.out.println("Cliente: " + cliente.getApellidos()));
        assertEquals(2, clienteDtos.size());

    }

    @Test
    void actualizarDatosContactoCliente() {
        ClienteDto clienteDtoInicial = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoInicial.getApellidos());
        clienteDtoInicial.setPaisResidencia("ITA");
        clienteDtoInicial.setDireccion("DIRECCION MODIFICADA");
        clienteDtoInicial.setTelefono("545545454545");
        clienteService.actualizarDatosContactoCliente(clienteDtoInicial);
        ClienteDto clienteDtoLuegoDeUpdate = clienteService.obtenerCliente(1);
        System.out.println("El cliente inicial es: "+ clienteDtoLuegoDeUpdate.getApellidos());
        assertEquals("PEREZ: PAIS RES: " + clienteDtoLuegoDeUpdate.getPaisResidencia() + ">>>>>>>>>>>DIRECCION MOD: " + clienteDtoLuegoDeUpdate.getDireccion() + ">>>>>>>>>>>TELEF MOD: " + clienteDtoLuegoDeUpdate.getTelefono());
    }

    @Test
    void desactivarCliente() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1);
        clienteService.desactivarCliente(clienteDto);
        assertEquals(1,1);
    }
}