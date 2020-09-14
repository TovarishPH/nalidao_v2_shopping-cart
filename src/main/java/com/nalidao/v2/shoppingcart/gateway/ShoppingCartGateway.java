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

	public Optional<ShoppingCart> findById(final BigInteger id) {
		return this.repository.findById(id);
	}

	public Optional<ShoppingCart> findByUserId(final BigInteger userId) {
		return this.repository.findByUserId(userId);
	}
	
	public ShoppingCart save(final ShoppingCart shoppingCart) {
		return this.repository.save(shoppingCart);
	}

	public void deleteShoppingCart(final ShoppingCart shoppingCart) {
		this.repository.delete(shoppingCart);
	}

}
