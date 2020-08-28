package com.nalidao.v2.shoppingcart.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;

@Component
public class ProductConsumer {

	@Value("${product.api}")
	private String path;
	
	public ShoppingCartProduct getProduct(long id) {
		RestTemplate rt = new RestTemplate();
		try {
			ShoppingCartProduct prod = rt.getForObject(this.path + "/" + id, ShoppingCartProduct.class);
			return prod;
		} catch (HttpClientErrorException e) {
			throw new ProductNotFoundException("ProductApi não encontrou o produto de id " + id);
		}
	}
}
