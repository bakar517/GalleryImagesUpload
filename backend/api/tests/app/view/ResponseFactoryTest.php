<?php


class ResponseFactoryTest extends PHPUnit_Framework_TestCase
{
    public function testSuccessResponse()
    {
        $response = ResponseFactory::success(array());

        $output = $response->toJson();

        $data = json_decode($output, true);

        $this->assertArrayHasKey(Response::KEY_STATUS_CODE, $data);

        $this->assertEquals(ResponseFactory::KEY_CODE_SUCCESS,$data[Response::KEY_STATUS_CODE]);
    }

    public function testErrorResponse()
    {
        $response = ResponseFactory::error(array());

        $output = $response->toJson();

        $data = json_decode($output, true);

        $this->assertArrayHasKey(Response::KEY_STATUS_CODE, $data);

        $this->assertEquals(ResponseFactory::KEY_CODE_ERROR,$data[Response::KEY_STATUS_CODE]);
    }

}
