package com.example.backend.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class PicoPlacaService {

    public String verificarPicoPlaca(String placa, LocalDate fecha, LocalTime hora) {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        if (fecha.isBefore(fechaActual) ||
                (fecha.isEqual(fechaActual) && hora.isBefore(horaActual))) {
            return "La fecha y hora ingresadas no pueden ser anteriores a la fecha y hora actual.";
        }

        int ultimoDigito = Character.getNumericValue(placa.charAt(placa.length() - 1));
        List<Integer> lunes = List.of(1, 2);
        List<Integer> martes = List.of(3, 4);
        List<Integer> miercoles = List.of(5, 6);
        List<Integer> jueves = List.of(7, 8);
        List<Integer> viernes = List.of(9, 0);

        DayOfWeek diaSemana = fecha.getDayOfWeek();

        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return "Placa: " + placa + ", Puede circular. No aplica pico y placa los fines de semana.";
        }

        boolean tieneRestriccion = switch (diaSemana) {
            case MONDAY -> lunes.contains(ultimoDigito);
            case TUESDAY -> martes.contains(ultimoDigito);
            case WEDNESDAY -> miercoles.contains(ultimoDigito);
            case THURSDAY -> jueves.contains(ultimoDigito);
            case FRIDAY -> viernes.contains(ultimoDigito);
            default -> false;
        };

        if (tieneRestriccion) {
            if ((hora.isAfter(LocalTime.of(6, 0)) && hora.isBefore(LocalTime.of(9, 30))) ||
                    (hora.isAfter(LocalTime.of(16, 0)) && hora.isBefore(LocalTime.of(21, 0)))) {
                return "Placa: " + placa + ", no puede circular.";
            }
        }

        return "Placa: " + placa + ", puede circular.";
    }
}



