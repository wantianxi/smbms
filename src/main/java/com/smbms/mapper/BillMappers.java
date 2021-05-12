package com.smbms.mapper;

import com.smbms.pojo.Bill;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillMappers {
    List<Bill> getList(@Param("productName") String name,
                       @Param("providerId") Integer id
    );
    List<Bill> getList2();
}
