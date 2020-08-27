package com.nalidao.v2.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.dto.FormCreationShoppingCartDto;
import com.nalidao.v2.shoppingcart.service.ShoppingCartService;
import com.nalidao.v2.shoppingcart.utils.ConvertFormCreationShoppingCartDtoToEntity;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService service;
	
	@Autowired
	private ConvertFormCreationShoppingCartDtoToEntity convertFormCreationShoppingCartDtoToEntity;
	
	@GetMapping
	public List<ShoppingCart> getShoppingCartList() {
		return this.service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> createShoppingCart(@RequestBody FormCreationShoppingCartDto dto) {
		ShoppingCart sc = this.service.createShoppingCart(this.convertFormCreationShoppingCartDtoToEntity.convert(dto));
		return ResponseEntity.ok().build();
	}
}
