package com.koffe.koffe.repository;

import com.koffe.koffe.model.Address;
import com.koffe.koffe.repository.design.IRepository;

public interface IAddressRepository extends IRepository<Address> {
    Address findAllAddressByUserId(int userid);
    int getLastAddressId();
}
