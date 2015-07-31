<?php
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
