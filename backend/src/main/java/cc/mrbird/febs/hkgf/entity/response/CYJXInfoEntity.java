//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class CYJXInfoEntity {
    private String dhNo;
    private String zzdh;

    public CYJXInfoEntity() {
    }

    public String getDhNo() {
        return this.dhNo;
    }

    public void setDhNo(String dhNo) {
        this.dhNo = dhNo;
    }

    public String getZzdh() {
        return this.zzdh;
    }

    public void setZzdh(String zzdh) {
        this.zzdh = zzdh;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
