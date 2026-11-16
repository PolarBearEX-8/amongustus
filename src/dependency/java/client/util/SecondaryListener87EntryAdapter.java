package client.util;

import client.setting.ClientSettingsBlackType;
import client.setting.CombatNumberProperty;
import client.setting.MovementBooleanProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

public class SecondaryListener87EntryAdapter extends Listener87Entry {
   public void applyICommandSender5(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      int count2 = count + 1;
      ScorePlayerTeam scorePlayerTeam = this.resolveScorePlayerTeam(textArray[count]);
      if (scorePlayerTeam != null) {
         String text = textArray[count2++].toLowerCase();
         if (!text.equalsIgnoreCase("color")
            && !text.equalsIgnoreCase("friendlyfire")
            && !text.equalsIgnoreCase("seeFriendlyInvisibles")
            && !text.equalsIgnoreCase("nametagVisibility")
            && !text.equalsIgnoreCase("deathMessageVisibility")) {
            throw new AimDispatcher("commands.scoreboard.teams.option.usage", new Object[0]);
         }

         if (textArray.length == 4) {
            if (text.equalsIgnoreCase("color")) {
               throw new AimDispatcher(
                  "commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(ClientSettingsBlackType.resolveCollection(true, false))}
               );
            }

            if (!text.equalsIgnoreCase("friendlyfire") && !text.equalsIgnoreCase("seeFriendlyInvisibles")) {
               if (!text.equalsIgnoreCase("nametagVisibility") && !text.equalsIgnoreCase("deathMessageVisibility")) {
                  throw new AimDispatcher("commands.scoreboard.teams.option.usage", new Object[0]);
               }

               throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(MovementBooleanProperty.getTextArray())});
            }

            throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(Arrays.asList("true", "false"))});
         }

         String text2 = textArray[count2];
         if (text.equalsIgnoreCase("color")) {
            ClientSettingsBlackType clientSettingsBlackType = ClientSettingsBlackType.resolveClientSettingsBlackType(text2);
            if (clientSettingsBlackType == null || clientSettingsBlackType.isEnabled()) {
               throw new AimDispatcher(
                  "commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(ClientSettingsBlackType.resolveCollection(true, false))}
               );
            }

            scorePlayerTeam.setClientSettingsBlackType(clientSettingsBlackType);
            scorePlayerTeam.applyText(clientSettingsBlackType.toString());
            scorePlayerTeam.applyTextByText(ClientSettingsBlackType.RESET.toString());
         } else if (text.equalsIgnoreCase("friendlyfire")) {
            if (!text2.equalsIgnoreCase("true") && !text2.equalsIgnoreCase("false")) {
               throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(Arrays.asList("true", "false"))});
            }

            scorePlayerTeam.setEnabled(text2.equalsIgnoreCase("true"));
         } else if (text.equalsIgnoreCase("seeFriendlyInvisibles")) {
            if (!text2.equalsIgnoreCase("true") && !text2.equalsIgnoreCase("false")) {
               throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(Arrays.asList("true", "false"))});
            }

            scorePlayerTeam.setEnabledByFlag(text2.equalsIgnoreCase("true"));
         } else if (text.equalsIgnoreCase("nametagVisibility")) {
            MovementBooleanProperty movementBooleanProperty = MovementBooleanProperty.get(text2);
            if (movementBooleanProperty == null) {
               throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(MovementBooleanProperty.getTextArray())});
            }

            scorePlayerTeam.applyMovementBooleanPropertyByMovementBooleanP(movementBooleanProperty);
         } else if (text.equalsIgnoreCase("deathMessageVisibility")) {
            MovementBooleanProperty movementBooleanProperty2 = MovementBooleanProperty.get(text2);
            if (movementBooleanProperty2 == null) {
               throw new AimDispatcher("commands.scoreboard.teams.option.noValue", new Object[]{text, resolveText(MovementBooleanProperty.getTextArray())});
            }

            scorePlayerTeam.applyMovementBooleanProperty(movementBooleanProperty2);
         }

         applyICommandSender(iCommandSender, this, "commands.scoreboard.teams.option.success", new Object[]{text, scorePlayerTeam.getLabelText2(), text2});
      }
   }

   public void applyICommandSender(ICommandSender iCommandSender) throws InternalExceptionAdapter {
      Collection collection = this.getThePlayerNameManager().valuesAsCollection();
      if (collection.size() <= 0) {
         throw new InternalExceptionAdapter("commands.scoreboard.objectives.list.empty", new Object[0]);
      } else {
         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
            "commands.scoreboard.objectives.list.count", new Object[]{collection.size()}
         );
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);

         for (ScoreboardData scoreboardData : (java.lang.Iterable<ScoreboardData>) (collection)) {
            iCommandSender.applyIChatComponent(
               new BaseManagerStyleIChatComponentAdapter(
                  "commands.scoreboard.objectives.list.entry", new Object[]{scoreboardData.getLabelText2(), scoreboardData.getLabelText(), scoreboardData.getHealthHandler().getLabelText()}
               )
            );
         }
      }
   }

   @Override
   public String getText() {
      return "scoreboard";
   }

   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = textArray[count];
      HashSet set = Sets.newHashSet();
      HashSet set2 = Sets.newHashSet();
      if (iCommandSender instanceof EntityPlayer && count2 == textArray.length) {
         String text2 = resolveEntityPlayerAdapter(iCommandSender).getText();
         if (thePlayerNameManager.matchesText(text2, text)) {
            set.add(text2);
         } else {
            set2.add(text2);
         }
      } else {
         while (count2 < textArray.length) {
            String text3 = textArray[count2++];
            if (text3.startsWith("@")) {
               for (Entity entity : (java.lang.Iterable<Entity>) (resolveList(iCommandSender, text3))) {
                  String text4 = resolveTextByICommandSender(iCommandSender, entity.getEntityUniqueID().toString());
                  if (thePlayerNameManager.matchesText(text4, text)) {
                     set.add(text4);
                  } else {
                     set2.add(text4);
                  }
               }
            } else {
               String text5 = resolveTextByICommandSender(iCommandSender, text3);
               if (thePlayerNameManager.matchesText(text5, text)) {
                  set.add(text5);
               } else {
                  set2.add(text5);
               }
            }
         }
      }

      if (!set.isEmpty()) {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, set.size());
         applyICommandSender(
            iCommandSender, this, "commands.scoreboard.teams.join.success", new Object[]{set.size(), text, resolveText(set.toArray(new String[set.size()]))}
         );
      }

      if (!set2.isEmpty()) {
         throw new InternalExceptionAdapter(
            "commands.scoreboard.teams.join.failure", new Object[]{set2.size(), text, resolveText(set2.toArray(new String[set2.size()]))}
         );
      }
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return !textArray[0].equalsIgnoreCase("players")
         ? (textArray[0].equalsIgnoreCase("teams") ? count == 2 : false)
         : (textArray.length > 1 && textArray[1].equalsIgnoreCase("operation") ? count == 2 || count == 5 : count == 2);
   }

   public void applyICommandSender(ICommandSender iCommandSender, String text) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      ScoreboardData scoreboardData = this.resolveScoreboardData(text, false);
      thePlayerNameManager.applyScoreboardData3(scoreboardData);
      applyICommandSender(iCommandSender, this, "commands.scoreboard.objectives.remove.success", new Object[]{text});
   }

   public List getList() {
      Collection collection = this.getThePlayerNameManager().valuesAsCollection();
      ArrayList list = Lists.newArrayList();

      for (ScoreboardData scoreboardData : (java.lang.Iterable<ScoreboardData>) (collection)) {
         if (scoreboardData.getHealthHandler() == HealthHandler.healthHandler6) {
            list.add(scoreboardData.getLabelText2());
         }
      }

      return list;
   }

   public List resolveList(boolean flag) {
      Collection collection = this.getThePlayerNameManager().valuesAsCollection();
      ArrayList list = Lists.newArrayList();

      for (ScoreboardData scoreboardData : (java.lang.Iterable<ScoreboardData>) (collection)) {
         if (!flag || !scoreboardData.getHealthHandler().isActive()) {
            list.add(scoreboardData.getLabelText2());
         }
      }

      return list;
   }

   public void applyICommandSenderByICommandSender(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      if (textArray.length > count) {
         ScorePlayerTeam scorePlayerTeam = this.resolveScorePlayerTeam(textArray[count]);
         if (scorePlayerTeam == null) {
            return;
         }

         Collection collection = scorePlayerTeam.getCollection();
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, collection.size());
         if (collection.size() <= 0) {
            throw new InternalExceptionAdapter("commands.scoreboard.teams.list.player.empty", new Object[]{scorePlayerTeam.getLabelText2()});
         }

         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
            "commands.scoreboard.teams.list.player.count", new Object[]{collection.size(), scorePlayerTeam.getLabelText2()}
         );
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
         iCommandSender.applyIChatComponent(new ChatComponentText(resolveText(collection.toArray())));
      } else {
         Collection values = thePlayerNameManager.values();
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, values.size());
         if (values.size() <= 0) {
            throw new InternalExceptionAdapter("commands.scoreboard.teams.list.empty", new Object[0]);
         }

         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(
            "commands.scoreboard.teams.list.count", new Object[]{values.size()}
         );
         baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);

         for (ScorePlayerTeam scorePlayerTeam2 : (java.lang.Iterable<ScorePlayerTeam>) (values)) {
            iCommandSender.applyIChatComponent(
               new BaseManagerStyleIChatComponentAdapter(
                  "commands.scoreboard.teams.list.entry", new Object[]{scorePlayerTeam2.getLabelText2(), scorePlayerTeam2.getText2(), scorePlayerTeam2.getCollection().size()}
               )
            );
         }
      }
   }

   public void applyICommandSender8(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = textArray[count];
      int count3 = ThePlayerNameManager.computeCount(text);
      ScoreboardData scoreboardData = null;
      if (textArray.length == 4) {
         scoreboardData = this.resolveScoreboardData(textArray[count2], false);
      }

      if (count3 < 0) {
         throw new InternalExceptionAdapter("commands.scoreboard.objectives.setdisplay.invalidSlot", new Object[]{text});
      } else {
         thePlayerNameManager.applyScoreboardData(count3, scoreboardData);
         if (scoreboardData != null) {
            applyICommandSender(
               iCommandSender, this, "commands.scoreboard.objectives.setdisplay.successSet", new Object[]{ThePlayerNameManager.resolveText(count3), scoreboardData.getLabelText2()}
            );
         } else {
            applyICommandSender(iCommandSender, this, "commands.scoreboard.objectives.setdisplay.successCleared", new Object[]{ThePlayerNameManager.resolveText(count3)});
         }
      }
   }

   public ScorePlayerTeam resolveScorePlayerTeam(String text) throws InternalExceptionAdapter {
      ScorePlayerTeam byText = this.getThePlayerNameManager().getByText(text);
      if (byText == null) {
         throw new InternalExceptionAdapter("commands.scoreboard.teamNotFound", new Object[]{text});
      } else {
         return byText;
      }
   }

   public void applyICommandSender9(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      int count2 = count;
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      HashSet set = Sets.newHashSet();
      HashSet set2 = Sets.newHashSet();
      if (iCommandSender instanceof EntityPlayer && count == textArray.length) {
         String text = resolveEntityPlayerAdapter(iCommandSender).getText();
         if (thePlayerNameManager.matchesText(text)) {
            set.add(text);
         } else {
            set2.add(text);
         }
      } else {
         while (count2 < textArray.length) {
            String text2 = textArray[count2++];
            if (text2.startsWith("@")) {
               for (Entity entity : (java.lang.Iterable<Entity>) (resolveList(iCommandSender, text2))) {
                  String text3 = resolveTextByICommandSender(iCommandSender, entity.getEntityUniqueID().toString());
                  if (thePlayerNameManager.matchesText(text3)) {
                     set.add(text3);
                  } else {
                     set2.add(text3);
                  }
               }
            } else {
               String text4 = resolveTextByICommandSender(iCommandSender, text2);
               if (thePlayerNameManager.matchesText(text4)) {
                  set.add(text4);
               } else {
                  set2.add(text4);
               }
            }
         }
      }

      if (!set.isEmpty()) {
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, set.size());
         applyICommandSender(
            iCommandSender, this, "commands.scoreboard.teams.leave.success", new Object[]{set.size(), resolveText(set.toArray(new String[set.size()]))}
         );
      }

      if (!set2.isEmpty()) {
         throw new InternalExceptionAdapter(
            "commands.scoreboard.teams.leave.failure", new Object[]{set2.size(), resolveText(set2.toArray(new String[set2.size()]))}
         );
      }
   }

   @Override
   public int getCount() {
      return 2;
   }

   public ThePlayerNameManager getThePlayerNameManager() {
      return VanillaRunnable.getVanillaRunnable().resolveWorldServer(0).getThePlayerNameManager();
   }

   public void applyICommandSender10(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = resolveTextByICommandSender(iCommandSender, textArray[count]);
      if (text.length() > 40) {
         throw new AimEngine("commands.scoreboard.players.name.tooLong", new Object[]{text, 40});
      } else {
         ScoreboardData scoreboardData = this.resolveScoreboardData(textArray[count2++], false);
         if (!thePlayerNameManager.matchesText(text, scoreboardData)) {
            throw new InternalExceptionAdapter("commands.scoreboard.players.test.notFound", new Object[]{scoreboardData.getLabelText2(), text});
         } else {
            int count3 = textArray[count2].equals("*") ? Integer.MIN_VALUE : parseInt(textArray[count2]);
            count2++;
            int count4 = count2 < textArray.length && !textArray[count2].equals("*") ? computeCount(textArray[count2], count3) : Integer.MAX_VALUE;
            ComparatorEntry comparatorEntry = thePlayerNameManager.getComparatorEntry(text, scoreboardData);
            if (comparatorEntry.getIndex() >= count3 && comparatorEntry.getIndex() <= count4) {
               applyICommandSender(iCommandSender, this, "commands.scoreboard.players.test.success", new Object[]{comparatorEntry.getIndex(), count3, count4});
            } else {
               throw new InternalExceptionAdapter("commands.scoreboard.players.test.failed", new Object[]{comparatorEntry.getIndex(), count3, count4});
            }
         }
      }
   }

   public void applyICommandSender3(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = resolveTextByICommandSender(iCommandSender, textArray[count]);
      if (textArray.length > count2) {
         ScoreboardData scoreboardData = this.resolveScoreboardData(textArray[count2++], false);
         thePlayerNameManager.applyText(text, scoreboardData);
         applyICommandSender(iCommandSender, this, "commands.scoreboard.players.resetscore.success", new Object[]{scoreboardData.getLabelText2(), text});
      } else {
         thePlayerNameManager.applyText(text, (ScoreboardData)null);
         applyICommandSender(iCommandSender, this, "commands.scoreboard.players.reset.success", new Object[]{text});
      }
   }

   public ScoreboardData resolveScoreboardData(String text, boolean flag) throws InternalExceptionAdapter {
      ScoreboardData scoreboardData = this.getThePlayerNameManager().get(text);
      if (scoreboardData == null) {
         throw new InternalExceptionAdapter("commands.scoreboard.objectiveNotFound", new Object[]{text});
      } else if (flag && scoreboardData.getHealthHandler().isActive()) {
         throw new InternalExceptionAdapter("commands.scoreboard.objectiveReadOnly", new Object[]{text});
      } else {
         return scoreboardData;
      }
   }

   public void applyICommandSender11(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      if (textArray.length > count) {
         String text = resolveTextByICommandSender(iCommandSender, textArray[count]);
         Map map = thePlayerNameManager.resolveMap(text);
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, map.size());
         if (map.size() <= 0) {
            throw new InternalExceptionAdapter("commands.scoreboard.players.list.player.empty", new Object[]{text});
         }

         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(
            "commands.scoreboard.players.list.player.count", new Object[]{map.size(), text}
         );
         baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);

         for (ComparatorEntry comparatorEntry : (java.lang.Iterable<ComparatorEntry>) (map.values())) {
            iCommandSender.applyIChatComponent(
               new BaseManagerStyleIChatComponentAdapter(
                  "commands.scoreboard.players.list.player.entry",
                  new Object[]{comparatorEntry.getIndex(), comparatorEntry.getScoreboardData().getLabelText(), comparatorEntry.getScoreboardData().getLabelText2()}
               )
            );
         }
      } else {
         Collection collection = thePlayerNameManager.keySetAsCollection();
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.QUERY_RESULT, collection.size());
         if (collection.size() <= 0) {
            throw new InternalExceptionAdapter("commands.scoreboard.players.list.empty", new Object[0]);
         }

         BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter2 = new BaseManagerStyleIChatComponentAdapter(
            "commands.scoreboard.players.list.count", new Object[]{collection.size()}
         );
         baseManagerStyleIChatComponentAdapter2.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.DARK_GREEN);
         iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter2);
         iCommandSender.applyIChatComponent(new ChatComponentText(resolveText(collection.toArray())));
      }
   }

   public void applyICommandSender14(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      ScorePlayerTeam scorePlayerTeam = this.resolveScorePlayerTeam(textArray[count]);
      if (scorePlayerTeam != null) {
         ArrayList list = Lists.newArrayList(scorePlayerTeam.getCollection());
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new InternalExceptionAdapter("commands.scoreboard.teams.empty.alreadyEmpty", new Object[]{scorePlayerTeam.getLabelText2()});
         }

         for (String text : (java.lang.Iterable<String>) (list)) {
            thePlayerNameManager.applyText(text, scorePlayerTeam);
         }

         applyICommandSender(iCommandSender, this, "commands.scoreboard.teams.empty.success", new Object[]{list.size(), scorePlayerTeam.getLabelText2()});
      }
   }

   public void applyICommandSender7(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      int count2 = count + 1;
      String text = textArray[count];
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      if (thePlayerNameManager.getByText(text) != null) {
         throw new InternalExceptionAdapter("commands.scoreboard.teams.add.alreadyExists", new Object[]{text});
      } else if (text.length() > 16) {
         throw new AimEngine("commands.scoreboard.teams.add.tooLong", new Object[]{text, 16});
      } else if (text.length() == 0) {
         throw new AimDispatcher("commands.scoreboard.teams.add.usage", new Object[0]);
      } else {
         if (textArray.length > count2) {
            String string = resolveIChatComponent(iCommandSender, textArray, count2).getString();
            if (string.length() > 32) {
               throw new AimEngine("commands.scoreboard.teams.add.displayTooLong", new Object[]{string, 32});
            }

            if (string.length() > 0) {
               thePlayerNameManager.resolveScorePlayerTeam(text).applyText2(string);
            } else {
               thePlayerNameManager.resolveScorePlayerTeam(text);
            }
         } else {
            thePlayerNameManager.resolveScorePlayerTeam(text);
         }

         applyICommandSender(iCommandSender, this, "commands.scoreboard.teams.add.success", new Object[]{text});
      }
   }

   public void applyICommandSender6(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      int count2 = count + 1;
      String text = textArray[count];
      String text2 = textArray[count2++];
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      HealthHandler healthHandler = (HealthHandler)HealthHandler.map.get(text2);
      if (healthHandler == null) {
         throw new AimDispatcher("commands.scoreboard.objectives.add.wrongType", new Object[]{text2});
      } else if (thePlayerNameManager.get(text) != null) {
         throw new InternalExceptionAdapter("commands.scoreboard.objectives.add.alreadyExists", new Object[]{text});
      } else if (text.length() > 16) {
         throw new AimEngine("commands.scoreboard.objectives.add.tooLong", new Object[]{text, 16});
      } else if (text.length() == 0) {
         throw new AimDispatcher("commands.scoreboard.objectives.add.usage", new Object[0]);
      } else {
         if (textArray.length > count2) {
            String string = resolveIChatComponent(iCommandSender, textArray, count2).getString();
            if (string.length() > 32) {
               throw new AimEngine("commands.scoreboard.objectives.add.displayTooLong", new Object[]{string, 32});
            }

            if (string.length() > 0) {
               thePlayerNameManager.resolveScoreboardData(text, healthHandler).applyText(string);
            } else {
               thePlayerNameManager.resolveScoreboardData(text, healthHandler);
            }
         } else {
            thePlayerNameManager.resolveScoreboardData(text, healthHandler);
         }

         applyICommandSender(iCommandSender, this, "commands.scoreboard.objectives.add.success", new Object[]{text});
      }
   }

   public void applyICommandSender4(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      String text = textArray[count - 1];
      int count2 = count + 1;
      String text2 = resolveTextByICommandSender(iCommandSender, textArray[count]);
      if (text2.length() > 40) {
         throw new AimEngine("commands.scoreboard.players.name.tooLong", new Object[]{text2, 40});
      } else {
         ScoreboardData scoreboardData = this.resolveScoreboardData(textArray[count2++], true);
         int count3 = text.equalsIgnoreCase("set") ? parseInt(textArray[count2++]) : computeCount(textArray[count2++], 0);
         if (textArray.length > count2) {
            Entity entity = resolveEntity(iCommandSender, textArray[count]);

            try {
               NBTTagCompound tagCompound = DampenerEstimator.resolveNBTTagCompound(resolveText(textArray, count2));
               NBTTagCompound tagCompound2 = new NBTTagCompound();
               entity.applyNBTTagCompound3(tagCompound2);
               if (!AuxiliaryNameManager.matchesLISTManager(tagCompound, tagCompound2, true)) {
                  throw new InternalExceptionAdapter("commands.scoreboard.players.set.tagMismatch", new Object[]{text2});
               }
            } catch (SecondaryExceptionAdapter exception) {
               throw new InternalExceptionAdapter("commands.scoreboard.players.set.tagError", new Object[]{exception.getMessage()});
            }
         }

         ComparatorEntry comparatorEntry = this.getThePlayerNameManager().getComparatorEntry(text2, scoreboardData);
         if (text.equalsIgnoreCase("set")) {
            comparatorEntry.applyIndex(count3);
         } else if (text.equalsIgnoreCase("add")) {
            comparatorEntry.applyIndexByIndex(count3);
         } else {
            comparatorEntry.applyIndex2(count3);
         }

         applyICommandSender(iCommandSender, this, "commands.scoreboard.players.set.success", new Object[]{scoreboardData.getLabelText2(), text2, comparatorEntry.getIndex()});
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.scoreboard.usage";
   }

   public void applyICommandSender13(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = resolveText(iCommandSender, textArray[count]);
      if (text.length() > 40) {
         throw new AimEngine("commands.scoreboard.players.name.tooLong", new Object[]{text, 40});
      } else {
         ScoreboardData scoreboardData = this.resolveScoreboardData(textArray[count2], false);
         if (scoreboardData.getHealthHandler() != HealthHandler.healthHandler6) {
            throw new InternalExceptionAdapter("commands.scoreboard.players.enable.noTrigger", new Object[]{scoreboardData.getLabelText2()});
         } else {
            thePlayerNameManager.getComparatorEntry(text, scoreboardData).setEnabled(false);
            applyICommandSender(iCommandSender, this, "commands.scoreboard.players.enable.success", new Object[]{scoreboardData.getLabelText2(), text});
         }
      }
   }

   public void applyICommandSender2(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      ScorePlayerTeam scorePlayerTeam = this.resolveScorePlayerTeam(textArray[count]);
      if (scorePlayerTeam != null) {
         thePlayerNameManager.applyScorePlayerTeam(scorePlayerTeam);
         applyICommandSender(iCommandSender, this, "commands.scoreboard.teams.remove.success", new Object[]{scorePlayerTeam.getLabelText2()});
      }
   }

   private boolean matchesICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      int count = -1;

      for (int index = 0; index < textArray.length; index++) {
         if (this.matchesTextArray(textArray, index) && "*".equals(textArray[index])) {
            if (count >= 0) {
               throw new InternalExceptionAdapter("commands.scoreboard.noMultiWildcard", new Object[0]);
            }

            count = index;
         }
      }

      if (count < 0) {
         return false;
      } else {
         ArrayList list = Lists.newArrayList(this.getThePlayerNameManager().keySetAsCollection());
         String text = textArray[count];
         ArrayList list2 = Lists.newArrayList();

         for (String text2 : (java.lang.Iterable<String>) (list)) {
            textArray[count] = text2;

            try {
               this.applyICommandSender(iCommandSender, textArray);
               list2.add(text2);
            } catch (InternalExceptionAdapter exception) {
               BaseManagerStyleIChatComponentAdapter baseManagerStyleIChatComponentAdapter = new BaseManagerStyleIChatComponentAdapter(exception.getMessage(), exception.getObjectArray());
               baseManagerStyleIChatComponentAdapter.getStyleHasParentManager().withClientSettingsBlackType(ClientSettingsBlackType.RED);
               iCommandSender.applyIChatComponent(baseManagerStyleIChatComponentAdapter);
            }
         }

         textArray[count] = text;
         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, list2.size());
         if (list2.size() == 0) {
            throw new AimDispatcher("commands.scoreboard.allMatchesFailed", new Object[0]);
         } else {
            return true;
         }
      }
   }

   public void applyICommandSender12(ICommandSender iCommandSender, String[] textArray, int count) throws InternalExceptionAdapter {
      ThePlayerNameManager thePlayerNameManager = this.getThePlayerNameManager();
      int count2 = count + 1;
      String text = resolveTextByICommandSender(iCommandSender, textArray[count]);
      ScoreboardData scoreboardData = this.resolveScoreboardData(textArray[count2++], true);
      String text2 = textArray[count2++];
      String text3 = resolveTextByICommandSender(iCommandSender, textArray[count2++]);
      ScoreboardData scoreboardData2 = this.resolveScoreboardData(textArray[count2], false);
      if (text.length() > 40) {
         throw new AimEngine("commands.scoreboard.players.name.tooLong", new Object[]{text, 40});
      } else if (text3.length() > 40) {
         throw new AimEngine("commands.scoreboard.players.name.tooLong", new Object[]{text3, 40});
      } else {
         ComparatorEntry comparatorEntry = thePlayerNameManager.getComparatorEntry(text, scoreboardData);
         if (!thePlayerNameManager.matchesText(text3, scoreboardData2)) {
            throw new InternalExceptionAdapter("commands.scoreboard.players.operation.notFound", new Object[]{scoreboardData2.getLabelText2(), text3});
         } else {
            ComparatorEntry comparatorEntry2 = thePlayerNameManager.getComparatorEntry(text3, scoreboardData2);
            if (text2.equals("+=")) {
               comparatorEntry.applyIndex(comparatorEntry.getIndex() + comparatorEntry2.getIndex());
            } else if (text2.equals("-=")) {
               comparatorEntry.applyIndex(comparatorEntry.getIndex() - comparatorEntry2.getIndex());
            } else if (text2.equals("*=")) {
               comparatorEntry.applyIndex(comparatorEntry.getIndex() * comparatorEntry2.getIndex());
            } else if (text2.equals("/=")) {
               if (comparatorEntry2.getIndex() != 0) {
                  comparatorEntry.applyIndex(comparatorEntry.getIndex() / comparatorEntry2.getIndex());
               }
            } else if (text2.equals("%=")) {
               if (comparatorEntry2.getIndex() != 0) {
                  comparatorEntry.applyIndex(comparatorEntry.getIndex() % comparatorEntry2.getIndex());
               }
            } else if (text2.equals("=")) {
               comparatorEntry.applyIndex(comparatorEntry2.getIndex());
            } else if (text2.equals("<")) {
               comparatorEntry.applyIndex(Math.min(comparatorEntry.getIndex(), comparatorEntry2.getIndex()));
            } else if (text2.equals(">")) {
               comparatorEntry.applyIndex(Math.max(comparatorEntry.getIndex(), comparatorEntry2.getIndex()));
            } else {
               if (!text2.equals("><")) {
                  throw new InternalExceptionAdapter("commands.scoreboard.players.operation.invalidOperation", new Object[]{text2});
               }

               int index = comparatorEntry.getIndex();
               comparatorEntry.applyIndex(comparatorEntry2.getIndex());
               comparatorEntry2.applyIndex(index);
            }

            applyICommandSender(iCommandSender, this, "commands.scoreboard.players.operation.success", new Object[0]);
         }
      }
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (!this.matchesICommandSender(iCommandSender, textArray)) {
         if (textArray.length < 1) {
            throw new AimDispatcher("commands.scoreboard.usage", new Object[0]);
         }

         if (textArray[0].equalsIgnoreCase("objectives")) {
            if (textArray.length == 1) {
               throw new AimDispatcher("commands.scoreboard.objectives.usage", new Object[0]);
            }

            if (textArray[1].equalsIgnoreCase("list")) {
               this.applyICommandSender(iCommandSender);
            } else if (textArray[1].equalsIgnoreCase("add")) {
               if (textArray.length < 4) {
                  throw new AimDispatcher("commands.scoreboard.objectives.add.usage", new Object[0]);
               }

               this.applyICommandSender6(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("remove")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.scoreboard.objectives.remove.usage", new Object[0]);
               }

               this.applyICommandSender(iCommandSender, textArray[2]);
            } else {
               if (!textArray[1].equalsIgnoreCase("setdisplay")) {
                  throw new AimDispatcher("commands.scoreboard.objectives.usage", new Object[0]);
               }

               if (textArray.length != 3 && textArray.length != 4) {
                  throw new AimDispatcher("commands.scoreboard.objectives.setdisplay.usage", new Object[0]);
               }

               this.applyICommandSender8(iCommandSender, textArray, 2);
            }
         } else if (textArray[0].equalsIgnoreCase("players")) {
            if (textArray.length == 1) {
               throw new AimDispatcher("commands.scoreboard.players.usage", new Object[0]);
            }

            if (textArray[1].equalsIgnoreCase("list")) {
               if (textArray.length > 3) {
                  throw new AimDispatcher("commands.scoreboard.players.list.usage", new Object[0]);
               }

               this.applyICommandSender11(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("add")) {
               if (textArray.length < 5) {
                  throw new AimDispatcher("commands.scoreboard.players.add.usage", new Object[0]);
               }

               this.applyICommandSender4(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("remove")) {
               if (textArray.length < 5) {
                  throw new AimDispatcher("commands.scoreboard.players.remove.usage", new Object[0]);
               }

               this.applyICommandSender4(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("set")) {
               if (textArray.length < 5) {
                  throw new AimDispatcher("commands.scoreboard.players.set.usage", new Object[0]);
               }

               this.applyICommandSender4(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("reset")) {
               if (textArray.length != 3 && textArray.length != 4) {
                  throw new AimDispatcher("commands.scoreboard.players.reset.usage", new Object[0]);
               }

               this.applyICommandSender3(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("enable")) {
               if (textArray.length != 4) {
                  throw new AimDispatcher("commands.scoreboard.players.enable.usage", new Object[0]);
               }

               this.applyICommandSender13(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("test")) {
               if (textArray.length != 5 && textArray.length != 6) {
                  throw new AimDispatcher("commands.scoreboard.players.test.usage", new Object[0]);
               }

               this.applyICommandSender10(iCommandSender, textArray, 2);
            } else {
               if (!textArray[1].equalsIgnoreCase("operation")) {
                  throw new AimDispatcher("commands.scoreboard.players.usage", new Object[0]);
               }

               if (textArray.length != 7) {
                  throw new AimDispatcher("commands.scoreboard.players.operation.usage", new Object[0]);
               }

               this.applyICommandSender12(iCommandSender, textArray, 2);
            }
         } else {
            if (!textArray[0].equalsIgnoreCase("teams")) {
               throw new AimDispatcher("commands.scoreboard.usage", new Object[0]);
            }

            if (textArray.length == 1) {
               throw new AimDispatcher("commands.scoreboard.teams.usage", new Object[0]);
            }

            if (textArray[1].equalsIgnoreCase("list")) {
               if (textArray.length > 3) {
                  throw new AimDispatcher("commands.scoreboard.teams.list.usage", new Object[0]);
               }

               this.applyICommandSenderByICommandSender(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("add")) {
               if (textArray.length < 3) {
                  throw new AimDispatcher("commands.scoreboard.teams.add.usage", new Object[0]);
               }

               this.applyICommandSender7(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("remove")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.scoreboard.teams.remove.usage", new Object[0]);
               }

               this.applyICommandSender2(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("empty")) {
               if (textArray.length != 3) {
                  throw new AimDispatcher("commands.scoreboard.teams.empty.usage", new Object[0]);
               }

               this.applyICommandSender14(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("join")) {
               if (textArray.length < 4 && (textArray.length != 3 || !(iCommandSender instanceof EntityPlayer))) {
                  throw new AimDispatcher("commands.scoreboard.teams.join.usage", new Object[0]);
               }

               this.applyICommandSender(iCommandSender, textArray, 2);
            } else if (textArray[1].equalsIgnoreCase("leave")) {
               if (textArray.length < 3 && !(iCommandSender instanceof EntityPlayer)) {
                  throw new AimDispatcher("commands.scoreboard.teams.leave.usage", new Object[0]);
               }

               this.applyICommandSender9(iCommandSender, textArray, 2);
            } else {
               if (!textArray[1].equalsIgnoreCase("option")) {
                  throw new AimDispatcher("commands.scoreboard.teams.usage", new Object[0]);
               }

               if (textArray.length != 4 && textArray.length != 5) {
                  throw new AimDispatcher("commands.scoreboard.teams.option.usage", new Object[0]);
               }

               this.applyICommandSender5(iCommandSender, textArray, 2);
            }
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      if (textArray.length == 1) {
         return resolveList(textArray, new String[]{"objectives", "players", "teams"});
      } else {
         if (textArray[0].equalsIgnoreCase("objectives")) {
            if (textArray.length == 2) {
               return resolveList(textArray, new String[]{"list", "add", "remove", "setdisplay"});
            }

            if (textArray[1].equalsIgnoreCase("add")) {
               if (textArray.length == 4) {
                  Set keys = HealthHandler.map.keySet();
                  return resolveList(textArray, keys);
               }
            } else if (textArray[1].equalsIgnoreCase("remove")) {
               if (textArray.length == 3) {
                  return resolveList(textArray, this.resolveList(false));
               }
            } else if (textArray[1].equalsIgnoreCase("setdisplay")) {
               if (textArray.length == 3) {
                  return resolveList(textArray, ThePlayerNameManager.getTextArray());
               }

               if (textArray.length == 4) {
                  return resolveList(textArray, this.resolveList(false));
               }
            }
         } else if (textArray[0].equalsIgnoreCase("players")) {
            if (textArray.length == 2) {
               return resolveList(textArray, new String[]{"set", "add", "remove", "reset", "list", "enable", "test", "operation"});
            }

            if (!textArray[1].equalsIgnoreCase("set")
               && !textArray[1].equalsIgnoreCase("add")
               && !textArray[1].equalsIgnoreCase("remove")
               && !textArray[1].equalsIgnoreCase("reset")) {
               if (textArray[1].equalsIgnoreCase("enable")) {
                  if (textArray.length == 3) {
                     return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
                  }

                  if (textArray.length == 4) {
                     return resolveList(textArray, this.getList());
                  }
               } else if (!textArray[1].equalsIgnoreCase("list") && !textArray[1].equalsIgnoreCase("test")) {
                  if (textArray[1].equalsIgnoreCase("operation")) {
                     if (textArray.length == 3) {
                        return resolveList(textArray, this.getThePlayerNameManager().keySetAsCollection());
                     }

                     if (textArray.length == 4) {
                        return resolveList(textArray, this.resolveList(true));
                     }

                     if (textArray.length == 5) {
                        return resolveList(textArray, new String[]{"+=", "-=", "*=", "/=", "%=", "=", "<", ">", "><"});
                     }

                     if (textArray.length == 6) {
                        return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
                     }

                     if (textArray.length == 7) {
                        return resolveList(textArray, this.resolveList(false));
                     }
                  }
               } else {
                  if (textArray.length == 3) {
                     return resolveList(textArray, this.getThePlayerNameManager().keySetAsCollection());
                  }

                  if (textArray.length == 4 && textArray[1].equalsIgnoreCase("test")) {
                     return resolveList(textArray, this.resolveList(false));
                  }
               }
            } else {
               if (textArray.length == 3) {
                  return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
               }

               if (textArray.length == 4) {
                  return resolveList(textArray, this.resolveList(true));
               }
            }
         } else if (textArray[0].equalsIgnoreCase("teams")) {
            if (textArray.length == 2) {
               return resolveList(textArray, new String[]{"add", "remove", "join", "leave", "empty", "list", "option"});
            }

            if (textArray[1].equalsIgnoreCase("join")) {
               if (textArray.length == 3) {
                  return resolveList(textArray, this.getThePlayerNameManager().keySet());
               }

               if (textArray.length >= 4) {
                  return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
               }
            } else {
               if (textArray[1].equalsIgnoreCase("leave")) {
                  return resolveList(textArray, VanillaRunnable.getVanillaRunnable().getTextArray());
               }

               if (!textArray[1].equalsIgnoreCase("empty") && !textArray[1].equalsIgnoreCase("list") && !textArray[1].equalsIgnoreCase("remove")) {
                  if (textArray[1].equalsIgnoreCase("option")) {
                     if (textArray.length == 3) {
                        return resolveList(textArray, this.getThePlayerNameManager().keySet());
                     }

                     if (textArray.length == 4) {
                        return resolveList(textArray, new String[]{"color", "friendlyfire", "seeFriendlyInvisibles", "nametagVisibility", "deathMessageVisibility"});
                     }

                     if (textArray.length == 5) {
                        if (textArray[3].equalsIgnoreCase("color")) {
                           return resolveList(textArray, ClientSettingsBlackType.resolveCollection(true, false));
                        }

                        if (textArray[3].equalsIgnoreCase("nametagVisibility") || textArray[3].equalsIgnoreCase("deathMessageVisibility")) {
                           return resolveList(textArray, MovementBooleanProperty.getTextArray());
                        }

                        if (textArray[3].equalsIgnoreCase("friendlyfire") || textArray[3].equalsIgnoreCase("seeFriendlyInvisibles")) {
                           return resolveList(textArray, new String[]{"true", "false"});
                        }
                     }
                  }
               } else if (textArray.length == 3) {
                  return resolveList(textArray, this.getThePlayerNameManager().keySet());
               }
            }
         }

         return null;
      }
   }
}
