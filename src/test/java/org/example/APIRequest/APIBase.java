package org.example.APIRequest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

import static org.example.utils.Constants.BASE_URI;

public class APIBase {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI=BASE_URI;
    }
}
