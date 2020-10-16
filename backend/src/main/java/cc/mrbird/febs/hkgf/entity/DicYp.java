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
 * 药品字典表
 * </p>
 *
 * @author viki
 * @since 2020-09-28
 */

@Excel("dic_yp")
@Data
@Accessors(chain = true)
public class DicYp implements Serializable{

private static final long serialVersionUID=1L;

                    @TableId(value = "id" , type = IdType.AUTO)
                    private Integer id;

    /**
     * 自己加的序号
     */
            @ExcelField(value ="自己加的序号")
    private String udfxh;

    /**
     * 药品唯一序号
     */
            @ExcelField(value ="药品唯一序号")
    private String ypxh;

            private String ypmc;

    /**
     * 药品规格
     */
            @ExcelField(value ="药品规格")
    private String ypgg;

            private String abc;

    /**
     * 是否公费报账
     */
            @ExcelField(value ="是否公费报账")
    private String flagGfbz;

    /**
     * 医保保障标志
     */
            @ExcelField(value ="医保保障标志")
    private String flagYbbz;

    /**
     * 自费报账标志
     */
            @ExcelField(value ="自费报账标志")
    private String flagZfbz;

    /**
     * 是否控制药品
     */
            @ExcelField(value ="是否控制药品")
    private String flagKzyp;

            private String bxbl;

            private Integer xsMz;

            private Integer xsZy;

            private Integer zgMz;

            private Integer zgZy;

            private Integer txMz;

            private Integer txZy;

            private Integer lxMz;

            private Integer lxZy;

    /**
     * 0:否  1：是
     */
            @ExcelField(value ="0:否  1：是")
    private Integer zfFlag;



    public static final String ID ="id" ;

    public static final String UDFXH ="udfxh" ;

    public static final String YPXH ="ypxh" ;

    public static final String YPMC ="ypmc" ;

    public static final String YPGG ="ypgg" ;

    public static final String ABC ="abc" ;

    public static final String FLAG_GFBZ ="flag_gfbz" ;

    public static final String FLAG_YBBZ ="flag_ybbz" ;

    public static final String FLAG_ZFBZ ="flag_zfbz" ;

    public static final String FLAG_KZYP ="flag_kzyp" ;

    public static final String BXBL ="bxbl" ;

    public static final String XS_MZ ="xs_mz" ;

    public static final String XS_ZY ="xs_zy" ;

    public static final String ZG_MZ ="zg_mz" ;

    public static final String ZG_ZY ="zg_zy" ;

    public static final String TX_MZ ="tx_mz" ;

    public static final String TX_ZY ="tx_zy" ;

    public static final String LX_MZ ="lx_mz" ;

    public static final String LX_ZY ="lx_zy" ;

    public static final String ZF_FLAG ="zf_flag" ;

        }