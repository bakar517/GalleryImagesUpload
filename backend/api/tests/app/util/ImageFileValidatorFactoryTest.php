<?php


class ImageFileValidatorFactoryTest extends PHPUnit_Framework_TestCase
{
    public function testFileValidationWrongFileTag()
    {
        $requiredParamsArray = array('name', 'tmp_name', 'type');
        $fileTag = 'file';
        $input = array();
        $input[0] = $fileTag;
        $input = array(
            $fileTag => array('name', 'tmp_name', 'type')
        );

        $wrongFileTag = 'myfile';

        $result = ImageFileValidatorFactory::hasRequiredParams($wrongFileTag,$requiredParamsArray,$input);
        $this->assertFalse($result->status());

    }

    public function testFileValidation()
    {
        $requiredParamsArray = array('name', 'tmp_name', 'type');
        $fileTag = 'file';
        $input = array();
        $input[$fileTag]['name'] = "image.jpg";
        $input[$fileTag]['tmp_name'] = "test123.jpg";
        $input[$fileTag]['type'] = "image/jpeg";

        $result = ImageFileValidatorFactory::hasRequiredParams($fileTag,$requiredParamsArray,$input);
        $this->assertTrue($result->status());
    }

    public function testWrongFileTypeValidation()
    {
        $fileTag = 'file';
        $input = array();
        $input[$fileTag]['name'] = "image.txt";
        $input[$fileTag]['tmp_name'] = "test123.txt";
        $input[$fileTag]['type'] = "text/plain";

        $result = ImageFileValidatorFactory::hasValidFileFormat($fileTag,$input);
        $this->assertFalse($result->status());
    }

    public function testFileTypeValidation()
    {
        $fileTag = 'file';
        $input = array();
        $input[$fileTag]['name'] = "image.jpg";
        $input[$fileTag]['tmp_name'] = "test123.jpg";
        $input[$fileTag]['type'] = "image/jpeg";

        $result = ImageFileValidatorFactory::hasValidFileFormat($fileTag,$input);
        $this->assertTrue($result->status());
    }

}
