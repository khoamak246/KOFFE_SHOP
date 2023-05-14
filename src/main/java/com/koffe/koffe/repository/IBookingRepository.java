package com.koffe.koffe.repository;

import com.koffe.koffe.model.BookingTable;
import com.koffe.koffe.repository.design.IRepository;

import java.util.List;

public interface IBookingRepository extends IRepository<BookingTable>{
    boolean updateById(int id);
    int getCountOfBookTable();
    List<BookingTable> findAcceptedBookingTableByUserName(String name);
}
