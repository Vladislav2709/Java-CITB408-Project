@echo off
setlocal

REM Компилиране на всички Java файлове
javac magazin\*.java

REM Стартиране на програмата
java magazin.Main

pause
