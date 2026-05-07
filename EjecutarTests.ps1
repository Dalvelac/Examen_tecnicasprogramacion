$ErrorActionPreference = "Stop"

$base = $PSScriptRoot
$out = Join-Path $base "bin-test"
$junit = Join-Path $base "lib\junit-4.13.2.jar"
$hamcrest = Join-Path $base "lib\hamcrest-core-1.3.jar"
$ecj = Join-Path $base "lib\ecj-4.6.1.jar"

if (Test-Path -LiteralPath $out) {
    Remove-Item -LiteralPath $out -Recurse -Force
}

New-Item -ItemType Directory -Force -Path $out | Out-Null

java -jar $ecj -1.8 -encoding UTF-8 -d $out -cp "$junit;$hamcrest" `
    "$base\Persona.java" `
    "$base\CeldaHash.java" `
    "$base\TablaHash.java" `
    "$base\FactorialDinamico.java" `
    "$base\TablaHashTest.java"

java -cp "$out;$junit;$hamcrest" org.junit.runner.JUnitCore TablaHashTest
