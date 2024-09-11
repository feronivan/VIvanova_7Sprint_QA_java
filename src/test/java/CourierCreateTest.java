import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.practicum.model.Courier;
import ru.practicum.steps.CourierSteps;

import static org.hamcrest.Matchers.is;

@DisplayName("Создание курьера: POST /api/v1/courier")
public class CourierCreateTest extends AbstractTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUpCourierTest() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }


    @Test
    @DisplayName("Успешное создание курьера со всеми заполненными полями")
    @Description("ОР: код ответа - 201, возвращает - ok: true")
    public void shouldReturnOkTrue() {
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание курьера с существующими данными")
    @Description("ОР: код ответа - 409, возвращает - Этот логин уже используется. Попробуйте другой.")
    public void createCourierDouble() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("ОР: код ответа - 400, возвращает - Недостаточно данных для создания учетной записи")
    public void createCourierWithoutLogin() {
        courier.setLogin("");
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("ОР: код ответа - 400, возвращает - Недостаточно данных для создания учетной записи")
    public void createCourierWithoutPassword() {
        courier.setPassword("");
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без логина и пароля")
    @Description("ОР: код ответа - 400, возвращает - Недостаточно данных для создания учетной записи")
    public void createCourierWithoutLoginAndPassword() {
        courier.setLogin("");
        courier.setPassword("");
        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }


    @After
    @DisplayName("Удаление существуюшего курьера")
    public void deleteCourier() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        courier.setId(id);
        if (id != null) {
            courierSteps.delete(courier);
        }
    }
}