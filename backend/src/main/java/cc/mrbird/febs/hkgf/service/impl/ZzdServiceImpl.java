package cc.mrbird.febs.hkgf.service.impl;

import cc.mrbird.febs.hkgf.entity.*;
import com.google.gson.Gson;
import cc.mrbird.febs.hkgf.config.Constant;
import cc.mrbird.febs.hkgf.config.Constant.ZzdStatus;
import cc.mrbird.febs.hkgf.config.Constant.ZzdType;
import cc.mrbird.febs.hkgf.dao.GfjsZzdMapper;
import cc.mrbird.febs.hkgf.dao.HospitalDetailTmpMapper;
import cc.mrbird.febs.hkgf.dao.ReceiptBalanceItemMapper;
import cc.mrbird.febs.hkgf.dao.ReceiptItemMapper;
import cc.mrbird.febs.hkgf.dao.ReceiptRollsMapper;
import cc.mrbird.febs.hkgf.entity.HospitalDetailTmp;
import cc.mrbird.febs.hkgf.entity.ZzdTypeEntity;
import cc.mrbird.febs.hkgf.entity.request.CancelChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelOutPatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.CancelRegistrationRequest;
import cc.mrbird.febs.hkgf.entity.request.DailyQueryRequest;
import cc.mrbird.febs.hkgf.entity.request.FlagChangeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeItemData;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.InpatientChargeTotalData;
import cc.mrbird.febs.hkgf.entity.request.InpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.MakeSureUserRequest;
import cc.mrbird.febs.hkgf.entity.request.MoreReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.request.OutpatientItemData;
import cc.mrbird.febs.hkgf.entity.request.OutpatientRequest;
import cc.mrbird.febs.hkgf.entity.request.ReadyToChargeRequest;
import cc.mrbird.febs.hkgf.entity.response.CYJXInfoEntity;
import cc.mrbird.febs.hkgf.entity.response.DailyQueryResponse;
import cc.mrbird.febs.hkgf.entity.response.HzBean;
import cc.mrbird.febs.hkgf.entity.response.MakeSureUserResponse;
import cc.mrbird.febs.hkgf.service.ZzdService;
import cc.mrbird.febs.hkgf.utils.DateUtils;
import cc.mrbird.febs.hkgf.utils.ZzdhUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ZzdServiceImpl implements ZzdService {
    @Autowired
    private GfjsZzdMapper zzdMapper;
    @Autowired
    private ReceiptItemMapper receiptItemMapper;
    @Autowired
    private ReceiptRollsMapper receiptRollsMapper;
    @Autowired
    private ReceiptBalanceItemMapper receiptBalanceItemMapper;
    @Autowired
    private HospitalDetailTmpMapper hospitalDetailTmpMapper;
    @Autowired
    private ZzdhUtils zzdhUtils;
    @Autowired
    private DateUtils dateUtils;

    public ZzdServiceImpl() {
    }

    @Override
    public MakeSureUserResponse queryZzdByUserInfo(MakeSureUserRequest request) {
        Map<String, Object> result = this.zzdMapper.selectZzdByUserInfo(request);
        if (result != null) {
            MakeSureUserResponse response = new MakeSureUserResponse();
            int validDay = Integer.valueOf(result.get("DAYS").toString());
            Date date = (Date)result.get("CJSJ");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(5, validDay);
            date = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            response.setZZYXRQ(dateString);
            response.setZGLB(result.get("ZGLB").toString());
            response.setXB(result.get("XB").toString());
            response.setDWMC(result.get("DWMC").toString());
            response.setXM(result.get("XM").toString());
            response.setZZYYMC(result.get("ZZYYMC").toString());
            response.setZZYYBH(result.get("ZZYYBH").toString());
            response.setZZDH(result.get("ZZDH").toString());
            ZzdTypeEntity entity = new ZzdTypeEntity();
            entity.setZZDH(response.getZZDH());
            entity.setZZDLX(request.getZZDLX());
            this.zzdMapper.updateZZDLX(entity);
            return response;
        } else {
            return null;
        }
    }
@Override
    public int inpatientHospital(InpatientRequest request) {
        int result = this.zzdMapper.updateHospitalInfo(request);
        return result;
    }

    public int queryRollIsExist(Map<String, String> map) {
        return this.receiptRollsMapper.queryCont(map);
    }

    @Override
    public int cancelCharge(CancelChargeRequest request) {
        String jbr = request.getJBR();
        request.setJBR(request.getYYBH() + jbr);
        int result = this.zzdMapper.updateZzdStatus(request);
        return result;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 36000,
            rollbackFor = {Exception.class}
    )
    public DataInsertResult readyToCharge(ReadyToChargeRequest request) {
        DataInsertResult insertResult = new DataInsertResult();
        if (Integer.valueOf(request.getSJDNUM()) == 0) {
            insertResult.setResultCode(0);
            return insertResult;
        } else {
            BigDecimal totalMoney = BigDecimal.ZERO;
            int totalNum = 0;
            boolean inNeedInsert = true;
            boolean isNeedReDo = false;
            InpatientChargeTotalData reInsertData = new InpatientChargeTotalData();
            List<HospitalDetailTmp> hospitalDetailTmpEntities = new ArrayList();
            List<ReceiptItem> list = new ArrayList();
            if (request.getRESULTSET() != null) {
                Gson gson = new Gson();
                InpatientChargeTotalData targetItem = (InpatientChargeTotalData)gson.fromJson(gson.toJson(request.getRESULTSET()), InpatientChargeTotalData.class);
                reInsertData = targetItem;
                List<String> existsList = new ArrayList();
                List<String> result = this.receiptItemMapper.queryMXBHByZZDH(request.getZZDH());
                if (result != null) {
                    existsList = result;
                }

                Iterator var15 = targetItem.getResultSet().iterator();

                while(var15.hasNext()) {
                    InpatientChargeItemData item = (InpatientChargeItemData)var15.next();
                    if ("0".equals(item.getSFJK())) {
                        item.setXMBM(item.getXMBM() + "-zl");
                        if (Constant.getOperationMap().containsKey(item.getXMBM())) {
                            this.zzdMapper.updateDiagnosisCode(item.getZZDH(), (String)Constant.getOperationMap().get(item.getXMBM()));
                        }
                    }

                    float f1;
                    String s1;
                    int dwfy;
                    double d1;
                    if (Constant.getMxMap().containsKey(item.getXMBM())) {
                        ReceiptItem receiptItem = new ReceiptItem();

                        try {
                            receiptItem.setChargeDate(this.dateUtils.parseSJDH2Datetime(item.getJZSJ()));
                            Date date = this.dateUtils.parseSJDH2Datetime(item.getSJDH());
                            receiptItem.setReceiptNo(item.getZZDH() + this.dateUtils.createSJDH(date));
                        } catch (ParseException var29) {
                            var29.printStackTrace();
                        }

                        receiptItem.setReceiptItemType((String)Constant.getDataCatalogMap().get(item.getSDML()));
                        receiptItem.setReceiptItemName(item.getXMMC());
                        receiptItem.setReceiptItemSpec(item.getYPGG());
                        receiptItem.setReceiptItemUnit(item.getDW());
                        receiptItem.setReceiptItemNum(Double.valueOf(item.getMCYL()));
                        d1 = Double.valueOf(item.getDWFY());
                        f1 = (float)(d1 * 100.0D);
                        s1 = String.valueOf(f1);
                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                        receiptItem.setUnitFee(dwfy);
                        double d2 = Double.valueOf(item.getJE());
                        float f2 = (float)(d2 * 100.0D);
                        String s2 = String.valueOf(f2);
                        int fee = Integer.valueOf(s2.split("\\.")[0]);
                        if (fee < 0) {
                            isNeedReDo = true;
                        }

                        receiptItem.setFee(fee);
                        receiptItem.setDetailNo(item.getXMBM());
                        receiptItem.setMajorCatalogues(Integer.valueOf(item.getSDML()));
                        receiptItem.setReceiptItemCode(item.getMXBH());
                        receiptItem.setIsImp(Constant.getYpcds().contains(item.getSFJK()) ? 1 : 0);
                        receiptItem.setStatus(0);
                        receiptItem.setBillDept(item.getKDKS());
                        receiptItem.setExeDept(item.getZXKS());
                        receiptItem.setDetailId(item.getMXBH() + item.getXMBM());
                        if (!((List)existsList).contains(item.getMXBH())) {
                            list.add(receiptItem);
                        }

                        BigDecimal je = new BigDecimal(item.getJE());
                        totalMoney = totalMoney.add(je.multiply(new BigDecimal(100)));
                        ++totalNum;
                    } else {
                        HospitalDetailTmp entity = new HospitalDetailTmp();
                        entity.setDetailNo(item.getXMBM());
                        entity.setHospitalId("xh");
                        entity.setMajorCatalogues(item.getSDML());
                        d1 = Double.valueOf(item.getDWFY());
                        f1 = (float)(d1 * 100.0D);
                        s1 = String.valueOf(f1);
                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                        entity.setDetailPrice(String.valueOf(dwfy));
                        entity.setDetailName(item.getXMMC());
                        entity.setDetailXmbm(item.getMXBH());
                        entity.setCreateTime(new Date());
                        entity.setCardNo(item.getHZID());
                        entity.setUserName(item.getHZXM());
                        hospitalDetailTmpEntities.add(entity);
                    }
                }
            }

            if (hospitalDetailTmpEntities.size() > 0) {
                this.hospitalDetailTmpMapper.insertUserData(hospitalDetailTmpEntities);
                insertResult.setResultCode(-1);
                insertResult.setRemainData(hospitalDetailTmpEntities);
                return insertResult;
            } else {
                BigDecimal sjdfee = new BigDecimal(request.getSJDFEE());
                sjdfee = sjdfee.multiply(new BigDecimal(100));
                if (totalMoney.intValue() == sjdfee.intValue() && totalNum == Integer.valueOf(request.getSJDNUM())) {
                    ReceiptRolls rolls = new ReceiptRolls();
                    rolls.setUserName(request.getHZXM());
                    rolls.setCardNo(request.getHZID());
                    rolls.setCardId(request.getJZKH());

                    try {
                        Date date = this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH());
                        rolls.setReceiptNo(reInsertData.getZZDH() + this.dateUtils.createSJDH(date));
                        rolls.setReceiptCreateTime(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                        rolls.setStartDate(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                        rolls.setEndDate(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                    } catch (ParseException var28) {
                        var28.printStackTrace();
                    }

                    rolls.setIntoTime(new Date());
                    rolls.setZzdNo(request.getZZDH());
                    rolls.setIntoHospitalId(reInsertData.getYYBM());
                    rolls.setAdmissionNumber(reInsertData.getZYH());
                    rolls.setReceiptType(request.getJZLB());
                    rolls.setDateNum(1);
                    rolls.setOutHospitalId("hk");
                    rolls.setStatus(0);
                    int rollResult = 1;
                    if (inNeedInsert) {
                        Map<String, String> map = new HashMap();
                        map.put("receiptNo", rolls.getReceiptNo());
                        map.put("cardNo", rolls.getCardNo());
                        if (this.queryRollIsExist(map) == 0) {
                            rollResult = this.receiptRollsMapper.insertRoll(rolls);
                        } else {
                            rollResult = 1;
                        }
                    }

                    int rollItemResult;
                    if (isNeedReDo) {
                        rollItemResult = this.zzdMapper.updateRedoFlag(request.getZZDH());
                    }

                    if (rollResult > 0 && list.size() > 0) {
                        rollItemResult = this.receiptItemMapper.insertByList(list);
                        insertResult.setResultCode(rollItemResult);
                        return insertResult;
                    } else {
                        return insertResult;
                    }
                } else {
                    insertResult.setResultCode(-2);
                    return insertResult;
                }
            }
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 36000,
            rollbackFor = {Exception.class}
    )
    @Override
    public DataInsertResult newReadyToCharge(MoreReadyToChargeRequest moreRequest) {
        DataInsertResult insertResult = new DataInsertResult();
        insertResult.setResultCode(0);
        if (Integer.valueOf(moreRequest.getSJDNUM()) == 0) {
            return insertResult;
        } else {
            boolean isChildrenOk = true;
            int totalCount = 0;
            BigDecimal total = BigDecimal.ZERO;
            Iterator var7 = moreRequest.getRESULTSET().iterator();

            while(true) {
                InpatientChargeTotalData sj;
                BigDecimal dcFee;
                int childCount;
                BigDecimal childTotal;
                do {
                    if (!var7.hasNext()) {
                        BigDecimal sjTotalTmp = new BigDecimal(moreRequest.getSJDFEE());
                        BigDecimal sjTotal = sjTotalTmp.multiply(new BigDecimal(100));
                        if (totalCount == Integer.valueOf(moreRequest.getSJDNUM()) && sjTotal.intValue() == total.intValue() && isChildrenOk) {
                            Iterator var36 = moreRequest.getRESULTSET().iterator();

                            while(var36.hasNext()) {
                                InpatientChargeTotalData request = (InpatientChargeTotalData)var36.next();
                                boolean inNeedInsert = true;
                                boolean isNeedReDo = false;
                                InpatientChargeTotalData reInsertData = request;
                                List<ReceiptItem> list = new ArrayList();
                                List<HospitalDetailTmp> hospitalDetailTmpEntities = new ArrayList();
                                List<String> existsList = new ArrayList();
                                List<String> result = this.receiptItemMapper.queryMXBHByZZDH(request.getZZDH());
                                if (result != null) {
                                    existsList = result;
                                }

                                Iterator var18 = request.getResultSet().iterator();

                                while(var18.hasNext()) {
                                    InpatientChargeItemData item = (InpatientChargeItemData)var18.next();
                                    if ("0".equals(item.getSFJK())) {
                                        item.setXMBM(item.getXMBM() + "-zl");
                                        if (Constant.getOperationMap().containsKey(item.getXMBM())) {
                                            this.zzdMapper.updateDiagnosisCode(item.getZZDH(), (String)Constant.getOperationMap().get(item.getXMBM()));
                                        }
                                    }

                                    float f1;
                                    String s1;
                                    int dwfy;
                                    double d1;
                                    if (Constant.getMxMap().containsKey(item.getXMBM())) {
                                        ReceiptItem receiptItem = new ReceiptItem();

                                        try {
                                            receiptItem.setChargeDate(this.dateUtils.parseSJDH2Datetime(item.getJZSJ()));
                                            Date date = this.dateUtils.parseSJDH2Datetime(item.getSJDH());
                                            receiptItem.setReceiptNo(item.getZZDH() + this.dateUtils.createSJDH(date));
                                        } catch (ParseException var30) {
                                            var30.printStackTrace();
                                        }

                                        receiptItem.setReceiptItemType((String)Constant.getDataCatalogMap().get(item.getSDML()));
                                        receiptItem.setReceiptItemName(item.getXMMC());
                                        receiptItem.setReceiptItemSpec(item.getYPGG());
                                        receiptItem.setReceiptItemUnit(item.getDW());
                                        receiptItem.setReceiptItemNum(Double.valueOf(item.getMCYL()));
                                        d1 = Double.valueOf(item.getDWFY());
                                        f1 = (float)(d1 * 100.0D);
                                        s1 = String.valueOf(f1);
                                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                                        receiptItem.setUnitFee(dwfy);
                                        double d2 = Double.valueOf(item.getJE());
                                        float f2 = (float)(d2 * 100.0D);
                                        String s2 = String.valueOf(f2);
                                        int fee = Integer.valueOf(s2.split("\\.")[0]);
                                        if (fee < 0) {
                                            isNeedReDo = true;
                                        }

                                        receiptItem.setFee(fee);
                                        receiptItem.setDetailNo(item.getXMBM());
                                        receiptItem.setMajorCatalogues(Integer.valueOf(item.getSDML()));
                                        receiptItem.setReceiptItemCode(item.getMXBH());
                                        receiptItem.setIsImp(Constant.getYpcds().contains(item.getSFJK()) ? 1 : 0);
                                        receiptItem.setStatus(0);
                                        receiptItem.setBillDept(item.getKDKS());
                                        receiptItem.setExeDept(item.getZXKS());
                                        receiptItem.setDetailId(item.getMXBH() + item.getXMBM());
                                        if (!((List)existsList).contains(item.getMXBH())) {
                                            list.add(receiptItem);
                                        }
                                    } else {
                                        HospitalDetailTmp entity = new HospitalDetailTmp();
                                        entity.setDetailNo(item.getXMBM());
                                        entity.setHospitalId("xh");
                                        entity.setMajorCatalogues(item.getSDML());
                                        d1 = Double.valueOf(item.getDWFY());
                                        f1 = (float)(d1 * 100.0D);
                                        s1 = String.valueOf(f1);
                                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                                        entity.setDetailPrice(String.valueOf(dwfy));
                                        entity.setDetailName(item.getXMMC());
                                        entity.setDetailXmbm(item.getMXBH());
                                        entity.setCreateTime(new Date());
                                        entity.setCardNo(item.getHZID());
                                        entity.setUserName(item.getHZXM());
                                        hospitalDetailTmpEntities.add(entity);
                                    }
                                }

                                if (hospitalDetailTmpEntities.size() > 0) {
                                    this.hospitalDetailTmpMapper.insertUserData(hospitalDetailTmpEntities);
                                    insertResult.setResultCode(-1);
                                    insertResult.setRemainData(hospitalDetailTmpEntities);
                                    return insertResult;
                                }

                                ReceiptRolls rolls = new ReceiptRolls();
                                rolls.setUserName(request.getHZXM());
                                rolls.setCardNo(request.getHZID());
                                rolls.setCardId(request.getJZKH());

                                try {
                                    Date date = this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH());
                                    rolls.setReceiptNo(reInsertData.getZZDH() + this.dateUtils.createSJDH(date));
                                    rolls.setReceiptCreateTime(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                                    rolls.setStartDate(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                                    rolls.setEndDate(this.dateUtils.parseSJDH2Datetime(reInsertData.getSJDH()));
                                } catch (ParseException var32) {
                                    var32.printStackTrace();
                                }

                                rolls.setIntoTime(new Date());
                                rolls.setZzdNo(request.getZZDH());
                                rolls.setIntoHospitalId(request.getYYBM());
                                rolls.setAdmissionNumber(request.getZYH());
                                rolls.setReceiptType(moreRequest.getJZLB());
                                rolls.setDateNum(1);
                                rolls.setOutHospitalId("hk");
                                rolls.setStatus(0);
                                int rollResult = 1;
                                if (inNeedInsert) {
                                    Map<String, String> map = new HashMap();
                                    map.put("receiptNo", rolls.getReceiptNo());
                                    map.put("cardNo", rolls.getCardNo());
                                    if (this.queryRollIsExist(map) == 0) {
                                        rollResult = this.receiptRollsMapper.insertRoll(rolls);
                                    } else {
                                        rollResult = 1;
                                    }
                                }

                                int rollItemResult;
                                if (isNeedReDo) {
                                    rollItemResult = this.zzdMapper.updateRedoFlag(request.getZZDH());
                                }

                                if (rollResult > 0 && list.size() > 0) {
                                    try {
                                        rollItemResult = this.receiptItemMapper.insertByList(list);
                                        insertResult.setResultCode(rollItemResult);
                                    } catch (Exception var31) {
                                        var31.printStackTrace();
                                    }
                                }
                            }

                            return insertResult;
                        }

                        insertResult.setResultCode(-2);
                        return insertResult;
                    }

                    sj = (InpatientChargeTotalData)var7.next();
                    BigDecimal dcFeeTmp = new BigDecimal(sj.getSJDFEE());
                    dcFee = dcFeeTmp.multiply(new BigDecimal(100));
                    total = total.add(dcFee);
                    ++totalCount;
                    childCount = 0;
                    childTotal = BigDecimal.ZERO;

                    for(Iterator var13 = sj.getResultSet().iterator(); var13.hasNext(); ++childCount) {
                        InpatientChargeItemData item = (InpatientChargeItemData)var13.next();
                        BigDecimal dcChildFeeTmp = new BigDecimal(item.getJE());
                        BigDecimal dcChildFee = dcChildFeeTmp.multiply(new BigDecimal(100));
                        childTotal = childTotal.add(dcChildFee);
                    }
                } while(childCount == Integer.valueOf(sj.getSJDNUM()) && childTotal.intValue() == dcFee.intValue());

                isChildrenOk = false;
            }
        }
    }

    public DailyQueryResponse dailyChargeQuery(DailyQueryRequest request) {
        Map<String, String> result = this.receiptBalanceItemMapper.queryDailyCharge(request);
        DailyQueryResponse response = new DailyQueryResponse();
        if (result != null) {
            response.setFYZE((String)result.get("ZJE"));
            response.setGFJE((String)result.get("GFJE"));
            response.setZFJE((String)result.get("ZFJE"));
            response.setJZJLH(request.getJZJLH());
        }

        return response;
    }
@Override
    public int cancelRegistration(CancelRegistrationRequest request) {
        return this.zzdMapper.cancelRegister(request);
    }
    @Override
    public GfjsZzd queryInfoByZYH(String zyh) {
        return this.zzdMapper.queryInfoByZYH(zyh);
    }
    @Override
    public int insertRolls(ReceiptRolls receiptRolls) {
        Map<String, String> map = new HashMap();
        map.put("receiptNo", receiptRolls.getReceiptNo());
        int rollResult = 0;
        map.put("cardNo", receiptRolls.getCardNo());
        if (this.queryRollIsExist(map) == 0) {
            rollResult = this.receiptRollsMapper.insertRoll(receiptRolls);
        }

        return rollResult;
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 36000,
            rollbackFor = {Exception.class}
    )
    @Override
    public DataInsertResult syncMxItem(String sjdh, String zzdh, List<MXEntity> request) {
        boolean isNeedReDo = false;
        List<ReceiptItem> list = new ArrayList();
        List<String> existsList = this.receiptItemMapper.queryMXBHBySJDH(sjdh);
        List<HospitalDetailTmp> hospitalDetailTmpEntities = new ArrayList();
        DataInsertResult dataInsertResult = new DataInsertResult();
        Iterator var10 = request.iterator();

        while(var10.hasNext()) {
            MXEntity item = (MXEntity)var10.next();
            if ("0".equals(item.getYPCD())) {
                item.setXMBM(item.getXMBM() + "-zl");
                if (Constant.getOperationMap().containsKey(item.getXMBM())) {
                    this.zzdMapper.updateDiagnosisCode(item.getZZDH(), (String)Constant.getOperationMap().get(item.getXMBM()));
                }
            }

            float f1;
            String s1;
            int dwfy;
            double d1;
            if (Constant.getMxMap().containsKey(item.getXMBM())) {
                ReceiptItem receiptItem = new ReceiptItem();

                try {
                    Date date = this.dateUtils.parseSJDH2Datetime(item.getSJDH());
                    receiptItem.setReceiptNo(zzdh + this.dateUtils.createSJDH(date));
                    receiptItem.setChargeDate(this.dateUtils.parseSJDH2Datetime(item.getJZSJ()));
                } catch (ParseException var22) {
                    var22.printStackTrace();
                }

                receiptItem.setReceiptItemType((String)Constant.getDataCatalogMap().get(item.getSDML()));
                receiptItem.setReceiptItemName(item.getXMMC());
                receiptItem.setReceiptItemSpec(item.getYPGG());
                receiptItem.setReceiptItemUnit(item.getDW());
                receiptItem.setReceiptItemNum(Double.valueOf(item.getMCYL()));
                d1 = Double.valueOf(item.getDWFY());
                f1 = (float)(d1 * 100.0D);
                s1 = String.valueOf(f1);
                dwfy = Integer.valueOf(s1.split("\\.")[0]);
                receiptItem.setUnitFee(dwfy);
                double d2 = Double.valueOf(item.getJE());
                float f2 = (float)(d2 * 100.0D);
                String s2 = String.valueOf(f2);
                int fee = Integer.valueOf(s2.split("\\.")[0]);
                if (fee < 0) {
                    isNeedReDo = true;
                }

                receiptItem.setFee(fee);
                receiptItem.setDetailNo(item.getXMBM());
                receiptItem.setMajorCatalogues(Integer.valueOf(item.getSDML()));
                receiptItem.setReceiptItemCode(item.getMXBH());
                receiptItem.setIsImp(Constant.getYpcds().contains(item.getYPCD()) ? 1 : 0);
                receiptItem.setStatus(0);
                receiptItem.setBillDept(item.getKDKS());
                receiptItem.setExeDept(item.getZXKS());
                receiptItem.setDetailId(item.getMXBH() + item.getXMBM());
                if (!existsList.contains(item.getMXBH())) {
                    list.add(receiptItem);
                }
            } else {
                HospitalDetailTmp entity = new HospitalDetailTmp();
                entity.setDetailNo(item.getXMBM());
                entity.setHospitalId("xh");
                entity.setMajorCatalogues(item.getSDML());
                d1 = Double.valueOf(item.getDWFY());
                f1 = (float)(d1 * 100.0D);
                s1 = String.valueOf(f1);
                dwfy = Integer.valueOf(s1.split("\\.")[0]);
                entity.setDetailPrice(String.valueOf(dwfy));
                entity.setDetailName(item.getXMMC());
                entity.setDetailXmbm(item.getMXBH());
                entity.setCreateTime(new Date());
                entity.setCardNo(item.getHZID());
                entity.setUserName(item.getHZXM());
                hospitalDetailTmpEntities.add(entity);
            }
        }

        if (hospitalDetailTmpEntities.size() > 0) {
            this.hospitalDetailTmpMapper.insertUserData(hospitalDetailTmpEntities);
            dataInsertResult.setResultCode(-1);
            dataInsertResult.setRemainData(hospitalDetailTmpEntities);
            return dataInsertResult;
        } else {
            if (isNeedReDo) {
                this.zzdMapper.updateRedoFlag(zzdh);
            }

            int rollItemResult;
            if (list.size() == 0) {
                rollItemResult = 1;
            } else {
                rollItemResult = this.receiptItemMapper.insertByList(list);
            }

            dataInsertResult.setResultCode(rollItemResult);
            return dataInsertResult;
        }
    }
    @Override
    public List<HzBean> queryUnreadSum() {
        List<HzBean> list = this.zzdMapper.queryUnraedInfo();
        if (list.size() > 0) {
            list = (List)list.stream().filter((item) -> {
                return item.getZZDLX().equals("1");
            }).filter((item) -> {
                return !item.getHZRQ().contains("2000");
            }).collect(Collectors.toList());
            list.forEach((item) -> {
                item.setHZRQ(item.getHZRQ().substring(item.getHZRQ().length() - 8, item.getHZRQ().length()));
            });
        }

        return list;
    }
    @Override
    public int changeReadFlag(FlagChangeRequest request) {
        String hzrq = request.getHZRQ();
        request.setHZRQ(request.getZZDH() + hzrq);
        return this.zzdMapper.updateReadFlag(request);
    }
    @Override
    public int updateZzdInfo(InpatientChargeRequest request) {
        return this.zzdMapper.updateInfo(request);
    }
    @Override
    public String queryUserUnExistData(String hzid) {
        List<Map<String, String>> map = this.hospitalDetailTmpMapper.queryByUser(hzid);
        return (new Gson()).toJson(map);
    }
    @Override
    public List<CYJXInfoEntity> queryChargeList() {
        return this.zzdMapper.queryChargeList();
    }
    @Override
    public int queryItemCount() {
        return this.receiptItemMapper.queryItemCount();
    }
    @Override
    public MakeSureUserResponse queryMzZzdByUserInfo(MakeSureUserRequest request) {
        Map<String, Object> result = this.zzdMapper.selectZzdByUserInfo(request);
        if (result != null) {
            MakeSureUserResponse response = new MakeSureUserResponse();
            int validDay = Integer.valueOf(result.get("DAYS").toString());
            Date date = (Date)result.get("CJSJ");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(5, validDay);
            date = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            response.setZZYXRQ(dateString);
            response.setZGLB(result.get("ZGLB").toString());
            response.setXB(result.get("XB").toString());
            response.setDWMC(result.get("DWMC").toString());
            response.setXM(result.get("XM").toString());
            response.setZZYYMC(result.get("ZZYYMC").toString());
            response.setZZYYBH(result.get("ZZYYBH").toString());
            response.setZZDH(result.get("ZZDH").toString());
            ZzdTypeEntity entity = new ZzdTypeEntity();
            entity.setZZDH(response.getZZDH());
            entity.setZZDLX(request.getZZDLX());
            this.zzdMapper.updateZZDLX(entity);
            return response;
        } else {
            return null;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 36000,
            rollbackFor = {Exception.class}
    )
    @Override
    public DataInsertResult outpatientCharge(OutpatientRequest request) {
        DataInsertResult insertResult = new DataInsertResult();
        if (request.getRESULTSET() != null && request.getRESULTSET().size() != 0) {
            BigDecimal totalMoney = BigDecimal.ZERO;
            int totalNum = 0;
            boolean inNeedInsert = true;
            boolean isNeedReDo = false;
            List<HospitalDetailTmp> hospitalDetailTmpEntities = new ArrayList();
            List<ReceiptItem> list = new ArrayList();
            if (request.getRESULTSET() != null) {
                Iterator var10 = request.getRESULTSET().iterator();

                while(var10.hasNext()) {
                    OutpatientItemData item = (OutpatientItemData)var10.next();
                    if ("0".equals(item.getYPCD())) {
                        item.setXMBM(item.getXMBM() + "-zl");
                    }

                    float f1;
                    String s1;
                    int dwfy;
                    double d1;
                    if (Constant.getMxMap().containsKey(item.getXMBM())) {
                        ReceiptItem receiptItem = new ReceiptItem();

                        try {
                            Date date = this.dateUtils.parseSJDH2Datetime(request.getJSRQ());
                            receiptItem.setChargeDate(date);
                            receiptItem.setReceiptNo(request.getZZDH() + item.getFPHM());
                        } catch (ParseException var24) {
                            var24.printStackTrace();
                        }

                        receiptItem.setReceiptItemType((String)Constant.getDataCatalogMap().get(item.getSDML()));
                        receiptItem.setReceiptItemName(item.getXMMC());
                        receiptItem.setReceiptItemSpec(item.getYPGG());
                        receiptItem.setReceiptItemUnit(item.getDW());
                        receiptItem.setReceiptItemNum(Double.valueOf(item.getMCYL()));
                        d1 = Double.valueOf(item.getDWFY());
                        f1 = (float)(d1 * 100.0D);
                        s1 = String.valueOf(f1);
                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                        receiptItem.setUnitFee(dwfy);
                        double d2 = Double.valueOf(item.getJE());
                        float f2 = (float)(d2 * 100.0D);
                        String s2 = String.valueOf(f2);
                        int fee = Integer.valueOf(s2.split("\\.")[0]);
                        if (fee < 0) {
                            isNeedReDo = true;
                        }

                        receiptItem.setFee(fee);
                        receiptItem.setDetailNo(item.getXMBM());
                        receiptItem.setMajorCatalogues(Integer.valueOf(item.getSDML()));
                        receiptItem.setReceiptItemCode(item.getXMBH());
                        receiptItem.setIsImp(Constant.getYpcds().contains(item.getYPCD()) ? 1 : 0);
                        receiptItem.setStatus(0);
                        receiptItem.setBillDept(item.getKDKS());
                        receiptItem.setExeDept(item.getZXKS());
                        receiptItem.setDetailId(item.getXMBH() + item.getXMBM());
                        list.add(receiptItem);
                        BigDecimal je = new BigDecimal(item.getJE());
                        totalMoney = totalMoney.add(je.multiply(new BigDecimal(100)));
                        ++totalNum;
                    } else {
                        HospitalDetailTmp entity = new HospitalDetailTmp();
                        entity.setDetailNo(item.getXMBM());
                        entity.setHospitalId("xh");
                        entity.setMajorCatalogues(item.getSDML());
                        d1 = Double.valueOf(item.getDWFY());
                        f1 = (float)(d1 * 100.0D);
                        s1 = String.valueOf(f1);
                        dwfy = Integer.valueOf(s1.split("\\.")[0]);
                        entity.setDetailPrice(String.valueOf(dwfy));
                        entity.setDetailName(item.getXMMC());
                        entity.setDetailXmbm(item.getXMBM());
                        entity.setCreateTime(new Date());
                        entity.setCardNo(request.getHZID());
                        entity.setUserName(request.getHZXM());
                        hospitalDetailTmpEntities.add(entity);
                    }
                }
            }

            if (hospitalDetailTmpEntities.size() > 0) {
                this.hospitalDetailTmpMapper.insertUserData(hospitalDetailTmpEntities);
                insertResult.setResultCode(-1);
                insertResult.setRemainData(hospitalDetailTmpEntities);
                return insertResult;
            } else {
                BigDecimal sjdfee = new BigDecimal(request.getFYZE());
                sjdfee = sjdfee.multiply(new BigDecimal(100));
                if (totalMoney.intValue() == sjdfee.intValue() && totalNum == request.getRESULTSET().size()) {
                    ReceiptRolls rolls = new ReceiptRolls();
                    rolls.setUserName(request.getHZXM());
                    rolls.setCardNo(request.getHZID());
                    rolls.setCardId(request.getJZKH());

                    try {
                        Date date = this.dateUtils.parseSJDH2Datetime(request.getJSRQ());
                        rolls.setReceiptNo(request.getZZDH() + request.getFPHM());
                        rolls.setReceiptCreateTime(date);
                        rolls.setStartDate(date);
                        rolls.setEndDate(date);
                    } catch (ParseException var23) {
                        var23.printStackTrace();
                    }

                    rolls.setIntoTime(new Date());
                    rolls.setZzdNo(request.getZZDH());
                    rolls.setIntoHospitalId(request.getYYBH());
                    rolls.setAdmissionNumber(request.getJZBH());
                    rolls.setReceiptType("" + ZzdType.MZ);
                    rolls.setDateNum(1);
                    rolls.setOutHospitalId(request.getYYBH());
                    rolls.setStatus(0);
                    int rollResult = 1;
                    if (inNeedInsert) {
                        Map<String, String> map = new HashMap();
                        map.put("receiptNo", rolls.getReceiptNo());
                        map.put("cardNo", rolls.getCardNo());
                        if (this.queryRollIsExist(map) == 0) {
                            rollResult = this.receiptRollsMapper.insertRoll(rolls);
                        } else {
                            rollResult = 1;
                        }
                    }

                    if (rollResult > 0) {
                        int rollItemResult = this.receiptItemMapper.insertByList(list);
                        insertResult.setResultCode(rollItemResult);
                        return insertResult;
                    } else {
                        return insertResult;
                    }
                } else {
                    insertResult.setResultCode(-2);
                    return insertResult;
                }
            }
        } else {
            insertResult.setResultCode(0);
            return insertResult;
        }
    }
    @Override
    public MakeSureUserResponse queryMXzZdBySate(MakeSureUserRequest request) {
        Map<String, Object> result = this.zzdMapper.selectZzdByState(request);
        if (result != null) {
            MakeSureUserResponse response = new MakeSureUserResponse();
            int validDay = Integer.valueOf(result.get("DAYS").toString());
            Date date = (Date)result.get("CJSJ");
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(5, validDay);
            date = calendar.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            response.setZZYXRQ(dateString);
            response.setZGLB(result.get("ZGLB").toString());
            response.setXB(result.get("XB").toString());
            response.setDWMC(result.get("DWMC").toString());
            response.setXM(result.get("XM").toString());
            response.setZZYYMC(result.get("ZZYYMC").toString());
            response.setZZYYBH(result.get("ZZYYBH").toString());
            response.setZZDH(result.get("ZZDH").toString());
            return response;
        } else {
            return null;
        }
    }
    @Override
    public int updateZZdState(String zzdh) {
        return this.zzdMapper.useMzZzd(zzdh);
    }
    @Override
    public GfjsZzd queryUsedMzZzd(MakeSureUserRequest sureUserRequest) {
        return this.zzdMapper.queryUsedZZd(sureUserRequest);
    }
    @Override
    public int cancelZzd(String zzdNo) {
        GfjsZzd zzd = this.zzdMapper.queryZzdByZzdNo(zzdNo);
        return zzd != null && zzd.getFee() != 0L ? this.zzdMapper.changeZzdStatus1(zzdNo) : this.zzdMapper.changeZzdStatus(zzdNo);
    }
    @Override
    public int createNewZzd(GfjsZzd zzdh) {
        String oldZzdNo = zzdh.getZzdNo();
        zzdh.setZzdNo(this.zzdhUtils.createZzdh());
        zzdh.setZzdType("" + ZzdType.ZY);
        zzdh.setStatus(ZzdStatus.UN_ACTIVE);
        zzdh.setFee(0L);
        zzdh.setExpenseFee(0L);
        zzdh.setOwnFee(0L);
        zzdh.setCurrentCost(0L);
        zzdh.setOldZzdNo(oldZzdNo);
        return this.zzdMapper.insertByZzd(zzdh);
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            timeout = 36000,
            rollbackFor = {Exception.class}
    )
    @Override
    public int cancelOutpatientCharge(CancelOutPatientChargeRequest request) {
        int result = this.receiptBalanceItemMapper.deleteByFphm(request.getZZDH() + request.getFPHM());
        result += this.receiptRollsMapper.deleteByFphm(request.getZZDH() + request.getFPHM());
        result += this.receiptItemMapper.deleteByFphm(request.getZZDH() + request.getFPHM());
        return result;
    }
    @Override
    public String getZzdStatus(String zzdh) {
        return this.zzdMapper.queryZzdStatus(zzdh);
    }
    @Override
    public List<String> queryUsedZzd(String sfzh) {
        return this.zzdMapper.queryUsedZzdBySfzh(sfzh);
    }
    @Override
    public GfjsZzd queryZzdIsExist(String sfzh) {
        return this.zzdMapper.queryZzdIsExist(sfzh);
    }
    @Override
    public GfjsZzd queryZzdIsVaild(String sfzh) {
        return this.zzdMapper.queryZzdIsVaild(sfzh);
    }
    @Override
    public GfjsZzd queryZyExist(String zzdh) {
        return this.zzdMapper.queryZyExist(zzdh);
    }
    @Override
    public GfjsZzd queryZzdByCardNo(String cardNo) {
        return this.zzdMapper.queryZzdByCardNo(cardNo);
    }
    @Override
    public void updateZzdRecord() {
        this.zzdMapper.handleOverTimeZzd(DateUtils.getSubDate(-7));
        this.zzdMapper.handleCompledZzd(DateUtils.getSubDate(-7));
    }
}
