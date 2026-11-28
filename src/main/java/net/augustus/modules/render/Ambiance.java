package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.events.EventWorldColor;
import net.augustus.events.listeners.WorldColorListener;
import net.augustus.events.listeners.WorldTimeListener;
import net.augustus.settings.ColorSetting;
import net.augustus.events.EventWorldTime;
import net.augustus.Augustus;
import net.augustus.settings.SettingChangeListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.DoubleValue;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class Ambiance extends Module implements PacketListener, WorldColorListener, WorldTimeListener {
   public final DoubleValue time;
   public final DoubleValue timeSpeed;
   public final DoubleValue rain;
   public final BooleanValue snow;
   public final DoubleValue thunder;
   public final BooleanValue customWorldColor;
   public final ColorSetting worldColor;
   public final BooleanValue customFogColor;
   public final ColorSetting fogColor;
   public final BooleanValue customFogDistance;
   public final DoubleValue fogDistance;
   public long elapsedTimeOffset;
   private float savedRainStrength;
   private float savedThunderStrength;

   public Ambiance() {
       super("Ambiance", "Ambiente", new Color(224, 93, 40), Categorys.RENDER);
      this.time = new DoubleValue(this, "Time", 1900.0, 0.0, 24000.0, 0);
      this.timeSpeed = new DoubleValue(this, "TimeSpeed", 0.0, 0.0, 100.0, 1);
      this.rain = new DoubleValue(this, "Rain", 0.0, 0.0, 1.0, 2);
      this.snow = new BooleanValue(this, "Snow", false);
      this.thunder = new DoubleValue(this, "Thunder", 0.0, 0.0, 1.0, 2);
      this.customWorldColor = (BooleanValue)new BooleanValue(this, "CustomWorldColor", false).withChangeListener(() -> this.warnIfFullBrightGammaActive());
      ColorSetting colorSetting = new ColorSetting(this, "WorldColor", Augustus.getInstance().getClientColor());
      BooleanValue customWorldColor2 = this.customWorldColor;
      BooleanValue booleanValue2 = customWorldColor2;
      this.worldColor = (ColorSetting)colorSetting.withVisibility(() -> booleanValue2.getValue());
      this.customFogColor = new BooleanValue(this, "CustomFogColor", false);
      ColorSetting colorSetting2 = new ColorSetting(this, "FogColor", Augustus.getInstance().getClientColor());
      customWorldColor2 = this.customFogColor;
      BooleanValue booleanValue3 = customWorldColor2;
      this.fogColor = (ColorSetting)colorSetting2.withVisibility(() -> booleanValue3.getValue());
      this.customFogDistance = new BooleanValue(this, "CustomFogDistance", false);
      DoubleValue fogDistanceSetting = new DoubleValue(this, "FogDistance", 192.0, 0.0, 1000.0, 0);
      customWorldColor2 = this.customFogDistance;
      BooleanValue booleanValue4 = customWorldColor2;
      this.fogDistance = fogDistanceSetting.visibleWhen(() -> booleanValue4.getValue());
      this.savedRainStrength = 0.0F;
      this.savedThunderStrength = 0.0F;
   }

   @Override
   public void onEnable() {
      this.elapsedTimeOffset = 0L;
      if (mc.theWorld != null) {
         this.savedRainStrength = mc.theWorld.getRainStrength(1.0F);
         this.savedThunderStrength = mc.theWorld.getThunderStrength(1.0F);
      }
   }

   @Override
   public void onDisable() {
      this.elapsedTimeOffset = 0L;
      if (mc.theWorld != null) {
         mc.theWorld.setRainStrength(this.savedRainStrength);
         mc.theWorld.setThunderStrength(this.savedThunderStrength);
      }
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(44);
      this.toggleEventSubscription(45);
   }

   @Override
   public void onWorldColor(EventWorldColor event) {
      if (this.customWorldColor.getValue()) {
         this.worldColor.getColorInt((byte)0);
         event.setRed((float)this.worldColor.getRed());
         event.setGreen((float)this.worldColor.getGreen());
         event.setBlue((float)this.worldColor.getBlue());
      }
   }

   @Override
   public void onFogColor(EventWorldColor event) {
      if (this.customFogColor.getValue()) {
         this.fogColor.getColorInt((byte)0);
         event.setRed((float)this.fogColor.getRed());
         event.setGreen((float)this.fogColor.getGreen());
         event.setBlue((float)this.fogColor.getBlue());
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S03PacketTimeUpdate) {
         event.cancel();
      }
   }

   @Override
   public void onWorldTime(EventWorldTime event) {
      this.elapsedTimeOffset = this.timeSpeed.getValue() > 0.0 ? (long)((double)this.elapsedTimeOffset + this.timeSpeed.getValue()) : 0L;
      event.setWorldTime((long)(this.time.getValue() + (double)this.elapsedTimeOffset));
      if (this.elapsedTimeOffset > 24000L) {
         this.elapsedTimeOffset = 0L;
      }
   }

   private void warnIfFullBrightGammaActive() {
      if (Augustus.getInstance().getModuleManager().fullBright.isToggled() && Augustus.getInstance().getModuleManager().fullBright.mode.getSelectedValue().equals("Gamma") && this.customWorldColor.getValue()) {
         this.sendChatMessage("Disable gamma mode in FullBright module");
      }
   }

}
