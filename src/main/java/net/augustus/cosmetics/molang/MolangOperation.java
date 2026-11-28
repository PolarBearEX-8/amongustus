package net.augustus.cosmetics.molang;

import java.util.HashSet;
import java.util.Set;

public enum MolangOperation {
   ADD("+", 1) {
      @Override
      public double calculate(double d, double d2) {
         return d + d2;
      }
   },
   SUB("-", 1) {
      @Override
      public double calculate(double d, double d2) {
         return d - d2;
      }
   },
   MUL("*", 2) {
      @Override
      public double calculate(double d, double d2) {
         return d * d2;
      }
   },
   DIV("/", 2) {
      @Override
      public double calculate(double d, double d2) {
         return d / (d2 == 0.0 ? 1.0 : d2);
      }
   },
   MOD("%", 2) {
      @Override
      public double calculate(double d, double d2) {
         return d % d2;
      }
   },
   POW("^", 3) {
      @Override
      public double calculate(double d, double d2) {
         return Math.pow(d, d2);
      }
   },
   AND("&&", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d != 0.0 && d2 != 0.0 ? 1.0 : 0.0;
      }
   },
   OR("||", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d == 0.0 && d2 == 0.0 ? 0.0 : 1.0;
      }
   },
   LESS("<", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d < d2 ? 1.0 : 0.0;
      }
   },
   LESS_THAN("<=", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d <= d2 ? 1.0 : 0.0;
      }
   },
   GREATER_THAN(">=", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d >= d2 ? 1.0 : 0.0;
      }
   },
   GREATER(">", 5) {
      @Override
      public double calculate(double d, double d2) {
         return d > d2 ? 1.0 : 0.0;
      }
   },
   EQUALS("==", 5) {
      @Override
      public double calculate(double d, double d2) {
         return MolangOperation.equals(d, d2) ? 1.0 : 0.0;
      }
   },
   NOT_EQUALS("!=", 5) {
      @Override
      public double calculate(double d, double d2) {
         return MolangOperation.equals(d, d2) ? 0.0 : 1.0;
      }
   };

   public final String sign;
public static final Set OPERATORS = new HashSet();
   public final int value;

   static {
      for (MolangOperation molangOperation : values()) {
         OPERATORS.add(molangOperation.sign);
      }
   }

   public static boolean equals(double d, double d2) {
      return Math.abs(d - d2) < 1.0E-5;
   }

   public abstract double calculate(double d, double d2);

private MolangOperation(String str, int n) {
      this.sign = str;
      this.value = n;
   }

   private MolangOperation(String str, int n, Object obj) {
      this(str, n);
   }
}
