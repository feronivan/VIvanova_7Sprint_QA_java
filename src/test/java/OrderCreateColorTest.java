import io.qameta.allure.junit4.DisplayName;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.model.Order;
import ru.practicum.steps.OrderSteps;
import java.util.List;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.notNullValue;


@DisplayName("Тесты на создание заказа с вариантами цвета самоката: POST /api/v1/orders")

@RunWith(Parameterized.class)
public class OrderCreateColorTest {
    private OrderSteps orderSteps;
    private Order order;
    private List<String> color;

    public OrderCreateColorTest(List<String> color){
        this.color = color;
    }

    @Before
    public void setUpOrderColorScooter(){
        RestAssured.filters(new RequestLoggingFilter());

        orderSteps = new OrderSteps();
        order = new Order();
    }

    @Parameterized.Parameters
    public static Object[][] scooterColor() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK GRAY")},
                {List.of()},
        };
    }

    @Test
    @DisplayName("Создание заказа с вариантами цвета самоката")
    public void createOrderWithColorTest() {
        orderSteps
                .createOrder(order)
                .statusCode(201)
                .body("track", notNullValue());
    }
}
