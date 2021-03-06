# ANTLR-Interpreter

This project implements an interpreter for simple files using [ANTLR](https://www.antlr.org/). <br/>
The main aim was to understand how the different parts of compilers and interpreters (e.g. lexer, parser...) work. It was created for my class [Programmiersprachen und -konzepte](https://ufind.univie.ac.at/de/course.html?lv=051030&semester=2020W) at the University of Vienna.

## Structure

The program is put together by 3 main parts, which can be found in _/src_:
1. The `.g4` file defines the grammar on which the interpreter works. It sets up lexer and parser rules that will later get used.
2. The `VisitorImplementation` is the heart of the program. It specifies the interpreter´s action for every possible input set up in the grammar file.
3. `BigCalcProg.java` serves as the main. It sets up the ParseTree, visits the root, prints the result and handles errors.

## Setup

A Makefile was included for convenient setup. Make sure you properly include the _.jar_ file (you can find it in _/lib_). <br/>
When running the _.g4_ file, lots of files will be created by ANTLR. Don´t worry, they don´t hurt! They create a baseline implementation of the lexer, tokenizer, parser etc. for the specified grammar. <br/>

After compiling as specified in the Makefile, you can run BigCalcProg with a file as its argument. This file will be the input for the interpreter and its last statement serves as the output. <br/>
You can find some example inputs in _/files_ or create your own.

If you want to print a ParseTree, you can enter `make viz` in the console. Start writing statements as you would in any file. When you are finished, pressing `CTRL-D` stops the process and shows you a picture of the ParseTree corresponding to the input you just gave. Beautiful, isn´t it?

## Allowed Syntax

The interpreter can ... well ... _interpret_ files with following syntax:

Every statement must be closed by `;` <br/>
Statements can be simple expressions or assignments. <br/>
Variables can be assigned using `=`. **They have to start with exactly 1 letter followed by none-to-many digits.**<br/>
Compound Assignments with `+=`, `-=`, `*=`, `/=` also work conventionally.<br/>
Common mathematical operators `+`, `-`, `*`, `/`, `%`, `**` work with the usual presedence, `()` can also be set.<br/>
Moreover, the unary `+` and `-` can be used.<br/>
If you want the absolute value of an expression, `| |` is available. <br/>

Decimal Numbers use `.` as comma <br/>
_0.5_ can be written as _.5_<br/>
The _scientific exponential notation_ is also available - examples: `3.3e23`, `5.1e-11`, `6.513E4`<br/>
_Whitespaces_ and _empty lines_ are ignored, _commenting_ is based on java syntax.<br/>


What would an interpreter be without some coding-spice? <br/>
`if <expression> <statement1> else <statement2>` <br/>
  for every non-zero _expression_, _statement1_ will be executed (otherwise _statement2_)<br/>
  _nested ifs_ can be used <br/>

Use the _ternary operator_ for some additional style points: <br/>
`<expression1> ? <expression2> : <expression3>`
  
## What I´ve learned
* Working with ANTLR and it´s quirks to set up an interpreter
* Creating a grammar using regular expressions and recursions
* Implementing visitors on a ParseTree
* Java´s BigDecimals
* Interpreters and compilers are actually **not** _(only)_ sourcery
