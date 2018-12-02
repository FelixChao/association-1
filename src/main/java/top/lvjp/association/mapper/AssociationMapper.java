package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.PictureVO;
import top.lvjp.association.entity.Association;

import java.util.List;

public interface AssociationMapper {

    Association selectById(Integer id);

    List<AssociationInfo> selectPop(Integer count);

    List<AssociationInfo> selectByCategory(String category);

    List<AssociationInfo> getAssociationNames();

    int addApply(Integer id);

    List<PictureVO> listAssociationIcon();

    int updateAssociationIcon(@Param("pictureId") Integer picture_id, @Param("picturePath") String picture_path, @Param("associationId") Integer associationId);

    int updateApplyStatus(@Param("enable") Integer enable, @Param("associationId") Integer associationId);
}
