package com.smbms.service;

import com.smbms.pojo.Provider;

import java.util.List;

public interface ProvidersService {
    boolean add(Provider provider);



    List<Provider> getlist();
    Provider getById(Integer id);

    Provider getByProviderCode(String proCode);
}
