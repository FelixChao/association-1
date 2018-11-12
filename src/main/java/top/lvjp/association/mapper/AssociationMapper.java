package top.lvjp.association.mapper;

import top.lvjp.association.VO.AssociationInfo;
import top.lvjp.association.entity.Association;

import java.util.List;

public interface AssociationMapper {

    Association selectById(Integer id);

    List<AssociationInfo> selectPop(Integer count);

    List<AssociationInfo> selectByCategory(String category);

    List<AssociationInfo> getAssociationNames();

    int addApply(Integer id);

}
