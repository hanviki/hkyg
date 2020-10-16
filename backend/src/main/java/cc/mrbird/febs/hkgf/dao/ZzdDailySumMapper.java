//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ZzdDailySum;
import java.util.List;

public interface ZzdDailySumMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(ZzdDailySum var1);

        ZzdDailySum selectByPrimaryKey(Integer var1);

        List<ZzdDailySum> selectAll();

        int updateByPrimaryKey(ZzdDailySum var1);
}
