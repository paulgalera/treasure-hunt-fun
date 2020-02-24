package com.paul.service

import com.paul.domain.TreasureHunter

import javax.inject.Inject
import javax.inject.Singleton
import java.util.stream.Collectors

@Singleton
final class TreasureHuntService {

    @Inject
    TreasureHunter treasureHunter

    def mapOutput = { "${Math.floor(it / 10).toInteger()} ${it % 10}" }

    def ffindTreasure(Integer startingPoint) {
        def treasurePath = treasureHunter.fgoHuntingForTreasure(startingPoint)
        treasurePath != null ?
                treasurePath
                        .stream()
                        .map(mapOutput)
                        .collect(Collectors.joining("\n"))
                : "NO TREASURE"

    }
}
