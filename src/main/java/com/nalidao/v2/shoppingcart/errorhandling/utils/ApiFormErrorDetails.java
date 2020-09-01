package com.nalidao.v2.shoppingcart.errorhandling.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiFormErrorDetails {

	private String field;
	private String message;
}
