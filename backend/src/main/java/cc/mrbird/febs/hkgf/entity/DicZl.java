package cc.mrbird.febs.hkgf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

@Excel("dic_zl")
@Data
@Accessors(chain = true)
public class DicZl implements Serializable{

private static final long serialVersionUID=1L;

                    @TableId(value = "id" , type = IdType.AUTO)
                    private Integer id;

            private String fyxh;

    /**
     * 费用名称
     */
            @ExcelField(value ="费用名称")
    private String fymc;

    /**
     * 费用单位
     */
            @ExcelField(value ="费用单位")
    private String fydw;

    /**
     * 费用国标
     */
            @ExcelField(value ="费用国标")
    private String fygb;

    /**
     * 项目编码
     */
            @ExcelField(value ="项目编码")
    private String xmbm;

    /**
     * 是否公费报账
     */
            @ExcelField(value ="是否公费报账")
    private String flagGfbz;

    /**
     * 是否医保报账
     */
            @ExcelField(value ="是否医保报账")
    private String flagYbbz;

    /**
     * 是否自费报账
     */
            @ExcelField(value ="是否自费报账")
    private String flagZfbz;

            private String tsbl;

            private Integer xsMzRate;

            private Integer xsZyRate;

            private Integer zgMzRate;

            private Integer zgZyRate;

            private Integer txMzRate;

            private Integer txZyRate;

            private Integer lxMzRate;

            private Integer lxZyRate;



    public static final String ID ="id" ;

    public static final String FYXH ="fyxh" ;

    public static final String FYMC ="fymc" ;

    public static final String FYDW ="fydw" ;

    public static final String FYGB ="fygb" ;

    public static final String XMBM ="xmbm" ;

    public static final String FLAG_GFBZ ="flag_gfbz" ;

    public static final String FLAG_YBBZ ="flag_ybbz" ;

    public static final String FLAG_ZFBZ ="flag_zfbz" ;

    public static final String TSBL ="tsbl" ;

    public static final String XS_MZ_RATE ="xs_mz_rate" ;

    public static final String XS_ZY_RATE ="xs_zy_rate" ;

    public static final String ZG_MZ_RATE ="zg_mz_rate" ;

    public static final String ZG_ZY_RATE ="zg_zy_rate" ;

    public static final String TX_MZ_RATE ="tx_mz_rate" ;

    public static final String TX_ZY_RATE ="tx_zy_rate" ;

    public static final String LX_MZ_RATE ="lx_mz_rate" ;

    public static final String LX_ZY_RATE ="lx_zy_rate" ;

        }