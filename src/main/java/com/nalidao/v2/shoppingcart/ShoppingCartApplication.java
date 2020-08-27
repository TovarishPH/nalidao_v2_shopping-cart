package com.nalidao.v2.shoppingcart;

import java.math.BigInteger;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.service.ShoppingCartService;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Autowired
	private ShoppingCartService service;
	
	@PostConstruct
	public void startDataLoader() {
		ShoppingCart sc1 = new ShoppingCart();
		sc1.setProductList(new ArrayList<ShoppingCartProduct>());
		sc1.setUserId(BigInteger.valueOf(1));
		sc1.getProductList().add(new ShoppingCartProduct(1, "prodName1", 2.0, 3));
		sc1.getProductList().add(new ShoppingCartProduct(1, "prodName2", 2.0, 3));
		
		ShoppingCart sc2 = new ShoppingCart();
		sc2.setProductList(new ArrayList<ShoppingCartProduct>());
		sc2.setUserId(BigInteger.valueOf(1));
		sc2.getProductList().add(new ShoppingCartProduct(1, "prodName1", 2.0, 4));
		sc2.getProductList().add(new ShoppingCartProduct(1, "prodName2", 2.0, 4));
		
		this.service.createShoppingCart(sc1);
		this.service.createShoppingCart(sc2);
	}

}
