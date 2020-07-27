package nl.topicus.overheid.kamel

import nl.topicus.overheid.kamel.choice.choice
import nl.topicus.overheid.kamel.choice.given
import nl.topicus.overheid.kamel.route.from
import nl.topicus.overheid.kamel.choice.otherwise
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.builder.endpoint.EndpointRouteBuilder
import org.junit.jupiter.api.Test

class BasicTest {

    @Test
    fun basicTest() {
        println("dummy test")
    }
}

class ClassicRouteBuilder : RouteBuilder() {
    override fun configure() {
        from("direct:xyz") {
            choice {
                given(exchangeProperty("someprop").isNull) {
                    log("someprop was null")
                } otherwise {
                    log("someprop wasn't null")
                }
            }
        }
    }

}

class TypeSafeRouteBuilder : EndpointRouteBuilder() {
    override fun configure() {
        from(direct("abc")) {
            choice {
                given(exchangeProperty("someprop").isNull) {
                    log("someprop was null")
                } otherwise {
                    log("someprop wasn't null")
                }
            }
        }
    }

}