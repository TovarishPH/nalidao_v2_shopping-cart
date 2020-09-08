package com.nalidao.v2.shoppingcart.controller;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.doThrow;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nalidao.v2.shoppingcart.consumer.ProductConsumer;
import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ShoppingCartNotFoundException;
import com.nalidao.v2.shoppingcart.service.ShoppingCartService;
import com.nalidao.v2.shoppingcart.utils.TestUtils;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ShoppingCartController.class)
public class ShoppingCartControllerTest {

	@MockBean
	private ShoppingCartService service;
	@MockBean
	private ProductConsumer consumer;
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper mapper;
	
	private TestUtils utils = new TestUtils();
	
	@Test
	public void testGetShoppingCartListReturnsOk() throws Exception {
		List<ShoppingCartDto> scDtoList = this.utils.getShopingCartDtoListMock();
		
		when(this.service.findAll()).thenReturn(scDtoList);
		
		mockMvc.perform(get("/shopping-cart")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(MockMvcResultHandlers.print())
						.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testGetShoppingCartByIdReturnsOk() throws Exception {
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		BigInteger id = BigInteger.ONE;
		
		when(this.service.getShoppingCartById(id)).thenReturn(scDto);
		
		mockMvc.perform(get("/shopping-cart/{id}", id)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetShoppingCartByIdReturnsNotFound() throws Exception {
		BigInteger id = BigInteger.ONE;
		
		doThrow(ShoppingCartNotFoundException.class).when(this.service).getShoppingCartById(id);
		
		mockMvc.perform(get("/shopping-cart/{id}", id)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testGetShoppingCartByUserIdReturnsOk() throws Exception {
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		BigInteger userId = BigInteger.ONE;
		
		when(this.service.getShoppingCartByUserId(userId)).thenReturn(scDto);
		
		mockMvc.perform(get("/shopping-cart/user/{userId}", userId)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testGetShoppingCartByUserIdReturnsNotFound() throws Exception {
		BigInteger userId = BigInteger.ONE;
		
		doThrow(ShoppingCartNotFoundException.class).when(this.service).getShoppingCartByUserId(userId);
		
		mockMvc.perform(get("/shopping-cart/user/{userId}", userId)
							.accept(MediaType.APPLICATION_JSON))
							.andDo(MockMvcResultHandlers.print())
							.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void testCreateShoppingCartReturnsOk() throws Exception {
		FormShoppingCartDto formDto = this.utils.getFormShoppingCartDto();
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		
		when(this.service.createShoppingCart(formDto)).thenReturn(scDto);
		
		ResultActions result = mockMvc.perform(post("/shopping-cart")
											.contentType(MediaType.APPLICATION_JSON)
											.content(this.mapper.writeValueAsString(formDto)));
		
		result.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn();
		
	}
	
	@Test
	public void testDeleteShoppingCartReturnsOk() throws Exception {
		BigInteger userId = BigInteger.ONE;
		
		ResultActions result = mockMvc.perform(delete("/shopping-cart/user/{userId}", userId)
												.accept(MediaType.APPLICATION_JSON));
		
		result.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testUpdateShoppingCartReturnsOk() throws Exception {
		FormShoppingCartDto formDto = this.utils.getFormShoppingCartDto();
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		
		when(this.service.updateShoppingCartContent(formDto)).thenReturn(scDto);
		
		ResultActions result = mockMvc.perform(put("/shopping-cart")
												.contentType(MediaType.APPLICATION_JSON)
												.content(this.mapper.writeValueAsString(formDto)));
		
		result.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
}
