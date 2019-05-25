<?php


class Result
{
    private $status;

    private $data;

    /**
     * Result constructor.
     */
    public function __construct($status,$data=null)
    {
        $this->setStatus($status);
        $this->setData($data);
    }


    /**
     * @return mixed
     */
    public function status()
    {
        return $this->status;
    }

    /**
     * @param mixed $status
     */
    public function setStatus($status)
    {
        $this->status = $status;
    }

    /**
     * @return mixed
     */
    public function getData()
    {
        return $this->data;
    }

    /**
     * @param mixed $data
     */
    public function setData($data)
    {
        $this->data = $data;
    }


}