package harmony.chatservice.kafka;

import harmony.chatservice.dto.response.ChannelEventDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class ChannelEventConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id.channel-event}")
    private String groupName;

    @Bean
    public Map<String, Object> consumerConfigurationsForChannelEvent() {
        Map<String,Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return config;
    }

    @Bean
    public ConsumerFactory<String, ChannelEventDto> consumerFactoryForChannelEvent() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigurationsForChannelEvent(), new StringDeserializer(),
                new JsonDeserializer<>(ChannelEventDto.class, false));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ChannelEventDto> channelEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, ChannelEventDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryForChannelEvent());
        return factory;
    }
}