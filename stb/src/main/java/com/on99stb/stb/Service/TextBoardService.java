package com.on99stb.stb.Service;

import com.on99stb.stb.Domain.TextBoard;

import java.util.List;

public interface TextBoardService {
    public boolean insert(TextBoard textBoard);
    public boolean delete(TextBoard textBoard);
    public boolean update(TextBoard textBoard);
    public TextBoard setlectById(Long id);
    public List<TextBoard> selectAll();
    public Long CountBoard();
}
