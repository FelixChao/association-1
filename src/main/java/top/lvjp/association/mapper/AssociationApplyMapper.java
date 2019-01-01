package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Param;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.form.QueryForm;

import java.util.List;

public interface AssociationApplyMapper {

    int insertApply(AssociationApplyForm apply);

    List<AssociationApply> selectByAssociationAndStudent(@Param("associationId") String associationId,
                                                         @Param("studentNumber") String studentNumber);

    List<AssociationApply> listByAssociation(String associationId);

    int deleteAll(String associationId);

    List<AssociationApply> query(@Param("query") QueryForm query);

    int deleteByIds(@Param("ids") Integer[] ids, @Param("associationId") String associatoinId);
}
