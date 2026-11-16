param([string]$PythonPath = '')
$ErrorActionPreference = 'Stop'
if ($PythonPath) { if (-not (Test-Path -LiteralPath $PythonPath -PathType Leaf)) { throw "Python executable not found: $PythonPath" }; return $PythonPath }
$bundled = Join-Path (Split-Path $PSScriptRoot -Parent) 'build-tools\python\python.exe'
if (Test-Path -LiteralPath $bundled -PathType Leaf) { return $bundled }
foreach ($name in @('python','python3')) { $tool = Get-Command $name -ErrorAction SilentlyContinue; if ($tool -and $tool.Source -notmatch '\\Microsoft\\WindowsApps\\') { return $tool.Source } }
throw 'Python 3 is required. Pass -PythonPath or restore build-tools/python.'
