package com.nalidao.v2.shoppingcart.utils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;


public class TestUtils {

	public List<ShoppingCart> getShopingCartListMock() {
		List<ShoppingCart> scList = new ArrayList<ShoppingCart>();
		
		ShoppingCart sc = new ShoppingCart();
		sc.setId(BigInteger.valueOf(1));
		sc.setUserId(BigInteger.valueOf(1));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(14.0);
		sc.setCreationDate(LocalDateTime.now());
		sc.setUpdateDate(LocalDateTime.now().plusHours(1));
		scList.add(sc);
		
		ShoppingCart sc2 = new ShoppingCart();
		sc.setId(BigInteger.valueOf(2));
		sc.setUserId(BigInteger.valueOf(2));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(0.0);
		sc.setCreationDate(LocalDateTime.now());
		sc.setUpdateDate(LocalDateTime.now().plusHours(1));
		scList.add(sc2);
		
		return scList;
	}
	
	public ShoppingCart getShoppingCartMock() {
		ShoppingCart sc = new ShoppingCart();
		sc.setId(BigInteger.valueOf(100));
		sc.setUserId(BigInteger.valueOf(100));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(14.0);
		sc.setCreationDate(LocalDateTime.now().minusDays(3));
		sc.setUpdateDate(LocalDateTime.now());
		
		return sc;
	}
	
	public List<ShoppingCartProduct> getProductList() {
		List<ShoppingCartProduct> prodList = new ArrayList<ShoppingCartProduct>();
		
		ShoppingCartProduct prod1 = new ShoppingCartProduct();
		prod1.setId(1l);
		prod1.setName("prod1");
		prod1.setPrice(3.0);
		prod1.setProdAmmount(2);
		prodList.add(prod1);
		
		ShoppingCartProduct prod2 = new ShoppingCartProduct();
		prod2.setId(2l);
		prod2.setName("prod2");
		prod2.setPrice(4.0);
		prod2.setProdAmmount(2);
		prodList.add(prod2);
		
		return prodList;
		
	}
}
