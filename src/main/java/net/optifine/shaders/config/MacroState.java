package net.optifine.shaders.config;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.optifine.config.Config;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.ParseException;

public class MacroState {
   private boolean active = true;
   private Deque dequeState = new ArrayDeque();
   private Deque dequeResolved = new ArrayDeque();
   private Map mapMacroValues = new HashMap();
   private static final Pattern PATTERN_DIRECTIVE = Pattern.compile("\\s*#\\s*(\\w+)\\s*(.*)");
   private static final Pattern PATTERN_DEFINED = Pattern.compile("defined\\s+(\\w+)");
   private static final Pattern PATTERN_DEFINED_FUNC = Pattern.compile("defined\\s*\\(\\s*(\\w+)\\s*\\)");
   private static final Pattern PATTERN_MACRO = Pattern.compile("(\\w+)");
   private static final String DEFINE = null;
   private static final String UNDEF = null;
   private static final String IFDEF = null;
   private static final String IFNDEF = null;
   private static final String IF = null;
   private static final String ELSE = null;
   private static final String ELIF = null;
   private static final String ENDIF = null;
   private static final List MACRO_NAMES = Arrays.asList("define", "undef", "ifdef", "ifndef", "if", "else", "elif", "endif");

   public boolean processLine(String str) {
      Matcher matcher = PATTERN_DIRECTIVE.matcher(str);
      if (!matcher.matches()) {
         return this.active;
      } else {
         String str2 = matcher.group(1);
         String str3 = matcher.group(2);
         int n = str3.indexOf("//");
         if (n >= 0) {
            str3 = str3.substring(0, n);
         }

         boolean active2 = this.active;
         this.processMacro(str2, str3);
         this.active = !this.dequeState.contains(Boolean.FALSE);
         return this.active || active2;
      }
   }

   private boolean eval(String str) {
      str = PATTERN_DEFINED.matcher(str).replaceAll("defined_$1");
      str = PATTERN_DEFINED_FUNC.matcher(str).replaceAll("defined_$1");
      boolean flag = false;
      int n = 0;

      do {
         flag = false;
         Matcher matcher3 = PATTERN_MACRO.matcher(str);

         while (matcher3.find()) {
            String str2 = matcher3.group();
            if (str2.length() > 0) {
               char ch = str2.charAt(0);
               if ((Character.isLetter(ch) || ch == '_') && this.mapMacroValues.containsKey(str2)) {
                  String str3 = (String)this.mapMacroValues.get(str2);
                  if (str3 == null) {
                     str3 = "1";
                  }

                  int n2 = matcher3.start();
                  int n3 = matcher3.end();
                  str = str.substring(0, n2) + " " + str3 + " " + str.substring(n3);
                  flag = true;
                  n++;
                  break;
               }
            }
         }
      } while (flag && n < 100);

      if (n >= 100) {
         Config.warn("Too many iterations: " + n + ", when resolving: " + str);
         return true;
      } else {
         try {
            MacroExpressionResolver macroExpressionResolver = new MacroExpressionResolver(this.mapMacroValues);
            IExpression expression = new ExpressionParser(macroExpressionResolver).parse(str);
            if (expression.getFloatValue() == ExpressionType.BOOL) {
               return ((IExpressionBool)expression).eval();
            } else if (expression.getFloatValue() == ExpressionType.FLOAT) {
               return ((IExpressionFloat)expression).eval() != 0.0F;
            } else {
               throw new ParseException("Not a boolean or float expression: " + expression.getFloatValue());
            }
         } catch (ParseException e) {
            Config.warn("Invalid macro expression: " + str);
            Config.warn("Error: " + e.getMessage());
            return false;
         }
      }
   }

   public static boolean isMacroLine(String str) {
      Matcher matcher = PATTERN_DIRECTIVE.matcher(str);
      if (!matcher.matches()) {
         return false;
      } else {
         String str2 = matcher.group(1);
         return MACRO_NAMES.contains(str2);
      }
   }

   private void processMacro(String str, String str2) {
      StringTokenizer stringTokenizer = new StringTokenizer(str2, " \t");
      String str3 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken() : "";
      String str4 = stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken("").trim() : "";
      if (str.equals("define")) {
         this.mapMacroValues.put(str3, str4);
      } else if (str.equals("undef")) {
         this.mapMacroValues.remove(str3);
      } else if (str.equals("ifdef")) {
         boolean flag = this.mapMacroValues.containsKey(str3);
         this.dequeState.add(flag);
         this.dequeResolved.add(flag);
      } else if (str.equals("ifndef")) {
         boolean flag2 = !this.mapMacroValues.containsKey(str3);
         this.dequeState.add(flag2);
         this.dequeResolved.add(flag2);
      } else if (str.equals("if")) {
         boolean flag3 = this.eval(str2);
         this.dequeState.add(flag3);
         this.dequeResolved.add(flag3);
      } else if (!this.dequeState.isEmpty()) {
         if (str.equals("elif")) {
            boolean flag4 = (Boolean)this.dequeState.removeLast();
            boolean flag5 = (Boolean)this.dequeResolved.removeLast();
            if (flag5) {
               this.dequeState.add(false);
               this.dequeResolved.add(flag5);
            } else {
               boolean flag6 = this.eval(str2);
               this.dequeState.add(flag6);
               this.dequeResolved.add(flag6);
            }
         } else if (str.equals("else")) {
            boolean flag7 = (Boolean)this.dequeState.removeLast();
            boolean flag8 = (Boolean)this.dequeResolved.removeLast();
            this.dequeState.add(!flag8);
            this.dequeResolved.add(true);
         } else if (str.equals("endif")) {
            this.dequeState.removeLast();
            this.dequeResolved.removeLast();
         }
      }
   }
}
