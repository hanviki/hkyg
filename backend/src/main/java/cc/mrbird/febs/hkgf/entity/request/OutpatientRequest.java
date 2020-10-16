package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

import java.util.List;

@Data
public class OutpatientRequest extends BaseRequest {
    private String YYBH;
    private String JZBH;
    private String HZXM;
    private String HZID;
    private String JZKH;
    private String ZZDH;
    private String JZRQ;
    private String JZKS;
    private String MZZD;
    private String FPHM;
    private String JSRQ;
    private String FYZE;
    private String BZ;
    private String JBR;
    private List<OutpatientItemData> RESULTSET;
}
