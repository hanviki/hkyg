package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class InpatientChargeTotalData {
    private String JZJLH;
    private String ZYH;
    private String ZZDH;
    private String SJDH;
    private String HZXM;
    private String HZID;
    private String JZKH;
    private String YYBM;
    private String SJDFEE;
    private String SJDNUM;
    private List<InpatientChargeItemData> ResultSet;
}
