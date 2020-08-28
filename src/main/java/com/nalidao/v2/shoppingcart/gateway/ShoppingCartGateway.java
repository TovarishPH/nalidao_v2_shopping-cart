package com.nalidao.v2.shoppingcart.gateway;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

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

	public Optional<ShoppingCart> findById(BigInteger id) {
		return this.repository.findById(id);
	}

	public Optional<ShoppingCart> finByUserId(BigInteger userId) {
		return this.repository.findByUserId(userId);
	}

	public void deleteShoppingCart(ShoppingCart shoppingCart) {
		this.repository.delete(shoppingCart);
	}

}
