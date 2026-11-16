package augustus.modes.movement;

import augustus.modules.combat.Velocity;
import client.event.EventMoveEntity;
import client.event.EventPacket;
import client.event.MoveEntityListener;
import client.event.PacketListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.InternalStringArrayEntry;
import client.util.MoveUtil;
import client.util.Packet;
import java.util.function.BiFunction;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class JesusCustommotionMode extends InternalStringArrayEntry implements PacketListener, MoveEntityListener {
   public final NumberSetting settingXZMotionInAir;
   public final NumberSetting settingXZMotionOnGround;
   public boolean enabled2;

   @Override
   public void updateState() {
   }

   public static BiFunction getBiFunction() {
      return ((item, group) -> new JesusCustommotionMode((Velocity)item, (SettingGroup)group));
   }

   @Override
   public void clear() {
   }

   @Override
   public void onPostMoveEntity(EventMoveEntity event) {
      if (this.enabled2) {
         this.enabled2 = false;
         double doubleItem = MoveUtil.getDouble2();
         double offset = minecraft.thePlayer.onGround ? this.settingXZMotionOnGround.getValue() : this.settingXZMotionInAir.getValue();
         double offset2 = -(Math.sin(doubleItem) * offset);
         event.setCoordOffset2(offset2);
         double offset3 = Math.cos(doubleItem) * offset;
         event.setCoordOffset(offset3);
      }
   }

   @Override
   public void onPacketPostSend(EventPacket packet) {
      Packet packet2 = packet.getPacket();
      if (packet2 instanceof S12PacketEntityVelocity && this.isActive()) {
         if (((S12PacketEntityVelocity)packet2).getEntityId() == minecraft.thePlayer.getEntityId()) {
            this.enabled2 = true;
         }
      }
   }

   @Override
   public void updateState2() {
      byte byteValue = 26;
      this.applyIndex(byteValue);
      byte byteValue2 = 20;
      this.applyIndex(byteValue2);
   }

   public JesusCustommotionMode(Velocity velocity, SettingGroup settingGroup) {
      super(velocity, settingGroup, "CustomMotion");
      NumberSetting setting = new NumberSetting(this.module, "XZMotionInAir", 0.2, 0.0, 1.0, 4, this.settingGroup);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingXZMotionInAir = setting.getNumberSetting(booleanSupplier);
      setting = new NumberSetting(this.module, "XZMotionOnGround", 0.2, 0.0, 1.0, 4, this.settingGroup);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingXZMotionOnGround = setting.getNumberSetting(booleanSupplier2);
      this.enabled2 = true;
   }
}
