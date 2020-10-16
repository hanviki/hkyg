package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class InpatientChargeRequest extends BaseRequest {
    private String ZYH;
    private String ZZDH;
    private String HZID;
    private String YYBM;
    private String CYKS;
    private String CYRQ;
    private String ZYTS;
    private String ZJJE;
    private String DHNO;
}
