package cc.mrbird.febs.hkgf.entity;

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

@Excel("out_hospital_info")
@Data
@Accessors(chain = true)
public class OutHospitalInfo implements Serializable{

private static final long serialVersionUID=1L;

    /**
     * 编号
     */
                                @ExcelField(value ="编号")
    private String id;

    /**
     * 转诊单号
     */
            @ExcelField(value ="转诊单号")
    private String zzdNo;

    /**
     * 入院日期
     */
            @ExcelField(value ="入院日期")
    private Date intoDate;
    private transient String intoDateFrom;
    private transient String intoDateTo;

    /**
     * 出院情况
     */
            @ExcelField(value ="出院情况")
    private String outInfo;

    /**
     * 入院天数
     */
            @ExcelField(value ="入院天数")
    private Integer intoDayNum;

    /**
     * 入院情况
     */
            @ExcelField(value ="入院情况")
    private String intoInfo;

    /**
     * 入院诊断
     */
            @ExcelField(value ="入院诊断")
    private String intoDiagnosis;

    /**
     * 辅助检查
     */
            @ExcelField(value ="辅助检查")
    private String assistantCheck;

    /**
     * 诊疗经过
     */
            @ExcelField(value ="诊疗经过")
    private String treatment;

    /**
     * 出院诊断
     */
            @ExcelField(value ="出院诊断")
    private String outDiagnosis;

    /**
     * 出院医嘱
     */
            @ExcelField(value ="出院医嘱")
    private String outOrder;

    /**
     * 带药医嘱
     */
            @ExcelField(value ="带药医嘱")
    private String medicalOrder;

    /**
     * 生活医嘱
     */
            @ExcelField(value ="生活医嘱")
    private String lifeOrder;

    /**
     * 复诊医嘱
     */
            @ExcelField(value ="复诊医嘱")
    private String doctorAdvice;

    /**
     * 其他检查
     */
            @ExcelField(value ="其他检查")
    private String otherCheckNo;



    public static final String ID ="id" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String INTO_DATE ="into_date" ;

    public static final String OUT_INFO ="out_info" ;

    public static final String INTO_DAY_NUM ="into_day_num" ;

    public static final String INTO_INFO ="into_info" ;

    public static final String INTO_DIAGNOSIS ="into_diagnosis" ;

    public static final String ASSISTANT_CHECK ="assistant_check" ;

    public static final String TREATMENT ="treatment" ;

    public static final String OUT_DIAGNOSIS ="out_diagnosis" ;

    public static final String OUT_ORDER ="out_order" ;

    public static final String MEDICAL_ORDER ="medical_order" ;

    public static final String LIFE_ORDER ="life_order" ;

    public static final String DOCTOR_ADVICE ="doctor_advice" ;

    public static final String OTHER_CHECK_NO ="other_check_no" ;

        }