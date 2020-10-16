package cc.mrbird.febs.hkgf.utils;

public enum ResultCodeEnum {
    SUCCESS("1", "Success"),
    FAIL("-1", "FAIL"),
    PARAM_ERROR("40001", " 请求参数错误"),
    PARAM_ERROR_SFZH("41000", "缺少患者身份证号"),
    PARAM_ERROR_YYBH("41001", "缺少医院编号"),
    PARAM_ERROR_JZRQ("41002", "缺少就诊日期"),
    PARAM_ERROR_ZZLX("41003", "缺少转诊类型"),
    PARAM_ERROR_FPBH("41004", "缺少发票编号"),
    PARAM_ERROR_JZBH("41005", "缺少就诊编号"),
    PARAM_ERROR_JZKH("41006", "缺少就诊卡号"),
    PARAM_ERROR_RYRQ("41007", "缺少入院日期"),
    PARAM_ERROR_ZYKSBM("41008", "缺少住院科室编号"),
    PARAM_ERROR_ZYKS("41009", "缺少住院科室"),
    PARAM_ERROR_ZYHM("41010", "缺少住院号码"),
    PARAM_ERROR_ZZDH("41011", "缺少转诊单号"),
    PARAM_ERROR_HZXM("41012", "缺少患者姓名"),
    PARAM_ERROR_SJZJE("41013", "缺少收据总金额"),
    PARAM_ERROR_CYKS("41014", "缺少出院科室"),
    PARAM_ERROR_CYRQ("41015", "缺少出院日期"),
    PARAM_ERROR_ZYTS("41016", "缺少住院天数"),
    PARAM_ERROR_JZJLH("41017", "缺少就诊记录号"),
    PARAM_ERROR_SJDH("41018", "缺少收据单号"),
    PARAM_ERROR_SJMXZTS("41019", "缺少收据明细总数"),
    PARAM_ERROR_MXBH("41020", "缺少明细编号"),
    PARAM_ERROR_XMBMCF("41021", "项目编码重复"),
    PARAM_ERROR_YPWSZ("41022", "药品比例未设置"),
    PARAM_ERROR_CWF("41023", "床位费未设置"),
    PARAM_ERROR_JSRQ("41024", "缺少结算日期"),
    ACCOUNT_NULL("40002", "账号鉴权错误"),
    DATA_NOT_EXIST("40003", "药品数据不存在"),
    CHECK_NOT_OK("40004", "数据检查不通过，总金额或项目数量不一致"),
    ACCOUNT_NOT_EXIST("40005", "转诊单号不存在"),
    ZYH_NOT_EXIST("40006", "住院号不存在"),
    ZZD_IS_EXIST("40007", "已有生效的转诊单，不允许重复开具转诊单"),
    USER_TYPE_ERROR("40008", "学生不允许门诊转住院"),
    NOT_ALLOW_JS("40009", "患者住院期间不允许进行门诊结算"),
    NOT_EXIST_PATIENT("40010", "不存在该患者"),
    ZZD_NOT_EXIST("40011", "转诊单不存在，请联系华科工作人员"),
    NO_ALLOW_ZY_TO_ZM("40012", "住院期间不允许开门诊转诊单"),
    NO_ALLOW_ZM_TO_ZY_ST("40013", "学生不允许门诊转住院"),
    ACCESSTOKEN_BLANK("mall-404", "token为空"),
    ACCESSTOKEN_INVALID("mall-405", "token非法"),
    SERVER_INTERNAL_ERROR("50000", "server internal error"),
    DB_OPERATE_ERROR("50001", "DB error"),
    LOW_STOCKS("mall-502", "库存不足"),
    OTHER_ERROR("50002", "other error"),
    ZZD_STATUS_ERROR("50003", "转诊单状态异常");

    private String code;
    private String desc;

    private ResultCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }

    public static void main(String[] args) {
        System.out.println(SUCCESS.getCode() + " - " + SUCCESS.getDesc());
        System.out.println(SUCCESS.getCode());
    }
}

