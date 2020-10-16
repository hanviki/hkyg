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

@Excel("hk_xh_common_rule")
@Data
@Accessors(chain = true)
public class HkXhCommonRule implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String detailNo;

    /**
     * 条目名称
     */
            @ExcelField(value ="条目名称")
    private String detailName;

            private String detailType;

            private Integer majorCatalogues;

            private Integer detailPrice;

            private String detailSpec;

            private Integer xsMzRate;

            private Integer xsZyRate;

            private Integer zgMzRate;

            private Integer zgZyRate;

            private Integer txMzRate;

            private Integer txZyRate;

            private Integer lxMzRate;

            private Integer lxZyRate;

            private String templateNo;

    /**
     * 0:非全自费   1：全自费
     */
            @ExcelField(value ="0:非全自费   1：全自费")
    private Integer zfFlag;



    public static final String ID ="id" ;

    public static final String DETAIL_NO ="detail_no" ;

    public static final String DETAIL_NAME ="detail_name" ;

    public static final String DETAIL_TYPE ="detail_type" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String DETAIL_PRICE ="detail_price" ;

    public static final String DETAIL_SPEC ="detail_spec" ;

    public static final String XS_MZ_RATE ="xs_mz_rate" ;

    public static final String XS_ZY_RATE ="xs_zy_rate" ;

    public static final String ZG_MZ_RATE ="zg_mz_rate" ;

    public static final String ZG_ZY_RATE ="zg_zy_rate" ;

    public static final String TX_MZ_RATE ="tx_mz_rate" ;

    public static final String TX_ZY_RATE ="tx_zy_rate" ;

    public static final String LX_MZ_RATE ="lx_mz_rate" ;

    public static final String LX_ZY_RATE ="lx_zy_rate" ;

    public static final String TEMPLATE_NO ="template_no" ;

    public static final String ZF_FLAG ="zf_flag" ;

        }