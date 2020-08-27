package com.nalidao.v2.shoppingcart.domain;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ShoppingCart {

	@Id
	private BigInteger id;
	
	private BigInteger userId;
	
	private List<ShoppingCartProduct> productList;
	
	private Double totalPrice;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime updateDate;
	
}
