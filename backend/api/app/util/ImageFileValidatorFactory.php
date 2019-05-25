<?php


class ImageFileValidatorFactory
{
    public static function hasRequiredParams($fileTag,$requiredParamsArray,$input = null){
        if($input == null){
            $input = $_FILES;
        }
        foreach ($requiredParamsArray as $key){
            if (!isset($input[$fileTag][$key])) {
                return new Result(FALSE,$key);
            }
        }
        return new Result(TRUE);
    }

    public static function hasValidFileFormat($fileTag,$input = null){
        $allowed_types = array('image/jpeg', 'image/jpg', 'image/png', 'image/pjpeg');

        if($input == null){
            $input = $_FILES;
        }

        $content_type = strtolower($input[$fileTag]['type']);

        if (!in_array($content_type, $allowed_types)) {
            return new Result(FALSE,$content_type);
        }

        return new Result(TRUE);
    }
}