<?php


class ImageFileValidatorFactory
{
    public static function hasRequiredParams($fileTag,$requiredParamsArray,$input = null){
        if($input == null){
            $input = $_FILES;
        }
        if(!isset($input[$fileTag])){
            return array(FALSE,$fileTag);
        }
        foreach ($requiredParamsArray as $key){
            if (!isset($input[$fileTag][$key])) {
                return array(FALSE,$key);
            }
        }
        return array(TRUE);
    }

    public static function hasValidFileFormat($fileTag,$input = null){
        $allowed_types = array('image/jpeg', 'image/jpg', 'image/png', 'image/pjpeg');

        if($input == null){
            $input = $_FILES;
        }

        $content_type = strtolower($input[$fileTag]['type']);

        if (!in_array($content_type, $allowed_types)) {
            return array(FALSE,$content_type);
        }

        return array(TRUE);
    }
}