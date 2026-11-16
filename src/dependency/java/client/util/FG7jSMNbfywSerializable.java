package client.util;

import client.setting.CombatScaleProperty;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;

public final class FG7jSMNbfywSerializable implements Serializable {
   public static final int count3 = 9;
   public static final FG7jSMNbfywSerializable fG7jSMNbfywSerializable = new FG7jSMNbfywSerializable(7, CombatScaleProperty.HALF_EVEN);
   public static final int count = 0;
   public static final CombatScaleProperty combatScaleProperty2 = CombatScaleProperty.HALF_UP;
   public final CombatScaleProperty combatScaleProperty;
   public final int count2;
   public static final FG7jSMNbfywSerializable fG7jSMNbfywSerializable2 = new FG7jSMNbfywSerializable(34, CombatScaleProperty.HALF_EVEN);
   public static final FG7jSMNbfywSerializable fG7jSMNbfywSerializable3 = new FG7jSMNbfywSerializable(0, CombatScaleProperty.HALF_UP);
   public static final long serialVersionUID = 0x4d6f25c81f7601ffL;
   public static final FG7jSMNbfywSerializable fG7jSMNbfywSerializable4 = new FG7jSMNbfywSerializable(16, CombatScaleProperty.HALF_EVEN);

   public CombatScaleProperty getCombatScaleProperty() {
      return this.combatScaleProperty;
   }

   public int getCount2() {
      return this.count2;
   }

   public FG7jSMNbfywSerializable(String text) {
      if (text == null) {
         throw new NullPointerException("null String");
      } else {
         int count4;
         try {
            if (!text.startsWith("precision=")) {
               throw new RuntimeException();
            }

            int count5 = text.indexOf(32);
            count4 = Integer.parseInt(text.substring(10, count5));
            if (!text.startsWith("roundingMode=", count5 + 1)) {
               throw new RuntimeException();
            }

            int count6 = count5 + 1 + 13;
            String text2 = text.substring(count6);
            this.combatScaleProperty = CombatScaleProperty.valueOf(text2);
         } catch (RuntimeException exception) {
            throw new IllegalArgumentException("bad string format");
         }

         if (count4 < 0) {
            throw new IllegalArgumentException("Digits < 0");
         } else {
            this.count2 = count4;
         }
      }
   }

   public FG7jSMNbfywSerializable(int count4) {
      this(count4, combatScaleProperty2);
   }

   public FG7jSMNbfywSerializable(int count4, CombatScaleProperty combatScaleProperty3) {
      if (count4 < 0) {
         throw new IllegalArgumentException("Digits < 0");
      } else if (combatScaleProperty3 == null) {
         throw new NullPointerException("null RoundingMode");
      } else {
         this.count2 = count4;
         this.combatScaleProperty = combatScaleProperty3;
      }
   }

   @Override
   public boolean equals(Object other) {
      Object entry = null;
      if (!(other instanceof FG7jSMNbfywSerializable)) {
         return false;
      } else {
         entry = (FG7jSMNbfywSerializable)other;
         return ((FG7jSMNbfywSerializable)entry).count2 == this.count2 && ((FG7jSMNbfywSerializable)entry).combatScaleProperty == this.combatScaleProperty;
      }
   }

   @Override
   public String toString() {
      return "precision=" + this.count2 + " roundingMode=" + this.combatScaleProperty.toString();
   }

   @Override
   public int hashCode() {
      return this.count2 + this.combatScaleProperty.hashCode() * 59;
   }

   private void readObject(long value, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
      Object entry = null;
      objectInputStream.defaultReadObject();
      if (this.count2 >= 0) {
         if (this.combatScaleProperty == null) {
            String text = "MathContext: null roundingMode in stream";
            throw new StreamCorruptedException(text);
         }
      } else {
         entry = "MathContext: invalid digits in stream";
         throw new StreamCorruptedException((String)entry);
      }
   }
}
