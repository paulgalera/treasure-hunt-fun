package com.paul.service

import com.paul.domain.TreasureHunter

import groovy.util.logging.Slf4j

import javax.inject.Inject
import javax.inject.Singleton
import java.util.function.Function
import java.util.stream.Collectors

@Singleton
@Slf4j
final class TreasureHuntService {

    @Inject
    TreasureHunter treasureHunter

    def mapOutput = {"${Math.floor(it / 10).toInteger()} ${it % 10}"}

    def ffindTreasure (Integer startingPoint) {
        def treasurePath = treasureHunter.fgoHuntingForTreasure(startingPoint)
        treasurePath != null ?
                treasurePath
                        .stream()
                        .map(mapOutput)
                        .collect(Collectors.joining("\n"))
                : "NO TREASURE"

    }

    final String findTreasure(int startingPoint) {
        List<Integer> treasurePath = treasureHunter.fgoHuntingForTreasure(startingPoint)
        if (treasurePath != null) {
            treasurePath
                    .stream()
                    .map(new Function<Integer, String>() {
                        @Override
                        String apply(Integer a) {
                            "${Math.floor(a / 10).toInteger()} ${a % 10}"
                        }
                    })
                    .collect(Collectors.joining("\n"))
        } else {
            "NO TREASURE"
        }
    }
}
