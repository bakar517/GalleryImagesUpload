<?php


class AppConfigTest extends PHPUnit_Framework_TestCase
{
    public function testVariableDataDirName()
    {
        $this->assertNotNull(DATA_DIR_NAME);
    }

    public function testVariableDataDirPath()
    {
        $this->assertNotNull(ROOT_DATA_DIR);
    }

}
