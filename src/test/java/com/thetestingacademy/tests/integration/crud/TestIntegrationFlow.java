package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstant;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class TestIntegrationFlow extends BaseTest {
    @Test(groups = {"integration", "P0"}, priority = 1)
    @Owner("Suraj")
    @Description("TC#INT1 - Step 1: Create the Booking Id")
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
    }

    @Test(groups = "integration", priority = 2)
    @Owner("Suraj")
    @Description("TC#INT2 - Step 2: Verify the Booking Id")
    public void testVerifyBookingId(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        //GET
        String basePathGET = APIConstant.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();

        validatableResponse = response.then().log().all();
        // validatable response
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Suraj");

    }


    @Test(groups = "integration", priority = 3)
    @Owner("Suraj")
    @Description("TC#INT3 - Step 3: Update the Booking Id")
    public void testUpdateBookingById(ITestContext iTestContext) {
        String token = getToken();
        iTestContext.setAttribute("token", token);
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");


        String basePathPUTPATCH = APIConstant.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);
        response = RestAssured.given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadString()).put();

        validatableResponse = response.then().log().all();
        // validatable Assertion
        validatableResponse.statusCode(200);
        Booking booking = payloadManager.getResponseFromJSON((response.asString()));

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("nidhi");
        assertThat(booking.getLastname()).isEqualTo("ghorpade");
    }


    @Test(groups = "integration", priority = 4)
    @Owner("Suraj")
    @Description("TC#INT4 - Step 4: Delete the Booking Id")

    public void testDeleteBookingById(ITestContext iTestContext) {
        String token = (String) iTestContext.getAttribute("token");
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstant.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);
    }
}