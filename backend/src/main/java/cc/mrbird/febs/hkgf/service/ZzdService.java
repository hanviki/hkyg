package cc.mrbird.febs.hkgf.service;

import cc.mrbird.febs.hkgf.entity.DataInsertResult;
import cc.mrbird.febs.hkgf.entity.GfjsZzd;
import cc.mrbird.febs.hkgf.entity.MXEntity;
import cc.mrbird.febs.hkgf.entity.ReceiptRolls;
import cc.mrbird.febs.hkgf.entity.request.CancelChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelOutPatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelRegistrationRequest;
import cc.mrbird.febs.hkgf.entity.request.DailyQueryRequest;
import cc.mrbird.febs.hkgf.entity.request.FlagChangeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.MakeSureUserRequest;
import cc.mrbird.febs.hkgf.entity.request.MoreReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.OutpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.ReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.response.CYJXInfoEntity;
import cc.mrbird.febs.hkgf.entity.response.DailyQueryResponse;
import cc.mrbird.febs.hkgf.entity.response.HzBean;
import cc.mrbird.febs.hkgf.entity.response.MakeSureUserResponse;
import java.util.List;

public interface ZzdService {
    MakeSureUserResponse queryZzdByUserInfo(MakeSureUserRequest var1);

    int inpatientHospital(InpatientRequest var1);

    int cancelCharge(CancelChargeRequest var1);

    DataInsertResult readyToCharge(ReadyToChargeRequest var1);

    DataInsertResult newReadyToCharge(MoreReadyToChargeRequest var1);

    DailyQueryResponse dailyChargeQuery(DailyQueryRequest var1);

    int cancelRegistration(CancelRegistrationRequest var1);

    GfjsZzd queryInfoByZYH(String var1);

    int insertRolls(ReceiptRolls var1);

    DataInsertResult syncMxItem(String var1, String var2, List<MXEntity> var3);

    List<HzBean> queryUnreadSum();

    int changeReadFlag(FlagChangeRequest var1);

    int updateZzdInfo(InpatientChargeRequest var1);

    String queryUserUnExistData(String var1);

    List<CYJXInfoEntity> queryChargeList();

    int queryItemCount();

    MakeSureUserResponse queryMzZzdByUserInfo(MakeSureUserRequest var1);

    DataInsertResult outpatientCharge(OutpatientRequest var1);

    MakeSureUserResponse queryMXzZdBySate(MakeSureUserRequest var1);

    int updateZZdState(String var1);

    GfjsZzd queryUsedMzZzd(MakeSureUserRequest var1);

    int cancelZzd(String var1);

    int createNewZzd(GfjsZzd var1);

    int cancelOutpatientCharge(CancelOutPatientChargeRequest var1);

    String getZzdStatus(String var1);

    List<String> queryUsedZzd(String var1);

    GfjsZzd queryZzdIsExist(String var1);

    GfjsZzd queryZzdIsVaild(String var1);

    GfjsZzd queryZyExist(String var1);

    GfjsZzd queryZzdByCardNo(String var1);

    void updateZzdRecord();
}

