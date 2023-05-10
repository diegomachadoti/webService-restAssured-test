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

	companion object{
	 const val REGISTER = 10
	}

	@Test
	fun `Create register`() {

		for (e in 0 until REGISTER) {
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

		val id = response.extract().path<Any>("id[0]").toString().replace("[","").replace("]","")
		val text = response.extract().path<Any>("text[0]").toString().replace("[","").replace("]","")
		Assert.assertNotNull(id)
		Assert.assertNotNull(text)
		Assert.assertEquals("8ce6e581-84c9-4c5e-a594-cf9edf24114a", id)

	}


}
