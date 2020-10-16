package cc.mrbird.febs.hkgf.service.impl;

import cc.mrbird.febs.hkgf.dao.ReceiptItemMapper;
import cc.mrbird.febs.hkgf.entity.ReceiptItem;
import cc.mrbird.febs.hkgf.service.ReceiptItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepitItemServiceImpl implements ReceiptItemService {
    @Autowired
    private ReceiptItemMapper mapper;

    public RecepitItemServiceImpl() {
    }
    @Override
    public int insertList(List<ReceiptItem> list) {
        return this.mapper.insertByList(list);
    }
}
