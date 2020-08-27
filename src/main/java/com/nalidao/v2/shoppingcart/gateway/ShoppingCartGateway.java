package com.nalidao.v2.shoppingcart.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.repository.ShoppingCartRepository;

@Component
public class ShoppingCartGateway {

	@Autowired
	private ShoppingCartRepository repository;

	public List<ShoppingCart> findAll() {
		return this.repository.findAll();
	}

	public ShoppingCart save(ShoppingCart shoppingCart) {
		return this.repository.save(shoppingCart);
	}

}
