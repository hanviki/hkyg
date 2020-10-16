//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.HkXhSpecRule;
import java.util.List;
import java.util.Map;

public interface HkXhSpecRuleMapper {
        int deleteByPrimaryKey(String var1);

        int insert(HkXhSpecRule var1);

        HkXhSpecRule selectByPrimaryKey(String var1);

        List<HkXhSpecRule> selectAll();

        int updateByPrimaryKey(HkXhSpecRule var1);

        List<Map<String, String>> querySpecRule();

        List<String> queryAllNo();
}
