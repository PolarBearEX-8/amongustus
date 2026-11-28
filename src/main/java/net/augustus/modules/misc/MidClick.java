package net.augustus.modules.misc;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;
import net.augustus.ClientManagers;
import net.augustus.events.EventClick;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.listeners.TickListener;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.settings.ActionSetting;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.settings.StringSetSetting;
import net.augustus.utils.input.KeyboardInputUtil;
import net.augustus.utils.rotation.RayTraceUtil;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.MovingObjectPosition;

public class MidClick extends Module implements ClickListener, TickListener {
   public HashSet friends = new HashSet();
   private final HashSet partyFriends = new HashSet();
   private final DoubleValue midClickDistance;
   private final BooleanValue ignoreWalls;
   public final BooleanValue autoUnfriend;
   private final BooleanValue partyFriend;
   private final BooleanValue partyFriendGomme;
   public final BooleanValue reverse;
   public final BooleanValue simple;
   private final ActionSetting deleteAllFriends;
   private final StringSetSetting savedFriendsSetting;
   public final Set savedFriendsCache = new HashSet();
   public boolean autoUnfriendTriggered = false;
   public boolean friendsLoaded = false;

   public MidClick() {
      super("MidClick", "Mittelklick", new Color(76, 78, 76), Categorys.MISC);
      this.midClickDistance = (DoubleValue)new DoubleValue(this, "MidClickDistance", 4.5, 3.0, 10.0, 2).markVisualOnly();
      this.ignoreWalls = new BooleanValue(this, "IgnoreWalls", true);
      this.autoUnfriend = (BooleanValue)new BooleanValue(this, "AutoUnfriend", true).markVisualOnly();
      this.partyFriend = (BooleanValue)new BooleanValue(this, "PartyFriend", true).markVisualOnly();
      this.partyFriendGomme = (BooleanValue)((BooleanValue)new BooleanValue(this, "PartyFriendGomme", true).withVisibility(this.partyFriend::getValue)).markVisualOnly();
      this.reverse = (BooleanValue)new BooleanValue(this, "Reverse", false).markVisualOnly();
      this.simple = (BooleanValue)new BooleanValue(this, "Simple", false).markVisualOnly();
      this.deleteAllFriends = (ActionSetting)new ActionSetting(this, "DeleteAllFriends", this::clearAllFriends).markVisualOnly();
      this.savedFriendsSetting = (StringSetSetting)((StringSetSetting)new StringSetSetting(this, "Friends").withChangeListener(this::reloadFriendsFromSetting)).markVisualOnly();
   }

   @Override
   public void onEnable() {
      this.partyFriends.clear();
      this.reloadFriendsFromSetting();
   }

   @Override
   public void onDisable() {
      this.partyFriends.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
   }

   public void clearAllFriends() {
      this.friends.clear();
      this.savedFriendsSetting.clear();
      this.savedFriendsCache.clear();
   }

   public boolean isFriend(Entity entity) {
      return this.isFriend(entity.getName());
   }

   public boolean isFriend(String name, boolean ignoreOnline) {
      if (this.isToggled()) {
         return this.reverse.getValue() != this.friends.contains(name);
      }
      return false;
   }

   public boolean isFriend(String name) {
      return this.isFriend(name, false);
   }

   @Override
   public void onTick() {
      if (!this.friendsLoaded) {
         this.friends.addAll(this.savedFriendsSetting.getSet());
         this.savedFriendsCache.clear();
         this.savedFriendsCache.addAll(this.savedFriendsSetting.getSet());
         this.friendsLoaded = true;
      }

      if (this.partyFriend.getValue()) {
         for (NetworkPlayerInfo info : (Iterable<NetworkPlayerInfo>)mc.thePlayer.sendQueue.getPlayerInfoMap()) {
            String playerName = info.getGameProfile().getName();
            if (!playerName.equals(mc.thePlayer.getGameProfile().getName())) {
               String display = info.getDisplayName() != null
                  ? info.getDisplayName().getUnformattedText()
                  : ScorePlayerTeam.formatPlayerName(info.getPlayerTeam(), playerName);
               if (this.partyFriendGomme.getValue() ? display.contains("§5Party§7") : display.contains("Party") || display.contains("party")) {
                  if (!this.partyFriends.contains(playerName)) {
                     this.friends.add(playerName);
                     this.partyFriends.add(playerName);
                  }
               } else if (this.partyFriends.contains(playerName)) {
                  this.partyFriends.remove(playerName);
                  this.friends.remove(playerName);
               }
            }
         }
      } else {
         for (String name : (Iterable<String>)this.partyFriends) {
            this.friends.remove(name);
         }
         this.partyFriends.clear();
      }

      this.updateAutoUnfriendState();
   }

   private void updateAutoUnfriendState() {
      if (mc.thePlayer == null || mc.theWorld == null) {
         return;
      }
      boolean active = this.isToggled() && this.autoUnfriend.getValue();
      if (!active) {
         this.autoUnfriendTriggered = false;
         return;
      }
      boolean hasNonFriendOpponent = false;
      NetHandlerPlayClient sendQueue = mc.thePlayer.sendQueue;
      for (NetworkPlayerInfo info : (Iterable<NetworkPlayerInfo>)sendQueue.getPlayerInfoMap()) {
         String playerName = info.getGameProfile().getName();
         if (!mc.thePlayer.getName().equals(playerName)
            && !this.friends.contains(playerName)
            && (!ClientManagers.moduleManager.teams.isToggled() || !ClientManagers.moduleManager.teams.isTeammateName(playerName))) {
            hasNonFriendOpponent = true;
            break;
         }
      }
      this.autoUnfriendTriggered = !hasNonFriendOpponent;
   }

   @Override
   public void onClick(EventClick event) {
      if (mc.gameSettings.keyBindPickBlock.getPressTime() > 0) {
         MovingObjectPosition hit = RayTraceUtil.rayTrace(
            1.0F,
            ClientManagers.rotationController.getPreviousRotation().yaw,
            ClientManagers.rotationController.getPreviousRotation().pitch,
            this.midClickDistance.getValue(),
            this.midClickDistance.getValue(),
            0.0,
            this.ignoreWalls.getValue()
         );
         if (hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && hit.entityHit instanceof EntityPlayer) {
            String playerName = hit.entityHit.getName();
            if (this.friends.contains(playerName)) {
               this.friends.remove(playerName);
               this.removeSavedFriend(playerName);
            } else {
               if (KeyboardInputUtil.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode())) {
                  this.saveFriend(playerName);
               }
               this.friends.add(playerName);
            }
         }
      }
   }

   public void saveFriend(String name) {
      this.savedFriendsSetting.updateDisplayName(name);
      this.savedFriendsCache.add(name);
   }

   public void removeSavedFriend(String name) {
      this.savedFriendsSetting.remove(name);
      this.savedFriendsCache.remove(name);
   }

   private void reloadFriendsFromSetting() {
      for (String name : (Iterable<String>)this.savedFriendsCache) {
         this.friends.remove(name);
      }
      this.friendsLoaded = false;
   }
}
