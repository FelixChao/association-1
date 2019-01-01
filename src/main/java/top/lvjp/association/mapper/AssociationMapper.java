package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.PictureInfo;
import top.lvjp.association.entity.Association;
import top.lvjp.association.form.AssociationForm;

import java.util.List;

public interface AssociationMapper {

    Association getById(String id);

    List<AssociationInfo> listPop(Integer count);

    List<AssociationInfo> listByCategory(String category);

    List<AssociationInfo> getAssociationNames();

    int addApply(String id);

    List<PictureInfo> listAssociationIcon();

    int updateAssociationIcon(@Param("pictureId") Integer pictureId, @Param("picturePath") String picturePath, @Param("associationId") String associationId);

    int updateApplyStatus(@Param("enable") Integer enable, @Param("associationId") String associationId);

    int updateAssociation(AssociationForm form);

    int updateAssociationDesc(@Param("associationId") String associationId, @Param("description") String description);

    int reduceApply(@Param("associationId") String associationId, @Param("count") int count);
}
