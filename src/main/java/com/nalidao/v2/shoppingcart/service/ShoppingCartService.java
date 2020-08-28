package com.nalidao.v2.shoppingcart.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nalidao.v2.shoppingcart.consumer.ProductConsumer;
import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;
import com.nalidao.v2.shoppingcart.gateway.ShoppingCartGateway;
import com.nalidao.v2.shoppingcart.utils.CalculateTotalPrice;
import com.nalidao.v2.shoppingcart.utils.ConvertFormCreationShoppingCartDtoToEntity;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartDtoToEntity;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartProductDtoToEntity;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartToDto;

@Service
public class ShoppingCartService {
	
	private static final Logger LOG = Logger.getLogger(ShoppingCartService.class.getName());

	@Autowired
	private ShoppingCartGateway gateway;
	
	@Autowired
	private ProductConsumer productConsumer;
	
	@Autowired
	private CalculateTotalPrice calculateTotalPrice;
	
	@Autowired
	private ConvertShoppingCartToDto convertShoppingCartToDto;

	@Autowired
	private ConvertShoppingCartDtoToEntity convertShoppingCartDtoToEntity;
	
	@Autowired
	private ConvertShoppingCartProductDtoToEntity convertProdcutDtoToEntity;
	

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

	public ShoppingCartDto createShoppingCart(FormShoppingCartDto formDto) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(formDto.getUserId());
		shoppingCart.setProductList(new ArrayList<ShoppingCartProduct>());
		shoppingCart.getProductList().add(this.validateProductForShoppingCartCreation(formDto));
		shoppingCart.setCreationDate(LocalDateTime.now());
		shoppingCart.setUpdateDate(LocalDateTime.now());
		shoppingCart.setTotalPrice(this.calculateTotalPrice.calculateProductLisTotalPrice(shoppingCart.getProductList()));
		
		return this.convertShoppingCartToDto.convert(this.gateway.save(shoppingCart));
	}
	
	public void deleteShoppingCart(BigInteger userId) {
		Optional<ShoppingCart> shoppinhCart = this.gateway.finByUserId(userId);
		if(shoppinhCart.isPresent()) {
			this.gateway.deleteShoppingCart(shoppinhCart.get());
		} else {
			System.out.println("Carrinho não foi encontrado!");
		}
		
	}

//	public ShoppingCartDto updateShoppingCartContent(FormCreationShoppingCartDto formUpdate) {
//		Optional<ShoppingCart> shoppingCart = this.gateway.finByUserId(formUpdate.getUserId());
//		//ShoppingCartEncontrado na base de dados
//		if(shoppingCart.isPresent()) {
//			//atualiza data de update
//			shoppingCart.get().setUpdateDate(LocalDateTime.now());
//			//Convertendo lista para mesmo tipo de objeto, para comparação
//			List<ShoppingCartProduct> prodUpdateList = this.convertProdcutDtoToEntity.convertList(formUpdate.getProductDtoList());
//			prodUpdateList.forEach(pu -> {
//				if (shoppingCart.get().getProductList().stream().filter(p -> p.equals(pu)).findFirst().isPresent()) {
//					//pega o produto com mesmo id e atualiza
//				} else {
//					//adiciona um produto ao carrinho
//				}
//			});
//			
//		} else {
//			System.out.println("Deu ruim na hora de achar o shoppingCart.");
//		}
//		return null;
//	}
	
	/**
	 * Valida se o produto existe e retorna o objeto para adição na lista
	 * @param formDto
	 * @return ShoppingCartProduct
	 */
	private ShoppingCartProduct validateProductForShoppingCartCreation(FormShoppingCartDto formDto) {
		ShoppingCartProduct prod = this.productConsumer.getProduct(formDto.getProductId());
		prod.setProdAmmount(formDto.getProductAmmount());
		return prod;
	}

}
