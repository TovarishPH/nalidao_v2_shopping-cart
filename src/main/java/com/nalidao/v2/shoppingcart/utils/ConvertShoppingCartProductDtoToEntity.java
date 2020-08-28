package com.nalidao.v2.shoppingcart.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartProductDto;

@Component
public class ConvertShoppingCartProductDtoToEntity implements Converter<ShoppingCartProductDto, ShoppingCartProduct> {

	@Override
	public ShoppingCartProduct convert(ShoppingCartProductDto source) {
		return new ShoppingCartProduct(source.getId(), source.getName(), source.getPrice(), source.getProdAmmount());
	}
	
	public List<ShoppingCartProduct> convertList(List<ShoppingCartProductDto> dtoList) {
		List<ShoppingCartProduct> prodList = new ArrayList<ShoppingCartProduct>();
		dtoList.forEach(pd -> {
			ShoppingCartProduct prod = this.convert(pd);
			prodList.add(prod);
		});
		
		return prodList;
	}

}
