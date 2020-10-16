package cc.mrbird.febs.hkgf.entity;

import lombok.Data;

import java.util.List;

@Data
public class DataInsertResult {
    private int ResultCode;
    private List<HospitalDetailTmp> remainData;
}
