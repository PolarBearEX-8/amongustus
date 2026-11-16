package augustus.modes.movement;

import augustus.modules.movement.FastLadder;
import client.event.EventMotionUpdate;
import client.event.MotionUpdateListener;
import client.setting.NumberSetting;
import client.setting.SettingGroup;
import client.util.BooleanSupplier;
import client.util.MinecraftAccessor;
import client.util.VectorSorter;
import net.minecraft.network.play.client.C06PacketPlayerPosLook;

public class SpeedPositionMode extends VectorSorter implements MotionUpdateListener {
   public final NumberSetting settingSpeed;
   public final NumberSetting settingTickDelay;

   @Override
   public void updateState() {
   }

   @Override
   public void onMotionUpdate(EventMotionUpdate event) {
      if (((FastLadder)super.module).checkCondition()) {
         if ((double)MinecraftAccessor.minecraft.thePlayer.ticksExisted % (this.settingTickDelay.getValue() + 1.0) == 0.0) {
            MinecraftAccessor.minecraft
               .thePlayer
               .netHandlerPlayClient
               .handlePacket(
                  new C06PacketPlayerPosLook(
                     MinecraftAccessor.minecraft.thePlayer.posX,
                     MinecraftAccessor.minecraft.thePlayer.posY + (double)((float)this.settingSpeed.getValue()),
                     MinecraftAccessor.minecraft.thePlayer.posZ,
                     MinecraftAccessor.minecraft.thePlayer.rotationYaw,
                     MinecraftAccessor.minecraft.thePlayer.rotationPitch,
                     false
                  )
               );
            MinecraftAccessor.minecraft
               .thePlayer
               .processDouble2(
                  MinecraftAccessor.minecraft.thePlayer.posX,
                  MinecraftAccessor.minecraft.thePlayer.posY + (double)((float)this.settingSpeed.getValue()),
                  MinecraftAccessor.minecraft.thePlayer.posZ
               );
         }
      }
   }

   @Override
   public void clear() {
   }

   @Override
   public void updateState2() {
      byte byteValue = 18;
      this.applyIndex(byteValue);
   }

   public SpeedPositionMode(FastLadder fastLadder, SettingGroup settingGroup) {
      super(fastLadder, settingGroup, "Position");
      NumberSetting setting = new NumberSetting(super.module, "PositionSpeed", 0.4, 0.2, 2.0, 3, super.settingGroup);
      String text = "Speed";
      setting = setting.getNumberSetting(text);
      BooleanSupplier booleanSupplier = this::booleanValue;
      this.settingSpeed = setting.getNumberSetting(booleanSupplier);
      setting = new NumberSetting(super.module, "PositionTickDelay", 0.0, 0.0, 20.0, 0, super.settingGroup);
      String text2 = "TickDelay";
      setting = setting.getNumberSetting(text2);
      BooleanSupplier booleanSupplier2 = this::booleanValue;
      this.settingTickDelay = setting.getNumberSetting(booleanSupplier2);
   }
}
