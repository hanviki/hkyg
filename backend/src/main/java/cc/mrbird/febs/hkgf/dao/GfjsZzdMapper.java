//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.GfjsZzd;
import cc.mrbird.febs.hkgf.entity.ZzdTypeEntity;
import cc.mrbird.febs.hkgf.entity.request.CancelChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelRegistrationRequest;
import cc.mrbird.febs.hkgf.entity.request.FlagChangeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.MakeSureUserRequest;
import cc.mrbird.febs.hkgf.entity.response.CYJXInfoEntity;
import cc.mrbird.febs.hkgf.entity.response.HzBean;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface GfjsZzdMapper {
        int deleteByPrimaryKey(String var1);

        int insert(GfjsZzd var1);

        GfjsZzd selectByPrimaryKey(String var1);

        List<GfjsZzd> selectAll();

        int updateByPrimaryKey(GfjsZzd var1);

        Map<String, Object> selectZzdByUserInfo(MakeSureUserRequest var1);

        int updateHospitalInfo(InpatientRequest var1);

        int updateRedoFlag(String var1);

        int updateZzdStatus(CancelChargeRequest var1);

        int cancelRegister(CancelRegistrationRequest var1);

        GfjsZzd queryInfoByZYH(String var1);

        List<HzBean> queryUnraedInfo();

        int updateReadFlag(FlagChangeRequest var1);

        int updateInfo(InpatientChargeRequest var1);

        List<CYJXInfoEntity> queryChargeList();

        int updateSummaryFlag(String var1);

        int updateZZDLX(ZzdTypeEntity var1);

        Map<String, Object> selectZzdByState(MakeSureUserRequest var1);

        int useMzZzd(String var1);

        GfjsZzd queryUsedZZd(MakeSureUserRequest var1);

        int changeZzdStatus(String var1);

        int insertByZzd(GfjsZzd var1);

        String queryZzdStatus(String var1);

        List<String> queryUsedZzdBySfzh(String var1);

        void updateDiagnosisCode(@Param("zzdNo") String var1, @Param("diagnosisCode") String var2);

        GfjsZzd queryZzdIsExist(String var1);

        GfjsZzd queryZzdIsVaild(String var1);

        GfjsZzd queryZyExist(String var1);

        GfjsZzd queryZzdByCardNo(String var1);

        int handleOverTimeZzd(String var1);

        int handleCompledZzd(String var1);

        GfjsZzd queryZzdByZzdNo(String var1);

        int changeZzdStatus1(String var1);
}
