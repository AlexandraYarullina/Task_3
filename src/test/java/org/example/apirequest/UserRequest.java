package org.example.apirequest;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.pojo.DataUser;

import static io.restassured.RestAssured.given;
import static org.example.utils.Constants.*;

public class UserRequest extends APIBase{
    private DataUser dataUser;

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    @Step("Создание пользователя")
    public Response createUser() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(dataUser)
                        .when()
                        .post(CREATE_ENDPOINT_USER);
        return response;
    }

    @Step("Авторизация пользователя")
    public Response loginUser() {
                Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(dataUser)
                        .when()
                        .post(LOGIN_ENDPOINT_USER);
        return response;
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        Response response =
                given()
                        .header("Authorization", accessToken)
                        .when()
                        .delete(CHANGES_ENDPOINT_USER);
        return response;
    }
}
