package cn.edu.ecnu.controller;

import cn.edu.ecnu.exception.CustomizeException;
import cn.edu.ecnu.model.enums.ResultCodeEnum;
import cn.edu.ecnu.util.Result;
import cn.edu.ecnu.util.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.ecnu.service.*;

@Api("文件处理 Controller")
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam(name = "file") MultipartFile file) {
        if(file == null) {
            throw new CustomizeException(ResultCodeEnum.PARAM_ILLEGAL);
        }
        String url = fileService.upload(file);
        return ResultGenerator.genSuccessResult(url);
    }
}
