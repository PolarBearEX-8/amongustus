package net.augustus.cosmetics.molang.ast;

import net.augustus.cosmetics.molang.MolangParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class MolangMultiStatement extends MolangExpression {
   public Map locals;
   public List expressions = new ArrayList();

   @Override
   public String toString() {
      StringJoiner stringJoiner = new StringJoiner("; ");

      for (MolangExpression molangExpression : (Iterable<MolangExpression>)(this.expressions)) {
         stringJoiner.add(molangExpression.toString());
         if (molangExpression instanceof MolangValueExpression && ((MolangValueExpression)molangExpression).returns) {
            break;
         }
      }

      return stringJoiner.toString();
   }

   public double get() {
      double d = 0.0;

      for (MolangExpression molangExpression : (Iterable<MolangExpression>)(this.expressions)) {
         d = molangExpression.get();
      }

      return d;
   }

   public MolangMultiStatement(MolangParser molangParser) {
      super(molangParser);
      this.locals = new HashMap();
   }

}
