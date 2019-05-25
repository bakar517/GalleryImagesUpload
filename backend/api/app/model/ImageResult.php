<?php


class ImageResult extends Result
{
    private $hasMore;

    /**
     * ImageResult constructor.
     * @param $hasMore
     */
    public function __construct($status,$data=null,$hasMore=FALSE)
    {
        parent::__construct($status,$data);
        $this->setHasMore($hasMore);
    }

    /**
     * @return mixed
     */
    public function hasMore()
    {
        return $this->hasMore;
    }

    /**
     * @param mixed $hasMore
     */
    public function setHasMore($hasMore)
    {
        $this->hasMore = $hasMore;
    }


}