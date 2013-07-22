package org.wbuck

import spock.lang.Specification
import spock.lang.Shared
import grails.test.mixin.TestFor
import wslite.rest.*

@TestFor(RegistryCheckController)
class RegistryCheckControllerSpec extends Specification {

    @Shared def restClient = new RESTClient(TargetAPI.API_BASE)

    void setup() {
        restClient.httpClient.sslTrustAllCerts = true
    }

    void "authenticate then grab order then grab list"() {
        when: "we authenticate"
        def authResponse = restClient.post(
            path: "/guests/v3/auth?key=${TargetAPI.consumerKey}&userid=${TargetAPI.userId}", 
            accept: ContentType.JSON) 
        {
            json logonId: "gr8conf@mailinator.com", logonPassword: "GR8confRocks"
        }

        assert 200 == authResponse.statusCode
        assert "gr8conf@mailinator.com" == authResponse.json.logonId
        assert authResponse.json.accessToken != null
        

        and: "we grab orders"
        def ordersResponse = restClient.get(
            path: "/orders/v1?access_token=${authResponse.json.accessToken}&key=${TargetAPI.consumerKey}&userid=${TargetAPI.userId}", 
            accept: ContentType.JSON)        

        then:        
        assert 200 == ordersResponse.statusCode        
        
    }
}
