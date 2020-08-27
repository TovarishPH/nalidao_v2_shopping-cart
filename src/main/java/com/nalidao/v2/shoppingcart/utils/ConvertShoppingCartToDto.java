package com.nalidao.v2.shoppingcart.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartProductDto;

/**
 * Converte entidade ShoppingCart em Dto
 * @author paulo
 */
@Component
public class ConvertShoppingCartToDto implements Converter<ShoppingCart, ShoppingCartDto> {

	@Override
	public ShoppingCartDto convert(ShoppingCart source) {
		ShoppingCartDto scDto = new ShoppingCartDto();
		scDto.setId(source.getId());
		scDto.setUserId(source.getUserId());
		source.getProductList().forEach(p -> {
			ShoppingCartProductDto scpDto = new ShoppingCartProductDto();
			scpDto.setId(p.getId());
			scpDto.setName(p.getName());
			scpDto.setPrice(p.getPrice());
			scpDto.setProdAmmount(p.getProdAmmount());
			scDto.getProductDtoList().add(scpDto);
		});
		scDto.setCreationDate(source.getCreationDate());
		scDto.setUpdateDate(source.getUpdateDate());
		scDto.setTotalPrice(source.getTotalPrice());
		return scDto;
	}

}
