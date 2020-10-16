package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class ReadyToChargeRequest extends BaseRequest {
    private String JZJLH;
    private String ZYH;
    private String ZZDH;
    private String HZXM;
    private String HZID;
    private String JZKH;
    private String YYBM;
    private String SJDFEE;
    private String SJDNUM;
    private String JZLB;
    private Object RESULTSET;
}
