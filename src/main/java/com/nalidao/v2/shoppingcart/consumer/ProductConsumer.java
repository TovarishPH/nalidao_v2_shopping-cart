package com.nalidao.v2.shoppingcart.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;

/**
 * Arquitetura refeita.
 * Esta classe faz parte da arquitetura antiga onde o
 * RestTemplate substituído pelo Feign
 */
@Deprecated
@Component
public class ProductConsumer {
	/*o nome dessa classe deveria ser ProductGateway
	uma vez que a expressão Consumer é geralmente utilizada
	em cenário de mensageria*/
	@Value("${product.api}")
	private String path;
	
	//utilizar Feign ao invés de RestTemplate
	@Autowired
	private RestTemplate restTemplate;
	
	public ShoppingCartProduct getProduct(long id) {
		try {
			ShoppingCartProduct prod = this.restTemplate.getForObject(this.path + "/" + id, ShoppingCartProduct.class);
			return prod;
		} catch (HttpClientErrorException e) {
			throw new ProductNotFoundException("ProductApi não encontrou o produto de id " + id);
		}
	}
}
