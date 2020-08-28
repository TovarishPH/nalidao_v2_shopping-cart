package com.nalidao.v2.shoppingcart.consumer;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;

@Component
public class ProductConsumer {

	@Value("${product.api}")
	private String path;
	
	public ShoppingCartProduct getProduct(long id) {
		RestTemplate rt = new RestTemplate();
		
		ShoppingCartProduct prod = rt.getForObject(this.path + "/" + id, ShoppingCartProduct.class);
		
		return prod;
	}
}
