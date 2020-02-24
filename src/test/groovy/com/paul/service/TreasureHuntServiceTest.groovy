package com.paul.service


import io.micronaut.test.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TreasureHuntServiceTest extends Specification {

    @Inject
    @Shared
    TreasureHuntService treasureHuntService

    def "testFindTreasure11"() {
        when:
        String path = treasureHuntService.ffindTreasure(11)

        then:
        path == "1 1\n" +
                "5 5\n" +
                "1 5\n" +
                "2 5\n" +
                "3 1\n" +
                "5 4\n" +
                "1 3\n" +
                "3 2\n" +
                "4 5\n" +
                "3 5\n" +
                "2 3\n" +
                "4 3\n" +
                "5 1\n" +
                "2 1\n" +
                "1 4\n" +
                "4 1\n" +
                "3 3\n" +
                "5 2"
    }
}
