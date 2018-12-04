package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.RightsForm;
import top.lvjp.association.mapper.RightsMapper;
import top.lvjp.association.service.RightsService;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService {

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public RightsVO getById(Integer rightsId, Integer associationId) {
        if (associationId == 0){
            associationId = null;
        }
        return rightsMapper.getById(rightsId, associationId);
    }

    @Override
    public PageVO<RightsInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listAll();
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<RightsInfo>(rightsInfoPageInfo);
    }

    @Override
    public PageVO<RightsInfo> listByAssociation(Integer associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listByAssociation(associationId);
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<>(rightsInfoPageInfo);
    }

    @Override
    public PageVO<RightsInfo> listByStatus(Integer status, Integer associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<RightsInfo> rightsInfos = rightsMapper.listByStatus(status, associationId);
        PageInfo<RightsInfo> rightsInfoPageInfo = new PageInfo<>(rightsInfos);
        return new PageVO<>(rightsInfoPageInfo);
    }

    @Override
    public int insertRights(RightsForm rightsForm) {
        return rightsMapper.insertRights(rightsForm);
    }

    @Override
    public int update(Integer associationId, Integer rightsId, Integer status, String solution) {
        int count = rightsMapper.update(associationId, rightsId, status, solution);
        if (count == 0) throw new MyException(ResultEnum.OPERATE_IS_FAIL);
        return count;
    }
}
