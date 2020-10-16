package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class MakeSureUserRequest extends BaseRequest {
    private String SFZH;
    private String YYBH;
    private String JZRQ;
    private String ZZDLX;

    public MakeSureUserRequest(String SFZH, String YYBH, String JZRQ, String ZZDLX) {
        this.SFZH = SFZH;
        this.YYBH = YYBH;
        this.JZRQ = JZRQ;
        this.ZZDLX = ZZDLX;
    }
}
