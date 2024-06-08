import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.model.Order;
import ru.practicum.steps.OrderSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static ru.practicum.constant.EndPoints.ORDER;

public class OrdersListCheck extends AbstractTest {
    private OrderSteps orderSteps;
    private Order order;

    @Before
    public void setUpOrderListCheck() {
        RestAssured.filters(new RequestLoggingFilter());

        orderSteps = new OrderSteps();
        order = new Order();
    }

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void ordersList() {
        orderSteps
                .createOrder(order);
        given()
                .get(ORDER)
                .then()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
