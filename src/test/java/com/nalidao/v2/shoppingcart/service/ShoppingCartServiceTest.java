package com.nalidao.v2.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.nalidao.v2.shoppingcart.consumer.ProductConsumer;
import com.nalidao.v2.shoppingcart.domain.ShoppingCart;
import com.nalidao.v2.shoppingcart.domain.ShoppingCartProduct;
import com.nalidao.v2.shoppingcart.domain.dto.FormShoppingCartDto;
import com.nalidao.v2.shoppingcart.domain.dto.ShoppingCartDto;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ShoppingCartNotFoundException;
import com.nalidao.v2.shoppingcart.gateway.ShoppingCartGateway;
import com.nalidao.v2.shoppingcart.utils.CalculateTotalPrice;
import com.nalidao.v2.shoppingcart.utils.ConvertShoppingCartToDto;
import com.nalidao.v2.shoppingcart.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ShoppingCartServiceTest {
	
	private final static LocalDate LOCAL_DATE = LocalDate.of(2020, 9, 3);

	@InjectMocks
	private ShoppingCartService service;
	
	@Mock
	private ShoppingCartGateway gateway;
	
	@Mock
	private ConvertShoppingCartToDto convertShoppingCartToDto;
	
	@Mock
	private ProductConsumer consumer;
	
	@Mock
	private CalculateTotalPrice calculateTotalPrice;
	
	@Mock
	private Clock clock;
	
	private Clock fixedClock;
	
	private TestUtils utils = new TestUtils();
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		this.fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		doReturn(this.fixedClock.instant()).when(this.clock).instant();
		doReturn(this.fixedClock.getZone()).when(this.clock).getZone();
	}
	
	@Test
	public void testFindAllReturnsOk() {
		List<ShoppingCart> scList = this.utils.getShopingCartListMock();
		List<ShoppingCartDto> scDtoList = this.utils.getShopingCartDtoListMock();
		
		when(this.gateway.findAll()).thenReturn(scList);
		when(this.convertShoppingCartToDto.convertList(scList)).thenReturn(scDtoList);
		
		List<ShoppingCartDto> resultList = this.service.findAll();
		
		assertThat(resultList).isNotNull().isNotEmpty();
		assertThat(scDtoList.size()).isEqualTo(scList.size());
		assertThat(resultList.size()).isEqualTo(scDtoList.size());
		assertThat(resultList).isSameAs(scDtoList);
		
	}
	
	@Test
	public void testFindByIdReturnsOk() {
		BigInteger id = BigInteger.valueOf(100);
		ShoppingCart sc = this.utils.getShoppingCartMock();
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		
		when(this.gateway.findById(id)).thenReturn(Optional.of(sc));
		when(this.convertShoppingCartToDto.convert(sc)).thenReturn(scDto);
		
		ShoppingCartDto result = this.service.getShoppingCartById(id);
		
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(scDto);
		assertThat(result).isSameAs(scDto);
		
	}
	
	@Test
	public void testFindByIdThrowShoppingCartNotFoundException() {
		BigInteger id = BigInteger.valueOf(100);
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.getShoppingCartById(id);
		});
		
		assertThat(thrown).isInstanceOf(ShoppingCartNotFoundException.class).hasMessage("Carrinho com id " + id + " não encontrado.");
		
	}
	
	@Test
	public void testFindByUserIdreturnsOk() {
		BigInteger userId = BigInteger.valueOf(100);
		ShoppingCart sc = this.utils.getShoppingCartMock();
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		
		when(this.gateway.findByUserId(userId)).thenReturn(Optional.of(sc));
		when(this.convertShoppingCartToDto.convert(sc)).thenReturn(scDto);
		
		ShoppingCartDto result = this.service.getShoppingCartByUserId(userId);
		
		assertThat(result).isNotNull();
		assertThat(result).isEqualTo(scDto);
		assertThat(result).isSameAs(scDto);
		
	}
	
	@Test
	public void testFindByUserIdThrowShoppingCartNotFoundException() {
		BigInteger userId = BigInteger.valueOf(100);
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.getShoppingCartByUserId(userId);
		});
		
		assertThat(thrown).isInstanceOf(ShoppingCartNotFoundException.class).hasMessage("Carrinho do usuário id " + userId + " não encontrado.");
		
	}
	
	@Test
	public void testCreateShoppingCartReturnsOk() {
		FormShoppingCartDto formDto = this.utils.getFormShoppingCartDto();
		ShoppingCart scToCreate = this.utils.getShoppingCartToCreateMock();
		scToCreate.setCreationDate(LocalDateTime.now(clock));
		scToCreate.setUpdateDate(LocalDateTime.now(clock));
		ShoppingCart sc = this.utils.getShoppingCartMock();
		ShoppingCartDto scDto = this.utils.getShoppingCartDtoMock();
		
		when(this.consumer.getProduct(formDto.getProductId())).thenReturn(this.utils.getProduct());
		when(this.gateway.save(scToCreate)).thenReturn(sc);
		when(this.convertShoppingCartToDto.convert(sc)).thenReturn(scDto);
		
		ShoppingCartDto created = this.service.createShoppingCart(formDto);
		
		assertThat(created).isNotNull();
		assertThat(formDto.getUserId()).isEqualTo(created.getUserId());
		assertThat(created.getProductDtoList()).anyMatch(p -> p.getId() == formDto.getProductId());
		
	}
	
	@Test
	public void testDeleteShoppingCartReturnsOk() {
		BigInteger userId = BigInteger.valueOf(100);
		ShoppingCart shoppingCart = this.utils.getShoppingCartMock();
		
		when(this.gateway.findByUserId(userId)).thenReturn(Optional.of(shoppingCart));
		
		this.service.deleteShoppingCart(userId);
		
		Mockito.verify(this.gateway).deleteShoppingCart(shoppingCart);
	}
	
	@Test
	public void testDeleteShoppingCartThrowsShoppingCartNotFoundException() {
		BigInteger userId = BigInteger.valueOf(100);
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.deleteShoppingCart(userId);
		});
		
		assertThat(thrown).isInstanceOf(ShoppingCartNotFoundException.class)
							.hasMessage("Não foi possível deletar o carrinho pois o usuário id " + userId + " não encontrado.");
	}
	
	@Test
	public void testUpdateShoppingCartWhenExistingProdcutAddingReturnsOk() {
		FormShoppingCartDto formUpdate = this.utils.getFormShoppingCartDto();
		ShoppingCart scToUpdate = this.utils.getShoppingCartMock();
		ShoppingCartDto scToUpdateDto = this.utils.getShoppingCartDtoMock();
		
		when(this.gateway.findByUserId(formUpdate.getUserId())).thenReturn(Optional.of(scToUpdate));
		when(this.gateway.save(scToUpdate)).thenReturn(scToUpdate);
		when(this.convertShoppingCartToDto.convert(scToUpdate)).thenReturn(scToUpdateDto);
		
		ShoppingCartDto expected = this.service.updateShoppingCartContent(formUpdate);
		
		assertThat(expected).isNotNull();
		assertThat(expected.getProductDtoList()).anyMatch(p -> p.getId() == formUpdate.getProductId());
		
	}
	
	@Test
	public void testUpdateShoppingCartWhenExistingProdcutRemovingReturnsOk() {
		FormShoppingCartDto formUpdate = this.utils.getFormShoppingCartDto();
		formUpdate.setProductAmmount(0);
		ShoppingCart scToUpdate = this.utils.getShoppingCartMock();
		ShoppingCartDto scToUpdateDto = this.utils.getShoppingCartDtoMock();
		
		when(this.gateway.findByUserId(formUpdate.getUserId())).thenReturn(Optional.of(scToUpdate));
		when(this.gateway.save(scToUpdate)).thenReturn(scToUpdate);
		when(this.convertShoppingCartToDto.convert(scToUpdate)).thenReturn(scToUpdateDto);
		
		ShoppingCartDto expected = this.service.updateShoppingCartContent(formUpdate);
		
		assertThat(expected).isNotNull();
		assertThat(expected.getProductDtoList()).anyMatch(p -> p.getId() == formUpdate.getProductId());
	}
	
	@Test
	public void testUpdateShoppingCartWhenNonExistingProdcutReturnsOk() {
		FormShoppingCartDto formUpdate = this.utils.getFormShoppingCartDto();
		ShoppingCart scToUpdate = this.utils.getShoppingCartMock();
		scToUpdate.setProductList(new ArrayList<ShoppingCartProduct>());
		ShoppingCartDto scToUpdateDto = this.utils.getShoppingCartDtoMock();
		ShoppingCartProduct prod = this.utils.getProduct();
		
		when(this.gateway.findByUserId(formUpdate.getUserId())).thenReturn(Optional.of(scToUpdate));
		when(this.consumer.getProduct(formUpdate.getProductId())).thenReturn(prod);
		when(this.gateway.save(scToUpdate)).thenReturn(scToUpdate);
		when(this.convertShoppingCartToDto.convert(scToUpdate)).thenReturn(scToUpdateDto);
		
		ShoppingCartDto expected = this.service.updateShoppingCartContent(formUpdate);
		
		assertThat(expected).isNotNull();
		assertThat(expected.getProductDtoList()).anyMatch(p -> p.getId() == formUpdate.getProductId());
		
	}
	
	@Test
	public void testUpdateShoppingCartThowsShoppingCartNotFoundException() {
		FormShoppingCartDto formUpdate = this.utils.getFormShoppingCartDto();
		
		Throwable thrown = ThrowableAssert.catchThrowable(() -> {
			this.service.updateShoppingCartContent(formUpdate);
		});
		
		assertThat(thrown).isInstanceOf(ShoppingCartNotFoundException.class)
							.hasMessage("Update cancelado. Carrinho de compras do usuário id " + formUpdate.getUserId() + " não encontrado");
	}
	
}
