package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class MoreReadyToChargeRequest extends BaseRequest {
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
    private String CYZD;
    private String CYZDCODE;
    private List<InpatientChargeTotalData> RESULTSET;
}
