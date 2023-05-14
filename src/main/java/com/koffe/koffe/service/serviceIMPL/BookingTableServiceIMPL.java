package com.koffe.koffe.service.serviceIMPL;

import com.koffe.koffe.model.BookingTable;
import com.koffe.koffe.repository.IBookingRepository;
import com.koffe.koffe.repository.repositoryIMPL.BookingTableRepositoryIMPL;
import com.koffe.koffe.service.IBookingTableService;
import com.koffe.koffe.utils.ConvertTimeStamp;
import com.koffe.koffe.utils.Validate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BookingTableServiceIMPL implements IBookingTableService {

    IBookingRepository bookingRepository = new BookingTableRepositoryIMPL();
    @Override
    public List<BookingTable> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingTable findById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public boolean save(BookingTable bookingTable) {
        return bookingRepository.save(bookingTable);
    }

    @Override
    public boolean update(BookingTable bookingTable) {
        return false;
    }

    @Override
    public boolean updateById(int id) {
        return bookingRepository.updateById(id);
    }

    @Override
    public long getLimitDateBookingTimeDistance() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime then = now.plusDays(30);
        Timestamp currTimestamp = Timestamp.valueOf(now);
        Timestamp nextTimestamp = Timestamp.valueOf(then);
        return nextTimestamp.getTime() - currTimestamp.getTime();
    }

    @Override
    public long getTimeLimitDistance() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime afterOneHour = now.plusHours(1);
        Timestamp currTimestamp = Timestamp.valueOf(now);
        Timestamp timestampAfterOneHour = Timestamp.valueOf(afterOneHour);
        return timestampAfterOneHour.getTime() - currTimestamp.getTime();
    }

    @Override
    public long getBookingDateAndCurrentDateTimeDistance(String bookingDate) {
        Timestamp bookingTimestamp = ConvertTimeStamp.formatTimeStampFromString(bookingDate);
        LocalDateTime now = LocalDateTime.now();
        Timestamp currTimestamp = Timestamp.valueOf(now);
        return bookingTimestamp.getTime() - currTimestamp.getTime();
    }

    @Override
    public boolean isBookingDateAfterCurrentDate(String bookingDate) {
        return getBookingDateAndCurrentDateTimeDistance(bookingDate) > 0;
    }

    @Override
    public boolean isBookingDateInLimitDateBookingTimeDistance(String bookingDate) {
        return getBookingDateAndCurrentDateTimeDistance(bookingDate) - getLimitDateBookingTimeDistance() < 0;
    }

    @Override
    public boolean isBookingDateAfterTimeLimitDistance(String bookingDate) {
        return getBookingDateAndCurrentDateTimeDistance(bookingDate) - getTimeLimitDistance() > 0;
    }

    @Override
    public String handleInputDateTime(String date, String time) {
        return ConvertTimeStamp.handleInputDateTimeString(date, time);
    }

    @Override
    public Timestamp getTimeStampFromInput(String timestampStringFormat) {
        return ConvertTimeStamp.formatTimeStampFromString(timestampStringFormat);
    }

    @Override
    public boolean isMatchesFullName(String fullName) {
        return Validate.isMatchesFullName(fullName);
    }

    @Override
    public boolean isMatchesPhoneNumber(String phoneNumber) {
        return Validate.isMatchesPhoneNumber(phoneNumber);
    }

    @Override
    public int getCountOfBookTable() {
        return bookingRepository.getCountOfBookTable();
    }

    @Override
    public List<BookingTable> findAcceptedBookingTableByUserName(String name) {
        return bookingRepository.findAcceptedBookingTableByUserName(name);
    }

    @Override
    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }
}
