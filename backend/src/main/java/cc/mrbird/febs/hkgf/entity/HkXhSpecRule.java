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

@Excel("hk_xh_spec_rule")
@Data
@Accessors(chain = true)
public class HkXhSpecRule implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String detailNo;

            private String detailType;

    /**
     * 明细三大类目
     */
            @ExcelField(value ="明细三大类目")
    private Integer majorCatalogues;

    /**
     * 单价
     */
            @ExcelField(value ="单价")
    private Integer detailPrice;

    /**
     * 规格
     */
            @ExcelField(value ="规格")
    private String detailSpec;

    /**
     * 用户类别  1：学生  2、在职职工  3、退休职工   4、离休干部
     */
            @ExcelField(value ="用户类别  1：学生  2、在职职工  3、退休职工   4、离休干部")
    private Integer userType;

    /**
     * 用户职称编码
     */
            @ExcelField(value ="用户职称编码")
    private String userPositionNo;

    /**
     * 满减额度  单位：分
     */
            @ExcelField(value ="满减额度  单位：分")
    private Integer fullQuota;

    /**
     * 折扣比例
     */
            @ExcelField(value ="折扣比例")
    private Integer discountRate;

    /**
     * 报销比例
     */
            @ExcelField(value ="报销比例")
    private Integer bxRate;

    /**
     * 定额额度
     */
            @ExcelField(value ="定额额度")
    private Integer fixCost;

    /**
     * 类别  1：比例类型  2：定额  3：满减类型（目前不错，保留）
     */
            @ExcelField(value ="类别  1：比例类型  2：定额  3：满减类型（目前不错，保留）")
    private Integer type;

            private String templateNo;

    /**
     * 就诊类别，对应转诊单类别  1：住院  2：门诊  3：急诊
     */
            @ExcelField(value ="就诊类别，对应转诊单类别  1：住院  2：门诊  3：急诊")
    private Integer jzType;

            private String detailName;



    public static final String ID ="id" ;

    public static final String DETAIL_NO ="detail_no" ;

    public static final String DETAIL_TYPE ="detail_type" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String DETAIL_PRICE ="detail_price" ;

    public static final String DETAIL_SPEC ="detail_spec" ;

    public static final String USER_TYPE ="user_type" ;

    public static final String USER_POSITION_NO ="user_position_no" ;

    public static final String FULL_QUOTA ="full_quota" ;

    public static final String DISCOUNT_RATE ="discount_rate" ;

    public static final String BX_RATE ="bx_rate" ;

    public static final String FIX_COST ="fix_cost" ;

    public static final String TYPE ="type" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String JZ_TYPE ="jz_type" ;

    public static final String DETAIL_NAME ="detail_name" ;

        }