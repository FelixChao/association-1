package top.lvjp.association.service;

import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;

import java.util.List;

public interface AssociationApplyService {

    void insertApply(AssociationApplyForm apply);

    List<AssociationApply> selectByAssociation(Integer id);
}
