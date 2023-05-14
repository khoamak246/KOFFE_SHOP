package com.koffe.koffe.service;

import com.koffe.koffe.model.BookingTable;
import com.koffe.koffe.service.design.IService;

import java.sql.Timestamp;
import java.util.List;

public interface IBookingTableService extends IService<BookingTable> {
    boolean updateById(int id);
    long getLimitDateBookingTimeDistance();
    long getTimeLimitDistance();
    long getBookingDateAndCurrentDateTimeDistance(String bookingDate);
    boolean isBookingDateAfterCurrentDate(String bookingDate);
    boolean isBookingDateInLimitDateBookingTimeDistance(String bookingDate);
    boolean isBookingDateAfterTimeLimitDistance(String bookingDate);
    String handleInputDateTime(String date, String time);
    Timestamp getTimeStampFromInput(String timestampStringFormat);
    boolean isMatchesFullName(String fullName);
    boolean isMatchesPhoneNumber(String phoneNumber);
    int getCountOfBookTable();
    List<BookingTable> findAcceptedBookingTableByUserName(String name);
}
