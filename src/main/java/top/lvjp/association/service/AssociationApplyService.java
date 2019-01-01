package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.form.QueryForm;

public interface AssociationApplyService {

    int insertApply(AssociationApplyForm apply);

    PageVO<AssociationApply> listByAssociation(String id, Integer pageNum, Integer size);

    PageVO<AssociationApply> query(QueryForm queryForm, Integer pageNum, Integer size);

    int deleteAll(String associationId);

    int deleteByIds(Integer[] ids, String associationId);
}
