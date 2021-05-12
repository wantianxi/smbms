package com.smbms.service;

import com.smbms.mapper.ProviderMappers;
import com.smbms.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvidersServiceImpl implements ProvidersService {
    @Autowired
    private ProviderMappers providerMappers;

    @Override
    public boolean add(Provider provider) {
        return providerMappers.add(provider)>0;
    }

    @Override
    public List<Provider> getlist() {
        return providerMappers.getlist();
    }

    @Override
    public Provider getById(Integer id) {
        return providerMappers.getById(id);
    }

    @Override
    public Provider getByProviderCode(String proCode) {
        return providerMappers.getByProviderCode(proCode);
    }
}
