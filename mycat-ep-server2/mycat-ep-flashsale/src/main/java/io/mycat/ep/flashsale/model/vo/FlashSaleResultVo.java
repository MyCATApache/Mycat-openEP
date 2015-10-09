package io.mycat.ep.flashsale.model.vo;

import io.mycat.ep.v1.flashsale.FlashSaleResult;

public class FlashSaleResultVo extends FlashSaleResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4951027883329331083L;
	
	public FlashSaleResultVo(int status){
		super.status=status;
	}
}
