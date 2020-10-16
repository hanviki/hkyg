package cc.mrbird.febs.hkgf.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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

@Excel("drug_enter_record")
@Data
@Accessors(chain = true)
public class DrugEnterRecord implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

    @TableField("YPCD")
            private Integer ypcd;

    @TableField("CDMC")
            private String cdmc;

    @TableField("PYDM")
            private String pydm;

    @TableField("CDQC")
            private String cdqc;

    /**
     * 0-未处理，1-进口，2-非进口
     */
    @TableField("IS_ENTER")
            @ExcelField(value ="0-未处理，1-进口，2-非进口")
    private Integer isEnter;

    @TableField("CREATE_TIME")
            private Date createTime;
    private transient String createTimeFrom;
    private transient String createTimeTo;



    public static final String ID ="id" ;

    public static final String YPCD ="YPCD" ;

    public static final String CDMC ="CDMC" ;

    public static final String PYDM ="PYDM" ;

    public static final String CDQC ="CDQC" ;

    public static final String IS_ENTER ="IS_ENTER" ;

    public static final String CREATE_TIME ="CREATE_TIME" ;

        }