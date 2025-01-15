package com.example.backend.controller;

import com.example.backend.dto.PicoPlacaRequest;
import com.example.backend.service.PicoPlacaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/picoplaca")
public class PicoPlacaController {

    @Autowired
    private PicoPlacaService picoPlacaService;

    @PostMapping("/verificar")
    public String verificarPicoPlaca(@RequestBody PicoPlacaRequest request) {
        System.out.println("Placa: " + request.getPlaca());
        System.out.println("Timestamp: " + request.getTimestamp());

        // Convertir timestamp a LocalDate y LocalTime
        long timestamp = request.getTimestamp();
        LocalDate fechaConsulta = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalTime horaConsulta = Instant.ofEpochMilli(timestamp)
                .atZone(ZoneId.systemDefault())
                .toLocalTime();


        System.out.println("Fecha: " + fechaConsulta);
        System.out.println("Hora: " + horaConsulta);

        return picoPlacaService.verificarPicoPlaca(request.getPlaca(), fechaConsulta, horaConsulta);
    }
}

