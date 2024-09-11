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

import static org.hamcrest.Matchers.*;
import static ru.practicum.constant.CourierTestData.*;


@DisplayName("Логин курьера в системе: POST  /api/v1/courier/login")
public class CourierLoginTest extends AbstractTest {
    private final CourierSteps courierSteps = new CourierSteps();
    private Courier courier;

    @Before
    public void setUpCourierLogin() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
    }

    @Test
    @DisplayName("Успешная авторизация")
    @Description("ОР: ответ 200, возвращает id")
    public void loginCourierLoginCheck() {
        courierSteps
                .createCourier(courier);
        courierSteps
                .loginCourier(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Авторизация без логина")
    @Description("ОР: ответ 400, возвращает ошибку - Недостаточно данных для входа")
    public void loginCourierWithoutLoginCheck() {
        courier.setLogin("");
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация без пароля")
    @Description("ОР: ответ 400, возвращает ошибку - Недостаточно данных для входа")
    public void loginCourierWithoutPasswordCheck() {
        courier.setPassword("");
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация оба поля пустые")
    @Description("ОР: ответ 400, возвращает ошибку - Недостаточно данных для входа")
    public void loginCourierWithoutLoginAndPasswordCheck() {
        courier.setLogin("");
        courier.setPassword("");
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация c несуществующим логином")
    @Description("ОР: ответ 404, возвращает ошибку - Учетная запись не найдена")
    public void loginCourierNonExistentLoginCheck() {
        courier.setLogin(NON_EXISTENT_LOGIN);
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация c несуществующим паролем")
    @Description("ОР: ответ 404, возвращает ошибку - Учетная запись не найдена")
    public void loginCourierNonExistentPasswordCheck() {
        courier.setPassword(NON_EXISTENT_PASSWORD);
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Авторизация c несуществующими логином и паролем")
    @Description("ОР: ответ 404, возвращает ошибку - Учетная запись не найдена")
    public void loginCourierNonExistentLoginAndPasswordCheck() {
        courier.setLogin(NON_EXISTENT_LOGIN);
        courier.setPassword(NON_EXISTENT_PASSWORD);
        courierSteps
                .loginCourier(courier)
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier() {
        Integer id = courierSteps.loginCourier(courier)
                .extract().body().path("id");
        if (id != null) {
            courier.setId(id);
            courierSteps.delete(courier);
        }
    }
}
