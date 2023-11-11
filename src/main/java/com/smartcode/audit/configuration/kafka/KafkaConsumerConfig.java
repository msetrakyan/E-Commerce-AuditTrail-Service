package com.smartcode.audit.configuration.kafka;

import com.smartcode.audit.model.dto.action.ActionRequestDto;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value(value = "${spring.kafka.bootstrap-server}")
    private String bootstrapAddress;
    @Value(value = "${kafka.activity.groupId}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ActionRequestDto> kafkaListenerContainerFactory() {
        var container = new ConcurrentKafkaListenerContainerFactory<String, ActionRequestDto>();
        container.setConsumerFactory(consumerFactory());
        return container;
    }

    @Bean
    public ConsumerFactory<String, ActionRequestDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                getConsumerProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(ActionRequestDto.class));
    }
    private Map<String, Object> getConsumerProps() {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.KEY_DEFAULT_TYPE, String.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ActionRequestDto.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        props.put(JsonDeserializer.TYPE_MAPPINGS, "audit:com.smartcode.audit.model.dto.action.ActionRequestDto");
        return props;
    }
}