package net.augustus.modules.player;

import net.minecraft.entity.Entity;

import java.awt.Color;
import java.util.LinkedList;
import java.util.stream.Stream;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.listeners.ClickListener;
import net.augustus.events.EventKey;
import net.augustus.utils.rotation.RotationUtil;
import net.augustus.ClientManagers;
import net.augustus.events.listeners.SilentSlotListener;
import net.augustus.events.EventClick;
import net.augustus.settings.KeybindSetting;
import net.augustus.events.listeners.KeyListener;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.rotation.RotationController;
import net.augustus.utils.world.BlockUtil;
import net.augustus.utils.player.PlayerUtil;
import net.augustus.utils.simulation.ProjectileSimulator;
import net.augustus.events.listeners.TickListener;
import net.augustus.utils.player.SilentSlotManager;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.utils.rotation.Rotation;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public class AutoEnderPearl extends Module implements ClickListener, TickListener, PacketListener, SilentSlotListener, KeyListener, LoadWorldListener {
   private final BooleanValue pearlOnKeyPressed;
   private final KeybindSetting keyToThrowPearl;
   private final BooleanValue autoDisable;
   private final BooleanValue autoAirstuck;
   private final BooleanValue unstuckIfCombatPearlDidnTLand;
   private final BooleanValue onlyIfPlayerIsFallingIntoTheVoid;
   private final BooleanValue avoidLandingNearOtherPlayers;
   private final DoubleValue minFallDistanceIntoVoid;
   private final BooleanValue silentSlotSwitching;
   private int pearlHotbarSlot;
   private boolean readyToThrow;
   private boolean airStuckEnabled;
   private boolean pearlThrown;
   private boolean receivedTeleportPacket;
   private long lastSearchTimeMs;
   private boolean pendingAirStuck;
   private final TimeHelper keyPressTimer;
   private final TimeHelper throwTimer;
   private final LinkedList recentSafePositions;

   public AutoEnderPearl() {
       super("AutoEnderPearl", "AutomatischeEnderperle", new Color(155, 66, 245), Categorys.PLAYER);
      this.pearlOnKeyPressed = new BooleanValue(this, "Pearl on key pressed", true);
      KeybindSetting keybindSetting = new KeybindSetting(this, "Key to throw pearl");
      BooleanValue pearlOnKeyPressed2 = this.pearlOnKeyPressed;
      BooleanValue booleanValue = pearlOnKeyPressed2;
      this.keyToThrowPearl = keybindSetting.visibleWhen(() -> booleanValue.getValue());
      BooleanValue booleanValue2 = new BooleanValue(this, "Auto disable", true);
      SettingVisibilityCondition condition2 = () -> {
         return !this.pearlOnKeyPressed.getValue();
      };
      this.autoDisable = (BooleanValue)booleanValue2.withVisibility(condition2);
      this.autoAirstuck = new BooleanValue(this, "Auto airstuck", true);
      BooleanValue booleanValue3 = new BooleanValue(this, "Unstuck if combatPearl didn't land", true);
      pearlOnKeyPressed2 = this.autoAirstuck;
      BooleanValue booleanValue4 = pearlOnKeyPressed2;
      this.unstuckIfCombatPearlDidnTLand = (BooleanValue)booleanValue3.withVisibility(() -> booleanValue4.getValue());
      this.onlyIfPlayerIsFallingIntoTheVoid = new BooleanValue(this, "Only if player is falling into the void", true);
      this.avoidLandingNearOtherPlayers = new BooleanValue(this, "Avoid landing near other players", true);
      DoubleValue minFallDistanceIntoVoid2 = new DoubleValue(this, "Min fall distance into void", 3.0, 0.0, 10.0, 1);
      pearlOnKeyPressed2 = this.onlyIfPlayerIsFallingIntoTheVoid;
      BooleanValue booleanValue5 = pearlOnKeyPressed2;
      this.minFallDistanceIntoVoid = minFallDistanceIntoVoid2.visibleWhen(() -> booleanValue5.getValue());
      this.silentSlotSwitching = new BooleanValue(this, "Silent slot switching", true);
      this.pearlHotbarSlot = -1;
      this.readyToThrow = false;
      this.airStuckEnabled = false;
      this.pearlThrown = false;
      this.receivedTeleportPacket = false;
      this.lastSearchTimeMs = 0L;
      this.pendingAirStuck = false;
      this.keyPressTimer = new TimeHelper();
      this.throwTimer = new TimeHelper();
      this.recentSafePositions = new LinkedList();
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
      if (this.autoAirstuck.getValue() && ClientManagers.moduleManager != null) {
         ClientManagers.moduleManager.airStuck.setToggled(false);
      }

      this.recentSafePositions.clear();
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(7);
      this.toggleEventSubscription(40);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(34);
      this.toggleEventSubscription(14);
      this.toggleEventSubscription(17);
   }

   @Override
   public void onEarlyTick() {
      this.recordSafeGroundPosition();
      this.activatePendingAirStuck();
      if (this.autoAirstuck.getValue() && this.unstuckIfCombatPearlDidnTLand.getValue()) {
         if (this.throwTimer.reached(12000L) && !this.hasPearlInInventory()) {
            ClientManagers.moduleManager.airStuck.setToggled(false);
         }
      }

      if (this.autoAirstuck.getValue() && ClientManagers.moduleManager != null && ClientManagers.moduleManager.airStuck.mode.getSelectedValue().equals("Intave")) {
         this.sendChatMessage("AutoAirstuck doesn't work with Airstuck Mode Intave!");
         this.setToggled(false);
      }

      if (this.pearlThrown) {
         if (!mc.thePlayer.onGround || !this.receivedTeleportPacket) {
            return;
         }

         this.resetThrowState();
      }

      if (!this.shouldThrowPearl()) {
         if (this.shouldAutoDisable()) {
            this.setToggled(false);
         }
      } else {
         if (this.pearlOnKeyPressed.getValue()) {
            if (this.keyPressTimer.reached(2000L)) {
               this.pearlHotbarSlot = -1;
               return;
            }
         }

         this.pearlHotbarSlot = this.findPearlHotbarSlot();
         if (this.pearlHotbarSlot != -1 && !this.readyToThrow) {
            if (System.currentTimeMillis() - this.lastSearchTimeMs < 1000L) {
               return;
            }

            ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(this.pearlHotbarSlot + 36).getStack();
            double d = mc.thePlayer.posX + (mc.thePlayer.prevPosX - mc.thePlayer.posX);
            double d2 = mc.thePlayer.posY + (mc.thePlayer.prevPosY - mc.thePlayer.posY);
            double d3 = mc.thePlayer.posZ + (mc.thePlayer.prevPosZ - mc.thePlayer.posZ);

            for (int i = this.recentSafePositions.size() - 1; i >= 0; i--) {
               BlockPos pos = (BlockPos)this.recentSafePositions.get(i);
               if (this.isSafeLandingPlatform(pos.getX(), pos.getY(), pos.getZ())) {
                  if (this.avoidLandingNearOtherPlayers.getValue()) {
                     Stream stream = mc.theWorld.playerEntities.stream();
                     BlockPos pos2 = pos;
                     if (stream.anyMatch(obj -> {
                        return ((Entity)obj).getDistance((double)pos2.getX() + 0.5, (double)pos2.getY() + 0.5, (double)pos2.getZ() + 0.5) < 10.0;
                     })) {
                        continue;
                     }
                  }

                  double d4 = (double)pos.getX() + 0.5 - d;
                  float f = (float)(MathHelper.atan2((double)pos.getZ() + 0.5 - d3, d4) * 180.0 / Math.PI - 90.0);

                  for (int j = -90; j < 90; j++) {
                     ProjectileSimulator projectileSimulator = new ProjectileSimulator();
                     float f2 = (float)j;
                     projectileSimulator.initFromPosAndAngles(mc.thePlayer, stack, f, f2, d, d2, d3);
                     projectileSimulator.stepThrowableTicks((short)200);
                     MovingObjectPosition hitResult = projectileSimulator.getHitResult();
                     if (hitResult != null && hitResult.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && hitResult.sideHit == EnumFacing.UP) {
                        if (this.isSafeLandingPlatform(hitResult.getBlockPos().getX(), hitResult.getBlockPos().getY(), hitResult.getBlockPos().getZ())) {
                           Rotation rotation = RotationUtil.applyGcdFix(
                              RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, f, 180.0F), (float)j, ClientManagers.rotationController.getPreviousRotation()
                           );
                           ClientManagers.rotationController.requestRotation(rotation, (byte)11, this);
                           this.readyToThrow = false;
                           this.lastSearchTimeMs = System.currentTimeMillis();
                           return;
                        }
                     }
                  }
               }
            }

            for (int k = -180; k < 180; k++) {
               for (int m = -90; m < 90; m++) {
                  ProjectileSimulator projectileSimulator2 = new ProjectileSimulator();
                  float f3 = (float)k;
                  float f4 = (float)m;
                  projectileSimulator2.initFromPosAndAngles(mc.thePlayer, stack, f3, f4, d, d2, d3);
                  projectileSimulator2.stepThrowableTicks((short)200);
                  MovingObjectPosition hitResult2 = projectileSimulator2.getHitResult();
                  if (hitResult2 != null && hitResult2.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && hitResult2.sideHit == EnumFacing.UP) {
                     if (this.isSafeLandingPlatform(hitResult2.getBlockPos().getX(), hitResult2.getBlockPos().getY(), hitResult2.getBlockPos().getZ())) {
                        Rotation rotation2 = RotationUtil.applyGcdFix(
                           RotationUtil.updateAngle(ClientManagers.rotationController.getPreviousRotation().yaw, (float)k, 180.0F), (float)m, ClientManagers.rotationController.getPreviousRotation()
                        );
                        ClientManagers.rotationController.requestRotation(rotation2, (byte)11, this);
                        this.readyToThrow = false;
                        this.lastSearchTimeMs = System.currentTimeMillis();
                        return;
                     }
                  }
               }
            }

            this.lastSearchTimeMs = System.currentTimeMillis();
            this.readyToThrow = false;
         } else if (this.readyToThrow) {
            ClientManagers.rotationController.requestRotation(ClientManagers.rotationController.getPreviousRotation(), (byte)11, this);
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook && this.autoAirstuck.getValue()) {
         if (this.shouldAutoDisable()) {
            this.setToggled(false);
         }

         ClientManagers.moduleManager.airStuck.setToggled(false);
         this.receivedTeleportPacket = true;
      }
   }

   @Override
   public void onClick(EventClick event) {
      if (ClientManagers.rotationController.isOwnedBy(this)) {
         if (this.readyToThrow
            && mc.thePlayer.getCurrentEquippedItem() != null
            && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemEnderPearl
            && !this.airStuckEnabled) {
            event.addRightClicks((byte)1);
            this.throwTimer.reset();
            if (this.autoAirstuck.getValue()) {
               this.pendingAirStuck = true;
            } else if (this.shouldAutoDisable()) {
               this.setToggled(false);
            }

            this.pearlHotbarSlot = -1;
            this.pearlThrown = true;
         }

         this.readyToThrow = true;
      }
   }

   @Override
   public void onToggleKey(EventKey event) {
      if (this.pearlOnKeyPressed.getValue() && event.getKeyCode() == this.keyToThrowPearl.getKeyCode() && mc.thePlayer != null && (!mc.thePlayer.onGround || !this.onlyIfPlayerIsFallingIntoTheVoid.getValue())) {
         this.keyPressTimer.reset();
      }
   }

   @Override
   public void onPostSilentSlot() {
      if (this.pearlHotbarSlot != -1) {
         SilentSlotManager.requestSlot(this, (byte)-2, this.pearlHotbarSlot, this.silentSlotSwitching.getValue());
      }
   }

   public int findPearlHotbarSlot() {

      for (int i = 36; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null && stack.getItem() instanceof ItemEnderPearl) {
            return i - 36;
         }
      }

      return -1;
   }

   public boolean hasPearlInInventory() {

      for (int i = 9; i < mc.thePlayer.inventoryContainer.inventorySlots.size(); i++) {
         ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
         if (stack != null && stack.getItem() instanceof ItemEnderPearl) {
            return true;
         }
      }

      return false;
   }

   private boolean isSafeLandingPlatform(int n, int n2, int n3) {
      boolean flag = true;

      for (int i = -1; i <= 1; i++) {
         for (int j = -1; j <= 1; j++) {
            if (!mc.theWorld.getBlockState2(n + i, n2, n3 + j).getBlock().isFullBlock() && !BlockUtil.hasCollisionBox(n + i, n2 + 1, n3 + j)) {
               flag = false;
               break;
            }
         }
      }

      return flag;
   }

   private boolean shouldThrowPearl() {
      if (this.onlyIfPlayerIsFallingIntoTheVoid.getValue()) {
if (mc.thePlayer.onGround) {
               return false;
            }

            if ((double)mc.thePlayer.fallDistance < this.minFallDistanceIntoVoid.getValue()) {
               return false;
            }

            MovingObjectPosition hit = mc.theWorld
               .rayTraceBlocks(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.posX, Math.min(0.0, mc.thePlayer.posY - 50.0), mc.thePlayer.posZ, true);
if (!(hit != null && hit.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)) {
if (!PlayerUtil.willEntityLandOnGround(mc.thePlayer, (byte)50)) {
return true;
}
}
} else {
return true;
}

      this.pearlHotbarSlot = -1;
      return false;
   }

   private void recordSafeGroundPosition() {
      if (mc.thePlayer.onGround) {
         while (this.recentSafePositions.size() > 100) {
            this.recentSafePositions.removeFirst();
         }

         BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 0.5, mc.thePlayer.posZ);
         if (!this.recentSafePositions.contains(pos)) {
            if (this.isSafeLandingPlatform(pos.getX(), pos.getY(), pos.getZ())) {
               this.recentSafePositions.add(pos);
            }
         }
      }
   }

   private void activatePendingAirStuck() {
      if (this.pendingAirStuck) {
         this.airStuckEnabled = true;
         this.pendingAirStuck = false;
         ClientManagers.moduleManager.airStuck.setToggled(true);
      }
   }

   private void resetThrowState() {
      this.readyToThrow = false;
      this.airStuckEnabled = false;
      this.pearlThrown = false;
      this.pendingAirStuck = false;
      this.receivedTeleportPacket = false;
   }

   public boolean shouldAutoDisable() {
      return this.autoDisable.getValue() && !this.pearlOnKeyPressed.getValue();
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.recentSafePositions.clear();
   }

}
