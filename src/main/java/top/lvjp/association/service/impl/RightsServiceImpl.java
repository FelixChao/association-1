package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.constant.SessionConstant;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.Rights;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.RightsForm;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.RightsMapper;
import top.lvjp.association.service.RightsService;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Override
    public RightsVO getById(Integer rightsId, String associationId) {
        Rights rights = rightsMapper.getById(rightsId);
        if (!associationId.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && !associationId.equals(rights.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        Association association = associationMapper.getById(rights.getAssociationId());
        RightsVO rightsVO = new RightsVO();
        BeanUtils.copyProperties(rights, rightsVO);
        rightsVO.setAssociationName(association.getAssociationName());
        return rightsVO;
    }

    @Override
    public PageVO<RightsInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listAll();
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<>(rightsInfoPageInfo);
    }

    @Override
    public PageVO<RightsInfo> listByAssociation(String associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listByAssociation(associationId);
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<>(rightsInfoPageInfo);
    }

    @Override
    public PageVO<RightsInfo> listByStatus(Integer status, String associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listByStatus(status, associationId);
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<>(rightsInfoPageInfo);
    }

    @Override
    @Transactional
    public int insertRights(RightsForm rightsForm) {
        Association association = associationMapper.getById(rightsForm.getAssociationId());
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        return rightsMapper.insertRights(rightsForm);
    }

    @Override
    @Transactional
    public int update(String associationId, Integer rightsId, Integer status, String solution) {
        Rights rights = rightsMapper.getById(rightsId);
        if (!associationId.equals(SessionConstant.ROOT_ASSOCIATION_VALUE)
                && !associationId.equals(rights.getAssociationId())){
            throw new MyException(ResultEnum.RIGHTS_NOT_SATISFY);
        }
        return rightsMapper.update(rightsId, status, solution);
    }

    @Override
    public int delete(Integer rightsId) {
        return rightsMapper.delete(rightsId);
    }
}
