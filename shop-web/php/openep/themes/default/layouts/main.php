<?php
use yii\helpers\Html;
use openep\assets\AppAsset;

AppAsset::register($this);
?>
<?php $this->beginPage() ?>
<!DOCTYPE html>
<html lang="<?= Yii::$app->language ?>">
<head>
    <meta charset="<?= Yii::$app->charset ?>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <?= Html::csrfMetaTags() ?>
    <title><?= Html::encode($this->title) . '-' . Html::encode(Yii::$app->name) ?></title>
    <?php $this->head() ?>
</head>
<body>
<header class="navbar navbar-static-top bs-docs-nav" id="top" role="banner">
  <div class="container">
    <div class="navbar-header">
      <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a href="../" class="navbar-brand">Mycat-openEP开放电商项目</a>
    </div>
  </div>
</header>
<?php $this->beginBody() ?>
 	<?= $content ?>
 <?php $this->endBody() ?>

 <footer>
 	<div class="container">
 		<div class="text-right">
 		<p>
 			Mycat-openEP开放电商项目
 		</p>
 		</div>
 	</div>
 </footer>

</body>
</html>
<?php $this->endPage() ?>