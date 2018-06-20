package org.common;


import org.common.configuration.Configurations;
import org.common.util.SerializationUtilities;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class CommonTest {


	private Configurations config;

	@BeforeClass
	public static void beforeClass() throws Exception {

	}

	@Test
	public void serializeUtilitiesGetBytesShouldBringBytes() {
		String message = "hello world";
		byte [] body = SerializationUtilities.getBytes(message);


		assertThat(body).isInstanceOf(byte [].class);
	}

	@Test
	public void serializeUtilitiesShouldSerealize() {
		String message = "hello world";
		byte [] body = SerializationUtilities.getBytes(message);
		String resultMessage = (String) SerializationUtilities.fromBytes(body);

		assertThat(resultMessage).isEqualTo(message);
	}

	



    @Test
    public void productListShouldAddAttributeMyProducts() throws InterruptedException, ExecutionException, TimeoutException, IOException {

    }
}
