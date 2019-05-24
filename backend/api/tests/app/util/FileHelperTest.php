<?php


class FileHelperTest extends PHPUnit_Framework_TestCase
{
    public function testFileCount()
    {
        $mock = $this->getMockBuilder('FileHelper')
            ->setMethods(array('fileCount'))
            ->getMock();

        $mock->expects($this->once())->method('fileCount')->will($this->returnValue(5));

        $this->assertEquals(5,$mock->fileCount(""));
    }

    public function testListOfDirectories()
    {
        $mock = $this->getMockBuilder('FileHelper')
            ->setMethods(array('listOfDirectories'))
            ->getMock();

        $list = array("1","2","3");

        $mock->expects($this->once())->method('listOfDirectories')->will($this->returnValue($list));

        $this->assertEquals($list,$mock->listOfDirectories(""));
    }

}
