package net.optifine.expr;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.MathHelper;
import net.optifine.config.Config;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.util.MathUtils;

public enum FunctionType {
   PLUS(10, ExpressionType.FLOAT, "+", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   MINUS(10, ExpressionType.FLOAT, "-", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   MUL(11, ExpressionType.FLOAT, "*", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   DIV(11, ExpressionType.FLOAT, "/", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   MOD(11, ExpressionType.FLOAT, "%", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   NEG(12, ExpressionType.FLOAT, "neg", new ExpressionType[]{ExpressionType.FLOAT}),
   PI(ExpressionType.FLOAT, "pi", new ExpressionType[0]),
   SIN(ExpressionType.FLOAT, "sin", new ExpressionType[]{ExpressionType.FLOAT}),
   COS(ExpressionType.FLOAT, "cos", new ExpressionType[]{ExpressionType.FLOAT}),
   ASIN(ExpressionType.FLOAT, "asin", new ExpressionType[]{ExpressionType.FLOAT}),
   ACOS(ExpressionType.FLOAT, "acos", new ExpressionType[]{ExpressionType.FLOAT}),
   TAN(ExpressionType.FLOAT, "tan", new ExpressionType[]{ExpressionType.FLOAT}),
   ATAN(ExpressionType.FLOAT, "atan", new ExpressionType[]{ExpressionType.FLOAT}),
   ATAN2(ExpressionType.FLOAT, "atan2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   TORAD(ExpressionType.FLOAT, "torad", new ExpressionType[]{ExpressionType.FLOAT}),
   TODEG(ExpressionType.FLOAT, "todeg", new ExpressionType[]{ExpressionType.FLOAT}),
   MIN(ExpressionType.FLOAT, "min", new ParametersVariable().first(new ExpressionType[]{ExpressionType.FLOAT}).repeat(new ExpressionType[]{ExpressionType.FLOAT})),
   MAX(ExpressionType.FLOAT, "max", new ParametersVariable().first(new ExpressionType[]{ExpressionType.FLOAT}).repeat(new ExpressionType[]{ExpressionType.FLOAT})),
   CLAMP(ExpressionType.FLOAT, "clamp", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   ABS(ExpressionType.FLOAT, "abs", new ExpressionType[]{ExpressionType.FLOAT}),
   FLOOR(ExpressionType.FLOAT, "floor", new ExpressionType[]{ExpressionType.FLOAT}),
   CEIL(ExpressionType.FLOAT, "ceil", new ExpressionType[]{ExpressionType.FLOAT}),
   EXP(ExpressionType.FLOAT, "exp", new ExpressionType[]{ExpressionType.FLOAT}),
   FRAC(ExpressionType.FLOAT, "frac", new ExpressionType[]{ExpressionType.FLOAT}),
   LOG(ExpressionType.FLOAT, "log", new ExpressionType[]{ExpressionType.FLOAT}),
   POW(ExpressionType.FLOAT, "pow", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   RANDOM(ExpressionType.FLOAT, "random", new ExpressionType[0]),
   ROUND(ExpressionType.FLOAT, "round", new ExpressionType[]{ExpressionType.FLOAT}),
   SIGNUM(ExpressionType.FLOAT, "signum", new ExpressionType[]{ExpressionType.FLOAT}),
   SQRT(ExpressionType.FLOAT, "sqrt", new ExpressionType[]{ExpressionType.FLOAT}),
   FMOD(ExpressionType.FLOAT, "fmod", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   TIME(ExpressionType.FLOAT, "time", new ExpressionType[0]),
   IF(ExpressionType.FLOAT, "if", new ParametersVariable()
         .first(new ExpressionType[]{ExpressionType.BOOL, ExpressionType.FLOAT})
         .repeat(new ExpressionType[]{ExpressionType.BOOL, ExpressionType.FLOAT})
         .last(new ExpressionType[]{ExpressionType.FLOAT})),
   NOT(12, ExpressionType.BOOL, "!", new ExpressionType[]{ExpressionType.BOOL}),
   AND(3, ExpressionType.BOOL, "&&", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
   OR(2, ExpressionType.BOOL, "||", new ExpressionType[]{ExpressionType.BOOL, ExpressionType.BOOL}),
   GREATER(8, ExpressionType.BOOL, ">", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   GREATER_OR_EQUAL(8, ExpressionType.BOOL, ">=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   SMALLER(8, ExpressionType.BOOL, "<", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   SMALLER_OR_EQUAL(8, ExpressionType.BOOL, "<=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   EQUAL(7, ExpressionType.BOOL, "==", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   NOT_EQUAL(7, ExpressionType.BOOL, "!=", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   BETWEEN(7, ExpressionType.BOOL, "between", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   EQUALS(7, ExpressionType.BOOL, "equals", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   IN(ExpressionType.BOOL, "in", new ParametersVariable()
         .first(new ExpressionType[]{ExpressionType.FLOAT})
         .repeat(new ExpressionType[]{ExpressionType.FLOAT})
         .last(new ExpressionType[]{ExpressionType.FLOAT})),
   SMOOTH(ExpressionType.FLOAT, "smooth", new ParametersVariable().first(new ExpressionType[]{ExpressionType.FLOAT}).repeat(new ExpressionType[]{ExpressionType.FLOAT}).maxCount(4)),
   TRUE(ExpressionType.BOOL, "true", new ExpressionType[0]),
   FALSE(ExpressionType.BOOL, "false", new ExpressionType[0]),
   VEC2(ExpressionType.FLOAT_ARRAY, "vec2", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT}),
   VEC3(ExpressionType.FLOAT_ARRAY, "vec3", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT}),
   VEC4(ExpressionType.FLOAT_ARRAY, "vec4", new ExpressionType[]{ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT});

private int precedence;
   private ExpressionType expressionType;
   private String name;
   private IParameters parameters;
   public static FunctionType[] VALUES = values();
   private static final Map mapSmooth = new HashMap();
public int getParameterCount(IExpression[] expressions) {
      return this.parameters.getParameterTypes(expressions).length;
   }

   public ExpressionType getExpressionType() {
      return this.expressionType;
   }

   public IParameters getParameters() {
      return this.parameters;
   }

   private static float evalFloat(IExpression[] expressions, int n) {
      return ((IExpressionFloat)expressions[n]).eval();
   }

   private float getMax(IExpression[] expressions) {
      if (expressions.length == 2) {
         return Math.max(evalFloat(expressions, 0), evalFloat(expressions, 1));
      } else {
         float f = evalFloat(expressions, 0);

         for (int i = 1; i < expressions.length; i++) {
            float f2 = evalFloat(expressions, i);
            if (f2 > f) {
               f = f2;
            }
         }

         return f;
      }
   }

   public int getPrecedence() {
      return this.precedence;
   }

   public static FunctionType parse(String str) {
      for (int i = 0; i < VALUES.length; i++) {
         FunctionType functionType = VALUES[i];
         if (functionType.getName().equals(str)) {
            return functionType;
         }
      }

      return null;
   }

   private FunctionType(int n, ExpressionType expressionType2, String str, IParameters parameters2) {
      this.precedence = n;
      this.expressionType = expressionType2;
      this.name = str;
      this.parameters = parameters2;
   }

   private float getMin(IExpression[] expressions) {
      if (expressions.length == 2) {
         return Math.min(evalFloat(expressions, 0), evalFloat(expressions, 1));
      } else {
         float f = evalFloat(expressions, 0);

         for (int i = 1; i < expressions.length; i++) {
            float f2 = evalFloat(expressions, i);
            if (f2 < f) {
               f = f2;
            }
         }

         return f;
      }
   }

   private FunctionType(ExpressionType expressionType2, String str, IParameters parameters2) {
      this(0, expressionType2, str, parameters2);
   }

   public float evalFloat(IExpression[] expressions) {
      switch (this) {
         case PLUS:
            return evalFloat(expressions, 0) + evalFloat(expressions, 1);
         case MINUS:
            return evalFloat(expressions, 0) - evalFloat(expressions, 1);
         case MUL:
            return evalFloat(expressions, 0) * evalFloat(expressions, 1);
         case DIV:
            return evalFloat(expressions, 0) / evalFloat(expressions, 1);
         case MOD:
            float f = evalFloat(expressions, 0);
            float f2 = evalFloat(expressions, 1);
            return f - f2 * (float)((int)(f / f2));
         case NEG:
            return -evalFloat(expressions, 0);
         case PI:
            return (float) Math.PI;
         case SIN:
            return MathHelper.sin(evalFloat(expressions, 0));
         case COS:
            return MathHelper.cos(evalFloat(expressions, 0));
         case ASIN:
            return MathUtils.asin(evalFloat(expressions, 0));
         case ACOS:
            return MathUtils.acos(evalFloat(expressions, 0));
         case TAN:
            return (float)Math.tan((double)evalFloat(expressions, 0));
         case ATAN:
            return (float)Math.atan((double)evalFloat(expressions, 0));
         case ATAN2:
            return (float)MathHelper.atan2((double)evalFloat(expressions, 0), (double)evalFloat(expressions, 1));
         case TORAD:
            return MathUtils.toRad(evalFloat(expressions, 0));
         case TODEG:
            return MathUtils.toDeg(evalFloat(expressions, 0));
         case MIN:
            return this.getMin(expressions);
         case MAX:
            return this.getMax(expressions);
         case CLAMP:
            return MathHelper.clamp_float(evalFloat(expressions, 0), evalFloat(expressions, 1), evalFloat(expressions, 2));
         case ABS:
            return MathHelper.abs(evalFloat(expressions, 0));
         case EXP:
            return (float)Math.exp((double)evalFloat(expressions, 0));
         case FLOOR:
            return (float)MathHelper.floor_float(evalFloat(expressions, 0));
         case CEIL:
            return (float)MathHelper.ceiling_float_int(evalFloat(expressions, 0));
         case FRAC:
            return (float)MathHelper.frac((double)evalFloat(expressions, 0));
         case LOG:
            return (float)Math.log((double)evalFloat(expressions, 0));
         case POW:
            return (float)Math.pow((double)evalFloat(expressions, 0), (double)evalFloat(expressions, 1));
         case RANDOM:
            return (float)Math.random();
         case ROUND:
            return (float)Math.round(evalFloat(expressions, 0));
         case SIGNUM:
            return Math.signum(evalFloat(expressions, 0));
         case SQRT:
            return MathHelper.sqrt_float(evalFloat(expressions, 0));
         case FMOD:
            float f3 = evalFloat(expressions, 0);
            float f4 = evalFloat(expressions, 1);
            return f3 - f4 * (float)MathHelper.floor_float(f3 / f4);
         case TIME:
            WorldClient theWorld = Minecraft.getMinecraft().theWorld;
            if (theWorld == null) {
               return 0.0F;
            }

            return (float)(theWorld.getTotalWorldTime() % 24000L) + Config.renderPartialTicks;
         case IF:
            int n = (expressions.length - 1) / 2;

            for (int i = 0; i < n; i++) {
               int n2 = i * 2;
               if (evalBool(expressions, n2)) {
                  return evalFloat(expressions, n2 + 1);
               }
            }

            return evalFloat(expressions, n * 2);
         case SMOOTH:
            int n3 = (int)evalFloat(expressions, 0);
            float f5 = evalFloat(expressions, 1);
            float f6 = expressions.length > 2 ? evalFloat(expressions, 2) : 1.0F;
            return Smoother.getSmoothValue(n3, f5, f6, expressions.length > 3 ? evalFloat(expressions, 3) : f6);
         default:
            Config.warn("Unknown function type: " + this);
            return 0.0F;
      }
   }

   public String getName() {
      return this.name;
   }

   public ExpressionType[] getParameterTypes(IExpression[] expressions) {
      return this.parameters.getParameterTypes(expressions);
   }

   private FunctionType(ExpressionType expressionType2, String str, ExpressionType[] expressionTypes) {
      this(0, expressionType2, str, expressionTypes);
   }

   public float[] evalFloatArray(IExpression[] expressions) {
      switch (this) {
         case VEC2:
            return new float[]{evalFloat(expressions, 0), evalFloat(expressions, 1)};
         case VEC3:
            return new float[]{evalFloat(expressions, 0), evalFloat(expressions, 1), evalFloat(expressions, 2)};
         case VEC4:
            return new float[]{evalFloat(expressions, 0), evalFloat(expressions, 1), evalFloat(expressions, 2), evalFloat(expressions, 3)};
         default:
            Config.warn("Unknown function type: " + this);
            return null;
      }
   }

   public boolean evalBool(IExpression[] expressions) {
      switch (this) {
         case TRUE:
            return true;
         case FALSE:
            return false;
         case NOT:
            return !evalBool(expressions, 0);
         case AND:
            return evalBool(expressions, 0) && evalBool(expressions, 1);
         case OR:
            return evalBool(expressions, 0) || evalBool(expressions, 1);
         case GREATER:
            return evalFloat(expressions, 0) > evalFloat(expressions, 1);
         case GREATER_OR_EQUAL:
            return evalFloat(expressions, 0) >= evalFloat(expressions, 1);
         case SMALLER:
            return evalFloat(expressions, 0) < evalFloat(expressions, 1);
         case SMALLER_OR_EQUAL:
            return evalFloat(expressions, 0) <= evalFloat(expressions, 1);
         case EQUAL:
            return evalFloat(expressions, 0) == evalFloat(expressions, 1);
         case NOT_EQUAL:
            return evalFloat(expressions, 0) != evalFloat(expressions, 1);
         case BETWEEN:
            float f = evalFloat(expressions, 0);
            return f >= evalFloat(expressions, 1) && f <= evalFloat(expressions, 2);
         case EQUALS:
            float f2 = evalFloat(expressions, 0) - evalFloat(expressions, 1);
            float f3 = evalFloat(expressions, 2);
            return Math.abs(f2) <= f3;
         case IN:
            float f4 = evalFloat(expressions, 0);

            for (int i = 1; i < expressions.length; i++) {
               if (f4 == evalFloat(expressions, i)) {
                  return true;
               }
            }

            return false;
         default:
            Config.warn("Unknown function type: " + this);
            return false;
      }
   }

   private FunctionType(int n, ExpressionType expressionType2, String str, ExpressionType[] expressionTypes) {
      this(n, expressionType2, str, new Parameters(expressionTypes));
   }

   private static boolean evalBool(IExpression[] expressions, int n) {
      return ((IExpressionBool)expressions[n]).eval();
   }

}
