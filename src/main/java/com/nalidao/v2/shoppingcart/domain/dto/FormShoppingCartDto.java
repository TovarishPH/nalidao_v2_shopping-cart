package com.nalidao.v2.shoppingcart.domain.dto;

import java.math.BigInteger;

import javax.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormShoppingCartDto {

	private BigInteger userId;
	private long productId;
	@PositiveOrZero(message = "A quantidade de produtos não pode ser negativa.")
	private int productAmmount;
	
	public FormShoppingCartDto(BigInteger userId, long productId,
			@PositiveOrZero(message = "A quantidade de produtos não pode ser negativa.") int productAmmount) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.productAmmount = productAmmount;
	}
	
}
