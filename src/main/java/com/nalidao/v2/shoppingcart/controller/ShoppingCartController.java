package com.nalidao.v2.shoppingcart.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.dto.FormCreationShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
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
	public List<ShoppingCartDto> getShoppingCartList() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingCartDto> getShoppingCartById(@PathVariable BigInteger id) {
		ShoppingCartDto shoppingCartDto = this.service.getShoppingCartById(id);
		return ResponseEntity.ok(shoppingCartDto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<ShoppingCartDto> getShoppingCartByUserId(@PathVariable BigInteger userId) {
		ShoppingCartDto shoppingCartDto = this.service.getShoppingCartByUserId(userId);
		return ResponseEntity.ok(shoppingCartDto);
	}
	
	@PostMapping
	public ResponseEntity<?> createShoppingCart(@RequestBody FormCreationShoppingCartDto formDto, UriComponentsBuilder builder) {
		ShoppingCartDto scDto = this.service.createShoppingCart(formDto);
		URI uri = builder.path("shopping-cart/user/{userId}").buildAndExpand(scDto.getUserId()).toUri();
		return ResponseEntity.created(uri).body(scDto);
	}
	
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteShoppingCart(@PathVariable BigInteger userId) {
		this.service.deleteShoppingCart(userId);
		return ResponseEntity.ok("Carrinho do usuário id " + userId + " excluido da base de dados.");
	}
	
	@PutMapping
	public ResponseEntity<ShoppingCartDto> updateShoppingCartContent(@RequestBody FormCreationShoppingCartDto formUpdate) {
		this.service.updateShoppingCartContent(formUpdate);
		return null;
	}
}
