package client.util;

import client.setting.CombatValueProperty;
import client.setting.RenderBooleanProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AltLoggerEntry {
   public static final Logger logger = LogManager.getLogger();
   public final CombatValueProperty combatValueProperty;
   public final RenderBooleanProperty renderBooleanProperty;
   public int count;
   public int count2;

   private final boolean matchesRenderBooleanProperty(int count3, RenderBooleanProperty renderBooleanProperty2) {
      return count3 == 0 || renderBooleanProperty2 == RenderBooleanProperty.UV;
   }

   public final int getCount() {
      return this.count;
   }

   public final int getCount2() {
      return this.count2;
   }

   public final boolean isActive() {
      return this.renderBooleanProperty == RenderBooleanProperty.POSITION;
   }

   public final CombatValueProperty getCombatValueProperty() {
      return this.combatValueProperty;
   }

   public final int getCountAsIndex() {
      return this.combatValueProperty.getCount() * this.count2;
   }

   public final RenderBooleanProperty getRenderBooleanProperty() {
      return this.renderBooleanProperty;
   }

   public AltLoggerEntry(int count3, CombatValueProperty combatValueProperty2, RenderBooleanProperty renderBooleanProperty2, int count4) {
      if (!this.matchesRenderBooleanProperty(count3, renderBooleanProperty2)) {
         logger.warn("Multiple vertex elements of the same type other than UVs are not supported. Forcing type to UV.");
         this.renderBooleanProperty = RenderBooleanProperty.UV;
      } else {
         this.renderBooleanProperty = renderBooleanProperty2;
      }

      this.combatValueProperty = combatValueProperty2;
      this.count = count3;
      this.count2 = count4;
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         AltLoggerEntry altLoggerEntry = (AltLoggerEntry)other;
         return this.count2 != altLoggerEntry.count2
            ? false
            : (
               this.count != altLoggerEntry.count
                  ? false
                  : (this.combatValueProperty != altLoggerEntry.combatValueProperty ? false : this.renderBooleanProperty == altLoggerEntry.renderBooleanProperty)
            );
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.count2 + "," + this.renderBooleanProperty.getLabelText() + "," + this.combatValueProperty.getLabelText();
   }

   @Override
   public int hashCode() {
      int hash = this.combatValueProperty.hashCode();
      int count3 = 31 * hash + this.renderBooleanProperty.hashCode();
      int count4 = 31 * count3 + this.count;
      return 31 * count4 + this.count2;
   }
}
