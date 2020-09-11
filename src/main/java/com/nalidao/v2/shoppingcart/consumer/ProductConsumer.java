package com.nalidao.v2.shoppingcart.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.nalidao.v2.shoppingcart.config.RestTemplateConfig;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;

@Component
public class ProductConsumer {

	@Value("${product.api}")
	private String path;
	
	@Autowired
	private RestTemplateConfig restTemplate;
	
	public ShoppingCartProduct getProduct(long id) {
		try {
			ShoppingCartProduct prod = this.restTemplate.getRestTemplate().getForObject(this.path + "/" + id, ShoppingCartProduct.class);
			return prod;
		} catch (HttpClientErrorException e) {
			throw new ProductNotFoundException("ProductApi não encontrou o produto de id " + id);
		}
	}
}
