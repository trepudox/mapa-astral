package com.trepudox.mapaastral;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        LocalDateTime marcoBirthDate = LocalDateTime.of(2002, 7, 1, 5, 0);
        String marcoHometown = "Sao Paulo"; // precisa estar sem acento
        printBirthInfo(marcoBirthDate, marcoHometown);
    }

    private static void printBirthInfo(LocalDateTime birthDate, String hometown) {
        printMethodInfo("BIRTH INFO");

        printBirthDateInfo(birthDate);
        printTimezoneInfo(hometown);
        printZodiacSignInfo(birthDate);
        printLunarSignInfo(birthDate, hometown);
    }

    private static void printMethodInfo(String methodLabel) {
        String decoration = "#".repeat(10);
        System.out.println(decoration.concat(" ").concat(methodLabel).concat(" ").concat(decoration));
    }

    private static void printBirthDateInfo(LocalDateTime birthDate) {
        LocalDateTime now = LocalDateTime.now();
        Period birthPeriod = Period.between(birthDate.toLocalDate(), now.toLocalDate());

        System.out.printf("%d anos, %d meses e %d dias%n", birthPeriod.getYears(), birthPeriod.getMonths(), birthPeriod.getDays());
        System.out.println(birthDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.printf("%d foi bissexto? %s%n", birthDate.getYear(), Year.isLeap(birthDate.getYear()));
    }

    private static void printTimezoneInfo(String hometown) {
        printMethodInfo("TIMEZONE INFO");
        getZoneIdByCityName(hometown).ifPresentOrElse(
                zoneId -> System.out.println("ZoneId do nascimento: ".concat(zoneId.toString())),
                () -> System.out.println("Não foi encontrada uma timezone específica para a cidade informada")
        );
    }

    private static Optional<ZoneId> getZoneIdByCityName(String cityName) {
        String treatedCityName = cityName.trim().replace(' ', '_');

        for(String zone : ZoneId.getAvailableZoneIds()) {
            if(zone.contains(treatedCityName))
                return Optional.of(ZoneId.of(zone));
        }

        return Optional.empty();
    }

    private static void printZodiacSignInfo(LocalDateTime birthDate) {
        printMethodInfo("ZODIAC SIGN INFO");
        System.out.printf("Signo: %s%n", Signo.getSigno(birthDate));
        System.out.printf("Ascendente: %s%n", Ascendente.getAscendente(birthDate));
    }

    private static void printLunarSignInfo(LocalDateTime birthDate, String hometown) {
        printMethodInfo("LUNAR SIGN INFO");

        String lunarSign = "Dinossauro";

        Optional<ZoneId> zoneOptional = getZoneIdByCityName(hometown);
        if(zoneOptional.isPresent()) {
            ZoneId zoneId = zoneOptional.get();
            if(birthDate.toLocalTime().isAfter(LocalTime.NOON)) {
                if(zoneId.equals(ZoneId.of("America/Recife")))
                    lunarSign = "Casimiro";
            } else {
                if(zoneId.equals(ZoneId.of("America/Cuiaba")))
                    lunarSign = "Odin";
            }

            if(zoneId.equals(ZoneId.of("America/Sao_Paulo")))
                lunarSign = "Gandalf";
        }

        System.out.println(lunarSign);
    }
}