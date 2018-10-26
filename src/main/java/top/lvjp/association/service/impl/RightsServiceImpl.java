package top.lvjp.association.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.form.RightsForm;
import top.lvjp.association.mapper.RightsMapper;
import top.lvjp.association.service.RightsService;

@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public void insertRights(RightsForm rightsForm) {
        rightsMapper.insertRights(rightsForm);
    }
}
