<?php


class ImageItem
{
    public $id;

    protected $user_id;

    public $public_path;

    public $uploaded_date;

    public $device_info;

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getUserId()
    {
        return $this->user_id;
    }

    /**
     * @param mixed $user_id
     */
    public function setUserId($user_id)
    {
        $this->user_id = $user_id;
    }

    /**
     * @return mixed
     */
    public function getPublicPath()
    {
        return $this->public_path;
    }

    /**
     * @param mixed $public_path
     */
    public function setPublicPath($public_path)
    {
        $this->public_path = $public_path;
    }

    /**
     * @return mixed
     */
    public function getUploadedDate()
    {
        return $this->uploaded_date;
    }

    /**
     * @param mixed $uploaded_date
     */
    public function setUploadedDate($uploaded_date)
    {
        $this->uploaded_date = $uploaded_date;
    }

    /**
     * @return mixed
     */
    public function getDeviceInfo()
    {
        return $this->device_info;
    }

    /**
     * @param mixed $device_info
     */
    public function setDeviceInfo($device_info)
    {
        $this->device_info = $device_info;
    }
}