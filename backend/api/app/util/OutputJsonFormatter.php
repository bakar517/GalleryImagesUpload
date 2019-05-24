<?php


class OutputJsonFormatter
{
    public static function encode($obj){
        $encoded = json_encode($obj);

        return $encoded;
    }
}