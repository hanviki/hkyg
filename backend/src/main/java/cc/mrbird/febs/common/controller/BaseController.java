package cc.mrbird.febs.common.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class BaseController {

    protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
        Map<String, Object> rspData = new HashMap<>();
        rspData.put("rows", pageInfo.getRecords());
        rspData.put("total", pageInfo.getTotal());
        return rspData;
    }
    public void printDebug(String methodName, String message) {
        log.debug("methodName:" + methodName + "|message:" + message);
    }

    public void printError(String methodName, String message) {
        log.error("methodName:" + methodName + "|message:" + message);
    }

    public void printDebugException(String methodName, Throwable throwable) {
        log.debug("methodName:" + methodName, throwable);
    }

    public void printErrorException(String methodName, String message, Throwable throwable) {
        log.error("methodName:" + methodName + "|message:" + message, throwable);
    }

}
