package com.pfcti.codechallengeV2.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

    @Data
    public class ClienteDto {
        private int id;
        @NotNull
        @Size(max = 10, message = "El nombre debe contener max 10 caracteres")
        private String nombre;
        private String apellidos;

        private String cedula;
        private String telefono;
        private  String paisNacimiento;
        private String paisResidencia;
        private String direccion;
        private Boolean estado;


}
