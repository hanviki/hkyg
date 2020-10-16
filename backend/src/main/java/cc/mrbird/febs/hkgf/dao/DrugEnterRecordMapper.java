//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.YpcdEntity;
import java.util.List;

public interface DrugEnterRecordMapper {
        int queryCount();

        List<String> queryAllNo();

        int insertByList(List<YpcdEntity> var1);

        List<String> queryUserData();

        int updateUserFlag(String var1);
}
