package cc.mrbird.febs.hkgf.entity.request;

import lombok.Data;

@Data
public class CancelRegistrationRequest extends BaseRequest {
    private String SFZH;
    private String JZBH;
}
