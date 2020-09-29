package com.nalidao.v2.shoppingcart.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.client.ProductClient;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;

@Component
public class ProductGateway {

	@Autowired
	private ProductClient productClient;

	public ShoppingCartProduct getProduct(long productId) {
		return this.productClient.getProduct(productId);
	}
}
