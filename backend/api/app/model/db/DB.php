<?php


abstract class DB
{

    private $db;

    private $hasError = false;

    private $exception;

    public function __construct($connectionTimeout=10) {
        /*connection timeout in seconds*/

        $timeoutParams = array(
            PDO::ATTR_TIMEOUT => $connectionTimeout, PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
        );

        try{
            $this->db = new pdo('mysql:host='.$this->getHost().';dbname='.$this->getDbName(),
                $this->getUserName(),$this->getPassword(),$timeoutParams);
            $this->db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $this->db->setAttribute( PDO::ATTR_CASE, PDO::CASE_NATURAL );

        }catch (Exception $exception){
            $this->setError($exception);
        }
    }

    public function __destruct()
    {
        $this->close();
    }

    abstract function getHost();

    abstract function getDbName();

    abstract function getUserName();

    abstract function getPassword();

    private function setError(Exception $exception){
        $this->hasError = true;
        $this->exception = $exception;
    }

    public function  hasError(){
        return $this->hasError;
    }

    public function errorMessage(){
        return $this->exception;
    }

    public function close(){
        $this->db = null;
    }

    function insert($sql,$params){
        try {
            $stmt = $this->db->prepare($sql);
            $this->db->beginTransaction();
            $stmt->execute($params);
            $id = $this->db->lastInsertId();
            $this->db->commit();
            return $id;
        } catch(Exception $exception) {
            $this->setError($exception);
        }
        return -1;
    }

    function fetch($sql,$params,$className=null){
        $stmt = $this->db->prepare($sql);
        $stmt->execute($params);
        if($className != null) {
            $result = $stmt->fetchAll(PDO::FETCH_CLASS, $className);
        }else{
            $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
        }
        return $result;
    }
}