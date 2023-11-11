package com.smartcode.audit.mapper;


import com.smartcode.audit.model.dto.action.ActionRequestDto;
import com.smartcode.audit.model.entity.ActionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    ActionEntity toEntity(ActionRequestDto actionRequestDto);
}
