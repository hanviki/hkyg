//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class DailyQueryResponse extends BaseResponse {
    private String JZJLH;
    private String JSXH;
    private String FYZE;
    private String GFJE;
    private String ZFJE;
    private String JSLX;

    public DailyQueryResponse() {
    }

    public String getJZJLH() {
        return this.JZJLH;
    }

    public void setJZJLH(String JZJLH) {
        this.JZJLH = JZJLH;
    }

    public String getJSXH() {
        return this.JSXH;
    }

    public void setJSXH(String JSXH) {
        this.JSXH = JSXH;
    }

    public String getFYZE() {
        return this.FYZE;
    }

    public void setFYZE(String FYZE) {
        this.FYZE = FYZE;
    }

    public String getGFJE() {
        return this.GFJE;
    }

    public void setGFJE(String GFJE) {
        this.GFJE = GFJE;
    }

    public String getZFJE() {
        return this.ZFJE;
    }

    public void setZFJE(String ZFJE) {
        this.ZFJE = ZFJE;
    }

    public String getJSLX() {
        return this.JSLX;
    }

    public void setJSLX(String JSLX) {
        this.JSLX = JSLX;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
