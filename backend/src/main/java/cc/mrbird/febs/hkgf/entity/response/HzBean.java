//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class HzBean {
    private String ZZDH;
    private String ZJJE;
    private String ZFJE;
    private String GFJE;
    private String HZRQ;
    private String ZZDLX;

    public HzBean() {
    }

    public String getZZDLX() {
        return this.ZZDLX;
    }

    public void setZZDLX(String ZZDLX) {
        this.ZZDLX = ZZDLX;
    }

    public String getZZDH() {
        return this.ZZDH;
    }

    public void setZZDH(String ZZDH) {
        this.ZZDH = ZZDH;
    }

    public String getZJJE() {
        return this.ZJJE;
    }

    public void setZJJE(String ZJJE) {
        this.ZJJE = ZJJE;
    }

    public String getZFJE() {
        return this.ZFJE;
    }

    public void setZFJE(String ZFJE) {
        this.ZFJE = ZFJE;
    }

    public String getGFJE() {
        return this.GFJE;
    }

    public void setGFJE(String GFJE) {
        this.GFJE = GFJE;
    }

    public String getHZRQ() {
        return this.HZRQ;
    }

    public void setHZRQ(String HZRQ) {
        this.HZRQ = HZRQ;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }

    public void format() {
        this.ZJJE = String.valueOf(Float.valueOf(this.ZJJE) / 100.0F);
        this.ZFJE = String.valueOf(Float.valueOf(this.ZFJE) / 100.0F);
        this.GFJE = String.valueOf(Float.valueOf(this.GFJE) / 100.0F);
    }
}
