package net.augustus.cosmetics.molang;

import net.augustus.cosmetics.molang.ast.ConstantExpression;
import net.augustus.cosmetics.molang.ast.MolangAssignment;
import net.augustus.cosmetics.molang.ast.MolangExpression;
import net.augustus.cosmetics.molang.ast.MolangMultiStatement;
import net.augustus.cosmetics.molang.ast.MolangValue;
import net.augustus.cosmetics.molang.ast.MolangValueExpression;
import net.augustus.cosmetics.molang.ast.MolangVariable;
import net.augustus.cosmetics.molang.functions.CosDegreesFunction;
import net.augustus.cosmetics.molang.functions.SinDegreesFunction;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;

public class MolangParser extends MathBuilder {
   public static final MolangExpression ZERO = new MolangValueExpression((MolangParser)null, new ConstantExpression(0.0));
   public static final String RETURN_KEYWORD = null;
   private MolangMultiStatement currentStatement;
   public static final MolangExpression ONE = new MolangValueExpression((MolangParser)null, new ConstantExpression(1.0));

   public MolangExpression parseJson(JsonElement elem) throws MolangException {
      if (elem.isJsonPrimitive()) {
         JsonPrimitive asJsonPrimitive = elem.getAsJsonPrimitive();
         if (asJsonPrimitive.isString()) {
            try {
               return new MolangValueExpression(this, new ConstantExpression((double)Float.parseFloat(asJsonPrimitive.getAsString())));
            } catch (Exception e) {
               return this.parseExpression(asJsonPrimitive.getAsString());
            }
         } else {
            return new MolangValueExpression(this, new ConstantExpression(asJsonPrimitive.getAsDouble()));
         }
      } else {
         return ZERO;
      }
   }

   public MolangParser() {
       super();
      this.functions.put("cos", CosDegreesFunction.class);
      this.functions.put("sin", SinDegreesFunction.class);
      String str = "math.abs";
      this.remap("abs", str);
      String str3 = "math.ceil";
      this.remap("ceil", str3);
      String str5 = "math.clamp";
      this.remap("clamp", str5);
      String str7 = "math.cos";
      this.remap("cos", str7);
      String str9 = "math.exp";
      this.remap("exp", str9);
      String str11 = "math.floor";
      this.remap("floor", str11);
      String str13 = "math.lerp";
      this.remap("lerp", str13);
      String str15 = "math.lerprotate";
      this.remap("lerprotate", str15);
      String str17 = "math.ln";
      this.remap("ln", str17);
      String str19 = "math.max";
      this.remap("max", str19);
      String str21 = "math.min";
      this.remap("min", str21);
      String str23 = "math.mod";
      this.remap("mod", str23);
      String str25 = "math.pow";
      this.remap("pow", str25);
      String str27 = "math.random";
      this.remap("random", str27);
      String str29 = "math.round";
      this.remap("round", str29);
      String str31 = "math.sin";
      this.remap("sin", str31);
      String str33 = "math.sqrt";
      this.remap("sqrt", str33);
      String str35 = "math.trunc";
      this.remap("trunc", str35);
   }

   @Override
   public MolangVariable getVariable(String str) {
      MolangVariable molangVariable = this.currentStatement == null ? null : (MolangVariable)this.currentStatement.locals.get(str);
      if (molangVariable == null) {
         molangVariable = super.getVariable(str);
      }

      if (molangVariable == null) {
         molangVariable = new MolangVariable(str, 0.0);
         this.register(molangVariable);
      }

      return molangVariable;
   }

   public MolangExpression parseExpression(String str) throws MolangException {
      ArrayList list = new ArrayList();

      for (String str2 : str.toLowerCase().trim().split(";")) {
         if (!str2.trim().isEmpty()) {
            list.add(str2);
         }
      }

      if (list.size() == 0) {
         throw new MolangException("Molang expression cannot be blank!");
      } else {
         MolangMultiStatement molangMultiStatement = new MolangMultiStatement(this);
         this.currentStatement = molangMultiStatement;

         try {
            for (String str3 : (Iterable<String>)(list)) {
               molangMultiStatement.expressions.add(this.parseOneLine(str3));
            }
         } catch (Exception e) {
            this.currentStatement = null;
            throw e;
         }

         this.currentStatement = null;
         return molangMultiStatement;
      }
   }

   public MolangExpression parseOneLine(String str) throws MolangException {
      str = str.trim();
      if (str.startsWith("return ")) {
         try {
            String str2 = str.substring("return ".length());
            return (new MolangValueExpression(this, this.parse(str2))).addReturn();
         } catch (Exception e) {
            throw new MolangException("Couldn't parse return '" + str + "' expression!");
         }
      } else {
         try {
            List list = this.breakdown(this.breakdownChars(str));
            if (list.size() >= 3 && list.get(0) instanceof String) {
               if (this.isVariable(list.get(0)) && list.get(1).equals("=")) {
                  String str3 = (String)list.get(0);
                  list = list.subList(2, list.size());
                  Object obj2 = null;
                  if (!this.variables.containsKey(str3) && !this.currentStatement.locals.containsKey(str3)) {
                     obj2 = new MolangVariable(str3, 0.0);
                     this.currentStatement.locals.put(str3, obj2);
                  } else {
                     obj2 = this.getVariable(str3);
                  }

                  return new MolangAssignment(this, (MolangVariable)obj2, this.parseSymbolsMolang(list));
               }
            }

            return new MolangValueExpression(this, this.parseSymbolsMolang(list));
         } catch (Exception e2) {
            throw new MolangException("Couldn't parse '" + str + "' expression!");
         }
      }
   }

   private MolangValue parseSymbolsMolang(List list) throws MolangException {

      try {
         return this.parseSymbols(list);
      } catch (Exception e) {
         e.printStackTrace();
         throw new MolangException("Couldn't parse an expression!");
      }
   }

   @Override
   public boolean isOperator(String str) {
      return super.isOperator(str) || str.equals("=");
   }

   public void remap(String str, String str2) {
      this.functions.put(str2, this.functions.remove(str));
   }


   public void setValue(String str, double d) {
      MolangVariable variable = this.getVariable(str);
      if (variable != null) {
         variable.setValue(d);
      }
   }




}
