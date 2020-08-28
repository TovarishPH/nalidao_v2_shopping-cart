package com.nalidao.v2.shoppingcart.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormCreationShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartProductDto;
import com.nalidao.v2.shoppingcart.gateway.ShoppingCartGateway;
import com.nalidao.v2.shoppingcart.utils.CalculateTotalPrice;
import com.nalidao.v2.shoppingcart.utils.ConvertFormCreationShoppingCartDtoToEntity;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartDtoToEntity;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartToDto;

@Service
public class ShoppingCartService {
	
	private static final Logger LOG = Logger.getLogger(ShoppingCartService.class.getName());

	@Autowired
	private ShoppingCartGateway gateway;
	
	@Autowired
	private CalculateTotalPrice calculateTotalPrice;
	
	@Autowired
	private ConvertShoppingCartToDto convertShoppingCartToDto;

	@Autowired
	private ConvertFormCreationShoppingCartDtoToEntity convertFormCreationShoppingCartDtoToEntity;

	@Autowired
	private ConvertShoppingCartDtoToEntity convertShoppingCartDtoToEntity;
	

	public List<ShoppingCartDto> findAll() {
		return this.convertShoppingCartToDto.convertList(this.gateway.findAll());
	}
	
	public ShoppingCartDto getShoppingCartById(BigInteger id) {
		Optional<ShoppingCart> shoppingCart = this.gateway.findById(id);
		if (shoppingCart.isPresent()) {
			return this.convertShoppingCartToDto.convert(shoppingCart.get());
		} else {
			System.out.println("Deu ruim. Tratar a Exception.");
		}
		return null;
		
	}
	
	public ShoppingCartDto getShoppingCartByUserId(BigInteger userId) {
		Optional<ShoppingCart> shoppingCart = this.gateway.finByUserId(userId);
		if (shoppingCart.isPresent()) {
			return this.convertShoppingCartToDto.convert(shoppingCart.get());
		} else {
			System.out.println("Deu ruim. Tratar a Exception.");
		}
		return null;
	}

	public ShoppingCartDto createShoppingCart(FormCreationShoppingCartDto formDto) {
		ShoppingCart shoppingCart = this.convertFormCreationShoppingCartDtoToEntity.convert(formDto);
		shoppingCart.setCreationDate(LocalDateTime.now());
		shoppingCart.setUpdateDate(LocalDateTime.now());
		shoppingCart.setTotalPrice(this.calculateTotalPrice.calculateProductLisTotalPrice(shoppingCart.getProductList()));
		try {
			return this.convertShoppingCartToDto.convert(this.gateway.save(shoppingCart));
		} catch (Exception e) {
			System.out.println("Deu ruim no gateway!");
		}
		return null;
	}
	
	public void deleteShoppingCart(BigInteger userId) {
		Optional<ShoppingCart> shoppinhCart = this.gateway.finByUserId(userId);
		if(shoppinhCart.isPresent()) {
			this.gateway.deleteShoppingCart(shoppinhCart.get());
		} else {
			System.out.println("Carrinho não foi encontrado!");
		}
		
	}

	public ShoppingCartDto updateShoppingCartContent(FormCreationShoppingCartDto formUpdate) {
//		Optional<ShoppingCart> shoppingCart = this.gateway.finByUserId(formUpdate.getUserId());
//		//ShoppingCartEncontrado na base de dados
//		if(shoppingCart.isPresent()) {
//			shoppingCart.get().setUpdateDate(LocalDateTime.now());
//			formUpdate.getProductDtoList().forEach(pu -> {
//				if(shoppingCart.get().getProductList().contains(pu.getId())) {
//					
//				}
//			});
//			
//		} else {
//			System.out.println("Deu ruim na hora de achar o shoppingCart.");
//		}
		return null;
	}

}
