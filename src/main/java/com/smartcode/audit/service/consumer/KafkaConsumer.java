package com.smartcode.audit.service.consumer;

import com.smartcode.audit.model.dto.action.ActionRequestDto;
import com.smartcode.audit.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ActionService actionService;

    @KafkaListener(
            topics = "activityTopic",
            groupId = "activity",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void CreateListener(@Payload(required = false) ActionRequestDto actionRequestDto) {
        actionService.saveAction(actionRequestDto);
    }
}