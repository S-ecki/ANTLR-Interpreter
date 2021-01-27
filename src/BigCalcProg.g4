grammar BigCalcProg;

root
        : (statement ';')+ EOF
        ;
        
statement
	: expression					        # stateExpr
	| 'if (' expression ')' statement 'else' statement	# stateIf
	| VAR op=('+=' | '-=' | '*=' | '/=')  expression	# stateComp
	| VAR '=' expression 					# stateAssign
	;

expression  
        : '('expression')'					# bracket
        | expression '?' expression ':' expression 		# ternary
        | '|' expression '|'					# abs
        | op=('+' | '-') expression				# unary
        | expression '%' expression				# mod
        | expression '**' expression				# power
        | expression op=('*' | '/') expression  		# mulDiv
        | expression op=('+' | '-') expression  		# addSub
        | VAR							# var
        | Number                                		# num
        ;

VAR
	: [a-zA-Z][0-9]*
	;

Number  
        : Digit* '.' Digit+
        | Digit* '.' Digit+ [e | E] [- | +]? Digit
        | Digit+
        ;

Digit   
        : [0-9]
        ;

WS      : [ \t\r\n\u000C]+ -> skip  
        ;

COMMENT
        :   '/*' .*? '*/' -> skip
        ;

LINE_COMMENT
        : '//' ~[\r\n]* -> skip 
        ;