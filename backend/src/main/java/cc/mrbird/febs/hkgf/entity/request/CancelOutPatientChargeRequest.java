package cc.mrbird.febs.hkgf.entity.request;


import lombok.Data;

@Data
public class CancelOutPatientChargeRequest extends BaseRequest {
    private String ZZDH;
    private String FPHM;
}
