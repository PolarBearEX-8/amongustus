package net.augustus.modules;

import net.minecraft.util.MovingObjectPosition;
import java.util.List;
import net.augustus.events.EventPacket;
import net.augustus.utils.ChatUtil;
import net.augustus.events.EventMoveInput;
import net.augustus.modules.modes.anticheat.AntiCheatPlayerData;
import net.augustus.utils.interfaces.MC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;

public abstract class ModuleMode implements MC {
   public final AntiCheatPlayerData antiCheatPlayerData;
   private final String name;
   public boolean visibleInGui = false;

   public void onClearTarget() {
   }

   public boolean isPlayerMovingFasterThan(AntiCheatPlayerData antiCheatPlayerData2, double d) {
      return Math.abs(antiCheatPlayerData2.getTargetMoveDelta().xCoord) > d || Math.abs(antiCheatPlayerData2.getTargetMoveDelta().zCoord) > d;
   }

   public void onPacketEvent(EventPacket event) {
   }

   public String getName() {
      return this.name;
   }

   public abstract void onTick();

   public void onMoveInput(EventMoveInput event) {
   }

   public void onMoveUpdate() {
   }

   public boolean isAimingAtLivingEntity() {
      return mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY && mc.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   public void sendAlert(String str) {
      ChatUtil.sendChat(this.name + ": " + str);
   }

   public boolean isMotionAboveThreshold(double d) {
      return Math.abs(mc.thePlayer.motionX) > d || Math.abs(mc.thePlayer.motionZ) > d;
   }

   public void onOutboundPacket(EventPacket event) {
   }

   public void resetState() {
   }

   public double averageDoubles(List list) {
      return list.stream().mapToDouble(obj -> ((Number)obj).doubleValue()).average().orElse(0.0);
   }

   public ModuleMode(String str, AntiCheatPlayerData antiCheatPlayerData2) {
      this.name = str;
      this.antiCheatPlayerData = antiCheatPlayerData2;
   }

   public void onServerPacket(EventPacket event) {
   }

   public double averageNumbers(List list) {
      return list.stream().mapToDouble(obj -> ((Number)obj).doubleValue()).average().orElse(0.0);
   }

   public abstract void onClientPacket(Packet packet);

   public double averageValues(List list) {
      return list.stream().mapToDouble(obj -> ((Number)obj).doubleValue()).average().orElse(0.0);
   }

   public void onRender() {
   }



}
