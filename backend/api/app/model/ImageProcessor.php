<?php


class ImageProcessor
{

    private $service;

    private $error;

    private $message;

    private $userId;

    private $db;

    /**
     * ImageProcessor constructor.
     */
    public function __construct()
    {
        $this->db = DBFactory::getDbService();
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
        $this->service = StorageFactory::getService();
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
        if($this->db->hasError()){
            return FALSE;
        }

        $device_info = "";

        $sql = "insert into images_data(user_id,public_path,uploaded_date,device_info) values(:user_id,:public_path,now(),:device_info)";

        $params = array(':user_id' => $this->getUserId(),':public_path' => trim($this->getPath()),
            ':device_info' => trim($device_info));

        $record_id = $this->db->insert($sql,$params);

        if($this->db->hasError()){
            return FALSE;
        }
        return TRUE;
    }

    public function fetchUserImages($userId,$lastSeen,$resultCount){
        $this->setUserId($userId);
        if($this->db->hasError()){
            return new ImageResult(FALSE);
        }

        $sql = "select count(*) as count from images_data where user_id = :user_id and id > :lastSeen";

        $params = array(':user_id' => $this->getUserId(),':lastSeen' => $lastSeen);

        $data = $this->db->fetch($sql,$params);

        $total = $data[0]['count'];

        $sql = "select * from images_data where user_id = :user_id and id > :lastSeen order by id desc limit $resultCount";

        $params = array(':user_id' => $this->getUserId(),':lastSeen' => $lastSeen);

        $records = $this->db->fetch($sql,$params,'ImageItem');

        return new ImageResult(TRUE,$records,($total-count($records)) > 0);
    }
}