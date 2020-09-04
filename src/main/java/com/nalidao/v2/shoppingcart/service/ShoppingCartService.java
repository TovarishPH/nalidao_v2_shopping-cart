package com.nalidao.v2.shoppingcart.service;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.nalidao.v2.shoppingcart.consumer.ProductConsumer;
import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ShoppingCartNotFoundException;
import com.nalidao.v2.shoppingcart.gateway.ShoppingCartGateway;
import com.nalidao.v2.shoppingcart.utils.CalculateTotalPrice;
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
	private Clock clock;

	public List<ShoppingCartDto> findAll() {
		return this.convertShoppingCartToDto.convertList(this.gateway.findAll());
	}
	
	public ShoppingCartDto getShoppingCartById(BigInteger id) {
		Optional<ShoppingCart> shoppingCart = this.gateway.findById(id);
		if (shoppingCart.isPresent()) {
			return this.convertShoppingCartToDto.convert(shoppingCart.get());
		} 
		throw new ShoppingCartNotFoundException("Carrinho com id " + id + " não encontrado.");
		
	}
	
	public ShoppingCartDto getShoppingCartByUserId(BigInteger userId) {
		Optional<ShoppingCart> shoppingCart = this.gateway.findByUserId(userId);
		if (shoppingCart.isPresent()) {
			return this.convertShoppingCartToDto.convert(shoppingCart.get());
		}
		throw new ShoppingCartNotFoundException("Carrinho do usuário id " + userId + " não encontrado.");
	}

	public ShoppingCartDto createShoppingCart(FormShoppingCartDto formDto) {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(formDto.getUserId());
		shoppingCart.setProductList(new ArrayList<ShoppingCartProduct>());
		shoppingCart.getProductList().add(this.validateProductForShoppingCartCreation(formDto));
		shoppingCart.setCreationDate(LocalDateTime.now(this.clock));
		shoppingCart.setUpdateDate(LocalDateTime.now(this.clock));
		shoppingCart.setTotalPrice(this.calculateTotalPrice.calculateProductLisTotalPrice(shoppingCart.getProductList()));
		
		return this.convertShoppingCartToDto.convert(this.gateway.save(shoppingCart));
	}
	
	public void deleteShoppingCart(BigInteger userId) {
		Optional<ShoppingCart> shoppingCart = this.gateway.findByUserId(userId);
		if(shoppingCart.isPresent()) {
			this.gateway.deleteShoppingCart(shoppingCart.get());
		} else {
			throw new ShoppingCartNotFoundException("Não foi possível deletar o carrinho pois o usuário id " + userId + " não encontrado.");
		}
		
	}

	public ShoppingCartDto updateShoppingCartContent(FormShoppingCartDto formUpdate) {
		Optional<ShoppingCart> sc = this.gateway.findByUserId(formUpdate.getUserId());
		if(sc.isPresent()) {
			Optional<ShoppingCartProduct> prod = sc.get().getProductList().stream().filter(p -> p.getId() == formUpdate.getProductId()).findFirst();
			if(prod.isPresent()) {
				this.handleExistingProduct(formUpdate, sc, prod);
			} else {
				this.addingNotExistingProduct(formUpdate, sc);
			}
			
			sc.get().setTotalPrice(this.calculateTotalPrice.calculateProductLisTotalPrice(sc.get().getProductList()));
			sc.get().setUpdateDate(LocalDateTime.now());
			
			return this.convertShoppingCartToDto.convert(this.gateway.save(sc.get()));
		}
		
		throw new ShoppingCartNotFoundException("Update cancelado. Carrinho de compras do usuário id " + formUpdate.getUserId() + " não encontrado");
	}

	/**
	 * Trata da alteração de um produto previamente existente no carrinho
	 * @param formUpdate
	 * @param sc
	 * @param prod
	 */
	private void handleExistingProduct(FormShoppingCartDto formUpdate, Optional<ShoppingCart> sc,
			Optional<ShoppingCartProduct> prod) {
		if(formUpdate.getProductAmmount() == 0) {
			sc.get().getProductList().remove(prod.get());
		} else {
			sc.get().getProductList().forEach(p -> {
				if(p.getId() == formUpdate.getProductId()) {
					p.setProdAmmount(formUpdate.getProductAmmount());
				}
			});
		}
	}

	/**
	 * Trata da adição de um produto que não existe préviamente no carrinho
	 * @param formUpdate
	 * @param sc
	 */
	private void addingNotExistingProduct(FormShoppingCartDto formUpdate, Optional<ShoppingCart> sc) {
		ShoppingCartProduct prod = this.productConsumer.getProduct(formUpdate.getProductId());
		prod.setProdAmmount(formUpdate.getProductAmmount());
		sc.get().getProductList().add(prod);
	}

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
