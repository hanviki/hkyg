//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ReceiptCallLog;
import java.util.List;

public interface ReceiptCallLogMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(ReceiptCallLog var1);

        ReceiptCallLog selectByPrimaryKey(Integer var1);

        List<ReceiptCallLog> selectAll();

        int updateByPrimaryKey(ReceiptCallLog var1);
}
