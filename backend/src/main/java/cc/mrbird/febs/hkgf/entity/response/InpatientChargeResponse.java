//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class InpatientChargeResponse extends BaseResponse {
    private String FZJE;
    private String GFZJE;
    private String ZFZJE;
    private String JSXH;
    private String notExistData;

    public InpatientChargeResponse() {
    }

    public String getNotExistData() {
        return this.notExistData;
    }

    public void setNotExistData(String notExistData) {
        this.notExistData = notExistData;
    }

    public String getJSXH() {
        return this.JSXH;
    }

    public void setJSXH(String JSXH) {
        this.JSXH = JSXH;
    }

    public String getFZJE() {
        return this.FZJE;
    }

    public void setFZJE(String FZJE) {
        this.FZJE = FZJE;
    }

    public String getGFZJE() {
        return this.GFZJE;
    }

    public void setGFZJE(String GFZJE) {
        this.GFZJE = GFZJE;
    }

    public String getZFZJE() {
        return this.ZFZJE;
    }

    public void setZFZJE(String ZFZJE) {
        this.ZFZJE = ZFZJE;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
