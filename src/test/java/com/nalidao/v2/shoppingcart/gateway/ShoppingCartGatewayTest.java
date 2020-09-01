package com.nalidao.v2.shoppingcart.gateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.repository.ShoppingCartRepository;
import com.nalidao.v2.shoppingcart.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartGatewayTest {

	@InjectMocks
	private ShoppingCartGateway gateway;
	
	@Mock
	private ShoppingCartRepository repository;
	
	private TestUtils utils = new TestUtils();
	
	@Test
	public void testFindAll() {
		List<ShoppingCart> mockList = this.utils.getShopingCartListMock();
		when(this.repository.findAll()).thenReturn(mockList);
		
		List<ShoppingCart> list = this.gateway.findAll();
		
		assertThat(list).isNotNull().isNotEmpty();
		assertThat(list.size()).isEqualTo(mockList.size());
		assertSame(list, mockList);
	}
	
	@Test
	public void testFindShoppingCartById() {
		ShoppingCart sc = new ShoppingCart();
		BigInteger id = BigInteger.valueOf(100);
		
		when(this.repository.findById(id)).thenReturn(Optional.of(sc));
		ShoppingCart found = this.gateway.findById(id).get();
		
		assertThat(found).isNotNull();
		assertThat(found).isEqualTo(sc);
		assertSame(found, sc);
		
	}
	
	@Test
	public void testFindShoppingCartByUserId() {
		ShoppingCart sc = new ShoppingCart();
		BigInteger userId = BigInteger.valueOf(100);
		
		when(this.repository.findByUserId(userId)).thenReturn(Optional.of(sc));
		ShoppingCart found = this.gateway.findByUserId(userId).get();
		
		assertThat(found).isNotNull();
		assertThat(found).isEqualTo(sc);
		assertSame(found, sc);
	}
	
	@Test
	public void testSaveShoppingCart() {
		ShoppingCart sc = this.utils.getShoppingCartMock();
		
		this.gateway.save(sc);
		
		Mockito.verify(this.repository).save(sc);
	}
	
	@Test
	public void testDeleteShoppingCart() {
		ShoppingCart sc = this.utils.getShoppingCartMock();
		
		this.gateway.deleteShoppingCart(sc);
		
		Mockito.verify(this.repository).delete(sc);
	}
}
