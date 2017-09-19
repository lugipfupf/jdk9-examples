#!/usr/bin/env bash
/opt/jdk-9/bin/javac --module-path mods -d mods/javakafi/ src/javakafi/module-info.java src/javakafi/ch/puzzle/javakafi/jdk9/*.java
/opt/jdk-9/bin/java --module-path mods -m javakafi/ch.puzzle.javakafi.jdk9.$1 --add-modules jdk.incubator.httpclient