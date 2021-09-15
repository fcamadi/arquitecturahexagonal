package org.francd.pricer.integration;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;


public class PriceControllerTest {

    @Test
    public void testPriceList() {
        get("/prices")
                .then()
                .statusCode(200)
                .assertThat().body("size()",is(6));
    }

    //Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 1
    @Test
    public void test01__2020_06_14_1000_brandId_1_productId_35455() {
        get("/prices/2020-06-14-10.00.00/1/35455")
                .then()
                .statusCode(200)
                .assertThat().body("priceList",is(1));
    }

    //Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // There are two prices: the one with priceList 2 has the highest priority
    @Test
    public void test02__2020_06_14_1600_brandId_1_productId_35455() {
        get("/prices/2020-06-14-16.00.00/1/35455")
                .then()
                .statusCode(200)
                .assertThat().body("priceList",is(2));
    }

    //Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 1
    @Test
    public void test03__2020_06_14_2100_brandId_1_productId_35455() {
        get("/prices/2020-06-14-21.00.00/1/35455")
                .then()
                .statusCode(200)
                .assertThat().body("priceList",is(1));
    }

    //Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
    // expected priceList 3
    @Test
    public void test04__2020_06_15_1000_brandId_1_productId_35455() {
        get("/prices/2020-06-15-10.00.00/1/35455")
                .then()
                .statusCode(200)
                .assertThat().body("priceList",is(3));
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
    // There are two prices: the one with priceList 4 has the highest priority
    @Test
    public void test05__2020_06_16_2100_brandId_1_productId_35455() {
        get("/prices/2020-06-16-21.00.00/1/35455")
                .then()
                .statusCode(200)
                .assertThat().body("priceList",is(4));
    }

}
