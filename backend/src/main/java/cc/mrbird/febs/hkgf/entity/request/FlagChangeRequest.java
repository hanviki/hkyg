package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class FlagChangeRequest extends BaseRequest {
    private String ZZDH;
    private String HZRQ;
}
