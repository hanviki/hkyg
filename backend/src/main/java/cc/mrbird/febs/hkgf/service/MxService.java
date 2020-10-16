package cc.mrbird.febs.hkgf.service;

import cc.mrbird.febs.hkgf.entity.CyxjInfoEntity;
import cc.mrbird.febs.hkgf.entity.YpcdEntity;
import cc.mrbird.febs.hkgf.entity.request.BaseDataEntity;
import java.util.List;

public interface MxService {
    int queryAllMx();

    int queryNoRuleCount();

    List<String> queryLocalNo();

    int uploadData(List<BaseDataEntity> var1);

    int queryYpcdCount();

    List<String> queryYpcdNo();

    int uploadYpcds(List<YpcdEntity> var1);

    List<String> queryNeedUploadUser();

    int updateUserFlag(String var1);

    int insertCyxj(CyxjInfoEntity var1);
}
