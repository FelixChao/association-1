package top.lvjp.association.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.lvjp.association.dto.AssociationInfo;

import java.util.List;

@Mapper
public interface AssociationMapper {

//    AssociationInfo selectAssociationById(Integer id);

    AssociationInfo selectAssociationById(Integer id);

    List<AssociationInfo> selectPopAssociation();

    List<AssociationInfo> selectAssociationByCategory(String category);


}
