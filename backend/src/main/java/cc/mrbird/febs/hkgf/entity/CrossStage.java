package cc.mrbird.febs.hkgf.entity;

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

@Excel("cross_stage")
@Data
@Accessors(chain = true)
public class CrossStage implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String stageCode;

            private Integer startFee;

            private Integer endFee;

            private Integer startFlag;

            private Integer endFlag;

            private String intoHospitalId;

            private String outHospitalId;

            private String crossStageNo;

            private Integer rate;

            private Integer type;

    /**
     * 1：报销下浮比例   2：报销定比例，以此比例统一报销，基础比例失效
     */
            @ExcelField(value ="1：报销下浮比例   2：报销定比例，以此比例统一报销，基础比例失效")
    private Integer rateType;



    public static final String ID ="id" ;

    public static final String STAGE_CODE ="stage_code" ;

    public static final String START_FEE ="start_fee" ;

    public static final String END_FEE ="end_fee" ;

    public static final String START_FLAG ="start_flag" ;

    public static final String END_FLAG ="end_flag" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String CROSS_STAGE_NO ="cross_stage_no" ;

    public static final String RATE ="rate" ;

    public static final String TYPE ="type" ;

    public static final String RATE_TYPE ="rate_type" ;

        }