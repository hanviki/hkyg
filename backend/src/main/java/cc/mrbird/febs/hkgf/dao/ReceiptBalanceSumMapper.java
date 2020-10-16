//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ReceiptBalanceSum;
import java.util.List;

public interface ReceiptBalanceSumMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(ReceiptBalanceSum var1);

        ReceiptBalanceSum selectByPrimaryKey(Integer var1);

        List<ReceiptBalanceSum> selectAll();

        int updateByPrimaryKey(ReceiptBalanceSum var1);
}
