package cc.mrbird.febs.hkgf.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;

/**
 * <p>
 * 
 * </p>
 *
 * @author viki
 * @since 2020-09-28
 */

@Excel("gfjs_zzd")
@Data
@Accessors(chain = true)
public class GfjsZzd implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String zzdNo;

            private String userName;

            private Integer gender;

            private Integer age;

            private Integer userType;

            private String cardNo;

            private String illness;

            private String userPositionCode;

            private Date createTime;
    private transient String createTimeFrom;
    private transient String createTimeTo;

            private Integer validDays;

    /**
     * 转诊单状态： 1、生效   2、已完结   0、未生效  -1：失效、3、待审核
     */
            @ExcelField(value ="转诊单状态： 1、生效   2、已完结   0、未生效  -1：失效、3、待审核")
    private Integer status;

            private Date intoHospitalTime;
    private transient String intoHospitalTimeFrom;
    private transient String intoHospitalTimeTo;

            private String createHospitalId;

            private String receiveHospitalId;

            private Date outHospitalTime;
    private transient String outHospitalTimeFrom;
    private transient String outHospitalTimeTo;

            private String remark;

            private String deptName;

            private String deptTel;

            private String deptUserName;

            private String intoDeptName;

            private String bedNo;

            private String workUnit;

            private Integer zzdDiagnosisCode;

            private Integer intoDiagnosisCode;

            private Integer outDiagnosisCode;

            private String diagnosisSuggest;

            private String userSign;

            private String templateNo;

            private String registerNo;

            private String zzdType;

            private Date effectiveDate;
    private transient String effectiveDateFrom;
    private transient String effectiveDateTo;

            private String phone;

            private String intoDeptNo;

            private Long historyCost;

    /**
     * 当前转诊单从history_cost开始计算的累计住院总费用
     */
            @ExcelField(value ="当前转诊单从history_cost开始计算的累计住院总费用")
    private Long currentCost;

            private String zyNo;

    /**
     * 协和填入 可控
     */
            @ExcelField(value ="协和填入 可控")
    private String jzCardNo;

    /**
     * 结账出院时，是否需要重新结算的标志  0：不需要   1：需要
     */
            @ExcelField(value ="结账出院时，是否需要重新结算的标志  0：不需要   1：需要")
    private Integer redoFlag;

    /**
     * 创建转诊单用户account
     */
            @ExcelField(value ="创建转诊单用户account")
    private String createUserCode;

            private Long fee;

            private Long expenseFee;

            private Long ownFee;

    /**
     * 是否已经月结算： 0：未结算   1：已结算
     */
            @ExcelField(value ="是否已经月结算： 0：未结算   1：已结算")
    private Integer monthFlag;

            private String dhNo;

            private Integer summaryFlag;

            private String oldZzdNo;



    public static final String ID ="id" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String USER_NAME ="user_name" ;

    public static final String GENDER ="gender" ;

    public static final String AGE ="age" ;

    public static final String USER_TYPE ="user_type" ;

    public static final String CARD_NO ="card_no" ;

    public static final String ILLNESS ="illness" ;

    public static final String USER_POSITION_CODE ="user_position_code" ;

    public static final String CREATE_TIME ="create_time" ;

    public static final String VALID_DAYS ="valid_days" ;

    public static final String STATUS ="status" ;

    public static final String INTO_HOSPITAL_TIME ="into_hospital_time" ;

    public static final String CREATE_HOSPITAL_ID ="create_hospital_id" ;

    public static final String RECEIVE_HOSPITAL_ID ="receive_hospital_id" ;

    public static final String OUT_HOSPITAL_TIME ="out_hospital_time" ;

    public static final String REMARK ="remark" ;

    public static final String DEPT_NAME ="dept_name" ;

    public static final String DEPT_TEL ="dept_tel" ;

    public static final String DEPT_USER_NAME ="dept_user_name" ;

    public static final String INTO_DEPT_NAME ="into_dept_name" ;

    public static final String BED_NO ="bed_no" ;

    public static final String WORK_UNIT ="work_unit" ;

    public static final String ZZD_DIAGNOSIS_CODE ="zzd_diagnosis_code" ;

    public static final String INTO_DIAGNOSIS_CODE ="into_diagnosis_code" ;

    public static final String OUT_DIAGNOSIS_CODE ="out_diagnosis_code" ;

    public static final String DIAGNOSIS_SUGGEST ="diagnosis_suggest" ;

    public static final String USER_SIGN ="user_sign" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String REGISTER_NO ="register_no" ;

    public static final String ZZD_TYPE ="zzd_type" ;

    public static final String EFFECTIVE_DATE ="effective_date" ;

    public static final String PHONE ="phone" ;

    public static final String INTO_DEPT_NO ="into_dept_no" ;

    public static final String HISTORY_COST ="history_cost" ;

    public static final String CURRENT_COST ="current_cost" ;

    public static final String ZY_NO ="zy_no" ;

    public static final String JZ_CARD_NO ="jz_card_no" ;

    public static final String REDO_FLAG ="redo_flag" ;

    public static final String CREATE_USER_CODE ="create_user_code" ;

    public static final String FEE ="fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String MONTH_FLAG ="month_flag" ;

    public static final String DH_NO ="dh_no" ;

    public static final String SUMMARY_FLAG ="summary_flag" ;

    public static final String OLD_ZZD_NO ="old_zzd_no" ;

        }