# ANTLR-Interpreter

This project implements an interpreter for simple files using ANTLR. <br/>
The main aim was to understand how the different parts of a compiler and interpreters (lexer, parser etc.) work. It was created for my class [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) at the University of Vienna.

## Structure

The program is put together by 3 main parts:
1. The `.g4` file defines the grammar on which the interpreter works. It sets up lexer and parser rules that will later get used.
2. The `VisitorImplementation` is the heart of the program. It specifies the interpreter´s action for every possible input set up in the grammar file.
3. `BigCalcProg.java` serves as the main. It sets up the ParseTree, visits the root, prints the result and handles errors.

## Setup

A Makefile was included for convenient setup. Make sure you properly include the _.jar_ file (you can find it in /lib).
When running the _.g4_ file, lots of files will be created by ANTLR. Don´t worry, they don´t hurt! They create a baseline implementation of the lexer, tokenizer, parser etc for the specified grammar.

## Allowed Syntax

The interpreter can ... well ... _interpret_ files with following syntax:

Every statement must be closed by `;` <br/>
Variables can be assigned using `=`. They have to start with exactly 1 letter followed by none-to-many digits.<br/>
Compound Assignments with `+=`, `-=`, `*=`, `/=` also work conventionally.<br/>
Common mathematical operators `+`, `-`, `*`, `/`, `%`, `**` work with the usual presedence, `()` can also be set.<br/>
Moreover, the unary `+` and `-` can also be used.<br/>
If you want the absolute value of an expression, `| |` is available. <br/>

To everyones

Decimal Numbers use `.` as comma <br/>
_0.5_ can be written as _.5_<br/>
The scientific exponential notation is also available - examples: `3.3e23`, `5.1e-11`, `6.513E4`<br/>
_Whitespaces_ and _empty lines_ are ignored, _commenting_ is based on java syntax.<br/>




## What I´ve learned
* Working with ANTLR and it´s quirks to set up a compiler
* Creating a grammar using regular expressions
* Implementing visitors on a parse tree
* Java´s BigDecimals
* Compilers are actually **not** _(only)_ sourcery
