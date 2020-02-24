package com.paul.web

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class TreasureHuntControllerTest extends Specification {
    @Inject
    @Client("/")
    RxHttpClient client

    def "testAllowedIntParam"() {
        when:
        HttpRequest req = HttpRequest.GET("/11")
        String rsp = client.toBlocking().retrieve(req)

        then:
        rsp.toString() == "1 1\n" +
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

    def "testWrongIntParam"() {
        when:
        HttpRequest req = HttpRequest.GET("/18")
        String rsp = client.toBlocking().retrieve(req)

        then:
        rsp == "Holy smoke...18 is a wrong index for a 5x5 TreasureMap!"
    }

    void "testStringParam"() {
        when:
        HttpRequest req = HttpRequest.GET("/crap")
        client.toBlocking().retrieve(req)

        then:
        thrown(HttpClientException)
    }

}
