package web.service.webServiceTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class WebServiceTestApplication

fun main(args: Array<String>) {
	runApplication<WebServiceTestApplication>(*args)
}

@RestController
class MessageResource {
	@GetMapping
	fun index(): List<Message> = listOf(
		Message("1", "Teste de Contrato!"),
		Message("2", "Testes E2E"),
		Message("3", "Teste Unitário"),
		Message("4", "Teste de Componente"),
		Message("5", "Teste de Carga"),
		Message("6", "Teste de Mutação"),
		Message("7", "Teste Integrado"),
		Message("8", "Teste de Fumaça"),
		Message("9", "Teste Manual"),
	)

}

data class Message(val id: String?, val text: String)
