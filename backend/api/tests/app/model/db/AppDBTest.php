<?php


class AppDBTest extends PHPUnit_Framework_TestCase
{
    public function testConnection()
    {
        $dbConnection = new AppDB();
        $this->assertFalse($dbConnection->hasError());
    }


    public function testConnectionWithConfig()
    {
        $array = array(DB_HOST,DB_NAME,DB_USER,DB_PWD);
        $dbConnection = new AppDB($array);
        $this->assertFalse($dbConnection->hasError());
    }

    public function testWrongHost()
    {
        $array = array("192.168.1.100",DB_NAME,DB_USER,DB_PWD);
        $dbConnection = new AppDB($array,3);
        $this->assertTrue($dbConnection->hasError());
    }

    public function testWrongDbName()
    {
        $array = array(DB_HOST,"wrong_db_name",DB_USER,DB_PWD);
        $dbConnection = new AppDB($array);
        $this->assertTrue($dbConnection->hasError());
    }

    public function testWrongUserName()
    {
        $array = array(DB_HOST,DB_NAME,"wrong_username",DB_PWD);
        $dbConnection = new AppDB($array);
        $this->assertTrue($dbConnection->hasError());
    }

    public function testWrongPassword()
    {
        $array = array(DB_HOST,DB_NAME,DB_USER,"wrong_password");
        $dbConnection = new AppDB($array);
        $this->assertTrue($dbConnection->hasError());
    }

}
