package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.form.RightsForm;

public interface RightsService {

    RightsVO getById(Integer rightsId, Integer associationId);

    PageVO<RightsInfo> listAll(Integer pageNum, Integer size);

    PageVO<RightsInfo> listByAssociation(Integer associationId, Integer pageNum, Integer size);

    PageVO<RightsInfo> listByStatus(Integer status, Integer associationId, Integer pageNum, Integer size);

    int insertRights(RightsForm rightsForm);

    int update(Integer associationId, Integer rightsId, Integer status, String solution);

}
