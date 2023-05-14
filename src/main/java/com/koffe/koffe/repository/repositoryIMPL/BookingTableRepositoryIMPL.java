package com.koffe.koffe.repository.repositoryIMPL;

import com.koffe.koffe.model.BookingTable;
import com.koffe.koffe.repository.IBookingRepository;
import com.koffe.koffe.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingTableRepositoryIMPL implements IBookingRepository {

    @Override
    public List<BookingTable> findAll() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<BookingTable> bookingTables = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAllBookingTable()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                bookingTables.add(createBookingTable(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return bookingTables;
    }

    @Override
    public BookingTable findById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        BookingTable bookingTable = null;
        try {
            callableStatement = conn.prepareCall("{call findBookingTableById(?)}");
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                bookingTable = createBookingTable(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return bookingTable;
    }

    @Override
    public boolean save(BookingTable bookingTable) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call saveBookingTable(?, ?, ?, ?)}");
            callableStatement.setTimestamp(1, bookingTable.getArrivedTime());
            callableStatement.setInt(2, bookingTable.getAmountPeople());
            callableStatement.setString(3, bookingTable.getPhoneNumber());
            callableStatement.setString(4, bookingTable.getName());
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public boolean update(BookingTable bookingTable) {
        return false;
    }

    @Override
    public boolean updateById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call updateStatusBookingTable(?)}");
            callableStatement.setInt(1, id);
            int response = callableStatement.executeUpdate();
            if (response == 1)
                return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return false;
    }

    @Override
    public int getCountOfBookTable() {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call getCountOfBookTable()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return 0;
    }

    @Override
    public List<BookingTable> findAcceptedBookingTableByUserName(String name) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        List<BookingTable> bookingTables = new ArrayList<>();
        try {
            callableStatement = conn.prepareCall("{call findAcceptedBookingTableByUserName(?)}");
            callableStatement.setString(1, name);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                bookingTables.add(createBookingTable(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
        return bookingTables;
    }

    @Override
    public void deleteById(int id) {
        Connection conn = ConnectionDB.getConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = conn.prepareCall("{call deleteBookingTable(?)}");
            callableStatement.setInt(1, id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callableStatement);
        }
    }

    private BookingTable createBookingTable(ResultSet rs) {
        BookingTable bookingTable;
        try {
                int bookingTableId = rs.getInt(1);
                Timestamp arrivedTime = rs.getTimestamp(2);
                byte amountPeople = rs.getByte(3);
                String phoneNumber = rs.getString(4);
                String name = rs.getString(5);
                boolean status = rs.getBoolean(6);
                bookingTable = new BookingTable(bookingTableId, arrivedTime, amountPeople, phoneNumber, name, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookingTable;
    }
}
