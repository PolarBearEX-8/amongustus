package client.util;

import client.setting.CombatNumberProperty;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;

public class CommandStatsManager {
   public static final int count = CombatNumberProperty.values().length;
   public static final String[] stringArray2 = new String[count];
   public String[] stringArray3 = stringArray2;
   public String[] stringArray = stringArray2;

   public static void applyCommandStatsManager(CommandStatsManager commandStatsManager, CombatNumberProperty combatNumberProperty, String text, String text2) {
      if (text != null && text.length() != 0 && text2 != null && text2.length() != 0) {
         if (commandStatsManager.stringArray3 == stringArray2 || commandStatsManager.stringArray == stringArray2) {
            commandStatsManager.stringArray3 = new String[count];
            commandStatsManager.stringArray = new String[count];
         }

         commandStatsManager.stringArray3[combatNumberProperty.getCount()] = text;
         commandStatsManager.stringArray[combatNumberProperty.getCount()] = text2;
      } else {
         applyCommandStatsManager(commandStatsManager, combatNumberProperty);
      }
   }

   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      if (tagCompound.matchesText("CommandStats", 10)) {
         NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("CommandStats");

         for (CombatNumberProperty combatNumberProperty : CombatNumberProperty.values()) {
            String text = combatNumberProperty.getLabelText() + "Name";
            String text2 = combatNumberProperty.getLabelText() + "Objective";
            if (nBTTagCompound.matchesText(text, 8) && nBTTagCompound.matchesText(text2, 8)) {
               String text3 = nBTTagCompound.resolveText(text);
               String text4 = nBTTagCompound.resolveText(text2);
               applyCommandStatsManager(this, combatNumberProperty, text3, text4);
            }
         }
      }
   }

   private static void applyCommandStatsManager(CommandStatsManager commandStatsManager, CombatNumberProperty combatNumberProperty) {
      if (commandStatsManager.stringArray3 != stringArray2 && commandStatsManager.stringArray != stringArray2) {
         commandStatsManager.stringArray3[combatNumberProperty.getCount()] = null;
         commandStatsManager.stringArray[combatNumberProperty.getCount()] = null;
         boolean flag = true;

         for (CombatNumberProperty combatNumberProperty2 : CombatNumberProperty.values()) {
            if (commandStatsManager.stringArray3[combatNumberProperty2.getCount()] != null && commandStatsManager.stringArray[combatNumberProperty2.getCount()] != null) {
               flag = false;
               break;
            }
         }

         if (flag) {
            commandStatsManager.stringArray3 = stringArray2;
            commandStatsManager.stringArray = stringArray2;
         }
      }
   }

   public void applyICommandSender(ICommandSender iCommandSender, CombatNumberProperty combatNumberProperty, int count2) {
      String text = this.stringArray3[combatNumberProperty.getCount()];
      if (text != null) {
         RayTraceFilter rayTraceFilter = new RayTraceFilter(this, iCommandSender);

         String text2;
         try {
            text2 = Listener87Entry.resolveTextByICommandSender(rayTraceFilter, text);
         } catch (AimStack exception) {
            return;
         }

         String text3 = this.stringArray[combatNumberProperty.getCount()];
         if (text3 != null) {
            ThePlayerNameManager thePlayerNameManager = iCommandSender.getWorldObj().getThePlayerNameManager();
            ScoreboardData scoreboardData = thePlayerNameManager.get(text3);
            if (scoreboardData != null && thePlayerNameManager.matchesText(text2, scoreboardData)) {
               thePlayerNameManager.getComparatorEntry(text2, scoreboardData).applyIndex(count2);
            }
         }
      }
   }

   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      NBTTagCompound tagCompound2 = new NBTTagCompound();

      for (CombatNumberProperty combatNumberProperty : CombatNumberProperty.values()) {
         String text = this.stringArray3[combatNumberProperty.getCount()];
         String text2 = this.stringArray[combatNumberProperty.getCount()];
         if (text != null && text2 != null) {
            tagCompound2.put(combatNumberProperty.getLabelText() + "Name", text);
            tagCompound2.put(combatNumberProperty.getLabelText() + "Objective", text2);
         }
      }

      if (!tagCompound2.isEmpty()) {
         tagCompound.applyText("CommandStats", tagCompound2);
      }
   }

   public void applyCommandStatsManager(CommandStatsManager commandStatsManager) {
      for (CombatNumberProperty combatNumberProperty : CombatNumberProperty.values()) {
         applyCommandStatsManager(this, combatNumberProperty, commandStatsManager.stringArray3[combatNumberProperty.getCount()], commandStatsManager.stringArray[combatNumberProperty.getCount()]);
      }
   }
}
