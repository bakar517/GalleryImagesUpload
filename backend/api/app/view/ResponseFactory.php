<?php

class ResponseFactory
{

    const KEY_CODE_SUCCESS = 1;

    const KEY_CODE_ERROR = -1;

    public static function success(array $array = array(),$message=null){
        $response = new Response();
        $response->put(Response::KEY_STATUS_CODE,self::KEY_CODE_SUCCESS);
        if($message != null){
            $response->put(Response::KEY_MESSAGE,$message);
        }
        $response->putAll($array);
        return $response;
    }

    public static function error(array $array=array(),$message=null){
        $response = new Response();
        $response->put(Response::KEY_STATUS_CODE,self::KEY_CODE_ERROR);
        if($message != null){
            $response->put(Response::KEY_MESSAGE,$message);
        }
        $response->putAll($array);
        return $response;
    }
}