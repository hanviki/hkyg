//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;
import java.util.List;

public class UserDataResponse extends BaseResponse {
    private List<String> users;

    public UserDataResponse() {
    }

    public List<String> getUsers() {
        return this.users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }
}
