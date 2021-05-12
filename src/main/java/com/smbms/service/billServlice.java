package com.smbms.service;

import com.smbms.mapper.BillMappers;
import com.smbms.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class billServlice {
    @Autowired
    private BillMappers billMappers;
    public List<Bill> getLis2t(){
        return billMappers.getList2();
    }
}
