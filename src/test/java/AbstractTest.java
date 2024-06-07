import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.Before;

import static ru.practicum.config.RestGonfig.HOST;

public abstract class AbstractTest{
    @Before
    public void setUpRestAssured() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(HOST)
                .build();
    }
}
