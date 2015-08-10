<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3>ICE接口数据读取demo <small>获取手机号为 13916969166  用户的所有订单信息</small></h3>
			<p class="text-info">输出结果</p>
			<table class="table">
				<tr>
					<th>orderId</th>
					<th>phone</th>
					<th>orderNum</th>
					<th>orderDate</th>
					<th>ticketType</th>
					<th>orderStatus</th>
				</tr>
				<?php
				foreach ($list as $key => $value) {
					echo '<tr>
					<td>' . $value->orderId . '</td>
					<td>' . $value->phone . '</td>
					<td>' . $value->orderNum . '</td>
					<td>' . $value->orderDate . '</td>
					<td>' . $value->ticketType . '</td>
					<td>' . $value->orderStatus . '</td>
					</tr>';
				}
				?>
			</table>
			<p class="text-info">demo代码</p>
			<pre>
namespace openep\controllers;

use Yii;
use yii\web\Controller;

$icepath = Yii::getAlias("@vendor/ice/ice_ns/Ice_ns.php");
$path = Yii::getAlias("@vendor/ticket/TicketServer.php");
require_once ($icepath);
require_once($path);

class SiteController extends Controller
{	
	public function actionIndex() 
	{
		$communicator = null;
		try
		{
			$communicator = \Ice\initialize();
			$proxy = $communicator->stringToProxy("TicketService:tcp -h 127.0.0.1 -p 8888");
			$TicketService = \ticket\TicketServicePrxHelper::checkedCast($proxy);
			$list = $TicketService->queryMyOrders('13916969166');
			return $this->render('index',array(
				'list' => $list,
			));
		}
		catch(\Ice\LocalException $ex)
		{			
			print_r($ex);
		}

		if($communicator)
		{
			try
			{
				$communicator->destroy();
			}
			catch(\Ice\LocalException $ex)
			{
				var_dump($ex);
			}
		}
	}
}
			</pre>
		</div>
	</div>
</div>