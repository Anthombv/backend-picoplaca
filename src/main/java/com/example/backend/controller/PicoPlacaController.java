package com.example.backend.controller;

import com.example.backend.dto.PicoPlacaRequest;
import com.example.backend.service.PicoPlacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api/picoplaca")
public class PicoPlacaController {

    @Autowired
    private PicoPlacaService picoPlacaService;

    @PostMapping("/verificar")
    public String verificarPicoPlaca(@RequestBody PicoPlacaRequest request) {
        System.out.println("Placa: " + request.getPlaca());
        System.out.println("Timestamp: " + request.getTimestamp());

        ZoneId zonaHoraria = ZoneId.of("America/Guayaquil");

        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(request.getTimestamp())
                .atZone(zonaHoraria);

        LocalDate fechaConsulta = zonedDateTime.toLocalDate();
        LocalTime horaConsulta = zonedDateTime.toLocalTime();


        System.out.println("Fecha: " + fechaConsulta);
        System.out.println("Hora: " + horaConsulta);

        return picoPlacaService.verificarPicoPlaca(request.getPlaca(), fechaConsulta, horaConsulta);
    }
}

