package com.nalidao.v2.shoppingcart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.gateway.ShoppingCartGateway;

@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingCartGateway gateway;
	
	private double priceTotalSum = 0;

	public List<ShoppingCart> findAll() {
		return this.gateway.findAll();
	}

	public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
		shoppingCart.setCreationDate(LocalDateTime.now());
		shoppingCart.setUpdateDate(LocalDateTime.now());
		shoppingCart.setTotalPrice(this.calculateProductLisTotalPrice(shoppingCart.getProductList()));
		try {
			this.gateway.save(shoppingCart);
			return shoppingCart;
		} catch (Exception e) {
			System.out.println("Deu ruim no gateway!");
		}
		return null;
	}

	private Double calculateProductLisTotalPrice(List<ShoppingCartProduct> productList) {
		productList.forEach(p -> {
			double productPriceSum = p.getPrice() * p.getProdAmmount();
			this.priceTotalSum = this.priceTotalSum + productPriceSum;
		});
		
		return this.priceTotalSum;
	}

}
