//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package cc.mrbird.febs.hkgf.entity.response;

import com.google.gson.GsonBuilder;

public class CheckResponse {
    private int status;
    private String msg;
    private CheckResponse.DataBean data;

    public CheckResponse() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CheckResponse.DataBean getData() {
        return this.data;
    }

    public void setData(CheckResponse.DataBean data) {
        this.data = data;
    }

    public String toString() {
        return (new GsonBuilder()).create().toJson(this);
    }

    public static class DataBean {
        private int totalFee;
        private int zfFee;
        private int gfFee;
        private String checkoutNo;

        public DataBean() {
        }

        public String getCheckoutNo() {
            return this.checkoutNo;
        }

        public void setCheckoutNo(String checkoutNo) {
            this.checkoutNo = checkoutNo;
        }

        public int getTotalFee() {
            return this.totalFee;
        }

        public void setTotalFee(int totalFee) {
            this.totalFee = totalFee;
        }

        public int getZfFee() {
            return this.zfFee;
        }

        public void setZfFee(int zfFee) {
            this.zfFee = zfFee;
        }

        public int getGfFee() {
            return this.gfFee;
        }

        public void setGfFee(int gfFee) {
            this.gfFee = gfFee;
        }

        public String toString() {
            return (new GsonBuilder()).create().toJson(this);
        }
    }
}
