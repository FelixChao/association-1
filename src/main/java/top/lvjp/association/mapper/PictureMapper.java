package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.entity.Picture;

import java.util.List;

public interface PictureMapper {

//    List<PictureVO> listAll();

    List<PictureVO> listByAssociation(@Param("associationId") Integer associationId);

    Picture getById(Integer id);

    int insert(Picture picture);

    int updateIcon(@Param("pictureId") Integer pictureId, @Param("associationId") Integer associationId, @Param("isIcon") Integer isIcon);

    int delete(@Param("ids") Integer[] ids, @Param("associationId") Integer associationId);

//    int check(Integer[] ids, Integer associatinId);
}
