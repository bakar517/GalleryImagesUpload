<?php


class FileHelper
{
    public function fileCount($dir){
        $fileIterator = new FilesystemIterator($dir, FilesystemIterator::SKIP_DOTS);
        return iterator_count($fileIterator);
    }

    public function listOfDirectories($dir){
        $dirIterator = new DirectoryIterator($dir);
        $list = array();
        foreach ($dirIterator as $file) {
            if ($file->isDir() && !$file->isDot()) {
                array_push($list,$file->getFilename());
            }
        }
        return $list;
    }
}