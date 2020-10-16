//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.DicZl;
import java.util.List;
import java.util.Map;

public interface DicZlMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(DicZl var1);

        DicZl selectByPrimaryKey(Integer var1);

        List<DicZl> selectAll();

        int updateByPrimaryKey(DicZl var1);

        List<Map<String, String>> queryAllZl();
}
