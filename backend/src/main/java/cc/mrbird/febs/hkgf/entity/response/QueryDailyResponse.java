//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;
import java.util.List;

public class QueryDailyResponse extends BaseResponse {
    private List<HzBean> list;

    public QueryDailyResponse() {
    }

    public List<HzBean> getList() {
        return this.list;
    }

    public void setList(List<HzBean> list) {
        this.list = list;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
