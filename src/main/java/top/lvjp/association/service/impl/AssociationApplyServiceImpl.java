package top.lvjp.association.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.lvjp.association.VO.PageVO;
import top.lvjp.association.entity.Association;
import top.lvjp.association.entity.AssociationApply;
import top.lvjp.association.enums.ResultEnum;
import top.lvjp.association.exception.MyException;
import top.lvjp.association.form.AssociationApplyForm;
import top.lvjp.association.form.QueryForm;
import top.lvjp.association.mapper.AssociationApplyMapper;
import top.lvjp.association.mapper.AssociationMapper;
import top.lvjp.association.service.AssociationApplyService;

import java.util.List;

@Service
public class AssociationApplyServiceImpl implements AssociationApplyService {

    @Autowired
    private AssociationApplyMapper associationApplyMapper;

    @Autowired
    private AssociationMapper associationMapper;

    @Override
    @Transactional
    public int insertApply(AssociationApplyForm apply) {
        String id = apply.getAssociationId();
        Association association = associationMapper.getById(id);
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        if(association.getApplyEnable() == 0){
            throw new MyException(ResultEnum.NOW_NOT_APPLY);
        }
        List<AssociationApply> associationApplies = associationApplyMapper.selectByAssociationAndStudent(id,apply.getStudentNumber());
        if (!associationApplies.isEmpty()) {
            throw new MyException(ResultEnum.APPLY_ALREADY_EXISTS);
        }
        associationMapper.addApply(id);
        return associationApplyMapper.insertApply(apply);
    }

    @Override
    public PageVO<AssociationApply> listByAssociation(String id, Integer pageNum, Integer size){
        PageHelper.startPage(pageNum, size);
        List<AssociationApply> associationApplies = associationApplyMapper.listByAssociation(id);
        PageInfo<AssociationApply> associationApplyPageInfo = new PageInfo<>(associationApplies);
        return new PageVO<>(associationApplyPageInfo);
    }

    @Override
    public PageVO<AssociationApply> query(QueryForm queryForm, Integer pageNum, Integer size) {
        queryForm.setStudentName(queryForm.getStudentName() == null ? null : "%" + queryForm.getStudentName() + "%");
        queryForm.setStudentNumber(queryForm.getStudentNumber() == null ? null : "%"
                + queryForm.getStudentNumber() + "%");
        queryForm.setAcademy(queryForm.getAcademy() == null ? null : "%" + queryForm.getAcademy() + "%");
        queryForm.setQq(queryForm.getQq() == null ? null : "%" + queryForm.getQq() + "%");
        queryForm.setPhone(queryForm.getPhone() == null ? null : "%" + queryForm.getPhone() + "%");
        queryForm.setSpeciality(queryForm.getSpeciality() == null ? null : "%" + queryForm.getSpeciality() + "%");
        queryForm.setDepartment(queryForm.getDepartment() == null ? null : "%" + queryForm.getDepartment() + "%");
        PageHelper.startPage(pageNum,size);
        List<AssociationApply> associationApplies = associationApplyMapper.query(queryForm);
        PageInfo<AssociationApply> associationApplyPageInfo = new PageInfo<>(associationApplies);
        return new PageVO<>(associationApplyPageInfo);
    }

    @Override
    @Transactional
    public int deleteAll(String associationId) {
        int count = associationApplyMapper.deleteAll(associationId);
        associationMapper.reduceApply(associationId, count);
        return count;
    }

    @Override
    public int deleteByIds(Integer[] ids, String associationId) {
        int count = associationApplyMapper.deleteByIds(ids, associationId);
        associationMapper.reduceApply(associationId, count);
        return count;
    }
}
