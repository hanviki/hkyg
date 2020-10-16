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

@Excel("hospital_detail_tmp")
@Data
@Accessors(chain = true)
public class HospitalDetailTmp implements Serializable{

private static final long serialVersionUID=1L;

            private String detailNo;

            private String hospitalId;

            private String detailType;

            private String majorCatalogues;

            private String detailPrice;

            private String detailSpec;

            private String detailName;

            private String detailXmbm;

            private Date createTime;
    private transient String createTimeFrom;
    private transient String createTimeTo;

            private Date handleTime;
    private transient String handleTimeFrom;
    private transient String handleTimeTo;

    /**
     * 0：未处理；1：已处理；2：处理失败
     */
            @ExcelField(value ="0：未处理；1：已处理；2：处理失败")
    private Integer status;

    /**
     * 处理失败说明
     */
            @ExcelField(value ="处理失败说明")
    private String remark;

            private String cardNo;

            private String userName;

                                private String id;

            private String abc;



    public static final String DETAIL_NO ="detail_no" ;

    public static final String HOSPITAL_ID ="hospital_id" ;

    public static final String DETAIL_TYPE ="detail_type" ;

    public static final String MAJOR_CATALOGUES ="major_catalogues" ;

    public static final String DETAIL_PRICE ="detail_price" ;

    public static final String DETAIL_SPEC ="detail_spec" ;

    public static final String DETAIL_NAME ="detail_name" ;

    public static final String DETAIL_XMBM ="detail_xmbm" ;

    public static final String CREATE_TIME ="create_time" ;

    public static final String HANDLE_TIME ="handle_time" ;

    public static final String STATUS ="status" ;

    public static final String REMARK ="remark" ;

    public static final String CARD_NO ="card_no" ;

    public static final String USER_NAME ="user_name" ;

    public static final String ID ="id" ;

    public static final String ABC ="abc" ;

        }