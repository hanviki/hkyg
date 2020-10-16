//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class CancelChargeResponse extends BaseResponse {
    public CancelChargeResponse() {
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
