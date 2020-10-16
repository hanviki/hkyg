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

@Excel("receipt_rolls")
@Data
@Accessors(chain = true)
public class ReceiptRolls implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String receiptNo;

            private String userName;

            private String cardNo;

            private String cardId;

            private Date receiptCreateTime;
    private transient String receiptCreateTimeFrom;
    private transient String receiptCreateTimeTo;

            private Date intoTime;
    private transient String intoTimeFrom;
    private transient String intoTimeTo;

            private String zzdNo;

            private String billDept;

            private String exeDept;

            private String intoHospitalId;

            private String admissionNumber;

            private String receiptType;

            private Date startDate;
    private transient String startDateFrom;
    private transient String startDateTo;

            private Date endDate;
    private transient String endDateFrom;
    private transient String endDateTo;

            private Integer dateNum;

            private String outHospitalId;

    /**
     * 结算状态： 0：未结算   1：已结算
     */
            @ExcelField(value ="结算状态： 0：未结算   1：已结算")
    private Integer status;



    public static final String ID ="id" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String USER_NAME ="user_name" ;

    public static final String CARD_NO ="card_no" ;

    public static final String CARD_ID ="card_id" ;

    public static final String RECEIPT_CREATE_TIME ="receipt_create_time" ;

    public static final String INTO_TIME ="into_time" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String BILL_DEPT ="bill_dept" ;

    public static final String EXE_DEPT ="exe_dept" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String ADMISSION_NUMBER ="admission_number" ;

    public static final String RECEIPT_TYPE ="receipt_type" ;

    public static final String START_DATE ="start_date" ;

    public static final String END_DATE ="end_date" ;

    public static final String DATE_NUM ="date_num" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String STATUS ="status" ;

        }