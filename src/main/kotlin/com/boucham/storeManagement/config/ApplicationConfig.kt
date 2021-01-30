package com.boucham.storeManagement.config

import com.boucham.storeManagement.controller.ClientController
import com.boucham.storeManagement.models.Client
import com.boucham.storeManagement.models.Store
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket


@Configuration
@EnableWebMvc
class ApplicationConfig(@Autowired val clientRepository: ClientRepository,
                        @Autowired val storeRepository: StoreRepository) {

    val logger = LogFactory.getLog(ClientController::class.java)

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boucham.storeManagement.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        val jdk8Module = Jdk8Module().configureAbsentsAsNulls(true)
        objectMapper.registerModule(jdk8Module)
        objectMapper.registerModule(JavaTimeModule())
        return objectMapper
    }


    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Store Management Api")
                .description("Api Definition")
                .version("1.0.0")
                .build()
    }

    @EventListener(ApplicationReadyEvent::class)
    fun loadData() {
        clientRepository.insert(Client(firstName = "Foo", lastName = "Bar"))
        clientRepository.insert(Client(firstName = "Jean", lastName = "Dupont"))
        clientRepository.insert(Client(firstName = "Albert", lastName = "Marseille"))
        clientRepository.insert(Client(firstName = "Marie", lastName = "Curie"))

        storeRepository.insert(Store(name = "Foo"))
        storeRepository.insert(Store(name = "Auchan"))
        storeRepository.insert(Store(name = "Leclerc"))
        storeRepository.insert(Store(name = "LeroyMerlin"))
    }
}