package example.springframework.boot.webflux;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.linecorp.armeria.server.annotation.ExceptionHandler;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;

/**
 * Note that this is not a Spring WebFlux-based component but an annotated HTTP service that leverages
 * Armeria's built-in annotations.
 *
 * @see <a href="https://line.github.io/armeria/server-annotated-service.html">Annotated HTTP Service</a>
 */
@Component
@Validated
@ExceptionHandler(ValidationExceptionHandler.class)
public class HelloAnnotatedService {

    /**
     * An example in order to show how to use validation framework in an annotated HTTP service.
     */
    @Get("/hello/{name}")
    public String hello(
            @Size(min = 3, max = 10, message = "name should have between 3 and 10 characters")
            @Param String name) {
        return String.format("Hello, %s! This message is from Armeria annotated service!", name);
    }
}
