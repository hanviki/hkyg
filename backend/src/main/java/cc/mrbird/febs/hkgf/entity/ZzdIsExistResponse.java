package cc.mrbird.febs.hkgf.entity;

import cc.mrbird.febs.hkgf.utils.ResultCodeEnum;

public class ZzdIsExistResponse {
    private static final long serialVersionUID = 4076288078992935315L;
    private ZzdIsExistResponse.ZzdBean data;
    private String resultCode;
    private String resultDesc;

    public ZzdIsExistResponse() {
    }

    public ZzdIsExistResponse.ZzdBean getData() {
        return this.data;
    }

    public void setData(ZzdIsExistResponse.ZzdBean data) {
        this.data = data;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return this.resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public void setResult(ResultCodeEnum code) {
        this.resultCode = code.getCode();
        this.resultDesc = code.getDesc();
    }

    public static class ZzdBean {
        private String zzdNo;
        private boolean isCanZz;

        public ZzdBean() {
        }

        public String getZzdNo() {
            return this.zzdNo;
        }

        public void setZzdNo(String zzdNo) {
            this.zzdNo = zzdNo;
        }

        public boolean isCanZz() {
            return this.isCanZz;
        }

        public void setCanZz(boolean canZz) {
            this.isCanZz = canZz;
        }

    }
}
