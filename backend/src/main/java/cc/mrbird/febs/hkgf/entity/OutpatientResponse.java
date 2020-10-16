package cc.mrbird.febs.hkgf.entity;

import cc.mrbird.febs.hkgf.entity.response.BaseResponse;
import lombok.Data;

@Data
public class OutpatientResponse extends BaseResponse {
    private String JSXH;
    private String ZJJE;
    private String GFZE;
    private String ZFZE;
    private String JSSJ;
    private String NotExistData;
}
