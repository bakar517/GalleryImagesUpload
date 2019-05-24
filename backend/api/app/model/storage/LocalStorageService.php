<?php


class LocalStorageService extends StorageService
{
    const DIRECTORY_START = 1;

    const NUMBER_OF_FILES_PER_DIRECTORY = 10;

    const DIR_MODE = 0755;

    /**
     * LocalStorageService constructor.
     */
    public function __construct()
    {

    }


    function storeFile($userId,$file)
    {
        $tmpFile = $file['tmp_name'];
        $extension = pathinfo($file['name'], PATHINFO_EXTENSION);
        $result = $this->copyToPath($userId,$tmpFile,$extension);
        $storageResult = new StorageResult();
        $storageResult->setError($result[0]);
        if($result[0]){//has error
            $storageResult->setMessage($result[1]);
        }else{
            $storageResult->setPath($result[1]);
        }
        return $storageResult;
    }

    private function getDataDir(){
        return ROOT_DATA_DIR;
    }

    private function copyToPath($userId,$tmpFile,$extension){
        $path = $this->getDataDir();
        if (!file_exists($path)) {
            if(!mkdir($path, self::DIR_MODE, true)){
                return array(TRUE,ERROR_DIR_CREATION);
            }
        }

        $fileHelper = new FileHelper();

        $dirList = $fileHelper->listOfDirectories($path);

        $lastDir = self::DIRECTORY_START;

        if(count($dirList) > 0){
            $lastDir = max($dirList);
            $newPath = $path.$lastDir;
            if (file_exists($newPath)) {
                if($fileHelper->fileCount($newPath) >= self::NUMBER_OF_FILES_PER_DIRECTORY){
                    $lastDir = intval($lastDir)+1;
                }
            }
        }

        $newPath = $path.$lastDir;

        if (!file_exists($newPath)) {
            if(!mkdir($newPath, self::DIR_MODE, true)){
                return array(TRUE,ERROR_DIR_CREATION);
            }
        }

        $fileName = $userId.'_'.rand(1000, 9999).'_'.time().'.'.$extension;

        $newFilePath = $newPath.'/'.$fileName;

        $relativePath = $lastDir.'/'.$fileName;

        if(!move_uploaded_file($tmpFile, $newFilePath)){
            return array(TRUE,ERROR_FILE_COPY);
        }
        return array(FALSE,CloudTool::getPublicUrl($relativePath));
    }
}