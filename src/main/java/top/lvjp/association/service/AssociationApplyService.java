package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.form.QueryForm;

public interface AssociationApplyService {

    void insertApply(AssociationApplyForm apply);

    PageVO<AssociationApply> listByAssociation(Integer id, Integer pageNum, Integer size);

    PageVO<AssociationApply> query(QueryForm queryForm, Integer associationId, Integer pageNum, Integer size);

    int delete(Integer associationId);
}
