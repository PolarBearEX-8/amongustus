package client.util;

import client.setting.CombatValueProperty;
import client.setting.RenderBooleanProperty;

public class DefaultVertexFormats {
   public static AuxiliaryLoggerEntry auxiliaryLoggerEntry5 = new AuxiliaryLoggerEntry();
   public static AuxiliaryLoggerEntry auxiliaryLoggerEntry11 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry13 = auxiliaryLoggerEntry5;
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry10 = auxiliaryLoggerEntry11;
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry4 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry8 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry1 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry3 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry7 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry6 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry12 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry9 = new AuxiliaryLoggerEntry();
   public static final AuxiliaryLoggerEntry auxiliaryLoggerEntry2 = new AuxiliaryLoggerEntry();
   public static final AltLoggerEntry altLoggerEntry = new AltLoggerEntry(0, CombatValueProperty.FLOAT, RenderBooleanProperty.POSITION, 3);
   public static final AltLoggerEntry altLoggerEntry6 = new AltLoggerEntry(0, CombatValueProperty.UBYTE, RenderBooleanProperty.COLOR, 4);
   public static final AltLoggerEntry altLoggerEntry4 = new AltLoggerEntry(0, CombatValueProperty.FLOAT, RenderBooleanProperty.UV, 2);
   public static final AltLoggerEntry altLoggerEntry3 = new AltLoggerEntry(1, CombatValueProperty.SHORT, RenderBooleanProperty.UV, 2);
   public static final AltLoggerEntry altLoggerEntry5 = new AltLoggerEntry(0, CombatValueProperty.BYTE, RenderBooleanProperty.NORMAL, 3);
   public static final AltLoggerEntry altLoggerEntry2 = new AltLoggerEntry(0, CombatValueProperty.BYTE, RenderBooleanProperty.PADDING, 1);

   public static void updateState() {
      if (BuildManager.canExecute()) {
         auxiliaryLoggerEntry5 = RotationEvaluator.getAuxiliaryLoggerEntry();
         auxiliaryLoggerEntry11 = RotationEvaluator.getAuxiliaryLoggerEntryAsAuxiliaryLoggerE();
      } else {
         auxiliaryLoggerEntry5 = auxiliaryLoggerEntry13;
         auxiliaryLoggerEntry11 = auxiliaryLoggerEntry10;
      }
   }

   static {
      auxiliaryLoggerEntry5.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry5.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry5.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry5.resolveAuxiliaryLoggerEntry(altLoggerEntry3);
      auxiliaryLoggerEntry11.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry11.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry11.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry11.resolveAuxiliaryLoggerEntry(altLoggerEntry5);
      auxiliaryLoggerEntry11.resolveAuxiliaryLoggerEntry(altLoggerEntry2);
      auxiliaryLoggerEntry4.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry4.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry4.resolveAuxiliaryLoggerEntry(altLoggerEntry5);
      auxiliaryLoggerEntry4.resolveAuxiliaryLoggerEntry(altLoggerEntry2);
      auxiliaryLoggerEntry8.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry8.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry8.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry8.resolveAuxiliaryLoggerEntry(altLoggerEntry3);
      auxiliaryLoggerEntry1.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry3.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry3.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry7.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry7.resolveAuxiliaryLoggerEntry(altLoggerEntry5);
      auxiliaryLoggerEntry7.resolveAuxiliaryLoggerEntry(altLoggerEntry2);
      auxiliaryLoggerEntry6.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry6.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry6.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry12.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry12.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry12.resolveAuxiliaryLoggerEntry(altLoggerEntry5);
      auxiliaryLoggerEntry12.resolveAuxiliaryLoggerEntry(altLoggerEntry2);
      auxiliaryLoggerEntry9.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry9.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry9.resolveAuxiliaryLoggerEntry(altLoggerEntry3);
      auxiliaryLoggerEntry9.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry2.resolveAuxiliaryLoggerEntry(altLoggerEntry);
      auxiliaryLoggerEntry2.resolveAuxiliaryLoggerEntry(altLoggerEntry4);
      auxiliaryLoggerEntry2.resolveAuxiliaryLoggerEntry(altLoggerEntry6);
      auxiliaryLoggerEntry2.resolveAuxiliaryLoggerEntry(altLoggerEntry5);
      auxiliaryLoggerEntry2.resolveAuxiliaryLoggerEntry(altLoggerEntry2);
   }
}
