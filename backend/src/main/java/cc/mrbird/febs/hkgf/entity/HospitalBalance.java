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

@Excel("hospital_balance")
@Data
@Accessors(chain = true)
public class HospitalBalance implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String intoHospitalId;

            private String outHospitalId;

            private Date balanceDate;
    private transient String balanceDateFrom;
    private transient String balanceDateTo;

            private Integer fee;

            private Integer expenseFee;

            private Integer ownFee;

    /**
     * 0:未结算    1：已结算    2：已转账
     */
            @ExcelField(value ="0:未结算    1：已结算    2：已转账")
    private Integer balanceFlag;

            private Date intoDate;
    private transient String intoDateFrom;
    private transient String intoDateTo;

            private Integer chargeMonth;

            private Integer chargeYear;



    public static final String ID ="id" ;

    public static final String INTO_HOSPITAL_ID ="into_hospital_id" ;

    public static final String OUT_HOSPITAL_ID ="out_hospital_id" ;

    public static final String BALANCE_DATE ="balance_date" ;

    public static final String FEE ="fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String BALANCE_FLAG ="balance_flag" ;

    public static final String INTO_DATE ="into_date" ;

    public static final String CHARGE_MONTH ="charge_month" ;

    public static final String CHARGE_YEAR ="charge_year" ;

        }