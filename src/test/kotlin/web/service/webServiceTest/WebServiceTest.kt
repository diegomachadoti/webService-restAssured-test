package web.service.webServiceTest

import io.restassured.RestAssured
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import org.apache.http.HttpStatus
import org.junit.Assert
import org.junit.jupiter.api.Test
import util.Data
import java.util.*

class WebServiceTest {

	private val requestExchange = WebServiceExchange()

	@Test
	fun `Create register`() {

		val random = UUID.randomUUID().toString()
		val request = requestExchange.payloadCreate(text = "Test_${random}")

		RestAssured.given().relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.filter(RequestLoggingFilter())
			.filter(ResponseLoggingFilter())
			.body(request)
			.`when`()
			.post(Data.URL)
			.then()
			.statusCode(HttpStatus.SC_OK)
	}

	@Test
	fun `search register`() {

		val response = RestAssured.given().relaxedHTTPSValidation()
			.contentType(ContentType.JSON)
			.filter(RequestLoggingFilter())
			.filter(ResponseLoggingFilter())
			.`when`()
			.get(Data.URL)
			.then()
			.statusCode(HttpStatus.SC_OK)

		val registro = response.extract().path<Any>("id[0]").toString().replace("[","").replace("]","")
		Assert.assertEquals("d57c68b1-d6dc-458a-9016-2484486cb798", registro)

	}


}
