<?php
require_once 'app/config/AppConfig.php';
require_once 'app/util/ImageFileValidatorFactory.php';
require_once 'app/util/OutputJsonFormatter.php';
require_once 'app/view/Response.php';
require_once 'app/view/ResponseFactory.php';
require_once 'app/controller/ImageController.php';
require_once 'app/model/storage/StorageService.php';
require_once 'app/model/storage/LocalStorageService.php';
require_once 'app/model/storage/StorageFactory.php';
require_once 'app/model/db/DB.php';
require_once 'app/model/db/AppDB.php';
require_once 'app/util/FileHelper.php';
require_once 'app/model/storage/StorageResult.php';
require_once 'app/model/Image.php';
require_once 'app/util/CloudTool.php';
