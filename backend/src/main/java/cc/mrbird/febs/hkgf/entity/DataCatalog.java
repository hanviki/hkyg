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

@Excel("data_catalog")
@Data
@Accessors(chain = true)
public class DataCatalog implements Serializable{

private static final long serialVersionUID=1L;

                                private Integer id;

            private String name;

            private Integer mzNo;

            private Integer zyNo;

            private Integer status;

            private Date createTm;
    private transient String createTmFrom;
    private transient String createTmTo;



    public static final String ID ="id" ;

    public static final String NAME ="name" ;

    public static final String MZ_NO ="mz_no" ;

    public static final String ZY_NO ="zy_no" ;

    public static final String STATUS ="status" ;

    public static final String CREATE_TM ="create_tm" ;

        }