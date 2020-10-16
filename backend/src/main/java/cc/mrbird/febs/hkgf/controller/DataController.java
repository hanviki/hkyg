package cc.mrbird.febs.hkgf.controller;

import cc.mrbird.febs.common.controller.BaseController;
import lombok.extern.slf4j.Slf4j;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import cc.mrbird.febs.hkgf.config.Constant;
import cc.mrbird.febs.hkgf.entity.CyxjInfoEntity;
import cc.mrbird.febs.hkgf.entity.DataInsertResult;
import cc.mrbird.febs.hkgf.entity.GfjsZzd;
import cc.mrbird.febs.hkgf.entity.MXEntity;
import cc.mrbird.febs.hkgf.entity.ReceiptRolls;
import cc.mrbird.febs.hkgf.entity.YpcdEntity;
import cc.mrbird.febs.hkgf.entity.request.BaseDataEntity;
import cc.mrbird.febs.hkgf.entity.request.BaseRequest;
import cc.mrbird.febs.hkgf.entity.request.FlagChangeRequest;
import cc.mrbird.febs.hkgf.entity.request.MxRequest;
import cc.mrbird.febs.hkgf.entity.response.BaseResponse;
import cc.mrbird.febs.hkgf.entity.response.CYJXInfoEntity;
import cc.mrbird.febs.hkgf.entity.response.ChargeListResponse;
import cc.mrbird.febs.hkgf.entity.response.CountResponse;
import cc.mrbird.febs.hkgf.entity.response.HzBean;
import cc.mrbird.febs.hkgf.entity.response.QueryDailyResponse;
import cc.mrbird.febs.hkgf.entity.response.QueryNoResponse;
import cc.mrbird.febs.hkgf.entity.response.UploadDataResponse;
import cc.mrbird.febs.hkgf.entity.response.UserDataResponse;
import cc.mrbird.febs.hkgf.service.MxService;
import cc.mrbird.febs.hkgf.service.ZzdService;
import cc.mrbird.febs.hkgf.utils.DateUtils;
import cc.mrbird.febs.hkgf.utils.HttpUtils;
import cc.mrbird.febs.hkgf.utils.ResultCodeEnum;
import cc.mrbird.febs.hkgf.utils.XmlConvertEntityUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class DataController extends BaseController {

    @Autowired
    private ZzdService zzdService;
    @Autowired
    private MxService mxService;
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private DateUtils dateUtils;

    public DataController() {
    }

    @PostMapping({"/uploadData"})
    public ResponseEntity<String> uploadData(@RequestBody String data) {
        String methodName = "uploadData";
        this.printDebug(methodName, data);
        UploadDataResponse response = new UploadDataResponse();
        MxRequest request = null;

        try {
            request = (MxRequest)(new Gson()).fromJson(data, MxRequest.class);
            if (StringUtils.isEmpty(request.getSFZH()) || StringUtils.isEmpty(request.getZYH()) || request.getRESULTSET().size() == 0) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            GfjsZzd zzd = this.zzdService.queryInfoByZYH(request.getZYH());
            if (zzd == null) {
                response.setResult(ResultCodeEnum.ZYH_NOT_EXIST);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            ReceiptRolls receiptRolls = new ReceiptRolls();
            Date date = this.dateUtils.parseSJDH2Datetime(((MXEntity)request.getRESULTSET().get(0)).getSJDH());
            receiptRolls.setReceiptNo(zzd.getZzdNo() + this.dateUtils.createSJDH(date));
            receiptRolls.setUserName(zzd.getUserName());
            receiptRolls.setCardNo(zzd.getCardNo());
            receiptRolls.setCardId(zzd.getJzCardNo());
            receiptRolls.setReceiptCreateTime(this.dateUtils.parseSJDH2Datetime(((MXEntity)request.getRESULTSET().get(0)).getSJDH()));
            receiptRolls.setIntoTime(new Date());
            receiptRolls.setZzdNo(zzd.getZzdNo());
            receiptRolls.setIntoHospitalId(zzd.getReceiveHospitalId());
            receiptRolls.setAdmissionNumber(zzd.getRegisterNo());
            receiptRolls.setReceiptType(zzd.getZzdType());
            receiptRolls.setStartDate(this.dateUtils.parseSJDH2Datetime(((MXEntity)request.getRESULTSET().get(0)).getSJDH()));
            receiptRolls.setEndDate(this.dateUtils.parseSJDH2Datetime(((MXEntity)request.getRESULTSET().get(0)).getSJDH()));
            receiptRolls.setDateNum(1);
            receiptRolls.setOutHospitalId(zzd.getCreateHospitalId());
            receiptRolls.setStatus(0);
            this.zzdService.insertRolls(receiptRolls);
            DataInsertResult insertResult = this.zzdService.syncMxItem(((MXEntity)request.getRESULTSET().get(0)).getSJDH(), zzd.getZzdNo(), request.getRESULTSET());
            switch(insertResult.getResultCode()) {
                case -2:
                    response.setResult(ResultCodeEnum.CHECK_NOT_OK);
                    this.printDebug(methodName, response.toString());
                    return ResponseEntity.ok(response.toString());
                case -1:
                    response.setResult(ResultCodeEnum.DATA_NOT_EXIST);
                    response.setNotExistData(this.zzdService.queryUserUnExistData(((MXEntity)request.getRESULTSET().get(0)).getHZID()));
                    this.printDebug(methodName, response.toString());
                    return ResponseEntity.ok(response.toString());
                default:
                    response.setResult(ResultCodeEnum.SUCCESS);
            }
        } catch (JsonParseException var9) {
            this.printDebugException(methodName, var9);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var10) {
            this.printDebugException(methodName, var10);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/queryJsData"})
    public ResponseEntity<String> queryJsData() {
        BaseRequest request = null;
        String methodName = "queryJsData";
        this.printDebug(methodName, "");
        QueryDailyResponse response = new QueryDailyResponse();

        try {
            List<HzBean> list = this.zzdService.queryUnreadSum();
            response.setList(list);
            response.setResult(ResultCodeEnum.SUCCESS);
        } catch (JsonParseException var5) {
            this.printDebugException(methodName, var5);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/readFlagChange"})
    public ResponseEntity<String> readFlagChange(@RequestBody String data) {
        BaseResponse response = new BaseResponse();
        String methodName = "readFlagChange";
        this.printDebug(methodName, data);
        FlagChangeRequest request = null;

        try {
            request = (FlagChangeRequest)(new Gson()).fromJson(data, FlagChangeRequest.class);
            if (StringUtils.isEmpty(request.getHZRQ()) || StringUtils.isEmpty(request.getZZDH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            int result = this.zzdService.changeReadFlag(request);
            if (result > 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_OPERATE_ERROR);
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/checkRuleCount"})
    public ResponseEntity<String> checkRuleCount() {
        String methodName = "checkRuleCount";
        this.printDebug(methodName, "");
        CountResponse response = new CountResponse();

        try {
            int localRuleCount = Constant.getMxMap().size();
            int noRuleCount = this.mxService.queryNoRuleCount();
            response.setResult(ResultCodeEnum.SUCCESS);
            response.setRuleCount(String.valueOf(localRuleCount + noRuleCount));
        } catch (JsonParseException var5) {
            this.printDebugException(methodName, var5);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/queryNoExist"})
    public ResponseEntity<String> queryNoExist(@RequestBody String data) {
        QueryNoResponse response = new QueryNoResponse();
        String methodName = "queryNoExist";
        this.printDebug(methodName, data);
        ArrayList result = new ArrayList();

        try {
            List<String> list = (List)(new Gson()).fromJson(data, (new TypeToken<List<String>>() {
            }).getType());
            List<String> localDataNo = this.mxService.queryLocalNo();
            list.stream().filter((existItem) -> {
                return !localDataNo.contains(existItem);
            }).forEach((item) -> {
                result.add(item);
            });
            response.setResult(ResultCodeEnum.SUCCESS);
            response.setData(result);
        } catch (JsonParseException var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/uploadBaseData"})
    public ResponseEntity<String> uploadBaseData(@RequestBody String data) {
        BaseResponse response = new BaseResponse();
        String methodName = "uploadBaseData";
        this.printDebug(methodName, data);

        try {
            List<BaseDataEntity> request = (List)(new Gson()).fromJson(data, (new TypeToken<ArrayList<BaseDataEntity>>() {
            }).getType());
            if (request.size() == 0) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            int result = this.mxService.uploadData(request);
            if (result > 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_OPERATE_ERROR);
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/queryYpcdCount"})
    public ResponseEntity<String> queryLocalYpcdCount() {
        String methodName = "queryYpcdCount";
        this.printDebug(methodName, "");
        CountResponse response = new CountResponse();

        try {
            int count = this.mxService.queryYpcdCount();
            response.setRuleCount(String.valueOf(count));
            response.setResult(ResultCodeEnum.SUCCESS);
        } catch (Exception var4) {
            this.printDebugException(methodName, var4);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/queryYpcdNoExist"})
    public ResponseEntity<String> queryYpcdNoExist(@RequestBody String data) {
        String methodName = "queryYpcdNoExist";
        QueryNoResponse response = new QueryNoResponse();
        this.printDebug(methodName, data);
        ArrayList result = new ArrayList();

        try {
            List<String> list = (List)(new Gson()).fromJson(data, (new TypeToken<ArrayList<String>>() {
            }).getType());
            List<String> localData = this.mxService.queryYpcdNo();
            list.stream().filter((existItem) -> {
                return !localData.contains(existItem);
            }).forEach((item) -> {
                result.add(item);
            });
            response.setResult(ResultCodeEnum.SUCCESS);
            response.setData(result);
        } catch (JsonParseException var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/updateYpcds"})
    public ResponseEntity<String> updateYpcds(@RequestBody String data) {
        String methodName = "updateYpcds";
        BaseResponse response = new BaseResponse();
        this.printDebug(methodName, data);

        try {
            List<YpcdEntity> list = (List)(new Gson()).fromJson(data, (new TypeToken<ArrayList<YpcdEntity>>() {
            }).getType());
            if (list.size() == 0) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            int result = this.mxService.uploadYpcds(list);
            if (result > 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_OPERATE_ERROR);
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/queryUserDataToUpload"})
    public ResponseEntity<String> queryUserDataToUpload() {
        UserDataResponse response = new UserDataResponse();
        String methodName = "queryUserDataToUpload";
        this.printDebug(methodName, "");

        try {
            List<String> list = this.mxService.queryNeedUploadUser();
            response.setUsers(list);
            response.setResult(ResultCodeEnum.SUCCESS);
        } catch (JsonParseException var4) {
            this.printDebugException(methodName, var4);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var5) {
            this.printDebugException(methodName, var5);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/updateUserFlag"})
    public ResponseEntity<String> updateUserFlag(@RequestBody String zyh) {
        BaseResponse response = new BaseResponse();
        String methodName = "updateUserFlag";
        this.printDebug(methodName, zyh);

        try {
            if (StringUtils.isEmpty(zyh)) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return ResponseEntity.ok(response.toString());
            }

            this.mxService.updateUserFlag(zyh);
            response.setResult(ResultCodeEnum.SUCCESS);
        } catch (JsonParseException var5) {
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
            this.printDebugException(methodName, var5);
        } catch (Exception var6) {
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
            this.printDebugException(methodName, var6);
        }

        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/getChargeList"})
    public ResponseEntity<String> queryChargeList() {
        String methodName = "getChargeList";
        this.printDebug(methodName, "");
        ChargeListResponse response = new ChargeListResponse();
        List<CYJXInfoEntity> list = this.zzdService.queryChargeList();
        response.setChargeList(list);
        this.printDebug(methodName, response.toString());
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/startSync"})
    public ResponseEntity<String> startSync() {
        String methodName = "startSync";
        this.printDebug(methodName, "");
        int count = this.zzdService.queryItemCount();
        if (count > 0) {
            String s = this.httpUtils.getSyncStart("xh", "hk");
            this.printDebug(methodName, "sync:statt:result:" + s);
        }

        BaseResponse response = new BaseResponse();
        response.setResult(ResultCodeEnum.SUCCESS);
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping({"/syncSummary"})
    public ResponseEntity<String> syncSummary(@RequestBody String data) {
        String methodName = "syncSummary";
        this.printDebug(methodName, data);
        BaseResponse response = new BaseResponse();

        try {
            CyxjInfoEntity entity = (CyxjInfoEntity)(new Gson()).fromJson(data, CyxjInfoEntity.class);
            entity.setInfo(XmlConvertEntityUtils.readStringXml(entity.getInfo()));
            int result = this.mxService.insertCyxj(entity);
            if (result >= 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
            }

            this.printDebug(methodName, entity.toString());
        } catch (JsonParseException var6) {
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
            this.printDebugException(methodName, var6);
        } catch (Exception var7) {
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
            this.printDebugException(methodName, var7);
        }

        return ResponseEntity.ok(response.toString());
    }
}
