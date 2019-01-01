package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.entity.Picture;

import java.util.List;

public interface PictureMapper {

//    List<PictureInfo> listAll();

    List<PictureInfo> listByAssociation(@Param("associationId") String associationId);

    Picture getById(Integer id);

    int insert(Picture picture);

    /// 由下面两个替代
//    int updateIcon(@Param("pictureId") Integer pictureId, @Param("isIcon") Integer isIcon);

    int addIconCount(@Param("pictureId") Integer pictureId);

    int reduceIconCount(@Param("pictureId") Integer pictureId);

    int delete(@Param("ids") Integer[] ids, @Param("associationId") String associationId);

//    int check(Integer[] ids, Integer associatinId);
}
