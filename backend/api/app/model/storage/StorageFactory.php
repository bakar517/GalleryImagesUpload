<?php


class StorageFactory
{
    public static function getService(){
        return StorageFactory::getLocalService();
    }

    private static function getLocalService(){
        $service = new LocalStorageService();
        return $service;
    }
}