<?php


class ImageController
{
    const KEY_PATH = "public_path";

    const KEY_IMAGE_LIST = "list";

    const KEY_HAS_MORE_ELEMENTS = "hasMore";

    public function handleFile(){
        $requiredParamsArray = ['userid','device_info'];

        $result = RequestValidatorFactory::hasRequiredParams($requiredParamsArray);

        if(!$result->status()){
            $response = ResponseFactory::error(array(),$result->getData()." parameter is missing");
            $response->json();
            return;
        }

        $fileTag = 'file';
        $result = ImageFileValidatorFactory::hasValidFileFormat($fileTag);

        if(!$result->status()){
            $response = ResponseFactory::error(array(),$result->getData()." invalid file type $result[1]");
            $response->json();
            return;
        }

        $fileRequiredParamsArray = array('name', 'tmp_name', 'type');

        $result = ImageFileValidatorFactory::hasRequiredParams($fileTag,$fileRequiredParamsArray);

        if(!$result->status()){
            $response = ResponseFactory::error(array(),$result->getData()." is missing");
            $response->json();
            return;
        }

        /*
         * we can perform other validations as well like image size etc
         *
         * */

        $image = new ImageProcessor();

        $userid = 10;

        $image->moveFile($userid,$_FILES[$fileTag]);

        if($image->hasError()){
            $response = ResponseFactory::error(array(),$image->getMessage());
        }else{
            $response = ResponseFactory::success(array(),"Uploaded successfully");
            $response->put(ImageController::KEY_PATH,$image->getPath());
        }
        $response->json();

    }

    public function fetchImages(){
        $requiredParamsArray = ['userid'];

        $result = RequestValidatorFactory::hasRequiredParams($requiredParamsArray);

        if(!$result->status()){
            $response = ResponseFactory::error(array(),$result->getData()." parameter is missing");
            $response->json();
            return;
        }

        $image = new ImageProcessor();

        $userid = $_REQUEST['userid'];

        $lastSeen = 0;

        $resultCount = DEFAULT_RECORD_COUNT;

        if(isset($_REQUEST['lastSeen'])){
            $lastSeen = $_REQUEST['lastSeen'];
        }

        if(isset($_REQUEST['resultCount'])){
            $resultCount = $_REQUEST['resultCount'];
            if($resultCount > MAX_RECORD_COUNT){
                $resultCount = MAX_RECORD_COUNT;
            }
        }

        $result = $image->fetchUserImages($userid,$lastSeen,$resultCount);

        if(!$result->status()){
            $response = ResponseFactory::error(array(),$result->getData());
        }else{
            $response = ResponseFactory::success(array(),"Loaded successfully");
            $response->put(ImageController::KEY_HAS_MORE_ELEMENTS,$result->hasMore());
            $response->put(ImageController::KEY_IMAGE_LIST,$result->getData());
        }
        $response->json();
    }
}