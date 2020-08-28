package com.nalidao.v2.shoppingcart;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nalidao.v2.shoppingcart.domain.dto.FormCreationShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartProductDto;
import com.nalidao.v2.shoppingcart.service.ShoppingCartService;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	private static final Logger LOG = Logger.getLogger(ShoppingCartApplication.class.getName());
	
	@Autowired
	private ShoppingCartService service;
	
	@PostConstruct
	public void startDataLoader() {
		LOG.info("Inicio da carga de dados para testes iniciais");
		FormCreationShoppingCartDto form1 = new FormCreationShoppingCartDto();
		form1.setProductDtoList(new ArrayList<ShoppingCartProductDto>());
		form1.setUserId(BigInteger.valueOf(1));
		form1.getProductDtoList().add(new ShoppingCartProductDto(1, "prodName1", 2.0, 3));
		form1.getProductDtoList().add(new ShoppingCartProductDto(2, "prodName2", 2.0, 3));
		
		FormCreationShoppingCartDto sc2 = new FormCreationShoppingCartDto();
		sc2.setProductDtoList(new ArrayList<ShoppingCartProductDto>());
		sc2.setUserId(BigInteger.valueOf(2));
		sc2.getProductDtoList().add(new ShoppingCartProductDto(1, "prodName1", 2.0, 4));
		sc2.getProductDtoList().add(new ShoppingCartProductDto(2, "prodName2", 2.0, 4));
		
		this.service.createShoppingCart(form1);
		this.service.createShoppingCart(sc2);
		LOG.info("Carga de dados terminada com sucesso");
	}

}
