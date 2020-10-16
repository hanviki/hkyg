//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.HospitalDetailTmp;
import cc.mrbird.febs.hkgf.entity.request.BaseDataEntity;
import java.util.List;
import java.util.Map;

public interface HospitalDetailTmpMapper {
        int queryCount();

        List<String> queryAllNo();

        int insertByList(List<BaseDataEntity> var1);

        int insertUserData(List<HospitalDetailTmp> var1);

        List<Map<String, String>> queryByUser(String var1);
}
