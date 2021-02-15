package com.rvr.sistemacadastroalimentar.api.resource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class AlimentosApiTeste {

	static String API = "/api/alimentos";
	
	@Autowired
	MockMvc mvc;
	
	@Test
	@DisplayName("Deve retornar lista de todos alimentos cadastrados")
	public void getAlimentos() throws Exception {
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		mvc
		.perform(request)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("nome").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("tipo").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("kcal_calculado").isNotEmpty())
		.andExpect(MockMvcResultMatchers.jsonPath("peso_calculado").isNotEmpty());
	}
}
