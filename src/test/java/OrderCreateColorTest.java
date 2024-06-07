import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.model.Order;
import ru.practicum.steps.OrderSteps;

import static org.hamcrest.Matchers.notNullValue;
import static ru.practicum.constant.OrderTestData.*;
@DisplayName("Тесты на создание заказа с вариантами цвета самоката: POST /api/v1/orders")

@RunWith(Parameterized.class)
public class OrderCreateTest extends AbstractTest{
    private String[] color;

    public СreateOrderWithColor(String[] color) {
        this.color=color;
    }

    @Parameterized.Parameters(name = "Test {index} Цвет самоката: {0}")
    public static Object[][]getColor(){
        return new Object[][]{
                {"Черный", new String[]{"BLACK"}},
                {"Серый", new String[]{"GREY"}},
                {"Черный, Серый", new String[]{"BLACK","GREY"}},
                {"Пусто", new String[]{""}}
        };
    }

    public Order order = new Order(FIRST_NAME,
            LAST_NAME,
            ADDRESS,
            METRO_STATION,
            PHONE,
            RENT_TIME,
            DELIVERY_DATE,
            COMMENT,
            color);
}

    @Test
    public void createOrder() {
    Response response = OrderSteps.createOrder(order);

    response.then()
            .assertThat()
            .statusCode(201)
            .body("track", notNullValue());
}

