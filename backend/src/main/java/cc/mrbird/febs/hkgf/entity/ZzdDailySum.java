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

@Excel("zzd_daily_sum")
@Data
@Accessors(chain = true)
public class ZzdDailySum implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String zzdNo;

            private String intoHospitalId;

            private String receiptNo;

            private Date chargeDate;
    private transient String chargeDateFrom;
    private transient String chargeDateTo;

            private String chargeMonth;

            private Integer fee;

            private Integer ownFee;

            private Integer expenseFee;

            private String templateNo;

            private Integer majorCatalogues;

            private String stageCode;

            private Date intoTime;
    private transient String intoTimeFrom;
    private transient String intoTimeTo;

    /**
     * 是否已经读取过
     */
            @ExcelField(value ="是否已经读取过")
    private Integer isRead;

    /**
     * 月统计标志  0：为统计   1：已统计
     */
            @ExcelField(value ="月统计标志  0：为统计   1：已统计")
    private Integer monthFlag;



    public static final String ID ="id" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String CHARGE_DATE ="charge_date" ;

    public static final String CHARGE_MONTH ="charge_month" ;

    public static final String FEE ="fee" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String STAGE_CODE ="stage_code" ;

    public static final String INTO_TIME ="into_time" ;

    public static final String IS_READ ="is_read" ;

    public static final String MONTH_FLAG ="month_flag" ;

        }