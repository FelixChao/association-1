package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.form.RightsForm;

import java.util.List;

public interface RightsMapper {

    List<RightsInfo> listAll();

    List<RightsInfo> listByAssociation(Integer associationId);

    List<RightsInfo> listByStatus(@Param("status") Integer status, @Param("associationId") Integer associationId);

    RightsVO getById(@Param("rightsId") Integer rightsId, @Param("associationId") Integer associationId);

    int insertRights(RightsForm rightsForm);

    int update(@Param("associationId") Integer associationId, @Param("rightsId") Integer rightsId, @Param("status") Integer status, @Param("solution") String solution);
}
