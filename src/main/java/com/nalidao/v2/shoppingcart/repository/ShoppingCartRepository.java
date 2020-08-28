package com.nalidao.v2.shoppingcart.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nalidao.v2.shoppingcart.domain.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, BigInteger> {

	Optional<ShoppingCart> findByUserId(BigInteger userId);

}
