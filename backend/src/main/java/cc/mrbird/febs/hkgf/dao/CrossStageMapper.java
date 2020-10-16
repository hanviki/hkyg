//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.CrossStage;
import java.util.List;

public interface CrossStageMapper {
        int deleteByPrimaryKey(Integer var1);

        int insert(CrossStage var1);

        CrossStage selectByPrimaryKey(Integer var1);

        List<CrossStage> selectAll();

        int updateByPrimaryKey(CrossStage var1);
}
