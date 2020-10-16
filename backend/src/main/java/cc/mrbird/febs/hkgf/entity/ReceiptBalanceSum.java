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

@Excel("receipt_balance_sum")
@Data
@Accessors(chain = true)
public class ReceiptBalanceSum implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String zzdNo;

            private String receiptNo;

            private Integer fee;

            private Integer ownFee;

            private Integer expenseFee;

            private String templateNo;

            private Integer majorCatalogues;

            private String stageCode;

            private Date intoTime;
    private transient String intoTimeFrom;
    private transient String intoTimeTo;



    public static final String ID ="id" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String FEE ="fee" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String STAGE_CODE ="stage_code" ;

    public static final String INTO_TIME ="into_time" ;

        }