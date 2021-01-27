# ANTLR-Interpreter

This project implements an interpreter for simple files using ANTLR. <br/>
The main aim was to understand how the different parts of a compiler and interpreters (lexer, parser etc.) work. It was created for my class [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) at the University of Vienna.

## Structure

The program is put together by 3 main parts:
1. The `.g4` file defines the grammar on which the interpreter works. It sets up lexer and parser rules that will later get used.
2. The `VisitorImplementation` is the heart of the program. It specifies the interpreter´s action for every possible input set up in the grammar file.
3. `BigCalcProg.java` serves as the main. It sets up the ParseTree, visits the root, prints the result and handles errors.



## What I´ve learned
* Working with ANTLR and it´s quirks to set up a compiler
* Creating a grammar using regular expressions
* Implementing visitors on a parse tree
* Java´s BigDecimals
* Compilers are actually **not** _(only)_ sourcery
