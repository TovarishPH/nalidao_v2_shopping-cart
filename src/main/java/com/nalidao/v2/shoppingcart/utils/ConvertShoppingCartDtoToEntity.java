package com.nalidao.v2.shoppingcart.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;

/**
 * Converte ShoppingCartDto para entidade
 * @author paulo
 */
@Component
public class ConvertShoppingCartDtoToEntity implements Converter<ShoppingCartDto, ShoppingCart>{

	@Override
	public ShoppingCart convert(ShoppingCartDto source) {
		ShoppingCart sc = new ShoppingCart();
		sc.setId(source.getId());
		sc.setUserId(source.getUserId());
		source.getProductDtoList().forEach(p -> {
			ShoppingCartProduct scp = new ShoppingCartProduct();
			scp.setId(p.getId());
			scp.setName(p.getName());
			scp.setPrice(p.getPrice());
			scp.setProdAmmount(p.getProdAmmount());
			sc.getProductList().add(scp);
		});
		sc.setCreationDate(source.getCreationDate());
		sc.setUpdateDate(source.getUpdateDate());
		sc.setTotalPrice(source.getTotalPrice());
		return sc;
	}

}
