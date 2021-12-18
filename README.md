# CSC-8310-ProjectCompiler
## Running Project Compiler
```
 javacc ProjLang.jj
 cd output
 javac *.java
 java InterpretMain
```
## Syntax
INPUT −→ EXPR ;\
EXPR −→ IF EXP\
| LETVAL EXP\
| LETFUN EXP\
| WHILE EXP\
| SEQ EXP\
| ASSN EXP\
| NOT EXP\
| REL EXP\
IF EXP −→ if EXPR then EXPR else EXPR\
LETVAL EXP −→ let val id = EXPR in EXPR end\
LETFUN EXP −→ let fun id ( id ) = EXPR in EXPR end\
WHILE EXP −→ while EXPR do EXPR\
SEQ EXP −→ { EXPR (; EXPR)* }\
NOT EXP −→ ! EXPR\
ASSN EXP −→ id := EXPR\
REL EXP −→ ARITH EXP (relop ARITH EXP)?\
ARITH EXP −→ TERM (addop TERM)*\
TERM −→ FACTOR (mulop FACTOR)*\
FACTOR −→ num\
| true\
| false\
| id ( EXPR )\
| id\
| ( EXPR )
## Semantics
* The language is statically scoped and dynamically typed. Thus, for example, 1+true is a run-time type error.
* Division by zero is considered an evaluation error.
* The condition expression of an if-then-else expression must be a boolean value. Type equivalence of the then-expression and else-expression is not possible under dynamic typing since only one of the branches will get evaluated.
* Short-circuit evaluation is used for the logical operators & and |. However, note that unlike, say, Python, the second argument must be a boolean value if evaluated.
* Parameters are passed by value.
* Where applicable, arguments are evaluated in the left-to-right order.
* Equality test of boolean expressions is allowed. Equality of functions is meaningless and thus an error. Inequality of booleans is optional.
* The assignment expression is meaningful only if a binding for the corresponding variable already
exists (in the current environment). The new value must be compatible with the existing value.
The value returned is the value of its right hand side.
* While-expressions are used for side-effects only. The value they return is of no importance. For this implementation, we will have all while-expressions return tthe value ‘false’.1
* For (binary) sequencing, the value returned is the value of the second expression. In general, for a sequence of expressions, the value returned is that of the last expression in the sequence. Of course, we do have to evaluate each expression in a sequence and that too in the given left-to-right order.
## Example Compiler Success:
```
let fun pow2(n) =
let val x = 1
in {
while !( n < 1) do {
x := 2 * x;
n := n -1
};
x
}
end
in
pow2(3)
end;
8
```
```
let fun pow2(n) =
if( n < (n / (2 + 1)) )
then
1
else
2 * pow2(n -1)
in
pow2(3)
end;
16
```
