//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class InpatientResponse extends BaseResponse {
    private String JZJLH;

    public InpatientResponse() {
    }

    public String getJZJLH() {
        return this.JZJLH;
    }

    public void setJZJLH(String JZJLH) {
        this.JZJLH = JZJLH;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
