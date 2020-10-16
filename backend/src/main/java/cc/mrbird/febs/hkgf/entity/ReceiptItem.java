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

@Excel("receipt_item")
@Data
@Accessors(chain = true)
public class ReceiptItem implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String receiptNo;

            private String detailNo;

            private String projectCode;

            private String receiptItemName;

            private String receiptItemType;

            private String receiptItemSpec;

            private String receiptItemUnit;

            private Double receiptItemNum;

            private Integer unitFee;

            private Integer fee;

            private Integer majorCatalogues;

            private String receiptItemCode;

    /**
     * 是否进口药品  0：非进口   1：进口
     */
            @ExcelField(value ="是否进口药品  0：非进口   1：进口")
    private Integer isImp;

    /**
     * 结算标志  0：未结算   1：已结算
     */
            @ExcelField(value ="结算标志  0：未结算   1：已结算")
    private Integer status;

            private String billDept;

            private String exeDept;

    /**
     * 院方收费条目的唯一记录id
     */
            @ExcelField(value ="院方收费条目的唯一记录id")
    private String detailId;

            private Date chargeDate;
    private transient String chargeDateFrom;
    private transient String chargeDateTo;



    public static final String ID ="id" ;

    public static final String RECEIPT_NO ="receipt_no" ;

    public static final String DETAIL_NO ="detail_no" ;

    public static final String PROJECT_CODE ="project_code" ;

    public static final String RECEIPT_ITEM_NAME ="receipt_item_name" ;

    public static final String RECEIPT_ITEM_TYPE ="receipt_item_type" ;

    public static final String RECEIPT_ITEM_SPEC ="receipt_item_spec" ;

    public static final String RECEIPT_ITEM_UNIT ="receipt_item_unit" ;

    public static final String RECEIPT_ITEM_NUM ="receipt_item_num" ;

    public static final String UNIT_FEE ="unit_fee" ;

    public static final String FEE ="fee" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String RECEIPT_ITEM_CODE ="receipt_item_code" ;

    public static final String IS_IMP ="is_imp" ;

    public static final String STATUS ="status" ;

    public static final String BILL_DEPT ="bill_dept" ;

    public static final String EXE_DEPT ="exe_dept" ;

    public static final String DETAIL_ID ="detail_id" ;

    public static final String CHARGE_DATE ="charge_date" ;

        }