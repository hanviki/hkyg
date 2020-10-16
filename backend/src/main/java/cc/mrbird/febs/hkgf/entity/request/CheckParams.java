package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class CheckParams {
    private int type;
    private String zzdNo;
    private String redoFlag;
    private String sjdh;
}
