package example.armeria.server.annotated;

import com.linecorp.armeria.common.logging.LogLevel;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.decorator.LoggingDecorator;

/**
 * Examples how to use path patterns provided by Armeria.
 *
 * @see <a href="https://line.github.io/armeria/server-annotated-service.html#mapping-http-service-methods">
 *      Mapping HTTP service methods</a>
 */
@LoggingDecorator(
        requestLogLevel = LogLevel.INFO,            // Log every request sent to this service at INFO level.
        successfulResponseLogLevel = LogLevel.INFO  // Log every response sent from this service at INFO level.
)
public class PathPatternService {
    /**
     * Accesses the parameter with the name of the path variable.
     * NOTE: Configure -parameters javac option to use variable name as the parameter name.
     *       (i.e. '@Param String name' instead of '@Param("name") String name')
     */
    @Get("/path/{name}")
    public String pathVar(@Param String name) {
        return "path: " + name;
    }

    @Get("/path2")
    public String pathVar2(@Param("name") String name) { // @Param()可以获得请求中的参数
        return "path2: " + name;
    }

    /**
     * Accesses the parameter with the name of the capturing group.
     */
    @Get("regex:^/regex/(?<abc>.*)$") // ?<name>可以给正则式的匹配组命名
    public String regex(@Param String abc) {
        return "regex: " + abc;
    }

    @Get("regex:^/regex2/(?<abc>.*)$")
    public String regex2(@Param String abc, @Param("name") String name) { // 同样可以获得请求参数,不影响path的匹配
        return "regex: " + abc + " " + name;
    }

    /**
     * Access the parameter with the index number.
     */
    @Get("glob:/glob/*/**")
    public String glob(@Param("0") String name, @Param("1") String title) { // 0代表第一个wildcard,1代表第二个wildcard
        return "glob: " + name + " " + title ;
    }
}
