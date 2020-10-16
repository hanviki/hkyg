//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.dao;

import cc.mrbird.febs.hkgf.entity.Hospitals;
import java.util.List;

public interface HospitalsMapper {
        int deleteByPrimaryKey(String var1);

        int insert(Hospitals var1);

        Hospitals selectByPrimaryKey(String var1);

        List<Hospitals> selectAll();

        int updateByPrimaryKey(Hospitals var1);
}
