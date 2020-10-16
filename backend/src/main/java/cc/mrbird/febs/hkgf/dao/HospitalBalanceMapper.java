//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.HospitalBalance;
import java.util.List;

public interface HospitalBalanceMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(HospitalBalance var1);

        HospitalBalance selectByPrimaryKey(Integer var1);

        List<HospitalBalance> selectAll();

        int updateByPrimaryKey(HospitalBalance var1);
}
