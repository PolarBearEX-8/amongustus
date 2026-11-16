param([string]$RecoveryRoot='', [string]$Jdk17='', [string]$Java8='', [string]$PythonPath='', [int]$Jobs=2, [switch]$ReuseBuild)
$ErrorActionPreference = 'Stop'
$python = & (Join-Path $PSScriptRoot 'tools\ResolvePython.ps1') -PythonPath $PythonPath
$buildArguments = @((Join-Path $PSScriptRoot 'build.py'), '--jobs', "$Jobs")
if ($RecoveryRoot) { $buildArguments += @('--recovery-root', $RecoveryRoot) }
if ($Jdk17) { $buildArguments += @('--jdk17', $Jdk17) }
if ($Java8) { $buildArguments += @('--java8', $Java8) }
if ($ReuseBuild) { $buildArguments += @('--reuse-build', (Join-Path $PSScriptRoot 'dist\build-report.json')) }
& $python @buildArguments
if ($LASTEXITCODE -ne 0) { throw 'Editable client build failed. Inspect build/run-*/logs.' }
