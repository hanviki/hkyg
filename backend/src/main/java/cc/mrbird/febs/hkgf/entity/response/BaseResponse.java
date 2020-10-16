//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;
import cc.mrbird.febs.hkgf.utils.ResultCodeEnum;

public class BaseResponse {
    protected String FHZ;
    protected String MSG;

    public BaseResponse() {
    }

    public void setResult(ResultCodeEnum result) {
        this.FHZ = result.getCode();
        this.MSG = result.getDesc();
    }

    public void setFHZ(String FHZ) {
        this.FHZ = FHZ;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
