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

@Excel("user_balance_sum")
@Data
@Accessors(chain = true)
public class UserBalanceSum implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String userName;

            private String cardNo;

            private String zzdNo;

            private Integer fee;

            private Integer ownFee;

            private Integer expenseFee;

            private Integer majorCatalogues;

            private String stageCode;

            private Date staticDate;
    private transient String staticDateFrom;
    private transient String staticDateTo;

            private Date intoTime;
    private transient String intoTimeFrom;
    private transient String intoTimeTo;



    public static final String ID ="id" ;

    public static final String USER_NAME ="user_name" ;

    public static final String CARD_NO ="card_no" ;

    public static final String ZZD_NO ="zzd_no" ;

    public static final String FEE ="fee" ;

    public static final String OWN_FEE ="own_fee" ;

    public static final String EXPENSE_FEE ="expense_fee" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String STAGE_CODE ="stage_code" ;

    public static final String STATIC_DATE ="static_date" ;

    public static final String INTO_TIME ="into_time" ;

        }