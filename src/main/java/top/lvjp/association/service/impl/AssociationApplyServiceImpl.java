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
    public void insertApply(AssociationApplyForm apply) {
        Integer id = apply.getAssociationId();
        Association association = associationMapper.selectById(id);
        if (association == null) {
            throw new MyException(ResultEnum.ASSOCIATION_NOT_EXISTS);
        }
        else if(association.getApplyEnable() == 0){
            throw new MyException(ResultEnum.NOW_NOT_APPLY);
        }
        List<AssociationApply> associationApplies = associationApplyMapper.selectByAssociationAndStudent(id,apply.getStudentNumber());
        if (!associationApplies.isEmpty()) {
            throw new MyException(ResultEnum.APPLY_ALREADY_EXISTS);
        }
        associationApplyMapper.insertApply(apply);
        associationMapper.addApply(id);
    }

    @Override
    public PageVO<AssociationApply> listByAssociation(Integer id, Integer pageNum, Integer size){
        PageHelper.startPage(pageNum, size);
        List<AssociationApply> associationApplies = associationApplyMapper.listByAssociation(id);
        PageInfo<AssociationApply> associationApplyPageInfo = new PageInfo<>(associationApplies);
        return new PageVO<AssociationApply>(associationApplyPageInfo);
    }

    @Override
    public PageVO<AssociationApply> query(QueryForm queryForm, Integer associationId, Integer pageNum, Integer size) {
        queryForm.setStudentName(queryForm.getStudentName() == null ? null : "%" + queryForm.getStudentName() + "%");
        queryForm.setStudentNumber(queryForm.getStudentNumber() == null ? null : "%"
                + queryForm.getStudentNumber() + "%");
        queryForm.setAcademy(queryForm.getAcademy() == null ? null : "%" + queryForm.getAcademy() + "%");
        PageHelper.startPage(pageNum,size);
        List<AssociationApply> associationApplies = associationApplyMapper.query(queryForm,associationId);
        PageInfo<AssociationApply> associationApplyPageInfo = new PageInfo<>(associationApplies);
        return new PageVO<AssociationApply>(associationApplyPageInfo);
    }

    @Override
    public int delete(Integer associationId) {
        return associationApplyMapper.delete(associationId);
    }
}
