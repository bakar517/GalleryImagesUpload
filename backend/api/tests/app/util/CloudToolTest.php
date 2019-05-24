<?php


class CloudToolTest extends PHPUnit_Framework_TestCase
{
    public function testPublicUrl()
    {
        $relativePath = "test/test.png";
        $this->assertStringEndsWith($relativePath,CloudTool::getPublicUrl($relativePath));
    }

}
