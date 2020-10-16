package cc.mrbird.febs.hkgf.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static final String ZL_BM = "-zl";
    public static final String FILL_PATH = "D:/FILE";
    public static Map<String, String> mxMap = new HashMap();
    public static Map<String, String> operationMap = new HashMap();
    public static Map<String, String> dataCatalogMap = new HashMap();
    public static List<String> ypcds = new ArrayList();

    public Constant() {
    }

    public static Map<String, String> getOperationMap() {
        return operationMap;
    }

    public static void setOperationMap(Map<String, String> operationMap) {
        Constant.operationMap = operationMap;
    }

    public static Map<String, String> getDataCatalogMap() {
        return dataCatalogMap;
    }

    public static void setDataCatalogMap(Map<String, String> dataCatalogMap) {
        Constant.dataCatalogMap = dataCatalogMap;
    }

    public static Map<String, String> getMxMap() {
        return mxMap;
    }

    public static void setMxMap(Map<String, String> mxMap) {
        Constant.mxMap = mxMap;
    }

    public static List<String> getYpcds() {
        return ypcds;
    }

    public static void setYpcds(List<String> ypcds) {
        Constant.ypcds = ypcds;
    }

    public interface CHECK_DATA_RESULT {
        int DATA_NOT_EXIST = -1;
        int CHECK_NOT_OK = -2;
    }

    public interface CHECK_TYPE {
        int OUT_CHECK = 2;
        int DISPLAY_CHECK = 1;
        int MZ_CHECK = 3;
    }

    public interface DiganosCode {
        Integer SHUN_CHAN = 1;
        Integer POU_FU_CHAN = 2;
        Integer OTHER = 3;
    }

    public interface HospitalType {
        String XH = "xh";
        String HK = "hk";
    }

    public interface ReceiptStatus {
        Integer UN_SETTLE = 0;
        Integer SETTLED = 1;
    }

    public interface UserType {
        int STUDEND = 1;
        int STAFF = 2;
        int LIXIU = 3;
        int RETIRE = 4;
    }

    public interface ZzdStatus {
        Integer UN_ACTIVE = 0;
        Integer ACTIVED = 1;
        Integer FINISHED = 2;
        Integer EXPIRED = -1;
    }

    public interface ZzdType {
        Integer ZY = 1;
        Integer MZ = 2;
        Integer JZ = 3;
    }
}