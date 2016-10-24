package com.tongchen.carpool.service.impl;

import com.tongchen.carpool.dao.BillDao;
import com.tongchen.carpool.dao.CarDao;
import com.tongchen.carpool.dao.RequestDao;
import com.tongchen.carpool.entity.Bill;
import com.tongchen.carpool.exception.BillException;
import com.tongchen.carpool.exception.RequestCloseException;
import com.tongchen.carpool.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tongchen on 2016-10-19.
 */
@Service
public class BillServiceImpl implements BillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BillDao billDao;

    @Autowired
    RequestDao requestDao;

    @Autowired
    CarDao carDao;

    public List<Bill> getMyBill(long userId) {
        return billDao.getMyBill(userId);
    }

    public boolean successBill(long billId) {
        return billDao.successBill(billId) > 0;
    }

    public boolean cancelBill(long billId) {
        return billDao.cancelBill(billId) > 0;
    }

    public boolean generateBill(int scheduledTimes, long userId, long requestId) throws BillException,RequestCloseException {
        try {
            if (billDao.checkBillNumFromSameRequest(requestId)+scheduledTimes>carDao.getCarSeatNumByRequestId(requestId)){


            if (billDao.insertBillInfo(userId, requestId) > 0) {
                if (billDao.checkBillNumFromSameRequest(requestId)==carDao.getCarSeatNumByRequestId(requestId)){
                    requestDao.fullRequest(requestId);
                }
                return true;
            }else{
                throw new RequestCloseException("This request is closed");
            }}
            else {
                throw new
            }
        } catch (RequestCloseException e1){
            throw e1;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new BillException("Bill inner error"+e.getMessage());
        }
    }
}
