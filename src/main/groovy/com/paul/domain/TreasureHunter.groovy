package com.paul.domain

import groovy.util.logging.Slf4j

import javax.inject.Singleton

@Slf4j
@Singleton
final class TreasureHunter {

    def MAX_STEPS_TO_TREASURE = 28

    def ftreasureMap = [[34, 21, 32, 41, 25],
                        [14, 42, 43, 14, 31],
                        [54, 45, 52, 42, 23],
                        [33, 15, 51, 31, 35],
                        [21, 52, 33, 13, 23]]
    def initialSpots = [55, 15]

    def row = { loc -> Math.floor(loc / 10 - 1).toInteger() }
    def col = { loc -> (loc % 10) - 1 }
    def getClue = { r, c -> ftreasureMap[r][c] }

    def fpokeLocation = { location -> getClue(row(location), col(location)) }.memoize()
    def fisTreasureFound = {clue, location -> clue == location}

    def fgoCheckInitialSpots = {startPoint ->
        def treasureFound = false
        def initialSpots = [startPoint] + initialSpots
        def initialPath = []
        for (spot in initialSpots) {
            def clue = fpokeLocation(spot)
            initialPath.add(spot)
            if (fisTreasureFound(clue, spot)) {
                treasureFound = true
                break
            }
        }
        [treasureFound, initialPath]
    }


    // TODO: is there any sense to trampoline it
    def fdigForTreasure = {location, ArrayList initialPath ->
        def treasurePath = new ArrayList(initialPath)
        def clueFound = fpokeLocation(location)
        treasurePath.add(location)
        if (fisTreasureFound(clueFound, location))
            return treasurePath
        if (treasurePath.size() >= MAX_STEPS_TO_TREASURE)
            return null
        return fdigForTreasure(clueFound, treasurePath)
    }

    def fgoHuntingForTreasure = {location ->
        def wholeTreasurePath = []
        def found, initialPath
        (found, initialPath) = fgoCheckInitialSpots(location)
        wholeTreasurePath.addAll(initialPath)
        if (!found){
            wholeTreasurePath = fdigForTreasure(fpokeLocation(wholeTreasurePath[-1]), wholeTreasurePath)
        }
        wholeTreasurePath
    }
}
