<?php


class ImageController
{
    public function handleFile(){
        $requiredParamsArray = array('name', 'tmp_name', 'type');
        $fileTag = 'file';

        $result = ImageFileValidatorFactory::hasRequiredParams($fileTag,$requiredParamsArray);

        if(!$result[0]){
            $response = ResponseFactory::error(array(),"$result[1] is missing");
            $response->json();
            return;
        }

        $result = ImageFileValidatorFactory::hasValidFileFormat($fileTag);

        if(!$result[0]){
            $response = ResponseFactory::error(array(),"$result[1] invalid file type $result[1]");
            $response->json();
            return;
        }

        /*
         * we can perform other validations as well like image size etc
         *
         * */

        $image = new Image();

        $userid = 10;

        $image->moveFile($userid,$_FILES[$fileTag]);

        if($image->hasError()){
            $response = ResponseFactory::error(array(),$image->getMessage());
        }else{
            $response = ResponseFactory::success(array(),"Uploaded successfully");
            $response->put(Image::KEY_PATH,$image->getPath());
        }
        $response->json();

    }
}