package cc.mrbird.febs.hkgf.controller;

import cc.mrbird.febs.common.controller.BaseController;

import cc.mrbird.febs.hkgf.entity.response.BaseResponse;
import cc.mrbird.febs.hkgf.service.MxService;
import cc.mrbird.febs.hkgf.utils.ResultCodeEnum;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExtendController extends BaseController {
    @Autowired
    private MxService mxService;


    public ExtendController() {
    }


    @GetMapping({"/updateBaseData"})
    public ResponseEntity<String> reloadData() {
        BaseResponse response = new BaseResponse();
        int result = this.mxService.queryAllMx();
        if (result > 0) {
            response.setResult(ResultCodeEnum.SUCCESS);
        } else {
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/getUpdate"})
    private ResponseEntity<byte[]> downloadFile() throws IOException {
        String filePath = "D:/FILE/gfjsData.jar";
        File file = new File(filePath);
        byte[] buffer = null;
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] b = new byte[1024];

        int n;
        while((n = fis.read(b)) != -1) {
            bos.write(b, 0, n);
        }

        fis.close();
        bos.close();
        byte[] buffer2 = bos.toByteArray();
        return ResponseEntity.ok().body(buffer2);
    }
}