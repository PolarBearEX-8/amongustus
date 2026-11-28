package net.augustus.modules.render;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.utils.math.DefaultRandomGenerator;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.minecraft.client.network.NetworkPlayerInfo;

public class Protector extends Module {
   public BooleanValue time;
   public final StringValue nameGeneration;
   private int randomXOffset;
   private int randomZOffset;
   private int fakeMinute;
   private int fakeHour;

   public Protector() {
       super("Protector", "Versicherung", Color.yellow, Categorys.RENDER);
      this.time = new BooleanValue(this, "Time", true);
      this.nameGeneration = new StringValue(this, "NameGeneration", "List", new String[]{"List", "Esound", "Generated"});
   }

   public void onEnable() {
      this.randomXOffset = DefaultRandomGenerator.instance.nextInt((short)-1000, (short)1000);
      this.randomZOffset = DefaultRandomGenerator.instance.nextInt((short)-1000, (short)1000);
      this.fakeMinute = DefaultRandomGenerator.instance.nextInt((byte)10, (byte)60);
      this.fakeHour = DefaultRandomGenerator.instance.nextInt((byte)0, (byte)11);
      if (mc.thePlayer != null) {
         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            networkPlayerInfo.playerTexturesLoaded = false;
            networkPlayerInfo.loadPlayerTextures();
         }
      }
   }

   public void onDisable() {
      if (mc.thePlayer != null) {
         for (NetworkPlayerInfo networkPlayerInfo : (Iterable<NetworkPlayerInfo>)(mc.getNetHandler().getPlayerInfoMap())) {
            networkPlayerInfo.playerTexturesLoaded = false;
            networkPlayerInfo.loadPlayerTextures();
         }
      }
   }

   public void updateEventSubscriptions() {
   }

   public int getRandomXOffset() {
      return this.randomXOffset;
   }

   public int getRandomZOffset() {
      return this.randomZOffset;
   }

   public int getFakeMinute() {
      return this.fakeMinute;
   }

   public int getFakeHour() {
      return this.fakeHour;
   }

}
