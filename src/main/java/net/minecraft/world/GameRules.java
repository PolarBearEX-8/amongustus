package net.minecraft.world;

import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;

public class GameRules {
   private TreeMap theGameRules = new TreeMap();

   public String[] getRules() {
      Set keys = this.theGameRules.keySet();
      return (String[])keys.toArray(new String[keys.size()]);
   }

   public NBTTagCompound writeToNBT() {
      NBTTagCompound tag = new NBTTagCompound();

      for (Object obj : this.theGameRules.keySet()) {
         GameRules.Value value = (GameRules.Value)this.theGameRules.get(obj);
         tag.setString((String)obj, value.getString());
      }

      return tag;
   }

   public boolean getBoolean(String str) {
      GameRules.Value value = (GameRules.Value)this.theGameRules.get(str);
      return value != null ? value.getBoolean() : false;
   }

   public void addGameRule(String str, String str2, GameRules.ValueType valueType) {
      this.theGameRules.put(str, new GameRules.Value(str2, valueType));
   }

   public int getInt(String str) {
      GameRules.Value value = (GameRules.Value)this.theGameRules.get(str);
      return value != null ? value.getInt() : 0;
   }

   public String getString(String str) {
      GameRules.Value value = (GameRules.Value)this.theGameRules.get(str);
      return value != null ? value.getString() : "";
   }

   public boolean hasRule(String str) {
      return this.theGameRules.containsKey(str);
   }

   public GameRules() {
      this.addGameRule("doFireTick", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("mobGriefing", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("keepInventory", "false", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobSpawning", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobLoot", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doTileDrops", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doEntityDrops", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("commandBlockOutput", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("naturalRegeneration", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doDaylightCycle", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("logAdminCommands", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("showDeathMessages", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("randomTickSpeed", "3", GameRules.ValueType.NUMERICAL_VALUE);
      this.addGameRule("sendCommandFeedback", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("reducedDebugInfo", "false", GameRules.ValueType.BOOLEAN_VALUE);
   }

   public void readFromNBT(NBTTagCompound tag) {
      for (String str : (Iterable<String>)(tag.getKeySet())) {
         this.setOrCreateGameRule(str, tag.getString(str));
      }
   }

   public void setOrCreateGameRule(String str, String str2) {
      GameRules.Value value = (GameRules.Value)this.theGameRules.get(str);
      if (value != null) {
         value.setValue(str2);
      } else {
         this.addGameRule(str, str2, GameRules.ValueType.ANY_VALUE);
      }
   }

   public boolean areSameType(String str, GameRules.ValueType valueType) {
      GameRules.Value value = (GameRules.Value)this.theGameRules.get(str);
      return value != null && (value.getType() == valueType || valueType == GameRules.ValueType.ANY_VALUE);
   }


   public static class Value {
      private String valueString;
      private boolean valueBoolean;
      private int valueInteger;
      private double valueDouble;
      private final GameRules.ValueType type;

      public boolean getBoolean() {
         return this.valueBoolean;
      }

      public int getInt() {
         return this.valueInteger;
      }

      public String getString() {
         return this.valueString;
      }

      public GameRules.ValueType getType() {
         return this.type;
      }

      public Value(String str, GameRules.ValueType valueType) {
         this.type = valueType;
         this.setValue(str);
      }

      public void setValue(String str) {
         this.valueString = str;
         if (str != null) {
            if (str.equals("false")) {
               this.valueBoolean = false;
               return;
            }

            if (str.equals("true")) {
               this.valueBoolean = true;
               return;
            }
         }

         this.valueBoolean = Boolean.parseBoolean(str);
         this.valueInteger = this.valueBoolean ? 1 : 0;

         try {
            this.valueInteger = Integer.parseInt(str);
         } catch (NumberFormatException e) {
         }

         try {
            this.valueDouble = Double.parseDouble(str);
         } catch (NumberFormatException e2) {
         }
      }
   }

   public enum ValueType {
      ANY_VALUE("ANY_VALUE", 0),
      BOOLEAN_VALUE("BOOLEAN_VALUE", 1),
      NUMERICAL_VALUE("NUMERICAL_VALUE", 2);

   private ValueType(String str, int n) {
      }
   }
}
