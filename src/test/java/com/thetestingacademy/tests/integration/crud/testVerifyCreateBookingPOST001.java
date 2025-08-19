package com.thetestingacademy.tests.integration.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstant;
import com.thetestingacademy.pojos.BookingResponse;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testVerifyCreateBookingPOST001 extends BaseTest {

    @Test
    public void testVerifyCreateBookingPOST001() {
        requestSpecification.basePath(APIConstant.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //RestAssured Assertion
        validatableResponse.body("booking.firstname", Matchers.equalTo("Suraj"));
        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Suraj");


        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Suraj");
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank().isNotEmpty();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo("Suraj");

        //TestNG Assertions
        assertActions.verifyStatusCode(response, 200);
        assertActions.verifyResponseBody(bookingResponse.getBooking().getFirstname(), "Suraj", "Verify the first name");


    }
}
