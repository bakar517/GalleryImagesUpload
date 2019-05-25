<?php


class ImageResultTest extends PHPUnit_Framework_TestCase
{
    public function testResult()
    {
        $result = new ImageResult(TRUE,null);
        $this->assertTrue($result->status());
        $this->assertNull($result->getData());
        $this->assertFalse($result->hasMore());
    }


    public function testResult1()
    {
        $result = new ImageResult(TRUE,null,true);
        $this->assertTrue($result->status());
        $this->assertNull($result->getData());
        $this->assertTrue($result->hasMore());
    }

}
