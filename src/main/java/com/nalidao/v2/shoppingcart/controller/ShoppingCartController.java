package com.nalidao.v2.shoppingcart.controller;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.service.ShoppingCartService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shopping-cart")
@Api(value = "Api de carrinho de compras")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService service;
	
	@GetMapping
	@ApiOperation(value = "Lista todos os carrinhos de compras criados pela API")
	public List<ShoppingCartDto> getShoppingCartList() {
		return this.service.findAll();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Devolve um carrinho de compras baseado no id do mesmo")
	public ResponseEntity<ShoppingCartDto> getShoppingCartById(@PathVariable BigInteger id) {
		ShoppingCartDto shoppingCartDto = this.service.getShoppingCartById(id);
		return ResponseEntity.ok(shoppingCartDto);
	}
	
	@GetMapping("/user/{userId}")
	@ApiOperation(value = "Devolve um carrinho de compras baseado no id do usuário")
	public ResponseEntity<ShoppingCartDto> getShoppingCartByUserId(@PathVariable BigInteger userId) {
		ShoppingCartDto shoppingCartDto = this.service.getShoppingCartByUserId(userId);
		return ResponseEntity.ok(shoppingCartDto);
	}
	
	@PostMapping
	@ApiOperation(value = "Cria um carrinho de compras")
	public ResponseEntity<?> createShoppingCart(@RequestBody @Valid FormShoppingCartDto formDto, UriComponentsBuilder builder) {
		ShoppingCartDto scDto = this.service.createShoppingCart(formDto);
		URI uri = builder.path("shopping-cart/user/{userId}").buildAndExpand(scDto.getUserId()).toUri();
		return ResponseEntity.created(uri).body(scDto);
	}
	
	@DeleteMapping("/user/{userId}")
	@ApiOperation(value = "Deleta um carrinho de compras")
	public ResponseEntity<?> deleteShoppingCart(@PathVariable BigInteger userId) {
		this.service.deleteShoppingCart(userId);
		return ResponseEntity.ok("Carrinho do usuário id " + userId + " excluido da base de dados.");
	}
	
	@PutMapping
	@ApiOperation(value = "Faz o update do estado de um carrinho de compras")
	public ResponseEntity<ShoppingCartDto> updateShoppingCartContent(@RequestBody @Valid FormShoppingCartDto formUpdate) {
		ShoppingCartDto shoppingCartDto = this.service.updateShoppingCartContent(formUpdate);
		return ResponseEntity.ok(shoppingCartDto);
	}
}
