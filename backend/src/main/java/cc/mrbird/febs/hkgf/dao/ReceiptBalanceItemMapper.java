//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ReceiptBalanceItem;
import cc.mrbird.febs.hkgf.entity.request.DailyQueryRequest;
import java.util.List;
import java.util.Map;

public interface ReceiptBalanceItemMapper {
        int deleteByPrimaryKey(String var1);

        int insert(ReceiptBalanceItem var1);

        ReceiptBalanceItem selectByPrimaryKey(String var1);

        List<ReceiptBalanceItem> selectAll();

        int updateByPrimaryKey(ReceiptBalanceItem var1);

        Map<String, String> queryDailyCharge(DailyQueryRequest var1);

        int deleteByFphm(String var1);
}
