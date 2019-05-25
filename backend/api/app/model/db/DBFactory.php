<?php


class DBFactory
{
    /**
     * Default DB instance of the Application.
     * @return AppDB
     */
    public static function getDbService(){
        $db = new AppDB();
        return $db;
    }

    /**
     * We can create db instance of any database service by providing config array same as below
     * @return AppDB
     */
    public static function getOtherDbService(){
        $config = array(DB_HOST,DB_NAME,DB_USER,DB_PWD);
        $db = new AppDB($config);
        return $db;
    }
}