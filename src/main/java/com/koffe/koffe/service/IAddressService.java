package com.koffe.koffe.service;

import com.koffe.koffe.model.Address;
import com.koffe.koffe.service.design.IService;

public interface IAddressService extends IService<Address> {
    Address findAllAddressByUserId(int userid);
    int getLastAddressId();
}
