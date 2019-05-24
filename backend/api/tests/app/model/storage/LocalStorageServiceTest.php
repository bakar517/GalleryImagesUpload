<?php


class LocalStorageServiceTest extends PHPUnit_Framework_TestCase
{
    public function testLocalStorage()
    {
        $mock = $this->getMockBuilder('LocalStorageService')
            ->setMethods(array('storeFile'))
            ->getMock();

        $error_msg = ERROR_DIR_CREATION;
        $storageResult = new StorageResult();
        $storageResult->setError(TRUE);
        $storageResult->setMessage($error_msg);

        $userId = 65060;

        $mock->expects($this->once())->method('storeFile')->will($this->returnValue($storageResult));

        $result = $mock->storeFile($userId,"");

        $this->assertEquals($storageResult,$result);
    }

}
