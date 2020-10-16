//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.HkXhCommonRule;
import java.util.List;
import java.util.Map;

public interface HkXhCommonRuleMapper {
        int deleteByPrimaryKey(String var1);

        int insert(HkXhCommonRule var1);

        HkXhCommonRule selectByPrimaryKey(String var1);

        List<HkXhCommonRule> selectAll();

        int updateByPrimaryKey(HkXhCommonRule var1);

        List<Map<String, String>> queryCommonRule();

        List<String> queryAllNo();
}
