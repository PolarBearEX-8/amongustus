param([string]$PythonPath='')
$ErrorActionPreference = 'Stop'
$python = & (Join-Path $PSScriptRoot 'tools\ResolvePython.ps1') -PythonPath $PythonPath
& $python (Join-Path $PSScriptRoot 'verify.py')
if ($LASTEXITCODE -ne 0) { throw 'Editable client verification failed.' }
