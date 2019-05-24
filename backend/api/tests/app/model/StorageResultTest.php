<?php


class StorageResultTest extends PHPUnit_Framework_TestCase
{
    public function testResult()
    {
        $error_msg = ERROR_DIR_CREATION;
        $storageResult = new StorageResult();
        $storageResult->setError(TRUE);
        $storageResult->setMessage($error_msg);

        $this->assertTrue($storageResult->hasError());
        $this->assertEquals($error_msg,$storageResult->getMessage());
    }

    public function testUnableToCopyFile()
    {
        $error_msg = ERROR_FILE_COPY;
        $storageResult = new StorageResult();
        $storageResult->setError(TRUE);
        $storageResult->setMessage($error_msg);

        $this->assertTrue($storageResult->hasError());
        $this->assertEquals($error_msg,$storageResult->getMessage());
    }

    public function testCopyFile()
    {
        $filePath = "/data/picture.png";
        $storageResult = new StorageResult();
        $storageResult->setError(FALSE);
        $storageResult->setPath($filePath);

        $this->assertFalse($storageResult->hasError());
        $this->assertEquals($filePath,$storageResult->getPath());
    }

}
