<?php


class ResultTest extends PHPUnit_Framework_TestCase
{
    public function testResult()
    {
        $result = new Result(TRUE,null);
        $this->assertTrue($result->status());
        $this->assertNull($result->getData());
    }

    public function testResult1()
    {
        $data = array();
        $result = new Result(FALSE,$data);
        $this->assertFalse($result->status());
        $this->assertEquals($data,$result->getData());
    }

}
