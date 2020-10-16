package cc.mrbird.febs.hkgf.service.impl;

import cc.mrbird.febs.hkgf.config.Constant;
import cc.mrbird.febs.hkgf.config.Constant.DiganosCode;
import cc.mrbird.febs.hkgf.dao.DataCatalogMapper;
import cc.mrbird.febs.hkgf.dao.DrugEnterRecordMapper;
import cc.mrbird.febs.hkgf.dao.GfjsZzdMapper;
import cc.mrbird.febs.hkgf.dao.HkXhCommonRuleMapper;
import cc.mrbird.febs.hkgf.dao.HkXhSpecRuleMapper;
import cc.mrbird.febs.hkgf.dao.HospitalDetailTmpMapper;
import cc.mrbird.febs.hkgf.dao.OutHospitalInfoMapper;
import cc.mrbird.febs.hkgf.entity.CyxjInfoEntity;
import cc.mrbird.febs.hkgf.entity.OutHospitalInfo;
import cc.mrbird.febs.hkgf.entity.YpcdEntity;
import cc.mrbird.febs.hkgf.entity.request.BaseDataEntity;
import cc.mrbird.febs.hkgf.service.MxService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MxServiceImpl implements MxService {
    @Autowired
    private HkXhCommonRuleMapper commonMapper;
    @Autowired
    private HkXhSpecRuleMapper specMapper;
    @Autowired
    private HospitalDetailTmpMapper hospitalDetailTmpMapper;
    @Autowired
    private DrugEnterRecordMapper drugEnterRecordMapper;
    @Autowired
    private DataCatalogMapper dataCatalogMapper;
    @Autowired
    private OutHospitalInfoMapper outHospitalInfoMapper;
    @Autowired
    private GfjsZzdMapper zzdMapper;

    public MxServiceImpl() {
    }

    @PostConstruct
    @Override
    public int queryAllMx() {
        List<Map<String, String>> ypList = this.commonMapper.queryCommonRule();
        List<Map<String, String>> zlList = this.specMapper.querySpecRule();
        List<Map<String, String>> catalogList = this.dataCatalogMapper.queryAllCatalog();
        ypList.addAll(zlList);
        String[] ypcds = new String[]{"2", "4", "6", "9", "11", "13", "16"};
        Constant.setYpcds(Arrays.asList(ypcds));
        Map<String, String> mapDataCatalog = new HashMap();
        Iterator var7 = catalogList.iterator();

        while(var7.hasNext()) {
            Map<String, String> item = (Map)var7.next();
            mapDataCatalog.put(String.valueOf(item.get("id")), (String)item.get("name"));
        }

        Constant.setDataCatalogMap(mapDataCatalog);
        Map<String, String> mapData = new HashMap();
        Iterator var8 = ypList.iterator();

        while(var8.hasNext()) {
            Map<String, String> map = (Map)var8.next();
            mapData.put((String)map.get("key"), (String)map.get("name"));
        }

        Constant.setMxMap(mapData);
        Map<String, String> operaMap = new HashMap();
        operaMap.put("23768-zl", "" + DiganosCode.SHUN_CHAN);
        operaMap.put("23773-zl", "" + DiganosCode.SHUN_CHAN);
        operaMap.put("23778-zl", "" + DiganosCode.POU_FU_CHAN);
        operaMap.put("23779-zl", "" + DiganosCode.POU_FU_CHAN);
        operaMap.put("23780-zl", "" + DiganosCode.POU_FU_CHAN);
        operaMap.put("23781-zl", "" + DiganosCode.POU_FU_CHAN);
        Constant.setOperationMap(operaMap);
        System.out.println("药数据总数为：" + Constant.getMxMap().size());
        return Constant.getMxMap().size();
    }
    @Override
    public int queryNoRuleCount() {
        return this.hospitalDetailTmpMapper.queryCount();
    }
    @Override
    public List<String> queryLocalNo() {
        List<String> commonList = this.commonMapper.queryAllNo();
        List<String> specList = this.specMapper.queryAllNo();
        List<String> tmpList = this.hospitalDetailTmpMapper.queryAllNo();
        commonList.addAll(specList);
        commonList.addAll(tmpList);
        return commonList;
    }
    @Override
    public int uploadData(List<BaseDataEntity> request) {
        Iterator var3 = request.iterator();

        while(var3.hasNext()) {
            BaseDataEntity item = (BaseDataEntity)var3.next();
            double d2 = Double.valueOf(item.getFYDJ());
            float f2 = (float)(d2 * 100.0D);
            String s2 = String.valueOf(f2);
            int fee = Integer.valueOf(s2.split("\\.")[0]);
            item.setFYDJ(String.valueOf(fee));
        }

        return this.hospitalDetailTmpMapper.insertByList(request);
    }
    @Override
    public int queryYpcdCount() {
        return this.drugEnterRecordMapper.queryCount();
    }
    @Override
    public List<String> queryYpcdNo() {
        return this.drugEnterRecordMapper.queryAllNo();
    }
    @Override
    public int uploadYpcds(List<YpcdEntity> list) {
        return this.drugEnterRecordMapper.insertByList(list);
    }
    @Override
    public List<String> queryNeedUploadUser() {
        return this.drugEnterRecordMapper.queryUserData();
    }
    @Override
    public int updateUserFlag(String zyh) {
        return this.drugEnterRecordMapper.updateUserFlag(zyh);
    }
    @Override
    public int insertCyxj(CyxjInfoEntity entity) {
        OutHospitalInfo info = new OutHospitalInfo();
        info.setZzdNo(entity.getZzdh());
        info.setOutInfo(entity.getInfo());
        int result = this.outHospitalInfoMapper.insertInfo(info);
        if (result > 0) {
            result = this.zzdMapper.updateSummaryFlag(entity.getZzdh());
        }

        return result;
    }
}
