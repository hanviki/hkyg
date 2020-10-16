package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class CancelChargeRequest {
    private String JZJLH;
    private String JSXH;
    private String YYBH;
    private String JBR;
}
