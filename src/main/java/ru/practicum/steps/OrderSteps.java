package ru.practicum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import ru.practicum.model.Order;

import static io.restassured.RestAssured.given;
import static ru.practicum.config.RestConfig.HOST;
import static ru.practicum.constant.EndPoints.*;

public class OrderSteps {

    @Step("Создание заказа")
    public ValidatableResponse createOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(order)
                .when()
                .post(ORDER)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse listOrder(Order order) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .body(order)
                .when()
                .get(ORDER)
                .then();
    }

    @Step("Отмена заказа")
    public static Response cancelOrder(String trackId) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(HOST)
                .queryParams("track",trackId)
                .when()
                .put(ORDERCANCEL);
    }

}