package top.lvjp.association.service;

import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.VO.AssociationVO;
import top.lvjp.association.form.AssociationForm;

import java.util.List;

public interface AssociationService {

    /**
     * 通过 id 查询社团
     * @param id
     * @return 返回社团信息,AssociationInfo
     */
    AssociationVO getVOById(String id);

    /**
     * 查询热门社团
     * @param count 查询数量
     * @return List<AssociationInfo>
     */
    List<AssociationInfo> selectPop(Integer count);

    /**
     * 查询某一类社团
     * @param category
     * @return List<AssociationInfo>
     */
    List<AssociationInfo> selectByCategory(String category);

    /**
     * 获取所有社团的名字
     * @return
     */
    List<AssociationInfo> getAssociationNames();

    int updateApplyStatus(Integer status, String associationId);

    int updateAssociationIcon(String associationId, Integer pictureId);

    int updateAssociationDesc(String associationId, String description);

    int updateAssociation(AssociationForm form);

    AssociationForm getAssociationForm(String associationId);
}
