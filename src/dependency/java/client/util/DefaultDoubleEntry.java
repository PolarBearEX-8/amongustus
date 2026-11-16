package client.util;

import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;

public class DefaultDoubleEntry extends InternalRandomEntryAdapter {
   public double value = 0.004;

   public DefaultDoubleEntry(Map map) {
      for (Entry entry : (Iterable<Entry>)(Iterable<?>)map.entrySet()) {
         if (((String)entry.getKey()).equals("chance")) {
            this.value = MathHelper.getCoordinate((String)entry.getValue(), this.value);
         }
      }
   }

   @Override
   public AuxiliaryLinkedListEntry resolveAuxiliaryLinkedListEntry(int count, int count2) {
      return new TargetBuffer(this.world, this.random, count, count2);
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return this.random.nextDouble() < this.value && this.random.nextInt(80) < Math.max(Math.abs(count), Math.abs(count2));
   }

   public DefaultDoubleEntry() {
   }

   @Override
   public String getText() {
      return "Mineshaft";
   }
}
