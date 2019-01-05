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
import top.lvjp.association.enums.PictureIconEnum;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.mapper.PictureMapper;
import top.lvjp.association.mapper.UserMapper;
import top.lvjp.association.service.PictureService;
import top.lvjp.association.util.FileUtil;
import top.lvjp.association.util.RightsTestUtil;

import java.util.List;

import static top.lvjp.association.enums.PictureIconEnum.HEAD_ICON;
import static top.lvjp.association.enums.PictureIconEnum.NOT_ICON;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUtil fileUtil;


    @Override
    public PageVO<PictureInfo> listAll(Integer pageNum, Integer size) {
        PageHelper.startPage(pageNum, size);
        List<PictureInfo> pictureList = pictureMapper.listByAssociation(null);
        PageInfo<PictureInfo> picturePageInfo = new PageInfo<>(pictureList);
        return new PageVO<>(picturePageInfo);
    }

    @Override
    public PageVO<PictureInfo> listByAssociation(String associationId, Integer pageNum, Integer size) {
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
        int count = 0;
        String path;
        Picture picture;
        for (Integer id : pictureIds) {
            picture = pictureMapper.getById(id);
            if (RightsTestUtil.hasRights(associationId, picture.getAssociationId())) continue;
            path = picture.getPicturePath();
            if (pictureMapper.delete(id) == 1){
                fileUtil.deleteFile(path, FileUtil.IMAGE_FILE);
                count++;
            }
        }
        return count;
    }

    @Override
    public List<PictureInfo> listHeadPicture() {
        return pictureMapper.listHeadPicture();
    }

    @Override
    @Transactional
    public void replaceHeadIcon(Integer oldId, Integer newId) {
        pictureMapper.updateIcon(oldId, NOT_ICON.getValue());
        Picture picture = pictureMapper.getById(newId);
        if (picture.getIsIcon() != PictureIconEnum.NOT_ICON.getValue()){
            throw new MyException(ResultEnum.PICTURE_HAS_USED);
        }
        pictureMapper.updateIcon(newId, HEAD_ICON.getValue());
    }

    @Override
    public void updateHeadIcon(Integer pictureId, Integer isIcon) {
        if (isIcon.equals(HEAD_ICON.getValue())){
            Picture picture = pictureMapper.getById(pictureId);
            if (picture.getIsIcon() != PictureIconEnum.NOT_ICON.getValue()){
                throw new MyException(ResultEnum.PICTURE_HAS_USED);
            }
            pictureMapper.updateIcon(pictureId, HEAD_ICON.getValue());
            return;
        }
        if (isIcon.equals(NOT_ICON.getValue())){
            pictureMapper.updateIcon(pictureId, NOT_ICON.getValue());
            return;
        }
        throw new MyException(ResultEnum.PARAMETERS_IS_ERROR);
    }
}
