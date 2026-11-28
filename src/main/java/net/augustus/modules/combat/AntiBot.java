package net.augustus.modules.combat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.utils.TextFormatUtil;
import net.augustus.settings.options.AntiBotCheckOption;
import net.augustus.settings.MultiOptionSetting;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class AntiBot extends Module implements PacketListener, TickListener {
   private final HashSet bots;
   private final HashSet temporaryBots;
   private final List groundedPlayers;
   private final List swungPlayers;
   private final List soundPlayers;
   private final List heldItemPlayers;
   private final List armoredPlayers;
   private final MultiOptionSetting mode;
   private final DoubleValue ticksExisted;
   private final BooleanValue distanceCheck;
   private final DoubleValue checkDistance;
   private final BooleanValue blockHits;
   private final BooleanValue debug;

   public AntiBot() {
       super("AntiBot", "GegenFakespieler", Color.BLUE, Categorys.COMBAT);
      this.bots = new HashSet();
      this.temporaryBots = new HashSet();
      this.groundedPlayers = new ArrayList();
      this.swungPlayers = new ArrayList();
      this.soundPlayers = new CopyOnWriteArrayList();
      this.heldItemPlayers = new ArrayList();
      this.armoredPlayers = new ArrayList();
      this.mode = new MultiOptionSetting(this, "Mode", AntiBotCheckOption.values());
      this.ticksExisted = new DoubleValue(this, "TicksExisted", 20.0, 1.0, 100.0, 0).visibleWhen(AntiBotCheckOption.TICKS_EXISTED::isSelected);
      BooleanValue booleanValue = new BooleanValue(this, "DistanceCheck", true);
      SettingVisibilityCondition condition2 = () -> {
         return AntiBotCheckOption.isOptionSelected(AntiBotCheckOption.NEVER_ON_GROUND)
            || AntiBotCheckOption.SWING.isSelected()
            || AntiBotCheckOption.ITEM.isSelected()
            || AntiBotCheckOption.ARMOR.isSelected()
            || AntiBotCheckOption.SOUND.isSelected();
      };
      this.distanceCheck = (BooleanValue)booleanValue.withVisibility(condition2);
      DoubleValue checkDistanceSetting = new DoubleValue(this, "CheckDistance", 10.0, 6.0, 40.0, 0);
      SettingVisibilityCondition condition3 = () -> {
         return this.distanceCheck.getValue() && this.distanceCheck.isVisible();
      };
      this.checkDistance = checkDistanceSetting.visibleWhen(condition3);
      this.blockHits = new BooleanValue(this, "BlockHits", true);
      this.debug = new BooleanValue(this, "Debug", false);
   }

   @Override
   public void onEnable() {
      this.clearTrackedPlayers();
   }

   @Override
   public void onDisable() {
      this.clearTrackedPlayers();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onTick() {
      int size = this.bots.size();

      for (Entity entity : (Iterable<Entity>)(mc.theWorld.loadedEntityList)) {
         if (entity instanceof EntityPlayer && entity != mc.thePlayer && mc.thePlayer.ticksExisted > 5) {
            if (this.checkPlayerIsBot((EntityPlayer)entity)) {
               this.bots.add(entity);
            } else if (this.temporaryBots.contains(entity)) {
               this.bots.remove(entity);
               this.temporaryBots.remove(entity);
            }
         }
      }

      if (this.bots.size() > size && this.debug.getValue()) {
         this.sendChatMessage("Removed bot :)");
      }

      this.bots.removeIf(obj -> {
         return !mc.theWorld.loadedEntityList.contains(obj);
      });
      this.temporaryBots.removeIf(obj2 -> {
         return !mc.theWorld.loadedEntityList.contains(obj2);
      });
   }

   public boolean isBot(Entity entity) {
      return this.isToggled() && this.bots.contains(entity);
   }

   private boolean checkPlayerIsBot(EntityPlayer player) {
      NetworkPlayerInfo playerInfo = mc.getNetHandler().getPlayerInfo(player.getUniqueID());
      if (AntiBotCheckOption.ADVANCED.isSelected()) {
         if (mc.thePlayer.ticksExisted > 110) {
            if (player.getCustomNameTag() == "") {
               return true;
            }
         } else {
            this.markTemporaryBot(player);
         }
      }

      if (AntiBotCheckOption.ILLEGAL_PITCH.isSelected()) {
         if (Math.abs(player.rotationPitch) > 90.0F) {
            return true;
         }
      }

      if (AntiBotCheckOption.NULL_TEAM.isSelected() && player.getTeam() == null) {
         return true;
      } else if (AntiBotCheckOption.NEGATIVE_HEALTH.isSelected() && player.getCurrentHealth() < 0.0F) {
         return true;
      } else if (!AntiBotCheckOption.INVALID_ENTITY_ID.isSelected() || player.getEntityId() >= 0 && player.getEntityId() <= 999999999) {
         if (AntiBotCheckOption.INTAVE.isSelected() && playerInfo != null) {
            ScorePlayerTeam playerTeam = playerInfo.getPlayerTeam();
            if (playerTeam != null && playerTeam.getTeamName().equals("")) {
               return true;
            }
         }

         if (AntiBotCheckOption.HYPIXEL.isSelected() && playerInfo != null && playerInfo.getResponseTime() != 1) {
            return true;
         } else if (AntiBotCheckOption.ZERO_PING.isSelected() && player.ticksExisted > 5 && playerInfo != null && playerInfo.getResponseTime() == 0) {
            return true;
         } else if (AntiBotCheckOption.TAB_LIST.isSelected() && playerInfo == null && player.ticksExisted > 20) {
            this.markTemporaryBot(player);
            return true;
         } else if (AntiBotCheckOption.INVALID_NAME.isSelected()) {
            return !TextFormatUtil.isValidMinecraftUsername(player.getName());
         } else {
            if (AntiBotCheckOption.SWING.isSelected()) {
               if (player.isSwingInProgress && !this.swungPlayers.contains(player)) {
                  this.swungPlayers.add(player);
               }

               if (!this.swungPlayers.contains(player) && this.isWithinCheckDistance(player)) {
                  this.markTemporaryBot(player);
                  return true;
               }
            }

            if (AntiBotCheckOption.NEVER_ON_GROUND.isSelected()) {
               if (this.isPlayerOnGround(player) && !this.groundedPlayers.contains(player)) {
                  this.groundedPlayers.add(player);
               }

               if (!this.groundedPlayers.contains(player) && this.isWithinCheckDistance(player)) {
                  this.markTemporaryBot(player);
                  return true;
               }
            }

            if (AntiBotCheckOption.ITEM.isSelected()) {
               if (player.getHeldItem() != null && !this.heldItemPlayers.contains(player)) {
                  this.heldItemPlayers.add(player);
               }

               if (!this.heldItemPlayers.contains(player) && this.isWithinCheckDistance(player)) {
                  this.markTemporaryBot(player);
                  return true;
               }
            }

            if (AntiBotCheckOption.ARMOR.isSelected()) {
               if (this.hasArmorEquipped(player) && !this.armoredPlayers.contains(player)) {
                  this.armoredPlayers.add(player);
               }

               if (!this.armoredPlayers.contains(player) && this.isWithinCheckDistance(player)) {
                  this.markTemporaryBot(player);
                  return true;
               }
            }

            if (AntiBotCheckOption.SOUND.isSelected() && !this.soundPlayers.contains(player) && this.isWithinCheckDistance(player)) {
               this.markTemporaryBot(player);
               return true;
            } else if (AntiBotCheckOption.TICKS_EXISTED.isSelected() && (double)player.ticksExisted < this.ticksExisted.getValue()) {
               this.markTemporaryBot(player);
               return true;
            } else {
               return false;
            }
         }
      } else {
         return true;
      }
   }

   private boolean isWithinCheckDistance(EntityPlayer player) {
      return !this.distanceCheck.getValue() || (double)mc.thePlayer.getDistanceToEntity(player) < this.checkDistance.getValue();
   }

   private boolean isPlayerOnGround(EntityPlayer player) {
      return !player.entityWorld.getCollidingBoundingBoxesCapped(player, player.getEntityBoundingBox().offset(0.0, -0.01, 0.0)).isEmpty();
   }

   private void markTemporaryBot(EntityPlayer player) {
      this.temporaryBots.add(player);
   }

   private boolean hasArmorEquipped(EntityPlayer player) {
      return player.inventory.armorInventory[3] != null
         || player.inventory.armorInventory[2] != null
         || player.inventory.armorInventory[1] != null
         || player.inventory.armorInventory[0] != null;
   }

   private void clearTrackedPlayers() {
      this.bots.clear();
      this.temporaryBots.clear();
      this.groundedPlayers.clear();
      this.heldItemPlayers.clear();
      this.armoredPlayers.clear();
      this.soundPlayers.clear();
      this.swungPlayers.clear();
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      Packet packet = event.getPacket();
      if (packet instanceof S07PacketRespawn) {
         this.clearTrackedPlayers();
      }

      if (packet instanceof S29PacketSoundEffect && mc.theWorld != null) {
         S29PacketSoundEffect packet2 = (S29PacketSoundEffect)packet;
         if (!packet2.getSoundName().contains("step")) {
            return;
         }

         EntityPlayer closestPlayer = mc.theWorld.getClosestPlayer(packet2.getX(), packet2.getY(), packet2.getZ(), 1.0);
         if (closestPlayer != null && !this.soundPlayers.contains(closestPlayer)) {
            this.soundPlayers.add(closestPlayer);
         }
      }
   }


}
