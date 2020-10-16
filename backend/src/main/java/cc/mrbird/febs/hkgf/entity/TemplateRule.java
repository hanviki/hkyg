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

@Excel("template_rule")
@Data
@Accessors(chain = true)
public class TemplateRule implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String templateNo;

            private String templateName;

    /**
     * 模版状态：启用1,  禁用 2
     */
            @ExcelField(value ="模版状态：启用1,  禁用 2")
    private Integer status;

            private String createUser;

            private Date playTime;
    private transient String playTimeFrom;
    private transient String playTimeTo;

            private String outHospitalId;

            private Date stopTime;
    private transient String stopTimeFrom;
    private transient String stopTimeTo;

            private String remark;

            private String intoHospitalId;

            private String crossStageNo;

            private Date createTime;
    private transient String createTimeFrom;
    private transient String createTimeTo;



    public static final String ID ="id" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String TEMPLATE_NAME ="template_name" ;

    public static final String STATUS ="status" ;

    public static final String CREATE_USER ="create_user" ;

    public static final String PLAY_TIME ="play_time" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String STOP_TIME ="stop_time" ;

    public static final String REMARK ="remark" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String CROSS_STAGE_NO ="cross_stage_no" ;

    public static final String CREATE_TIME ="create_time" ;

        }