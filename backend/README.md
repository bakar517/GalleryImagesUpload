# App Backend

This application was created using PHP/mysql. Testing was done on following server

Local Machine
PHP Version 5.4.3
Mysql version 5.5.24
PHP extension: mysqli
Server: localhost (localhost via TCP/IP)


Online server
PHP Version 7.2.18
Mysql version 10.2.23
PHP extension: mysqli
Server: localhost (localhost via TCP/IP)


# Deploying backend


Follow these steps to deploy it on any PHP/mysql server. Minium supported PHP version is 5.4

1.Copy php source files using git or download zip file

Clone with Git
https://github.com/junto06/GalleryImagesUpload.git

or download zip file
https://codeload.github.com/junto06/GalleryImagesUpload/zip/master


2.Once we have copied with git or unzip downloaded file, we need to create Database. Follow these steps to created db.

-create a database "galleryimagesupload" in mysql db and import "db/galleryimagesupload.sql" file.
-open api\app\config\AppConfig.php file and update db configurations parameters

define('DB_HOST','127.0.0.1');//Update DB_HOST name in case its hosted on different VM/Machine.
define('DB_NAME','galleryimagesupload');//DB name in case its different
define('DB_USER','root');//DB username
define('DB_PWD',''); //DB password


There are other parameters as well like number of files per directory. I have put 15 files per
directory by default and maxium of 50. For better performance we should not store many files in 
a single directory thats why I proggrammed it this way. Default is 15 files per directory but you can
change it to any value.

define('DEFAULT_RECORD_COUNT',15);


I could have created a test.php script to verify if everything is working fine like apis and db but could not done because of time.


# Unit/Integation Tests

This application is built using PHP Storm. Automated tests can be found under "api/tests". 

We need to define configurations for unit tests in PHP storms in oder to run these local tests.
Under settings->Language and fragemework->PHP->Test Framewroks and then press Add button.
Add local unit test and then select "Path to phpunit.phar" and give browse file from "api\testconfig".
Then need to define Test Runner configurations that is also present in "api\testconfig". See screenshot
in "unit_test/test_config.png" for reference.
