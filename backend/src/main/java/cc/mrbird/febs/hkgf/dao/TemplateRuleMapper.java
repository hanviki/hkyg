//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.TemplateRule;
import java.util.List;

public interface TemplateRuleMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(TemplateRule var1);

        TemplateRule selectByPrimaryKey(Integer var1);

        List<TemplateRule> selectAll();

        int updateByPrimaryKey(TemplateRule var1);
}
