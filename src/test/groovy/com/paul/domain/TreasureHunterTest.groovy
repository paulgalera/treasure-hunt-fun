package com.paul.domain

import io.micronaut.test.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TreasureHunterTest extends Specification {

    @Inject
    @Shared
    TreasureHunter treasureHunter

    def "testDigForTreasure"() {
        when:
        List<Integer> path = treasureHunter.fdigForTreasure(14, new ArrayList<Integer>())

        then:
        path.size() > 0
        path.toString() == "[14, 41, 33, 52]"
    }

    def "testGoCheckInitialSpotsTreasureFound"() {
        when:
        List<Integer> path = treasureHunter.fgoHuntingForTreasure(52)

        then:
        path.size() == 1
        path.toString() == "[52]"
    }

}
