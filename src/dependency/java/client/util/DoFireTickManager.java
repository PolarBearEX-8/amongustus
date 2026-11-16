package client.util;

import client.setting.CombatBooleanProperty;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;

public class DoFireTickManager {
   public TreeMap treeMap = new TreeMap();

   public void put(String text, String text2, CombatBooleanProperty combatBooleanProperty) {
      this.treeMap.put(text, new FalseManager(text2, combatBooleanProperty));
   }

   public boolean matchesText(String text, CombatBooleanProperty combatBooleanProperty) {
      FalseManager falseManager = (FalseManager)this.treeMap.get(text);
      return falseManager != null && (falseManager.getCombatBooleanProperty() == combatBooleanProperty || combatBooleanProperty == CombatBooleanProperty.ANY_VALUE);
   }

   public boolean matchesText(String text) {
      FalseManager falseManager = (FalseManager)this.treeMap.get(text);
      return falseManager != null ? falseManager.isEnabled() : false;
   }

   public String[] getTextArray() {
      Set keys = this.treeMap.keySet();
      return ((String[])keys.toArray(new String[keys.size()]));
   }

   public NBTTagCompound getNBTTagCompound() {
      NBTTagCompound tagCompound = new NBTTagCompound();

      for (Object element : this.treeMap.keySet()) {
         FalseManager falseManager = (FalseManager)this.treeMap.get(element);
         tagCompound.put((String)element, falseManager.getLabelText());
      }

      return tagCompound;
   }

   public int computeCount(String text) {
      FalseManager falseManager = (FalseManager)this.treeMap.get(text);
      return falseManager != null ? falseManager.getCount() : 0;
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      for (String text : (Iterable<String>)(Iterable<?>)tagCompound.keySet()) {
         String text2 = tagCompound.resolveText(text);
         this.applyText(text, text2);
      }
   }

   public String resolveText(String text) {
      FalseManager falseManager = (FalseManager)this.treeMap.get(text);
      return falseManager != null ? falseManager.getLabelText() : "";
   }

   public boolean containsKey(String text) {
      return this.treeMap.containsKey(text);
   }

   public void applyText(String text, String text2) {
      FalseManager falseManager = (FalseManager)this.treeMap.get(text);
      if (falseManager != null) {
         falseManager.applyText(text2);
      } else {
         this.put(text, text2, CombatBooleanProperty.ANY_VALUE);
      }
   }

   public DoFireTickManager() {
      this.put("doFireTick", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("mobGriefing", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("keepInventory", "false", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("doMobSpawning", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("doMobLoot", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("doTileDrops", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("doEntityDrops", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("commandBlockOutput", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("naturalRegeneration", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("doDaylightCycle", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("logAdminCommands", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("showDeathMessages", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("randomTickSpeed", "3", CombatBooleanProperty.NUMERICAL_VALUE);
      this.put("sendCommandFeedback", "true", CombatBooleanProperty.BOOLEAN_VALUE);
      this.put("reducedDebugInfo", "false", CombatBooleanProperty.BOOLEAN_VALUE);
   }
}
