package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.Address;
import com.koffe.koffe.repository.IAddressRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressRepositoryIMPL implements IAddressRepository {
    @Override
    public List<Address> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<Address> addresses = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllAddress()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                addresses.add(createAddress(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return addresses;
    }

    @Override
    public Address findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Address address = null;
        try {
            callableStatement = conn.prepareCall("{call findAddressById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                address = createAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return address;
    }

    @Override
    public Address findAllAddressByUserId(int userid) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        Address address = null;
        try {
            callableStatement = conn.prepareCall("{call findAddressByUserId(?)}");
            callableStatement.setInt(1, userid);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                address = createAddress(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return address;
    }

    @Override
    public int getLastAddressId() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getLastAddressId()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    @Override
    public boolean save(Address address) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveAddress(?, ?)}");
            callableStatement.setString(1, address.getLocation());
            callableStatement.setInt(2, address.getUserId());
            int response = callableStatement.executeUpdate();
            if (response == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean update(Address address) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateAddressByAddressId(?, ?)}");
            callableStatement.setInt(1, address.getAddressId());
            callableStatement.setString(2, address.getLocation());
            int response = callableStatement.executeUpdate();
            if (response == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public void deleteById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call deleteAddress(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    private Address createAddress(ResultSet rs) {
        Address address;
        try {
            int addressId = rs.getInt(1);
            String location = rs.getString(2);
            int userId = rs.getInt(3);
            address = new Address(addressId, location, userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return address;
    }


}
