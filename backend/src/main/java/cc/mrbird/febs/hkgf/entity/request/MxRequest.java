package cc.mrbird.febs.hkgf.entity.request;

import cc.mrbird.febs.hkgf.entity.MXEntity;
import lombok.Data;

import java.util.List;

@Data
public class MxRequest {
    public String ZYH;
    public String SFZH;
    public List<MXEntity> RESULTSET;
}
