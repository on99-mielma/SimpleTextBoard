package com.on99stb.stb.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.on99stb.stb.DAO.TextBoardDAO;
import com.on99stb.stb.Domain.TextBoard;
import com.on99stb.stb.Service.TextBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextBoardServiceImpl implements TextBoardService {

    @Autowired
    private TextBoardDAO textBoardDAO;

    @Override
    public boolean insert(TextBoard textBoard) {
        return textBoardDAO.insert(textBoard)>0;
    }

    @Override
    public boolean delete(TextBoard textBoard) {
        return textBoardDAO.deleteById(textBoard)>0;
    }

    @Override
    public boolean update(TextBoard textBoard) {
        return textBoardDAO.updateById(textBoard)>0;
    }

    @Override
    public TextBoard setlectById(Long id) {
        return textBoardDAO.selectById(id);
    }

    @Override
    public List<TextBoard> selectAll() {
        QueryWrapper<TextBoard> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("date");
        return textBoardDAO.selectList(queryWrapper);
    }

    @Override
    public Long CountBoard() {
        QueryWrapper<TextBoard> queryWrapper = new QueryWrapper<>();
        return textBoardDAO.selectCount(queryWrapper);
    }
}
