
#服务接口列表

|模块     |服务代理类    |方法 |说明   |是否验证|
| ----    | -----       |----|----  |----    |
|user     |UserHandlerPrx | UserSession regist(ClientInfo info)| 注册|是|
|user     |UserHandlerPrx | UserCommonResult sendSMSCode(string phone)| 发送验证码|否|
|user     |UserHandlerPrx |UserCommonResult changePassword(long userId, string smsCode,string newPassword)| 修改密码|否|
|user     |UserHandlerPrx |UserSession login(ClientInfo info)|登陆|是|
|user     |UserSessionHandlerPrx|UserSession checkLoginStatus(long userId,string token)|验证登陆|是|
|goods    |GoodsHandlerPrx | GoodsPriceQueryResult queryGoodsPrice(GoodsPriceQuery query)|查询商品价格|否|
|goods    |GoodsStorageManageHandlerPrx | StorageAmount queryGoodsAmount(StorageQuery query)|查询库存|否|
|goods    |GoodsStorageManageHandlerPrx |StorageChangeResult changeStorage(StorageChange change)|修改库存|否|
|goods    |GoodsStorageManageHandlerPrx |StorageChangeResult changeStorageBatch(StorageChangeSeq change)|批量修改库存|否|
|order    |OrderHandlerPrx  |OrderResult makeOrder(OrderInfo info)|创建订单|否|
|order    |OrderHandlerPrx  |OrderResult makeOrderFromCart(CartOrderInfo info)|购物车创建订单|否|
|flashsale|FlashBuyHandlerPrx|FlashSaleResult flashBuy(FlashSaleInfo sale)|秒杀|否|
