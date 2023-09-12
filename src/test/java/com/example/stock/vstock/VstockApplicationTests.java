package com.example.stock.vstock;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class VstockApplicationTests {

	@Test
	void contextLoads() {

	}


	@Ignore
	void getDetails(){
		assertTrue(false);
	}

}
