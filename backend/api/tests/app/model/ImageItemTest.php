<?php


class ImageItemTest extends PHPUnit_Framework_TestCase
{
    public function testItem()
    {
        $item = new ImageItem();
        $item->setId(10);
        $this->assertEquals(10,$item->getId());
    }

    public function testItem1()
    {
        $item = new ImageItem();
        $item->setUserId(65060);
        $this->assertEquals(65060,$item->getUserId());
    }

    public function testItem2()
    {
        $item = new ImageItem();
        $item->setPublicPath("http://127.0.0.1/backend_data/1/10_6989_1558691309.png");
        $this->assertEquals("http://127.0.0.1/backend_data/1/10_6989_1558691309.png",$item->getPublicPath());
    }

    public function testItem3()
    {
        $item = new ImageItem();
        $item->setDeviceInfo("{}");
        $this->assertEquals("{}",$item->getDeviceInfo());
    }

}
