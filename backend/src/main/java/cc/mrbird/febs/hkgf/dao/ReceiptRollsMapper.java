//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ReceiptRolls;
import java.util.List;
import java.util.Map;

public interface ReceiptRollsMapper {
        int deleteByPrimaryKey(String var1);

        int insert(ReceiptRolls var1);

        ReceiptRolls selectByPrimaryKey(String var1);

        List<ReceiptRolls> selectAll();

        int updateByPrimaryKey(ReceiptRolls var1);

        int insertRoll(ReceiptRolls var1);

        int queryCont(Map<String, String> var1);

        int deleteByFphm(String var1);
}
