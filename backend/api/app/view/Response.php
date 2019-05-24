<?php


class Response
{
    const KEY_STATUS_CODE = "statusCode";

    const KEY_MESSAGE = "message";

    protected $response;

    public function __construct($array = null){
        if($array == null){
            $this->response = array();
        }else{
            $this->response = $array;
        }
    }

    public function put($key,$data){
        $this->response[$key] = $data;
    }

    public function putAll($array){
        if(count($array) > 0)
            $this->response = array_merge($this->response,$array);
    }

    public function toJson(){
        return OutputJsonFormatter::encode($this->response);
    }

    public function json(){
        echo OutputJsonFormatter::encode($this->response);
    }
}