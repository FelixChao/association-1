package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.entity.Rights;
import top.lvjp.association.form.RightsForm;

import java.util.List;

public interface RightsMapper {

    List<RightsInfo> listAll();

    List<RightsInfo> listByAssociation(String associationId);

    List<RightsInfo> listByStatus(@Param("status") Integer status, @Param("associationId") String associationId);

    Rights getById(@Param("rightsId") Integer rightsId);

    int insertRights(RightsForm rightsForm);

    int update(@Param("rightsId") Integer rightsId, @Param("status") Integer status, @Param("solution") String solution);

    int delete(Integer rightsId);
}
