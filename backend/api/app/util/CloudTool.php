<?php


class CloudTool
{
    public static function getPublicUrl($relativePath){
        $path = (isset($_SERVER["HTTPS"]) && $_SERVER["HTTPS"] == "on") ? "https://" : "http://";
        $path .=$_SERVER["SERVER_NAME"].'/'. DATA_DIR_NAME.'/'.$relativePath;
        return $path;
    }
}