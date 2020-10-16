package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class DailyQueryRequest extends BaseRequest {
    private String JZJLH;
    private String ZZDH;
    private String SFZH;
    private String HZXM;
    private String JSRQ;
    private String JSQSRQ;
    private String JSZZRQ;
    private String JSTS;
}
