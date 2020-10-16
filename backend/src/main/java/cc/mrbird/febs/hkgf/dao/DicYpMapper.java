//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.DicYp;
import java.util.List;
import java.util.Map;

public interface DicYpMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(DicYp var1);

        DicYp selectByPrimaryKey(Integer var1);

        List<DicYp> selectAll();

        int updateByPrimaryKey(DicYp var1);

        List<Map<String, String>> queryAllYp();
}
