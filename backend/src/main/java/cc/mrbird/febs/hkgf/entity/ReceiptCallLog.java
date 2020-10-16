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

@Excel("receipt_call_log")
@Data
@Accessors(chain = true)
public class ReceiptCallLog implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String intoHospitalId;

            private String outHospitalId;

            private String receiptNo;

            private String zzdNo;

            private Date intoDate;
    private transient String intoDateFrom;
    private transient String intoDateTo;

            private Date receiveDate;
    private transient String receiveDateFrom;
    private transient String receiveDateTo;



    public static final String ID ="id" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String INTO_DATE ="into_date" ;

    public static final String RECEIVE_DATE ="receive_date" ;

        }