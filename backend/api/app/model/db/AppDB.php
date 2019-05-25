<?php


class AppDB extends DB
{


    private $config ;

    /**
     * AppDB constructor.
     */
    public function __construct($array = null,$connectionTimeout=10)
    {
        if($array == null)
            $this->config = array(DB_HOST,DB_NAME,DB_USER,DB_PWD);
        else
            $this->config = $array;

        parent::__construct($connectionTimeout);
    }

    function getHost()
    {
        return $this->config[0];
    }

    function getDbName()
    {
        return $this->config[1];
    }

    function getUserName()
    {
        return $this->config[2];
    }

    function getPassword()
    {
        return $this->config[3];
    }
}