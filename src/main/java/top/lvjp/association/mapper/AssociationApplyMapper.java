package top.lvjp.association.mapper;

import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;

import java.util.List;

public interface AssociationApplyMapper {

    int insertApply(AssociationApplyForm apply);

    List<AssociationApply> selectByAssociation(Integer id);
}
