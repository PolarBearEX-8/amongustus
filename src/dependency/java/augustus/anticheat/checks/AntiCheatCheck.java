package augustus.anticheat.checks;

import client.event.EventMove;
import client.event.EventPacket;
import client.util.MinecraftAccessor;
import client.util.MovingObjectType;
import client.util.Packet;
import client.util.RayTraceUtil;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;

public abstract class AntiCheatCheck implements MinecraftAccessor {
   public final String labelText2;
   public boolean state = false;
   public final AntiCheatManager antiCheatManager;

   public abstract void updateState2();

   public double computeValue(List list) {
      return list.stream().mapToDouble((x -> ((Number)x).doubleValue())).average().orElse(0.0);
   }

   public void applyTextByText(String text) {
      RayTraceUtil.applyString(this.labelText2 + ": " + text);
   }

   public boolean checkDouble(double offset) {
      return Math.abs(minecraft.thePlayer.motionX) > offset || Math.abs(minecraft.thePlayer.motionZ) > offset;
   }

   public void clear() {
   }

   public void onPacket(EventPacket packet) {
   }

   public double computeValue2(List list) {
      return list.stream().mapToDouble((x -> ((Number)x).doubleValue())).average().orElse(0.0);
   }

   public String getText() {
      return this.labelText2;
   }

   public void updateState3() {
   }

   public boolean matchesAntiCheatManager(AntiCheatManager antiCheatManager2, double offset) {
      return Math.abs(antiCheatManager2.getVec4().xCoord) > offset || Math.abs(antiCheatManager2.getVec4().zCoord) > offset;
   }

   public boolean isActive() {
      return minecraft.objectMouseOver != null && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase;
   }

   public void updateState4() {
   }

   public void onMove(EventMove event) {
   }

   public void onPacket2(EventPacket packet) {
   }

   public abstract void handlePacket(Packet packet);

   public void onPacketByEventPacket(EventPacket packet) {
   }

   public void updateState() {
   }

   public double computeValueByList(List list) {
      return list.stream().mapToDouble((x -> ((Number)x).doubleValue())).average().orElse(0.0);
   }

   public AntiCheatCheck(String text, AntiCheatManager antiCheatManager2) {
      this.labelText2 = text;
      this.antiCheatManager = antiCheatManager2;
   }
}
