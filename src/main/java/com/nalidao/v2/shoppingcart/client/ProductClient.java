package com.nalidao.v2.shoppingcart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;

@FeignClient(value = "product", url = "http://localhost:8080/product-api")
public interface ProductClient {

	@RequestMapping(method = RequestMethod.GET, value = "{productId}")
	ShoppingCartProduct getProduct(@PathVariable long productId);

}
