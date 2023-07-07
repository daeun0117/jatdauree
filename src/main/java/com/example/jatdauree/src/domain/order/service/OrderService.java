package com.example.jatdauree.src.domain.order.service;

import com.example.jatdauree.config.BaseException;
import com.example.jatdauree.config.BaseResponseStatus;
import com.example.jatdauree.src.domain.order.dao.OrderDao;
import com.example.jatdauree.src.domain.order.dto.GetOrderProRes;
import com.example.jatdauree.src.domain.order.dto.GetOrderRes;
import com.example.jatdauree.src.domain.order.dto.PostOrderCancelReq;
import com.example.jatdauree.src.domain.order.dto.PostPickupReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderDao ordersDao;

    @Autowired
    public OrderService(OrderDao ordersDao) {this.ordersDao = ordersDao;}

    public List<GetOrderRes> getOrdersBySellerId(int sellerIdx)throws BaseException {
        try {
            int storeIdx = ordersDao.getStoreIdxBySellerIdx(sellerIdx);
            List<GetOrderRes> getOrdersResList = ordersDao.getOrdersByStoreIdx(storeIdx);
            return getOrdersResList;
        }
        catch (Exception exception){
            System.out.println(exception);
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
    }

    public void postOrderBySellerId(int sellerIdx, PostOrderCancelReq postOrderCancelReq) throws BaseException{
        try {
            int storeIdx = ordersDao.getStoreIdxBySellerIdx(sellerIdx);
            ordersDao.updateOrderStatus(storeIdx, postOrderCancelReq.getOrderIdx(), postOrderCancelReq.getStatus());
        }
        catch (Exception exception){
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
    }

    public List<GetOrderProRes> getOrderProBySellerIdx(int sellerIdx)throws BaseException {
        try {
            int storeIdx = ordersDao.getStoreIdxBySellerIdx(sellerIdx);
            List<GetOrderProRes> getOrderProResList = ordersDao.getOrderProByStoreIdx(storeIdx);
            return getOrderProResList;
        }
        catch (Exception exception){
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
    }

    public void postPickupBysellerIdx(int sellerIdx, PostPickupReq postPickupReq) throws BaseException {
        try{
            int storeIdx = ordersDao.getStoreIdxBySellerIdx(sellerIdx);
            ordersDao.updateOrderPickup(storeIdx, postPickupReq.getOrderIdx(), postPickupReq.getStatus());
        }
        catch(Exception exception){
            throw new BaseException(BaseResponseStatus.RESPONSE_ERROR);
        }
    }


}
