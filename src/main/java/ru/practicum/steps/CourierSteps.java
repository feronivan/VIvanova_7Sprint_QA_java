package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.practicum.model.Courier;

import static io.restassured.RestAssured.given;
import static ru.practicum.constant.EndPoints.*;

public class CourierSteps {

    @Step("Создание курьера")
    public ValidatableResponse createCourier(Courier courier) {

        return given()
                .body(courier)
                .when()
                .post(COURIERCREATE)
                .then();
    }

    @Step("Авторизация курьера")
    public ValidatableResponse loginCourier(Courier courier) {

        return given()
                .body(courier)
                .when()
                .post(COURIERLOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse delete(Courier courier) {
        return given()
                .pathParam("id", courier.getId())
                .when()
                .delete(COURIERDELETE)
                .then();
    }

}
