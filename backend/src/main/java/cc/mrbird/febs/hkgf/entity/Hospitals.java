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

@Excel("hospitals")
@Data
@Accessors(chain = true)
public class Hospitals implements Serializable{

private static final long serialVersionUID=1L;

                                private String id;

            private String name;

    /**
     * 1:三甲医院    2：
     */
            @ExcelField(value ="1:三甲医院    2：")
    private Integer type;



    public static final String ID ="id" ;

    public static final String NAME ="name" ;

    public static final String TYPE ="type" ;

        }