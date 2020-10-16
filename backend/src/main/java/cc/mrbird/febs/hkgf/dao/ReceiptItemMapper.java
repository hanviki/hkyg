//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.ReceiptItem;
import java.util.List;

public interface ReceiptItemMapper {
        int deleteByPrimaryKey(String var1);

        int insert(ReceiptItem var1);

        ReceiptItem selectByPrimaryKey(String var1);

        List<ReceiptItem> selectAll();

        int updateByPrimaryKey(ReceiptItem var1);

        int insertByList(List<ReceiptItem> var1);

        List<String> queryMXBHBySJDH(String var1);

        List<String> queryMXBHByZZDH(String var1);

        int queryItemCount();

        int deleteByFphm(String var1);
}
