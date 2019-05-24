<?php


class OutputJsonFormatterTest extends PHPUnit_Framework_TestCase
{
    public function testJsonFormat()
    {
        $data = array("code"=>1);

        $output = OutputJsonFormatter::encode($data);

        $data = json_decode($output, true);

        $this->assertArrayHasKey("code", $data);
    }

}
