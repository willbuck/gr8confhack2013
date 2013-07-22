package org.wbuck

import spock.lang.Specification
import spock.lang.Shared
import wslite.rest.*

class GuestsSpec extends Specification {

    @Shared def restClient = new RESTClient(TargetAPI.API_BASE)

    void setup() {
        restClient.httpClient.sslTrustAllCerts = true
    }

    void "guest authentication and update profile"() {
        when:
        def response = restClient.post(path: "/guests/v3/auth?key=${TargetAPI.consumerKey}&userid=${TargetAPI.userId}", accept: ContentType.JSON) {
            json logonId: "gr8conf@mailinator.com", logonPassword: "GR8confRocks"
        }

        then:
        assert 200 == response.statusCode
        assert "Test" == response.json.lastName
        assert "Gr8Conf" == response.json.firstName
        assert "gr8conf@mailinator.com" == response.json.logonId
        println response.json.accessToken
    }
}
