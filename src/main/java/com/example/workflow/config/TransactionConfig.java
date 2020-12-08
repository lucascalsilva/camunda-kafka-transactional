package com.example.workflow.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.transaction.ChainedKafkaTransactionManager;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionConfig {

    @Bean
    public ChainedKafkaTransactionManager<Object, Object> chainedKafkaTransactionManager(
            KafkaTransactionManager kafkaTransactionManager,
            PlatformTransactionManager transactionManager) {
        return new ChainedKafkaTransactionManager<>(kafkaTransactionManager, transactionManager);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory consumerFactory,
            ChainedKafkaTransactionManager<Object, Object> chainedKafkaTransactionManager) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory);
        factory.getContainerProperties().setTransactionManager(chainedKafkaTransactionManager);
        return factory;
    }
}
