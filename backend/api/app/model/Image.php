<?php


class Image
{
    const KEY_PATH = "key_path";

    private $service;

    private $error;

    private $message;

    private $userId;

    /**
     * Image constructor.
     */
    public function __construct()
    {
        $this->service = StorageFactory::getService();
    }


    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->userId;
    }

    /**
     * @param mixed $userId
     */
    public function setUserId($userId)
    {
        $this->userId = $userId;
    }

    /**
     * @param mixed $message
     */
    public function setMessage($message)
    {
        $this->message = $message;
    }

    /**
     * @param mixed $path
     */
    public function setPath($path)
    {
        $this->path = $path;
    }

    private $path;

    /**
     * @return mixed
     */
    public function hasError()
    {
        return $this->error;
    }

    /**
     * @param mixed $error
     */
    public function setError($error)
    {
        $this->error = $error;
    }

    /**
     * @return mixed
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * @return mixed
     */
    public function getPath()
    {
        return $this->path;
    }

    public function moveFile($userId,$file){
        $this->setUserId($userId);
        $result = $this->service->storeFile($this->getUserId(),$file);
        $this->setError($result->hasError());
        if($result->hasError()){
            $this->setMessage($result->getMessage());
        }else{
            //store path in db
            $this->setPath($result->getPath());
            if($this->updateDb() != TRUE){
                $this->setError(TRUE);
                $this->setMessage("There is error in connecting with db.");
            }
        }
    }

    private function updateDb(){
        $db = new AppDB();
        if($db->hasError()){
            return FALSE;
        }

        $device_info = "";

        $sql = "insert into images_data(user_id,file_path,uploaded_date,device_info) values(:user_id,:file_path,now(),:device_info)";

        $params = array(':user_id' => $this->getUserId(),':file_path' => trim($this->getPath()),
            ':device_info' => trim($device_info));

        $record_id = $db->insert($sql,$params);

        if($db->hasError()){
            return FALSE;
        }
        return TRUE;
    }
}