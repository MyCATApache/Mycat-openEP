package io.mycat.ep.flashsale.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface FlashSaleDAO {

	void updateFlashSaleUserId(@Param("userId")long userId,@Param("takeTime")Date takeTime, @Param("flashSaleId")long flashSaleId);

}
