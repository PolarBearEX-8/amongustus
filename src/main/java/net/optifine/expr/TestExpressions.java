package net.optifine.expr;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestExpressions {
   public static void main(String[] args) throws Exception {
      ExpressionParser expressionParser = new ExpressionParser((IExpressionResolver)null);

      while (true) {
         try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            String str = new BufferedReader(inputStreamReader).readLine();
            if (str.length() <= 0) {
               return;
            }

            IExpression expression = expressionParser.parse(str);
            if (expression instanceof IExpressionFloat) {
               float f = ((IExpressionFloat)expression).eval();
               System.out.println("" + f);
            }

            if (expression instanceof IExpressionBool) {
               boolean flag = ((IExpressionBool)expression).eval();
               System.out.println("" + flag);
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
