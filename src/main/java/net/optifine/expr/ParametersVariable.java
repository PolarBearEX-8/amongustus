package net.optifine.expr;

import java.util.ArrayList;
import java.util.Arrays;

public class ParametersVariable implements IParameters {
   private ExpressionType[] first;
   private ExpressionType[] repeat;
   private ExpressionType[] last;
   private int maxCount = Integer.MAX_VALUE;
   private static final ExpressionType[] EMPTY = new ExpressionType[0];

   public ParametersVariable first(ExpressionType[] expressionTypes) {
      return new ParametersVariable(expressionTypes, this.repeat, this.last);
   }

   public ExpressionType[] getRepeat() {
      return this.repeat;
   }

   public ParametersVariable() {
      this((ExpressionType[])null, (ExpressionType[])null, (ExpressionType[])null);
   }

   public ExpressionType[] getLast() {
      return this.last;
   }

   public ParametersVariable maxCount(int n) {
      return new ParametersVariable(this.first, this.repeat, this.last, n);
   }

   public ParametersVariable(ExpressionType[] expressionTypes, ExpressionType[] expressionTypes2, ExpressionType[] expressionTypes3, int n) {
      this.first = normalize(expressionTypes);
      this.repeat = normalize(expressionTypes2);
      this.last = normalize(expressionTypes3);
      this.maxCount = n;
   }

   public ParametersVariable last(ExpressionType[] expressionTypes) {
      return new ParametersVariable(this.first, this.repeat, expressionTypes);
   }

   @Override
   public ExpressionType[] getParameterTypes(IExpression[] expressions) {
      int n = this.first.length + this.last.length;
      int n2 = expressions.length - n;
      int n3 = 0;

      for (int i = 0; i + this.repeat.length <= n2 && n + i + this.repeat.length <= this.maxCount; i += this.repeat.length) {
         n3++;
      }

      ArrayList list = new ArrayList();
      list.addAll(Arrays.asList(this.first));

      for (int j = 0; j < n3; j++) {
         list.addAll(Arrays.asList(this.repeat));
      }

      list.addAll(Arrays.asList(this.last));
      return (ExpressionType[])list.toArray(new ExpressionType[list.size()]);
   }

   public ParametersVariable(ExpressionType[] expressionTypes, ExpressionType[] expressionTypes2, ExpressionType[] expressionTypes3) {
      this(expressionTypes, expressionTypes2, expressionTypes3, Integer.MAX_VALUE);
   }

   public int getCountRepeat() {
      return this.first == null ? 0 : this.first.length;
   }

   private static ExpressionType[] normalize(ExpressionType[] expressionTypes) {
      return expressionTypes == null ? EMPTY : expressionTypes;
   }

   public ParametersVariable repeat(ExpressionType[] expressionTypes) {
      return new ParametersVariable(this.first, expressionTypes, this.last);
   }

   public ExpressionType[] getFirst() {
      return this.first;
   }
}
