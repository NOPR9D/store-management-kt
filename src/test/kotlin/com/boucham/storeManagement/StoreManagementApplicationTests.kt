package com.boucham.storeManagement

import com.boucham.storeManagement.controller.ActionController
import com.boucham.storeManagement.controller.ClientController
import com.boucham.storeManagement.controller.StoreController
import com.boucham.storeManagement.repository.ClientRepository
import com.boucham.storeManagement.repository.StoreRepository
import com.ninjasquad.springmockk.MockkBean
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@WebMvcTest
@AutoConfigureMockMvc
@DataJpaTest
class StoreManagementApplicationTests {
    @InjectMocks
    private lateinit var clientController: ClientController

    @InjectMocks
    private lateinit var storeController: StoreController

    @InjectMocks
    private lateinit var actionController: ActionController

    @MockkBean
    private lateinit var clientRepository: ClientRepository

    @MockkBean
    private lateinit var storeRepository: StoreRepository

    @Autowired
    private lateinit var mockMvc: MockMvc


    //   @Test
    //   fun addFooBarClient() {
    // val result = mockMvc.perform(
    //       MockMvcRequestBuilders.get("/api/clients"))
    //     .andExpect(MockMvcResultMatchers.status().isOk)
    //   .andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist())


    //  val client = Gson().toJson(Store(name = "foo"))
    //  mockMvc.perform(
    //          MockMvcRequestBuilders
    //                 .post("/api/stores")
    //                 .contentType("application/json").content(client))
    //         .andExpect(MockMvcResultMatchers.status().isOk)

    //   }


}
