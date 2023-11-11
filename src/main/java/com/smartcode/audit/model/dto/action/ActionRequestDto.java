package com.smartcode.audit.model.dto.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ActionRequestDto implements Serializable {

    private String actionType;

    private String entityType;

    private LocalDateTime actionDate;

    private Integer userId;

}
