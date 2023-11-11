package com.smartcode.audit.service.action.impl;

import com.smartcode.audit.mapper.ActionMapper;
import com.smartcode.audit.model.dto.action.ActionRequestDto;
import com.smartcode.audit.model.entity.ActionEntity;
import com.smartcode.audit.repository.ActionRepository;
import com.smartcode.audit.service.action.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final ActionMapper actionMapper;

    @Override
    @Transactional
    public void saveAction(ActionRequestDto actionRequestDto) {
        ActionEntity entity = actionMapper.toEntity(actionRequestDto);
        actionRepository.save(entity);
    }
}
