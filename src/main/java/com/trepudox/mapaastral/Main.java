package com.trepudox.mapaastral;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDateTime marcoBirthDate = LocalDateTime.of(2002, 7, 1, 5, 0);
        printBirthInfo(marcoBirthDate);

        System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(marcoBirthDate));
    }

    private static void printBirthInfo(LocalDateTime birthDate) {
        LocalDateTime now = LocalDateTime.now();
        Period birthPeriod = Period.between(birthDate.toLocalDate(), now.toLocalDate());

        System.out.printf("%d anos, %d meses e %d dias%n", birthPeriod.getYears(), birthPeriod.getMonths(), birthPeriod.getDays());
        System.out.printf("%d é bissexto? %s%n", birthDate.getYear(), Year.isLeap(birthDate.getYear()));

        System.out.printf("Signo: %s%n", Signo.getSigno(birthDate));
//        System.out.printf("Signo: %s%n", Signo.getSigno(birthDate.toLocalDate()));

        System.out.printf("Ascendente: %s%n", Ascendente.getAscendente(birthDate));

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        System.out.println(zoneId);

        System.out.println(zoneId.getRules().getOffset(LocalDateTime.now()));

        ZoneOffset zoneOffset = ZoneOffset.of("-03:00");
        System.out.println(zoneOffset);
    }
}