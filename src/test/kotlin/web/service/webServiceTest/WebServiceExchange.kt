package web.service.webServiceTest

class WebServiceExchange {

    fun payloadCreate(text: String): String{
        return """
           {
              "text": "$text"
           }""".trimIndent()
    }
}