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

//    int addIconCount(@Param("pictureId") Integer pictureId);
//
//    int reduceIconCount(@Param("pictureId") Integer pictureId);

    int delete(@Param("id") Integer ids);

    List<PictureInfo> listHeadPicture();

    int updateIcon(@Param("id") Integer id, @Param("icon") int icon);

}
