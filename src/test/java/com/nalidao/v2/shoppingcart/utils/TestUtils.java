package com.nalidao.v2.shoppingcart.utils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartProductDto;


public class TestUtils {

	public List<ShoppingCart> getShopingCartListMock() {
		List<ShoppingCart> scList = new ArrayList<ShoppingCart>();
		
		ShoppingCart sc = new ShoppingCart();
		sc.setId(BigInteger.valueOf(1));
		sc.setUserId(BigInteger.valueOf(1));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(14.0);
		sc.setCreationDate(LocalDateTime.now());
		sc.setUpdateDate(LocalDateTime.now());
		scList.add(sc);
		
		ShoppingCart sc2 = new ShoppingCart();
		sc2.setId(BigInteger.valueOf(2));
		sc2.setUserId(BigInteger.valueOf(2));
		sc2.setProductList(this.getProductList());
		sc2.setTotalPrice(14.0);
		sc2.setCreationDate(LocalDateTime.now());
		sc2.setUpdateDate(LocalDateTime.now());
		scList.add(sc2);
		
		return scList;
	}
	
	public ShoppingCart getShoppingCartMock() {
		ShoppingCart sc = new ShoppingCart();
		sc.setId(BigInteger.valueOf(100));
		sc.setUserId(BigInteger.valueOf(100));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(0.0);
		sc.setCreationDate(LocalDateTime.now());
		sc.setUpdateDate(LocalDateTime.now());
		
		return sc;
	}
	
	public ShoppingCart getShoppingCartToCreateMock() {
		ShoppingCart sc = new ShoppingCart();
		sc.setUserId(BigInteger.valueOf(100));
		sc.setProductList(this.getProductList());
		sc.setTotalPrice(0.0);
		sc.setCreationDate(LocalDateTime.now());
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
		
//		ShoppingCartProduct prod2 = new ShoppingCartProduct();
//		prod2.setId(2l);
//		prod2.setName("prod2");
//		prod2.setPrice(4.0);
//		prod2.setProdAmmount(2);
//		prodList.add(prod2);
		
		return prodList;
		
	}
	
	public ShoppingCartProduct getProduct() {
		ShoppingCartProduct prod = new ShoppingCartProduct();
		prod.setId(1l);
		prod.setName("prod1");
		prod.setPrice(3.0);
		prod.setProdAmmount(2);
		return prod;
	}
	
	/* ###DTO### */
	public List<ShoppingCartDto> getShopingCartDtoListMock() {
		List<ShoppingCartDto> scDtoList = new ArrayList<ShoppingCartDto>();
		
		ShoppingCartDto scDto = new ShoppingCartDto();
		scDto.setId(BigInteger.valueOf(1));
		scDto.setUserId(BigInteger.valueOf(1));
		scDto.setProductDtoList(this.getProductDtoList());
		scDto.setTotalPrice(14.0);
		scDto.setCreationDate(LocalDateTime.now());
		scDto.setUpdateDate(LocalDateTime.now());
		scDtoList.add(scDto);
		
		ShoppingCartDto scDto2 = new ShoppingCartDto();
		scDto2.setId(BigInteger.valueOf(2));
		scDto2.setUserId(BigInteger.valueOf(2));
		scDto2.setProductDtoList(this.getProductDtoList());
		scDto2.setTotalPrice(14.0);
		scDto2.setCreationDate(LocalDateTime.now());
		scDto2.setUpdateDate(LocalDateTime.now());
		scDtoList.add(scDto2);
		
		return scDtoList;
	}
	
	public ShoppingCartDto getShoppingCartDtoMock() {
		ShoppingCartDto scDto = new ShoppingCartDto();
		scDto.setId(BigInteger.valueOf(100));
		scDto.setUserId(BigInteger.valueOf(100));
		scDto.setProductDtoList(this.getProductDtoList());
		scDto.setTotalPrice(14.0);
		scDto.setCreationDate(LocalDateTime.now());
		scDto.setUpdateDate(LocalDateTime.now());
		
		return scDto;
	}

	private List<ShoppingCartProductDto> getProductDtoList() {
		List<ShoppingCartProductDto> prodDtoList = new ArrayList<ShoppingCartProductDto>();
		
		ShoppingCartProductDto prodDto1 = new ShoppingCartProductDto();
		prodDto1.setId(1l);
		prodDto1.setName("prod1");
		prodDto1.setPrice(3.0);
		prodDto1.setProdAmmount(2);
		prodDtoList.add(prodDto1);
		
//		ShoppingCartProductDto prodDto2 = new ShoppingCartProductDto();
//		prodDto2.setId(2l);
//		prodDto2.setName("prod2");
//		prodDto2.setPrice(4.0);
//		prodDto2.setProdAmmount(2);
//		prodDtoList.add(prodDto2);
		
		return prodDtoList;
	}
	
	public FormShoppingCartDto getFormShoppingCartDto() {
		FormShoppingCartDto formDto = new FormShoppingCartDto();
		formDto.setUserId(BigInteger.valueOf(100));
		formDto.setProductId(1);
		formDto.setProductAmmount(2);
		
		return formDto;
	}
	
}
