package com.smbms.mapper;

import com.smbms.pojo.Provider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderMappers {
    int add(Provider provider);


    int Update(Provider Provider);

    int delelt(Integer id);
    List<Provider> getlist();
    Provider getById(Integer id);

    Provider getByProviderCode(String proCode);
}
