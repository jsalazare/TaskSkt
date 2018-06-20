package org.common;


import com.rabbitmq.client.Channel;
import org.common.configuration.Configurations;
import org.common.dto.ProductDTO;
import org.common.interfaces.ChannelFactory;
import org.common.util.SerializationUtilities;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonTest {

    @Autowired
    private Configurations config;

    @Autowired
    private ChannelFactory channelFactory;

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Test
    public void serializeUtilitiesGetBytesShouldBringBytes() {
        String message = "hello world";
        byte[] body = SerializationUtilities.getBytes(message);


        assertThat(body).isInstanceOf(byte[].class);
    }

    @Test
    public void serializeUtilitiesShouldSerealize() {
        String message = "hello world";
        byte[] body = SerializationUtilities.getBytes(message);
        String resultMessage = (String) SerializationUtilities.fromBytes(body);

        assertThat(resultMessage).isEqualTo(message);
    }

    @Test
    public void channelFactoryShouldChannel() throws Exception {
        assertThat(channelFactory.getNewChannel()).isInstanceOf(Channel.class);
    }

    @Test
    public void configurationShouldBringCorrectInformation() {
        assertThat(config.getHost()).isEqualTo("localhost");
        assertThat(config.getPassword()).isEqualTo("admin");
        assertThat(config.getUsername()).isEqualTo("admin");
        assertThat(config.getQueueManagnent()).isEqualTo("microservice-management");
        assertThat(config.getQueueMicroservice()).isEqualTo("management-microservice");
        assertThat(config.getRpcQueue()).isEqualTo("rpc_queue");
    }

    @Test
    public void productDTOShouldInstanciate() throws Exception {
        ProductDTO p = new ProductDTO();
        p.setId(0);
        p.setHeight(1.0f);
        p.setLength(1);
        p.setName("name1");
        p.setWeight(1.0f);
        p.setWidth(1);

        assertThat(p.getHeight()).isEqualTo(1.0f);
        assertThat(p.getId()).isEqualTo(0);
        assertThat(p.getLength()).isEqualTo(1);
        assertThat(p.getName()).isEqualTo("name1");
        assertThat(p.getWeight()).isEqualTo(1.0f);
        assertThat(p.getWidth()).isEqualTo(1);

    }

    @Test
    public void applicationTest() throws Exception {
        App.main(new String[]{});
    }


}
