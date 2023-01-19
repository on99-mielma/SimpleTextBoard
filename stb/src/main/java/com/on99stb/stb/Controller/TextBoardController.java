package com.on99stb.stb.Controller;


import com.on99stb.stb.Domain.TextBoard;
import com.on99stb.stb.Other.Code;
import com.on99stb.stb.Other.Msg;
import com.on99stb.stb.Other.Result;
import com.on99stb.stb.Service.TextBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/tb")
public class TextBoardController {
    @Autowired
    private TextBoardService textBoardService;

    @PostMapping
    public Result<Boolean> insert(@RequestBody TextBoard textBoard){
        boolean pd = textBoardService.insert(textBoard);
        return new Result<>(pd ? Code.INSERT_OK : Code.INSERT_ERR, pd ? Msg.NORMAL_SUCCESSFUL : Msg.NORMAL_FAILED, pd);
    }

    @DeleteMapping
    public Result<Boolean> delete(@RequestBody TextBoard textBoard){
        boolean pd = textBoardService.delete(textBoard);
        return new Result<>(pd ? Code.DELETE_OK : Code.DELETE_ERR, pd ? Msg.NORMAL_SUCCESSFUL : Msg.NORMAL_FAILED, pd);
    }

    @PutMapping
    public Result<Boolean> update(@RequestBody TextBoard textBoard){
        boolean pd = textBoardService.update(textBoard);
        return new Result<>(pd ? Code.UPDATE_OK : Code.UPDATE_ERR, pd ? Msg.NORMAL_SUCCESSFUL : Msg.NORMAL_FAILED, pd);
    }

    @GetMapping("/{id}")
    public Result<TextBoard> selectById(@PathVariable Long id){
        TextBoard textBoard = textBoardService.setlectById(id);
        Integer code = textBoard!=null?Code.SELECT_OK:Code.SELECT_ERR;
        String msg = textBoard!=null?Msg.NORMAL_SUCCESSFUL:Msg.NORMAL_FAILED;
        return new Result<>(code, msg, textBoard);
    }

    @GetMapping
    public Result<List<TextBoard>> selectAll(){
        List<TextBoard> textBoardList = textBoardService.selectAll();
        Integer code = textBoardList!=null?Code.SELECT_OK:Code.SELECT_ERR;
        String msg = textBoardList!=null?Msg.NORMAL_SUCCESSFUL:Msg.NORMAL_FAILED;
        return new Result<>(code,msg,textBoardList);
    }

    @GetMapping("/count")
    public Result<Long> countTextBoard(){
        Long ans = textBoardService.CountBoard();
        return new Result<>(Code.SELECT_OK,Msg.NORMAL_SUCCESSFUL,ans);
    }
}
