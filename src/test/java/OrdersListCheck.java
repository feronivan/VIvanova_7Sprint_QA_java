import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.practicum.constant.EndPoints.ORDER;

public class OrdersListCheck extends AbstractTest {
    @Test
    @DisplayName("Проверка получения списка заказов")
    public void ordersList() {
        given()
                .get(ORDER)
                .then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
