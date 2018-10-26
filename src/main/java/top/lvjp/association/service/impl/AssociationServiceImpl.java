package top.lvjp.association.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.ActivityInfo;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;
import top.lvjp.association.VO.NewsInfo;
import top.lvjp.association.dto.ActivitiesDTO;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.NewsMapper;
import top.lvjp.association.service.AssociationService;

import java.util.List;

@Service
public class AssociationServiceImpl implements AssociationService {

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public AssociationVO getVOById(Integer id) {
        Association association = associationMapper.selectById(id);
        List<NewsInfo> newsInfos = newsMapper.selectByAssociation(id);
        List<ActivityInfo> current = activityMapper.selectCurrentByAssociation(id);
        List<ActivityInfo> future = activityMapper.selectFutureByAssociation(id);
        List<ActivityInfo> past = activityMapper.selectPastByAssociation(id);
        ActivitiesDTO activitiesDTO = new ActivitiesDTO(current,future,past);
        AssociationVO associationVO = new AssociationVO();
        BeanUtils.copyProperties(association,associationVO);
        associationVO.setNewsInfos(newsInfos);
        associationVO.setActivities(activitiesDTO);
        return associationVO;

    }

    @Override
    public List<AssociationInfo> selectPop(Integer count) {
        return associationMapper.selectPop(count);
    }

    @Override
    public List<AssociationInfo> selectByCategory(String category) {
        return associationMapper.selectByCategory(category);
    }

}
