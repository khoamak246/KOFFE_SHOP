package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.Address;
import com.koffe.koffe.repository.IAddressRepository;
import com.koffe.koffe.repository.repositoryIMPL.AddressRepositoryIMPL;
import com.koffe.koffe.service.IAddressService;

import java.util.List;

public class AddressServiceIMPL implements IAddressService {

    IAddressRepository addressRepository = new AddressRepositoryIMPL();


    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findAllAddressByUserId(int userid) {
        return addressRepository.findAllAddressByUserId(userid);
    }

    @Override
    public int getLastAddressId() {
        return addressRepository.getLastAddressId();
    }


    @Override
    public Address findById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public boolean save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public boolean update(Address address) {
        return addressRepository.update(address);
    }

    @Override
    public void deleteById(int id) {
        addressRepository.deleteById(id);
    }
}
