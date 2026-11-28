@echo off
setlocal EnableExtensions EnableDelayedExpansion
title OpenAugustus b4.20.3

set "ROOT_DIR=%~dp0"
if "%ROOT_DIR:~-1%"=="\" set "ROOT_DIR=%ROOT_DIR:~0,-1%"
set "MC_DIR=%APPDATA%\.minecraft"

set "CLIENT_JAR=%ROOT_DIR%\OpenAugustus.jar"
if not exist "%CLIENT_JAR%" set "CLIENT_JAR=%ROOT_DIR%\Augustus-source-development.jar"
if not exist "%CLIENT_JAR%" set "CLIENT_JAR=%ROOT_DIR%\dist\Augustus-source-development.jar"

REM ============================================================================
REM 1. Auto-detect 64-bit Java 8 runtime
REM ============================================================================
set "JAVA8_EXE="

if defined JAVA8_HOME if exist "%JAVA8_HOME%\bin\java.exe" set "JAVA8_EXE=%JAVA8_HOME%\bin\java.exe"
if defined JAVA8_EXE goto :java_found

for /d %%D in (
    "%ROOT_DIR%\jre8*"
    "%ROOT_DIR%\build-tools\java8*"
    "C:\Program Files\Java\jre1.8*"
    "C:\Program Files\Java\jdk1.8*"
    "C:\Program Files\Eclipse Adoptium\jre-8*"
    "C:\Program Files\Eclipse Adoptium\jdk-8*"
    "C:\Program Files\Zulu\zulu-8*"
    "C:\Program Files\BellSoft\LibericaJRE-8*"
    "C:\Program Files\BellSoft\LibericaJDK-8*"
    "C:\Program Files\Amazon Corretto\jre8*"
    "C:\Program Files\Amazon Corretto\jdk1.8*"
    "%LOCALAPPDATA%\Programs\Eclipse Adoptium\jdk-8*"
) do (
    if not defined JAVA8_EXE if exist "%%~fD\bin\java.exe" set "JAVA8_EXE=%%~fD\bin\java.exe"
)
if defined JAVA8_EXE goto :java_found

if defined JAVA_HOME if exist "%JAVA_HOME%\bin\java.exe" set "JAVA8_EXE=%JAVA_HOME%\bin\java.exe"
if defined JAVA8_EXE goto :java_found

where java.exe >nul 2>&1
if not errorlevel 1 set "JAVA8_EXE=java.exe"
if defined JAVA8_EXE goto :java_found

echo [ERROR] 64-bit Java 8 runtime was not found on this system.
echo Please install a 64-bit JRE/JDK 8 (e.g., Adoptium Temurin 8 or Zulu 8) or set JAVA8_HOME.
pause
exit /b 1

:java_found

REM ============================================================================
REM 2. Locate compiled client JAR (or build from source if inside repository)
REM ============================================================================
if exist "%CLIENT_JAR%" goto :jar_ready

if not exist "%ROOT_DIR%\build.py" (
    echo [ERROR] Client JAR not found in the current directory.
    pause
    exit /b 1
)

echo [INFO] Compiled JAR not found in dist\. Building from clean source...
pushd "%ROOT_DIR%"
where py.exe >nul 2>&1
if not errorlevel 1 (
    py -B build.py --jobs 4
) else (
    python -B build.py --jobs 4
)
set "BUILD_ERR=%ERRORLEVEL%"
popd
if not "%BUILD_ERR%"=="0" (
    echo [ERROR] Source build failed.
    pause
    exit /b %BUILD_ERR%
)
set "CLIENT_JAR=%ROOT_DIR%\dist\Augustus-source-development.jar"

:jar_ready

REM ============================================================================
REM 3. Prepare .minecraft directory and ensure OpenGL Framebuffers (FBO)
REM ============================================================================
if not exist "%MC_DIR%" mkdir "%MC_DIR%" >nul 2>&1
if not exist "%MC_DIR%\assets\indexes" mkdir "%MC_DIR%\assets\indexes" >nul 2>&1
if not exist "%MC_DIR%\assets\objects" mkdir "%MC_DIR%\assets\objects" >nul 2>&1

powershell -NoProfile -ExecutionPolicy Bypass -Command ^
  "$of = Join-Path $env:MC_DIR 'optionsof.txt'; if (Test-Path $of) { $c = [IO.File]::ReadAllText($of); $n = ($c -replace '(?m)^ofFastRender:true(?=\r?$)', 'ofFastRender:false' -replace '(?m)^ofAaLevel:[1-9]\d*(?=\r?$)', 'ofAaLevel:0'); if ($n -ne $c) { [IO.File]::WriteAllText($of, $n) } }; " ^
  "$op = Join-Path $env:MC_DIR 'options.txt'; if (Test-Path $op) { $c = [IO.File]::ReadAllText($op); $n = ($c -replace '(?m)^fboEnable:false(?=\r?$)', 'fboEnable:true'); if ($n -ne $c) { [IO.File]::WriteAllText($op, $n) } }" >nul 2>&1

REM ============================================================================
REM 4. Launch OpenAugustus
REM ============================================================================
echo ============================================================
echo  Launching OpenAugustus b4.20.3
echo  Java : "%JAVA8_EXE%"
echo  JAR  : "%CLIENT_JAR%"
echo  Dir  : "%MC_DIR%"
echo ============================================================

pushd "%MC_DIR%"
"%JAVA8_EXE%" -Xms1G -Xmx2G -XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M -Dfile.encoding=UTF-8 -Dfml.ignoreInvalidMinecraftCertificates=true -jar "%CLIENT_JAR%" %*
set "EXIT_CODE=%ERRORLEVEL%"
popd

if not "%EXIT_CODE%"=="0" (
    echo.
    echo [WARN] Client exited with code %EXIT_CODE%.
    pause
)

exit /b %EXIT_CODE%
