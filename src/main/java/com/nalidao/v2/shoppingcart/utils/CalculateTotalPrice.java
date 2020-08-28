package com.nalidao.v2.shoppingcart.utils;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;

/**
 * Classe para calculo de preços do carrinho de compras
 * @author paulo
 */
@Component
public class CalculateTotalPrice {

	private double priceTotalSum;
	
	public Double calculateProductLisTotalPrice(List<ShoppingCartProduct> productList) {
		this.priceTotalSum = 0;
		
		productList.forEach(p -> {
			double productPriceSum = p.getPrice() * p.getProdAmmount();
			this.priceTotalSum = this.priceTotalSum + productPriceSum;
		});
		
		return this.priceTotalSum;
	}
}
