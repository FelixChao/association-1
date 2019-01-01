package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.Picture;
import top.lvjp.association.entity.User;
import top.lvjp.association.mapper.ActivityMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.service.PictureService;

import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public PageVO<PictureInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureInfo> pictureList = pictureMapper.listByAssociation(null);
        PageInfo<PictureInfo> picturePageInfo = new PageInfo<>(pictureList);
        return new PageVO<>(picturePageInfo);
    }

    @Override
    public PageVO<PictureInfo> listByAssciation(String associationId, Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureInfo> pictureList = pictureMapper.listByAssociation(associationId);
        PageInfo<PictureInfo> picturePageInfo = new PageInfo<>(pictureList);
        return new PageVO<>(picturePageInfo);
    }

    @Override
    public PageVO<PictureInfo> listAssociationIcon(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureInfo> pictureInfos = associationMapper.listAssociationIcon();
        PageInfo<PictureInfo> iconPageInfo = new PageInfo<>(pictureInfos);
        return new PageVO<>(iconPageInfo);
    }

//    @Override
//    public PageVO<PictureInfo> listActivityIcon(Integer pageNum, Integer size) {
//        PageHelper.startPage(pageNum, size);
//        List<PictureInfo> pictureInfos = activityMapper.listActivityIcon();
//        PageInfo<PictureInfo> iconPageInfo = new PageInfo<>(pictureInfos);
//        return new PageVO<>(iconPageInfo);
//    }

    @Override
    public PictureVO getById(Integer id) {
        Picture picture = pictureMapper.getById(id);
        PictureVO pictureVO = new top.lvjp.association.VO.PictureVO();
        BeanUtils.copyProperties(picture, pictureVO);
        Association association = associationMapper.getById(picture.getAssociationId());
        if (association != null) {
            pictureVO.setAssociationName(association.getAssociationName());
        }
        User user = userMapper.getById(picture.getUserId());
        if (user != null) {
            pictureVO.setUserName(user.getUserName());
        }
        return pictureVO;
    }

    @Override
    public int insert(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    @Transactional
    public int delete(Integer[] pictureIds, String associationId) {
        int length = pictureIds.length;
        int count = pictureMapper.delete(pictureIds, associationId);
        return count;
    }

}
