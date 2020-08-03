package nl.topicus.overheid.kamel

import nl.topicus.overheid.kamel.choice.`when`
import nl.topicus.overheid.kamel.choice.choice
import nl.topicus.overheid.kamel.choice.otherwise
import nl.topicus.overheid.kamel.route.from
import org.apache.camel.builder.endpoint.EndpointRouteBuilder

class StandardRouteBuilder : EndpointRouteBuilder() {
    override fun configure() {
        from(direct("abc"))
            .choice()
            .`when`(exchangeProperty("someprop").isNull)
            .log("someprop was null")
            .otherwise()
            .log("someprop wasn't null")
            .log("after choice")
    }
}

class TypeSafeRouteBuilder : EndpointRouteBuilder() {
    override fun configure() {
        from(direct("abc")) {
            choice {
                `when`(exchangeProperty("someprop").isNull) {
                    log("someprop was null")
                } otherwise {
                    log("someprop wasn't null")
                }
            }
            log("after choice")
        }
    }

}