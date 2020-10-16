//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class MakeSureUserResponse extends BaseResponse {
    private String ZZDH;
    private String XM;
    private String XB;
    private String ZGLB;
    private String DWMC;
    private String ZZYYBH;
    private String ZZYYMC;
    private String ZZYXRQ;

    public MakeSureUserResponse() {
    }

    public String getZZDH() {
        return this.ZZDH;
    }

    public void setZZDH(String ZZDH) {
        this.ZZDH = ZZDH;
    }

    public String getXM() {
        return this.XM;
    }

    public void setXM(String XM) {
        this.XM = XM;
    }

    public String getXB() {
        return this.XB;
    }

    public void setXB(String XB) {
        this.XB = XB;
    }

    public String getZGLB() {
        return this.ZGLB;
    }

    public void setZGLB(String ZGLB) {
        this.ZGLB = ZGLB;
    }

    public String getDWMC() {
        return this.DWMC;
    }

    public void setDWMC(String DWMC) {
        this.DWMC = DWMC;
    }

    public String getZZYYBH() {
        return this.ZZYYBH;
    }

    public void setZZYYBH(String ZZYYBH) {
        this.ZZYYBH = ZZYYBH;
    }

    public String getZZYYMC() {
        return this.ZZYYMC;
    }

    public void setZZYYMC(String ZZYYMC) {
        this.ZZYYMC = ZZYYMC;
    }

    public String getZZYXRQ() {
        return this.ZZYXRQ;
    }

    public void setZZYXRQ(String ZZYXRQ) {
        this.ZZYXRQ = ZZYXRQ;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
