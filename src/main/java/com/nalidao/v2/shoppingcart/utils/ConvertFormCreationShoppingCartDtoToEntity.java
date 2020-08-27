package com.nalidao.v2.shoppingcart.utils;

import java.util.ArrayList;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormCreationShoppingCartDto;

/**
 * Converte o dto que serve de formulario de criação de shopping-cart para entidade
 * @author paulo
 */
@Component
public class ConvertFormCreationShoppingCartDtoToEntity implements Converter<FormCreationShoppingCartDto, ShoppingCart> {

	@Override
	public ShoppingCart convert(FormCreationShoppingCartDto source) {
		ShoppingCart sc = new ShoppingCart();
		sc.setProductList(new ArrayList<ShoppingCartProduct>());
		sc.setUserId(source.getUserId());
		source.getProductDtoList().forEach(p -> {
			ShoppingCartProduct scp = new ShoppingCartProduct();
			scp.setId(p.getId());
			scp.setName(p.getName());
			scp.setPrice(p.getPrice());
			scp.setProdAmmount(p.getProdAmmount());
			sc.getProductList().add(scp);
		});
		return sc;
	}

}
