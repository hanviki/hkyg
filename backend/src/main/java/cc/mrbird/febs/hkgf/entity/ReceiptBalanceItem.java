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

@Excel("receipt_balance_item")
@Data
@Accessors(chain = true)
public class ReceiptBalanceItem implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String receiptNo;

            private Date chargeDate;
    private transient String chargeDateFrom;
    private transient String chargeDateTo;

            private String receiptItemCode;

            private String receiptItemType;

            private String receiptItemName;

            private String receiptItemSpec;

            private String receiptItemUnit;

            private Integer receiptItemNum;

            private String detailNo;

            private Integer unitFee;

            private Integer fee;

            private String zzdNo;

            private Integer majorCatalogues;

            private Integer ownFee;

            private Integer expenseFee;

            private Integer expenseRate;

    /**
     * 报销标志：  1：自费   2：常规报销  3：特殊报销  4：固定额度
     */
            @ExcelField(value ="报销标志：  1：自费   2：常规报销  3：特殊报销  4：固定额度")
    private Integer expenseFlag;

            private String templateNo;

            private String stageCode;

            private String intoHospitalId;

            private String outHospitalId;

            private Date intoTime;
    private transient String intoTimeFrom;
    private transient String intoTimeTo;



    public static final String ID ="id" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String CHARGE_DATE ="charge_date" ;

    public static final String RECEIPT_ITEM_CODE ="receipt_item_code" ;

    public static final String RECEIPT_ITEM_TYPE ="receipt_item_type" ;

    public static final String RECEIPT_ITEM_NAME ="receipt_item_name" ;

    public static final String RECEIPT_ITEM_SPEC ="receipt_item_spec" ;

    public static final String RECEIPT_ITEM_UNIT ="receipt_item_unit" ;

    public static final String RECEIPT_ITEM_NUM ="receipt_item_num" ;

    public static final String DETAIL_NO ="detail_no" ;

    public static final String UNIT_FEE ="unit_fee" ;

    public static final String FEE ="fee" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String EXPENSE_RATE ="expense_rate" ;

    public static final String EXPENSE_FLAG ="expense_flag" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String STAGE_CODE ="stage_code" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String INTO_TIME ="into_time" ;

        }