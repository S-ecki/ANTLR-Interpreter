import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigCalcProg {
    public static void main(String[] args) {  
        try {
        	
           final CharStream input = CharStreams.fromFileName(args[0]);		// takes file as input
           final BigCalcProgLexer lexer = new BigCalcProgLexer(input);		// creates lexer
           final CommonTokenStream tokens = new CommonTokenStream(lexer);	// lexer creates token
           final BigCalcProgParser parser = new BigCalcProgParser(tokens);	// parser from token
           final ParseTree tree = parser.root();								// parsetree starting at the root

           final BigCalcProgVisitor<BigDecimal> visitor = new BigCalcProgVisitorImpl();	// create visitor from own implementation, processing BigDecimal Objects
           final BigDecimal result = visitor.visit(tree);								// result is return of root (which visits whole tree)

           if (result != null) System.out.println("result: " + result.setScale(10, RoundingMode.HALF_UP));
           
        }
        catch (ArrayIndexOutOfBoundsException e) {
      	   System.out.println("usage: usage: java BigCalc <file>");
        }
        catch (Exception e) {
           System.out.println(e);
        }
    }
}
