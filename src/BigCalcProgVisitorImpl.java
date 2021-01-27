import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class BigCalcProgVisitorImpl extends BigCalcProgBaseVisitor<BigDecimal> {
	
	HashMap<String, BigDecimal> memory = new HashMap<>();

	
    @Override	// start here - visit all statements
    public BigDecimal visitRoot(BigCalcProgParser.RootContext ctx) {

    	int statementNumber = ctx.statement().size();
    	for(int i = 0; i < statementNumber; ++i) {
    		if(i == statementNumber - 1) return visit(ctx.statement(i));	// returns result of last statement
    		visit(ctx.statement(i));		// visit whole tree before returning
    	}
    	
		return null;
    }
    
    
    @Override	// statement is an expression
    public BigDecimal visitStateExpr(BigCalcProgParser.StateExprContext ctx) {
    	return visit(ctx.expression());
    }
    
    
    @Override	// statement is if - else statement
    public BigDecimal visitStateIf(BigCalcProgParser.StateIfContext ctx) {
    	
    	BigDecimal exp = visit(ctx.expression());	// evaluate left expression (if...)
    	if(exp.doubleValue() != 0.0) {		// not 0 -> return if
    		return visit(ctx.statement(0));	
    	}
    	else {								// = 0 -> return else
    		return visit(ctx.statement(1));
    	}
    	
    }
    
    
    @Override	// statement is an Compound Assignment
    public BigDecimal visitStateComp(BigCalcProgParser.StateCompContext ctx) {
    	
    	String var = ctx.VAR().getText();	
    	BigDecimal left = memory.getOrDefault(var, new BigDecimal(0));		// variable that is being reassigned - 0 if not assigned yet	
    	final BigDecimal right = visit(ctx.expression());					// determine expression
    	
    	if (ctx.op.getText().equals("+=")) {			// += 
    		left = left.add(right);
    	}
    	else if (ctx.op.getText().equals("-=")) {		// -=
    		left = left.subtract(right);
    	}
    	else if (ctx.op.getText().equals("*=")) {		// *= 
    		left = left.multiply(right);
        } 
    	else if (ctx.op.getText().equals("/=")) {		// /=
            left = left.divide(right, 10, RoundingMode.HALF_UP);
        }
    	
    	memory.put(ctx.VAR().getText(), left);			// save variable with correspondig number
    	return left;
    }

    
    @Override	// statement is an assignment
    public BigDecimal visitStateAssign(BigCalcProgParser.StateAssignContext ctx) {
    	
    	final BigDecimal expression = visit(ctx.expression());	// determine expression first
    	memory.put(ctx.VAR().getText(), expression);			// save variable with correspondig number
    	return expression;
    }
    
    
    @Override	// expression in brackets
    public BigDecimal visitBracket(BigCalcProgParser.BracketContext ctx) {
    	return visit(ctx.expression());
    }
    
    
    @Override	// expression is ternary operator
    public BigDecimal visitTernary(BigCalcProgParser.TernaryContext ctx) {
    	final BigDecimal exp0 = visit(ctx.expression(0));	// evaluate all expressions
    	final BigDecimal exp1 = visit(ctx.expression(1));
    	final BigDecimal exp2 = visit(ctx.expression(2));
    	
    	if(exp0.doubleValue() != 0.0) {			// if left is not 0 -> take expression after ?
    		return exp1;
    	}
    	else {									// if left is 0 -> take expression after :
    		return exp2;
    	}
    }
    
    
    @Override	// expression in | | - absolute value
    public BigDecimal visitAbs(BigCalcProgParser.AbsContext ctx) {
    	return visit(ctx.expression()).abs();
    }
    
    
    @Override	// unary + or -
    public BigDecimal visitUnary(BigCalcProgParser.UnaryContext ctx) {
    	String var = ctx.op.getText();						// + or -
    	BigDecimal expression = visit(ctx.expression());	//get value of expression	
    	if(var.equals("+")) {				// just return value if +
    		return expression;
    	}
    	else return expression.negate();	// negate if -
    }
    
    
    @Override 	// modulo operator %
    public BigDecimal visitMod(BigCalcProgParser.ModContext ctx) {
    	
    	final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        
        return left.remainder(right).abs();		// .remainder() can return negative value -> abs()
    }

    
    @Override	// expression is power **
    public BigDecimal visitPower(BigCalcProgParser.PowerContext ctx) {
    	
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        
        return left.pow(right.intValue());		// take int from right expression (cannot take something to the power of a BigDecimal)
    }
    
    
    @Override	// expression is multiplication or division
    public BigDecimal visitMulDiv(BigCalcProgParser.MulDivContext ctx) {
    	
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        
        if (ctx.op.getText().equals("*")) {		// multiply
            return left.multiply(right);
        } else {								// divide
            return left.divide(right, 10, RoundingMode.HALF_UP);
        }
    }

    
    @Override	// expression is addition or subtraction
    public BigDecimal visitAddSub(BigCalcProgParser.AddSubContext ctx) {
    	
        final BigDecimal left = visit(ctx.expression(0));
        final BigDecimal right = visit(ctx.expression(1));
        
        if (ctx.op.getText().equals("+")) {		//add
            return left.add(right);
        } else {								//substract
            return left.subtract(right);
        }
    }

    
    @Override	// returns var as BigDecimal
    public BigDecimal visitVar(BigCalcProgParser.VarContext ctx) {
    	
    	String var = ctx.VAR().getText();
    	if(!memory.containsKey(var)) return new BigDecimal(0);		// return 0 if not assigned
    	return memory.get(var);					// return value of var
    }
    
    
    @Override	// returns number as BigDecimal
    public BigDecimal visitNum(BigCalcProgParser.NumContext ctx) {
        return new BigDecimal(ctx.Number().getText());
    }

}
