package top.lvjp.association.mapper;

import top.lvjp.association.VO.AssociationInfo;

import java.util.List;

public interface AssociationMapper {

    AssociationInfo selectById(Integer id);

    List<AssociationInfo> selectPop(Integer count);

    List<AssociationInfo> selectByCategory(String category);


}
