package client.setting;

import java.util.HashSet;
import java.util.Set;

public enum CompositeOptionType {
   ADD("+", 1) {
      @Override
      public double pow(double offset, double offset2) {
         return offset + offset2;
      }
   },
   SUB("-", 1) {
      @Override
      public double pow(double offset, double offset2) {
         return offset - offset2;
      }
   },
   MUL("*", 2) {
      @Override
      public double pow(double offset, double offset2) {
         return offset * offset2;
      }
   },
   DIV("/", 2) {
      @Override
      public double pow(double offset, double offset2) {
         return offset / (offset2 == 0.0 ? 1.0 : offset2);
      }
   },
   MOD("%", 2) {
      @Override
      public double pow(double offset, double offset2) {
         return offset % offset2;
      }
   },
   POW("^", 3) {
      @Override
      public double pow(double offset, double offset2) {
         return Math.pow(offset, offset2);
      }
   },
   AND("&&", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset != 0.0 && offset2 != 0.0 ? 1.0 : 0.0;
      }
   },
   OR("||", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset == 0.0 && offset2 == 0.0 ? 0.0 : 1.0;
      }
   },
   LESS("<", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset < offset2 ? 1.0 : 0.0;
      }
   },
   LESS_THAN("<=", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset <= offset2 ? 1.0 : 0.0;
      }
   },
   GREATER_THAN(">=", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset >= offset2 ? 1.0 : 0.0;
      }
   },
   GREATER(">", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return offset > offset2 ? 1.0 : 0.0;
      }
   },
   EQUALS("==", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return CompositeOptionType.abs(offset, offset2) ? 1.0 : 0.0;
      }
   },
   NOT_EQUALS("!=", 5) {
      @Override
      public double pow(double offset, double offset2) {
         return CompositeOptionType.abs(offset, offset2) ? 0.0 : 1.0;
      }
   };

   public final String sign;
   public static final Set OPERATORS = new HashSet();
   public final int value;
   public static final CompositeOptionType[] ENUM_VALUES = values();

   public abstract double pow(double offset, double offset2);

   public static boolean abs(double offset, double offset2) {
      return Math.abs(offset - offset2) < 1.0E-5;
   }


   private CompositeOptionType(String text, int count) {
      this.sign = text;
      this.value = count;
   }


   static {

      for (CompositeOptionType compositeOptionType : values()) {
         OPERATORS.add(compositeOptionType.sign);
      }
   }
}
