package cc.mrbird.febs.hkgf.controller;

import cc.mrbird.febs.common.controller.BaseController;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import cc.mrbird.febs.hkgf.config.Constant.ZzdType;
import cc.mrbird.febs.hkgf.entity.DataInsertResult;
import cc.mrbird.febs.hkgf.entity.GfjsZzd;
import cc.mrbird.febs.hkgf.entity.OutpatientResponse;
import cc.mrbird.febs.hkgf.entity.request.CancelChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelOutPatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelRegistrationRequest;
import cc.mrbird.febs.hkgf.entity.request.CheckParams;
import cc.mrbird.febs.hkgf.entity.request.DailyQueryRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.MakeSureUserRequest;
import cc.mrbird.febs.hkgf.entity.request.MoreReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.OutHospitalRequest;
import cc.mrbird.febs.hkgf.entity.request.OutpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.ReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.response.BaseResponse;
import cc.mrbird.febs.hkgf.entity.response.CancelChargeResponse;
import cc.mrbird.febs.hkgf.entity.response.CheckResponse;
import cc.mrbird.febs.hkgf.entity.response.DailyQueryResponse;
import cc.mrbird.febs.hkgf.entity.response.InpatientChargeResponse;
import cc.mrbird.febs.hkgf.entity.response.InpatientResponse;
import cc.mrbird.febs.hkgf.entity.response.MakeSureUserResponse;
import cc.mrbird.febs.hkgf.service.ZzdService;
import cc.mrbird.febs.hkgf.utils.DateUtils;
import cc.mrbird.febs.hkgf.utils.HttpUtils;
import cc.mrbird.febs.hkgf.utils.ResultCodeEnum;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZzdController extends BaseController {
    @Autowired
    private ZzdService zzdService;
    @Autowired
    private HttpUtils httpUtils;
    @Autowired
    private DateUtils dateUtils;

    public ZzdController() {
    }

    @PostMapping({"/queryZzdIsExist"})
    public String queryZzdIsExist(@RequestBody String data) {
        String methodName = "queryZzdIsExist";
        this.printDebug(methodName, data);
       // data= "{\"SFZH\":\"420103198110051618\",\"YYBH\":\"xh\",\"JZRQ\":\"20200902\",\"ZZDLX\":\"1\"}";
        MakeSureUserRequest sureUserRequest = null;
        MakeSureUserResponse response = new MakeSureUserResponse();

        try {
            sureUserRequest = (MakeSureUserRequest)(new Gson()).fromJson(data, MakeSureUserRequest.class);
            if (StringUtils.isEmpty(sureUserRequest.getJZRQ())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZRQ);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(sureUserRequest.getSFZH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_SFZH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(sureUserRequest.getYYBH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_YYBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(sureUserRequest.getZZDLX())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZZLX);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            int zzd_type = Integer.valueOf(sureUserRequest.getZZDLX());
            if (zzd_type == ZzdType.ZY) {
                GfjsZzd tmp1Zzd = this.zzdService.queryZzdIsVaild(sureUserRequest.getSFZH());
                if (tmp1Zzd != null) {
                    response.setResult(ResultCodeEnum.ZZD_IS_EXIST);
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                }

                GfjsZzd gfjsZzd = this.zzdService.queryZzdByCardNo(sureUserRequest.getSFZH());
                if (gfjsZzd != null && gfjsZzd.getUserType() == 1) {
                    response.setResult(ResultCodeEnum.NO_ALLOW_ZM_TO_ZY_ST);
                    return response.toString();
                }

                response = this.zzdService.queryZzdByUserInfo(sureUserRequest);
                if (response == null) {
                    GfjsZzd zzdh = this.zzdService.queryUsedMzZzd(sureUserRequest);
                    if (zzdh != null) {
                        if (zzdh.getUserType() == 1) {
                            response.setResult(ResultCodeEnum.USER_TYPE_ERROR);
                            this.printDebug(methodName, response.toString());
                            return response.toString();
                        }

                        int updateResult = this.zzdService.cancelZzd(zzdh.getZzdNo());
                        if (updateResult > 0) {
                            this.zzdService.createNewZzd(zzdh);
                        }

                        response = this.zzdService.queryZzdByUserInfo(sureUserRequest);
                    }
                }
            } else if (zzd_type == ZzdType.MZ) {
                response = this.zzdService.queryMXzZdBySate(sureUserRequest);
                if (response == null) {
                    response = this.zzdService.queryMzZzdByUserInfo(sureUserRequest);
                    if (response != null) {
                        this.zzdService.updateZZdState(response.getZZDH());
                    }
                }
            }

            if (response != null) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response = new MakeSureUserResponse();
                List<String> list = this.zzdService.queryUsedZzd(sureUserRequest.getSFZH());
                if (list.isEmpty()) {
                    response.setResult(ResultCodeEnum.ACCOUNT_NOT_EXIST);
                } else {
                    response.setResult(ResultCodeEnum.ZZD_IS_EXIST);
                }
            }
        } catch (JsonParseException var10) {
            this.printDebugException(methodName, var10);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var11) {
            this.printDebugException(methodName, var11);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/cancelRegistration"})
    public String cancelRegistration(@RequestBody String data) {
        String methodName = "cancelRegistration";
        this.printDebug(methodName, data);
        BaseResponse response = new BaseResponse();
        CancelRegistrationRequest request = null;

        try {
            request = (CancelRegistrationRequest)(new Gson()).fromJson(data, CancelRegistrationRequest.class);
            if (StringUtils.isEmpty(request.getSFZH()) || StringUtils.isEmpty(request.getJZBH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            int result = this.zzdService.cancelRegistration(request);
            if (result > 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.FAIL);
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/inpatientRegistration"})
    public String inpatientRegistration(@RequestBody String data) {
        String methodName = "inpatientRegistration";
        InpatientResponse response = new InpatientResponse();
        this.printDebug(methodName, data);
        InpatientRequest request = null;

        try {
            request = (InpatientRequest)(new Gson()).fromJson(data, InpatientRequest.class);
            if (StringUtils.isEmpty(request.getSFZH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_SFZH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getYHXM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_HZXM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZBH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZZDH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZZDH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZYHM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYHM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZYKS())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYKS);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZYKSBM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYKSBM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getRYRQ())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_RYRQ);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZKH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZKH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            int result = this.zzdService.inpatientHospital(request);
            if (result > 0) {
                response.setResult(ResultCodeEnum.SUCCESS);
                response.setJZJLH(request.getZZDH());
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
        return response.toString();
    }

    @PostMapping({"/inpatientCharge"})
    public String inpatientCharge(@RequestBody String data) {
        String methodName = "inpatientCharge";
        this.printDebug(methodName, data);
        InpatientChargeResponse response = new InpatientChargeResponse();
        InpatientChargeRequest request = null;

        try {
            request = (InpatientChargeRequest)(new Gson()).fromJson(data, InpatientChargeRequest.class);
            if (StringUtils.isEmpty(request.getZYH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYHM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZZDH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZZDH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getHZID())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getYYBM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_YYBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getCYKS())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_CYKS);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getCYRQ())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_CYRQ);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZYTS())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYTS);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getDHNO())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            int updateInfoResult = this.zzdService.updateZzdInfo(request);
            if (updateInfoResult < 1) {
                response.setResult(ResultCodeEnum.DB_OPERATE_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            CheckParams checkParams = new CheckParams();
            checkParams.setType(2);
            checkParams.setZzdNo(request.getZZDH());
            checkParams.setRedoFlag("0");
            CheckResponse checkResponse = this.httpUtils.getCheckData(checkParams);
            this.printDebug(methodName, checkResponse.toString());
            if (checkResponse.getStatus() == 0) {
                response.setFZJE(String.valueOf((double)checkResponse.getData().getTotalFee() / 100.0D));
                response.setGFZJE(String.valueOf((double)checkResponse.getData().getGfFee() / 100.0D));
                response.setZFZJE(String.valueOf((double)checkResponse.getData().getZfFee() / 100.0D));
                response.setJSXH(checkResponse.getData().getCheckoutNo());
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.PARAM_ERROR_YPWSZ);
            }
        } catch (JsonParseException var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var9) {
            this.printDebugException(methodName, var9);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/cancelCharge"})
    public String cancelCharge(@RequestBody String data) {
        CancelChargeResponse response = new CancelChargeResponse();
        String methodName = "cancelCharge";
        this.printDebug(methodName, data);
        CancelChargeRequest request = null;

        try {
            request = (CancelChargeRequest)(new Gson()).fromJson(data, CancelChargeRequest.class);
            if (StringUtils.isEmpty(request.getJZJLH()) || StringUtils.isEmpty(request.getJSXH()) || StringUtils.isEmpty(request.getJBR())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            int result = this.zzdService.cancelCharge(request);
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
        return response.toString();
    }

    @PostMapping({"/ready2Charge"})
    public String ready2Charge(@RequestBody String data) {
        InpatientChargeResponse response = new InpatientChargeResponse();
        String methodName = "ready2Charge";
        this.printDebug(methodName, data);
        ReadyToChargeRequest request = null;

        try {
            request = (ReadyToChargeRequest)(new Gson()).fromJson(data, ReadyToChargeRequest.class);
            if (StringUtils.isEmpty(request.getJZJLH()) || StringUtils.isEmpty(request.getZYH()) || StringUtils.isEmpty(request.getZZDH()) || StringUtils.isEmpty(request.getHZXM()) || StringUtils.isEmpty(request.getHZID()) || StringUtils.isEmpty(request.getJZKH()) || StringUtils.isEmpty(request.getYYBM()) || StringUtils.isEmpty(request.getSJDFEE()) || StringUtils.isEmpty(request.getSJDNUM()) || StringUtils.isEmpty(request.getJZLB())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            DataInsertResult insertResult = this.zzdService.readyToCharge(request);
            switch(insertResult.getResultCode()) {
                case -2:
                    response.setResult(ResultCodeEnum.CHECK_NOT_OK);
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                case -1:
                    response.setResult(ResultCodeEnum.DATA_NOT_EXIST);
                    response.setNotExistData(this.zzdService.queryUserUnExistData(request.getHZID()));
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                default:
                    if (insertResult.getResultCode() >= 0) {
                        CheckParams checkParams = new CheckParams();
                        checkParams.setType(1);
                        checkParams.setZzdNo(request.getZZDH());
                        checkParams.setRedoFlag("1");
                        CheckResponse checkResponse = this.httpUtils.getCheckData(checkParams);
                        this.printDebug(methodName, checkResponse.toString());
                        if (checkResponse.getStatus() == 0) {
                            response.setFZJE(String.valueOf((double)checkResponse.getData().getTotalFee() / 100.0D));
                            response.setGFZJE(String.valueOf((double)checkResponse.getData().getGfFee() / 100.0D));
                            response.setZFZJE(String.valueOf((double)checkResponse.getData().getZfFee() / 100.0D));
                            response.setResult(ResultCodeEnum.SUCCESS);
                        } else {
                            response.setResult(ResultCodeEnum.PARAM_ERROR_YPWSZ);
                        }
                    } else {
                        response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
                    }
            }
        } catch (JsonParseException var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var9) {
            this.printDebugException(methodName, var9);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/readyMoreCharge"})
    public String readyMoreCharge(@RequestBody String data) {
        InpatientChargeResponse response = new InpatientChargeResponse();
        String methodName = "readyMoreCharge";
        this.printDebug(methodName, data);
        MoreReadyToChargeRequest request = null;

        try {
            request = (MoreReadyToChargeRequest)(new Gson()).fromJson(data, MoreReadyToChargeRequest.class);
            if (StringUtils.isEmpty(request.getJZJLH()) || StringUtils.isEmpty(request.getZYH()) || StringUtils.isEmpty(request.getZZDH()) || StringUtils.isEmpty(request.getHZXM()) || StringUtils.isEmpty(request.getHZID()) || StringUtils.isEmpty(request.getJZKH()) || StringUtils.isEmpty(request.getYYBM()) || StringUtils.isEmpty(request.getSJDFEE()) || StringUtils.isEmpty(request.getSJDNUM()) || StringUtils.isEmpty(request.getJZLB())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            DataInsertResult insertResult = this.zzdService.newReadyToCharge(request);
            switch(insertResult.getResultCode()) {
                case -2:
                    response.setResult(ResultCodeEnum.CHECK_NOT_OK);
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                case -1:
                    response.setResult(ResultCodeEnum.DATA_NOT_EXIST);
                    response.setNotExistData(this.zzdService.queryUserUnExistData(request.getHZID()));
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                default:
                    if (insertResult.getResultCode() >= 0) {
                        CheckParams checkParams = new CheckParams();
                        checkParams.setType(1);
                        checkParams.setZzdNo(request.getZZDH());
                        checkParams.setRedoFlag("1");
                        CheckResponse checkResponse = this.httpUtils.getCheckData(checkParams);
                        this.printDebug(methodName, checkResponse.toString());
                        if (checkResponse.getStatus() == 0) {
                            response.setFZJE(String.valueOf((double)checkResponse.getData().getTotalFee() / 100.0D));
                            response.setGFZJE(String.valueOf((double)checkResponse.getData().getGfFee() / 100.0D));
                            response.setZFZJE(String.valueOf((double)checkResponse.getData().getZfFee() / 100.0D));
                            response.setResult(ResultCodeEnum.SUCCESS);
                        } else {
                            response.setResult(ResultCodeEnum.PARAM_ERROR_YPWSZ);
                        }
                    } else {
                        response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
                    }
            }
        } catch (JsonParseException var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var9) {
            this.printDebugException(methodName, var9);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/outHospitalSave"})
    public String outHospitalSave(@RequestBody String data) {
        BaseResponse response = new BaseResponse();
        String methodName = "outHospitalSave";
        this.printDebug(methodName, data);
        OutHospitalRequest request = null;

        try {
            request = (OutHospitalRequest)(new Gson()).fromJson(data, OutHospitalRequest.class);
            if (StringUtils.isEmpty(request.getJZJLH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/inpatientDailyQuery"})
    public String inpatientDailyQuery(@RequestBody String data) {
        String methodName = "inpatientDailyQuery";
        this.printDebug(methodName, data);
        DailyQueryResponse response = new DailyQueryResponse();
        DailyQueryRequest request = null;

        try {
            request = (DailyQueryRequest)(new Gson()).fromJson(data, DailyQueryRequest.class);
            if (StringUtils.isEmpty(request.getJZJLH()) || StringUtils.isEmpty(request.getZZDH()) || StringUtils.isEmpty(request.getSFZH()) || StringUtils.isEmpty(request.getHZXM()) || StringUtils.isEmpty(request.getJSRQ()) || StringUtils.isEmpty(request.getJSQSRQ()) || StringUtils.isEmpty(request.getJSZZRQ()) || StringUtils.isEmpty(request.getJSTS())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            response = this.zzdService.dailyChargeQuery(request);
            if (response != null) {
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
            }
        } catch (JsonParseException var6) {
            this.printDebugException(methodName, var6);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/outpatientCharge"})
    public String outpatientCharge(@RequestBody String data) {
        String methodName = "outpatientCharge";
        this.printDebug(methodName, data);
        OutpatientResponse response = new OutpatientResponse();
        OutpatientRequest request = null;

        try {
            request = (OutpatientRequest)(new Gson()).fromJson(data, OutpatientRequest.class);
            if (StringUtils.isEmpty(request.getYYBH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_YYBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZBH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getHZXM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_HZXM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getHZID())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_SFZH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZKH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZKH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getZZDH())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZZDH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZRQ())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JZRQ);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJZKS())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_ZYKS);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getFPHM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_FPBH);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            if (StringUtils.isEmpty(request.getJSRQ())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR_JSRQ);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            GfjsZzd gfjsZzd = this.zzdService.queryZyExist(request.getZZDH());
            if (gfjsZzd != null) {
                response.setResult(ResultCodeEnum.NO_ALLOW_ZY_TO_ZM);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            DataInsertResult result = this.zzdService.outpatientCharge(request);
            switch(result.getResultCode()) {
                case -2:
                    response.setResult(ResultCodeEnum.CHECK_NOT_OK);
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                case -1:
                    response.setResult(ResultCodeEnum.DATA_NOT_EXIST);
                    response.setNotExistData(this.zzdService.queryUserUnExistData(request.getHZID()));
                    this.printDebug(methodName, response.toString());
                    return response.toString();
                default:
                    if (result.getResultCode() >= 0) {
                        CheckParams checkParams = new CheckParams();
                        checkParams.setType(3);
                        checkParams.setZzdNo(request.getZZDH());
                        checkParams.setRedoFlag("1");
                        checkParams.setSjdh(request.getFPHM());
                        CheckResponse checkResponse = this.httpUtils.getCheckData(checkParams);
                        this.printDebug(methodName, checkResponse.toString());
                        if (checkResponse.getStatus() == 0) {
                            response.setZJJE(String.valueOf((double)checkResponse.getData().getTotalFee() / 100.0D));
                            response.setGFZE(String.valueOf((double)checkResponse.getData().getGfFee() / 100.0D));
                            response.setZFZE(String.valueOf((double)checkResponse.getData().getZfFee() / 100.0D));
                            response.setJSSJ(this.dateUtils.parseDate2Sjdh(new Date()));
                            response.setJSXH(checkResponse.getData().getCheckoutNo());
                            response.setResult(ResultCodeEnum.SUCCESS);
                        } else {
                            response.setResult(ResultCodeEnum.PARAM_ERROR_YPWSZ);
                        }
                    } else {
                        response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
                    }
            }
        } catch (JsonParseException var9) {
            this.printDebugException(methodName, var9);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var10) {
            this.printDebugException(methodName, var10);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }

    @PostMapping({"/cancelOutPatientCharge"})
    public String cancelOutPatientCharge(@RequestBody String data) {
        BaseResponse response = new BaseResponse();
        String methodName = "cancelOutPatientCharge";
        this.printDebug(methodName, data);
        CancelOutPatientChargeRequest request = null;

        try {
            request = (CancelOutPatientChargeRequest)(new Gson()).fromJson(data, CancelOutPatientChargeRequest.class);
            if (StringUtils.isEmpty(request.getZZDH()) || StringUtils.isEmpty(request.getFPHM())) {
                response.setResult(ResultCodeEnum.PARAM_ERROR);
                this.printDebug(methodName, response.toString());
                return response.toString();
            }

            String status = this.zzdService.getZzdStatus(request.getZZDH());
            if ("1".equals(status)) {
                this.zzdService.cancelOutpatientCharge(request);
                response.setResult(ResultCodeEnum.SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.ZZD_STATUS_ERROR);
            }
        } catch (JsonParseException var7) {
            this.printDebugException(methodName, var7);
            response.setResult(ResultCodeEnum.PARAM_ERROR);
        } catch (Exception var8) {
            this.printDebugException(methodName, var8);
            response.setResult(ResultCodeEnum.SERVER_INTERNAL_ERROR);
        }

        this.printDebug(methodName, response.toString());
        return response.toString();
    }
}
