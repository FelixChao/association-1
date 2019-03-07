package top.lvjp.association.service;

import top.lvjp.association.VO.PageVO;
import top.lvjp.association.VO.RightsInfo;
import top.lvjp.association.VO.RightsVO;
import top.lvjp.association.form.RightsForm;

public interface RightsService {

    RightsVO getById(Integer rightsId, String associationId, Integer userType);

    PageVO<RightsInfo> listAll(Integer pageNum, Integer size);

    PageVO<RightsInfo> listByAssociation(String associationId, Integer pageNum, Integer size);

    PageVO<RightsInfo> listByStatus(Integer status, String associationId, Integer pageNum, Integer size);

    int insertRights(RightsForm rightsForm);

    int update(String associationId, Integer rightsId, Integer status, String solution, Integer userType);

    int delete(Integer rightsId);

}
