package com.nalidao.v2.shoppingcart.consumer;

import java.net.URI;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.nalidao.v2.shoppingcart.config.RestTemplateConfig;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductConsumerTest {
	
//	@InjectMocks
//	private ProductConsumer consumer;
//	
//	@Mock
//	private RestTemplateConfig restTemplate;
//	
//	@LocalServerPort
//	private int randomServerPort;
//	
//	private TestUtils utils = new TestUtils();
//	
//	@Test
//	public void testGetProductReturnsOk() throws Exception {
//		long id = 1;
//		String baseUrl = "htt://localhost:" + this.randomServerPort + "/product-api/" + id;
//		URI uri = new URI(baseUrl);
//		ShoppingCartProduct product = this.utils.getProduct();
//		
//		Mockito.when(this.restTemplate.getRestTemplate().getForObject(uri, ShoppingCartProduct.class)).thenReturn(product);
//		
//		ShoppingCartProduct expect = this.consumer.getProduct(id);
//		
//		Assertions.assertThat(expect).isNotNull();
//	}
	
}
