package io.mycat.ep.receiver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.mycat.ep.receiver.model.PurchaseReceiverMo;

public interface PurchaseReceiverDAO {

	List<PurchaseReceiverMo> findPurchaseReceiverListByUserId(@Param("userId")long userId);

}
