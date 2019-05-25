<?php


class RequestValidatorFactory
{
    public static function hasRequiredParams($requiredParamsArray,$input = null)
    {
        if ($input == null) {
            $input = $_REQUEST;
        }

        foreach ($requiredParamsArray as $key){
            if(!isset($input[$key])){
                return new Result(FALSE,$key);
            }
        }
        return new Result(TRUE);

    }
}