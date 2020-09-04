package com.nalidao.v2.shoppingcart.utils;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class TimeUtils {

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
