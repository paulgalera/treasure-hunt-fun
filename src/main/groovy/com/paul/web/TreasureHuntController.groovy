package com.paul.web

import com.paul.service.TreasureHuntService
import groovy.transform.CompileStatic
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.retry.annotation.CircuitBreaker

import javax.inject.Inject

@CompileStatic
@Controller("/")
class TreasureHuntController {

    TreasureHuntService treasureHuntService

    def allowedInput = [11, 12, 13, 14, 15,
                        21, 22, 23, 24, 25,
                        31, 32, 33, 34, 35,
                        41, 42, 43, 44, 45,
                        51, 52, 53, 54, 55]

    def isInputOk(Integer x) { allowedInput.any({ it == x }) }


    @Inject
    TreasureHuntController(TreasureHuntService treasureHuntService) { this.treasureHuntService = treasureHuntService }

    @Get("{startPoint}")
    @Produces(MediaType.TEXT_PLAIN)
    @CircuitBreaker(attempts = "2", reset = "30s")
    String index(Integer startPoint) {
        isInputOk(startPoint) ?
                treasureHuntService.ffindTreasure(startPoint) : "Holy smoke...$startPoint is a wrong index for a 5x5 TreasureMap!"
    }
}