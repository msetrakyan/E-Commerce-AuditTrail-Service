package com.smartcode.audit.service.action;


import com.smartcode.audit.model.dto.action.ActionRequestDto;

public interface ActionService {

    void saveAction(ActionRequestDto actionRequestDto);

}
