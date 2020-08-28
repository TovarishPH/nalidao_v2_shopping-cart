package com.nalidao.v2.shoppingcart;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
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
		FormShoppingCartDto form1 = new FormShoppingCartDto();
		form1.setUserId(BigInteger.valueOf(1));
		form1.setProductId(1);
		form1.setProductAmmount(3);
		
		FormShoppingCartDto form2 = new FormShoppingCartDto();
		form2.setUserId(BigInteger.valueOf(2));
		form2.setProductId(3);
		form2.setProductAmmount(2);
		
		this.service.createShoppingCart(form1);
		this.service.createShoppingCart(form2);
		LOG.info("Carga de dados terminada com sucesso");
	}

}
