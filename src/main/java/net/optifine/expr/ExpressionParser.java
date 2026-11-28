package net.optifine.expr;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.optifine.config.Config;

public class ExpressionParser {
   private IExpressionResolver expressionResolver;

   public ExpressionParser(IExpressionResolver expressionResolver2) {
      this.expressionResolver = expressionResolver2;
   }

   private static Deque getGroup(Deque deque, TokenType tokenType, boolean flag) throws ParseException {
      ArrayDeque arrayDeque = new ArrayDeque();
      int n = 0;
      Iterator it = deque.iterator();

      while (it.hasNext()) {
         Token token = (Token)it.next();
         it.remove();
         if (n == 0 && token.getType() == tokenType) {
            return arrayDeque;
         }

         arrayDeque.add(token);
         if (token.getType() == TokenType.BRACKET_OPEN) {
            n++;
         }

         if (token.getType() == TokenType.BRACKET_CLOSE) {
            n--;
         }
      }

      if (flag) {
         throw new ParseException("Missing end token: " + tokenType);
      } else {
         return arrayDeque;
      }
   }

   private IExpression makeFunction2(FunctionType functionType, Deque deque) throws ParseException {
      if (functionType.getParameterCount(new IExpression[0]) == 0) {
         Token token = (Token)deque.peek();
         if (token == null || token.getType() != TokenType.BRACKET_OPEN) {
            return makeFunction(functionType, new IExpression[0]);
         }
      }

      Token token2 = (Token)deque.poll();
      return makeFunction(functionType, this.parseExpressions(getGroup(deque, TokenType.BRACKET_CLOSE, true)));
   }

   public IExpressionFloat parseFloat(String str) throws ParseException {
      IExpression expression = this.parse(str);
      if (!(expression instanceof IExpressionFloat)) {
         throw new ParseException("Not a float expression: " + expression.getFloatValue());
      } else {
         return (IExpressionFloat)expression;
      }
   }

   public IExpressionBool parseBool(String str) throws ParseException {
      IExpression expression = this.parse(str);
      if (!(expression instanceof IExpressionBool)) {
         throw new ParseException("Not a boolean expression: " + expression.getFloatValue());
      } else {
         return (IExpressionBool)expression;
      }
   }

   private static IExpression makeConstantFloat(Token token) throws ParseException {
      float f = Config.parseFloat(token.getText(), Float.NaN);
      if (f == Float.NaN) {
         throw new ParseException("Invalid float value: " + token);
      } else {
         return new ConstantFloat(f);
      }
   }

   private FunctionType getFunctionType(Token token, Deque deque) throws ParseException {
      Token token2 = (Token)deque.peek();
      if (token2 != null && token2.getType() == TokenType.BRACKET_OPEN) {
         FunctionType functionType = FunctionType.parse(token.getText());
         checkNull(functionType, "Unknown function: " + token);
         return functionType;
      } else {
         FunctionType functionType2 = FunctionType.parse(token.getText());
         if (functionType2 == null) {
            return null;
         } else if (functionType2.getParameterCount(new IExpression[0]) > 0) {
            throw new ParseException("Missing arguments: " + functionType2);
         } else {
            return functionType2;
         }
      }
   }

   private IExpression parseInfix(Deque deque) throws ParseException {
      if (deque.isEmpty()) {
         return null;
      } else {
         LinkedList list = new LinkedList();
         LinkedList list2 = new LinkedList();
         IExpression expression = this.parseExpression(deque);
         checkNull(expression, "Missing expression");
         list.add(expression);

         while (true) {
            Token token = (Token)deque.poll();
            if (token == null) {
               return this.makeInfix(list, list2);
            }

            if (token.getType() != TokenType.OPERATOR) {
               throw new ParseException("Invalid operator: " + token);
            }

            IExpression expression2 = this.parseExpression(deque);
            checkNull(expression2, "Missing expression");
            list2.add(token);
            list.add(expression2);
         }
      }
   }

   private IExpression parseExpression(Deque deque) throws ParseException {
      Token token = (Token)deque.poll();
      checkNull(token, "Missing expression");
      switch (token.getType()) {
         case NUMBER:
            return makeConstantFloat(token);
         case IDENTIFIER:
            FunctionType functionType = this.getFunctionType(token, deque);
            if (functionType != null) {
               return this.makeFunction2(functionType, deque);
            }

            return this.makeVariable(token);
         case BRACKET_OPEN:
            return this.makeBracketed(token, deque);
         case OPERATOR:
            FunctionType functionType2 = FunctionType.parse(token.getText());
            checkNull(functionType2, "Invalid operator: " + token);
            if (functionType2 == FunctionType.PLUS) {
               return this.parseExpression(deque);
            } else if (functionType2 == FunctionType.MINUS) {
               IExpression expression = this.parseExpression(deque);
               return makeFunction(FunctionType.NEG, new IExpression[]{expression});
            } else if (functionType2 == FunctionType.NOT) {
               IExpression expression2 = this.parseExpression(deque);
               return makeFunction(FunctionType.NOT, new IExpression[]{expression2});
            }
         default:
            throw new ParseException("Invalid expression: " + token);
      }
   }

   private static IExpression makeFunction(FunctionType functionType, IExpression[] expressions) throws ParseException {
      ExpressionType[] parameterTypes = functionType.getParameterTypes(expressions);
      if (expressions.length != parameterTypes.length) {
         throw new ParseException(
            "Invalid number of arguments, function: \"" + functionType.getName() + "\", count arguments: " + expressions.length + ", should be: " + parameterTypes.length
         );
      } else {
         for (int i = 0; i < expressions.length; i++) {
            ExpressionType floatValue = expressions[i].getFloatValue();
            ExpressionType expressionType = parameterTypes[i];
            if (floatValue != expressionType) {
               throw new ParseException("Invalid argument type, function: \"" + functionType.getName() + "\", index: " + i + ", type: " + floatValue + ", should be: " + expressionType);
            }
         }

         if (functionType.getExpressionType() == ExpressionType.FLOAT) {
            return new FunctionFloat(functionType, expressions);
         } else if (functionType.getExpressionType() == ExpressionType.BOOL) {
            return new FunctionBool(functionType, expressions);
         } else if (functionType.getExpressionType() == ExpressionType.FLOAT_ARRAY) {
            return new FunctionFloatArray(functionType, expressions);
         } else {
            throw new ParseException("Unknown function type: " + functionType.getExpressionType() + ", function: " + functionType.getName());
         }
      }
   }

   private void mergeOperators(List list, List list2, int n) throws ParseException {
      for (int i = 0; i < list2.size(); i++) {
         FunctionType functionType = (FunctionType)list2.get(i);
         if (functionType.getPrecedence() == n) {
            list2.remove(i);
            IExpression expression = (IExpression)list.remove(i);
            IExpression expression2 = (IExpression)list.remove(i);
            list.add(i, makeFunction(functionType, new IExpression[]{expression, expression2}));
            i--;
         }
      }
   }

   private IExpression makeVariable(Token token) throws ParseException {
      if (this.expressionResolver == null) {
         throw new ParseException("Model variable not found: " + token);
      } else {
         IExpression expression = this.expressionResolver.getExpression(token.getText());
         if (expression == null) {
            throw new ParseException("Model variable not found: " + token);
         } else {
            return expression;
         }
      }
   }

   private IExpression makeBracketed(Token token, Deque deque) throws ParseException {
      return this.parseInfix(getGroup(deque, TokenType.BRACKET_CLOSE, true));
   }

   private IExpression[] parseExpressions(Deque deque) throws ParseException {
      ArrayList list = new ArrayList();

      while (true) {
         IExpression expression = this.parseInfix(getGroup(deque, TokenType.COMMA, false));
         if (expression == null) {
            return (IExpression[])list.toArray(new IExpression[list.size()]);
         }

         list.add(expression);
      }
   }

   public IExpression parse(String str) throws ParseException {
      try {
         Token[] tokens = TokenParser.parse(str);
         if (tokens == null) {
            return null;
         } else {
            ArrayDeque arrayDeque = new ArrayDeque<>(Arrays.asList(tokens));
            return this.parseInfix(arrayDeque);
         }
      } catch (IOException e) {
         throw new ParseException(e.getMessage(), e);
      }
   }

   private IExpression makeInfixFunc(List list, List list2) throws ParseException {
      if (list.size() != list2.size() + 1) {
         throw new ParseException("Invalid infix expression, expressions: " + list.size() + ", operators: " + list2.size());
      } else if (list.size() == 1) {
         return (IExpression)list.get(0);
      } else {
         int maxvalue = Integer.MAX_VALUE;
         int minvalue = Integer.MIN_VALUE;

         for (FunctionType functionType : (Iterable<FunctionType>)(list2)) {
            maxvalue = Math.min(functionType.getPrecedence(), maxvalue);
            minvalue = Math.max(functionType.getPrecedence(), minvalue);
         }

         if (minvalue >= maxvalue && minvalue - maxvalue <= 10) {
            for (int i = minvalue; i >= maxvalue; i--) {
               this.mergeOperators(list, list2, i);
            }

            if (list.size() == 1 && list2.size() == 0) {
               return (IExpression)list.get(0);
            } else {
               throw new ParseException("Error merging operators, expressions: " + list.size() + ", operators: " + list2.size());
            }
         } else {
            throw new ParseException("Invalid infix precedence, min: " + maxvalue + ", max: " + minvalue);
         }
      }
   }

   private IExpression makeInfix(List list, List list2) throws ParseException {
      LinkedList list3 = new LinkedList();

      for (Token token : (Iterable<Token>)(list2)) {
         FunctionType functionType = FunctionType.parse(token.getText());
         checkNull(functionType, "Invalid operator: " + token);
         list3.add(functionType);
      }

      return this.makeInfixFunc(list, list3);
   }

   private static void checkNull(Object obj, String str) throws ParseException {
      if (obj == null) {
         throw new ParseException(str);
      }
   }

}
