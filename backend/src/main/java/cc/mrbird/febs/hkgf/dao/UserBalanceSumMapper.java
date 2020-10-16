//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.UserBalanceSum;
import java.util.List;

public interface UserBalanceSumMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(UserBalanceSum var1);

        UserBalanceSum selectByPrimaryKey(Integer var1);

        List<UserBalanceSum> selectAll();

        int updateByPrimaryKey(UserBalanceSum var1);
}
