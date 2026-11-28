package net.augustus.modules.misc;

import java.awt.Color;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.events.EventPacket;
import net.augustus.settings.IntervalNoiseSetting;
import net.augustus.events.listeners.DownloadTerrainListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class Fixes extends Module implements DownloadTerrainListener, PacketListener {
   private final BooleanValue intave;
   private final BooleanValue intaveBlocking;
   private final BooleanValue mouseDelayFix;
   public final StringValue hitDelayFixMode;
   public final IntervalNoiseSetting hitdelayfixNonliteChance;
   public final BooleanValue alwaysFixUrgentHits;
   public final BooleanValue memoryFix;
   public final BooleanValue placeWhileBreak;
   private final BooleanValue noTerrainDownload;
   private final BooleanValue noReducedDebugInformation;
   public final TimeHelper unusedFixTimer;
   public final TimeHelper lastTeleportTimer;

   public Fixes() {
       super("Fixes", "Reparieren", new Color(169, 66, 237), Categorys.MISC);
      this.intave = new BooleanValue(this, "Intave", false);
      this.intaveBlocking = new BooleanValue(this, "IntaveBlocking", false);
      this.mouseDelayFix = new BooleanValue(this, "MouseDelayFix", true);
      this.hitDelayFixMode = new StringValue(
         this, "HitDelayFixMode", "Default", new String[]{"None", "Default", "WithoutDoubleClick", "OnlyAtTarget", "OnlyAtTargetLite"}
      );
      IntervalNoiseSetting intervalNoiseSetting = new IntervalNoiseSetting(this, "hitdelayfix_nonlite_chance", null, true);
      double d = 100.0;
      double d2 = 100.0;
      double d3 = 100.0;
      this.hitdelayfixNonliteChance = intervalNoiseSetting.withInterval(0.0, d3, d2, d, (byte)1).named("Chance").visibleWhen(() -> this.isHitDelayChanceVisible());
      this.alwaysFixUrgentHits = (BooleanValue)((BooleanValue)new BooleanValue(this, "AlwaysFixUrgentHits", true).withDisplayName("Always Fix Urgent Hits")).withVisibility(() -> this.isAlwaysFixUrgentHitsVisible());
      this.memoryFix = new BooleanValue(this, "MemoryFix", false);
      this.placeWhileBreak = new BooleanValue(this, "PlaceWhileBreak", true);
      this.noTerrainDownload = new BooleanValue(this, "NoTerrainDownload", true);
      this.noReducedDebugInformation = new BooleanValue(this, "NoReducedDebugInformation", true);
      this.unusedFixTimer = new TimeHelper();
      this.lastTeleportTimer = new TimeHelper();
      this.hitdelayfixNonliteChance.addDefaultLayer();
   }

   @Override
   public void onClientInit() {
      this.setToggled(true);
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(8);
      this.toggleEventSubscription(26);
   }

   @Override
   public void onDownloadTerrain() {
      if (this.memoryFix.getValue()) {
         long l = Runtime.getRuntime().maxMemory();
         if ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100L / l > 50L) {
            System.gc();
         }
      }
   }

   @Override
   public void onPacketReceive(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         this.lastTeleportTimer.reset();
      }
   }

   public boolean isIntaveFixEnabled() {
      return this.isToggled() && this.intave.getValue();
   }

   public boolean isMouseDelayFixEnabled() {
      return this.isToggled() && this.mouseDelayFix.getValue();
   }

   public boolean isNoTerrainDownloadEnabled() {
      return this.isToggled() && this.noTerrainDownload.getValue();
   }

   public boolean shouldApplyIntaveBlockingFix() {
      if (this.isToggled() && this.intaveBlocking.getValue()) {
         if (!this.lastTeleportTimer.reached(500L)) {
            return true;
         }
      }

      return false;
   }

   public boolean isNoReducedDebugInfoEnabled() {
      return this.isToggled() && this.noReducedDebugInformation.getValue();
   }

   public boolean isPlaceWhileBreakEnabled() {
      return this.isToggled() && this.placeWhileBreak.getValue();
   }

   public boolean shouldApplyHitDelayFix(boolean flag) {
      if (!this.hitDelayFixMode.getSelectedValue().equals("OnlyAtTarget")) {
         return true;
      } else if (flag && this.alwaysFixUrgentHits.getValue()) {
         return true;
      } else {
         if (this.hitdelayfixNonliteChance.intervalRange.getMaxValue(true) == 100.0) {
            if (this.hitdelayfixNonliteChance.intervalRange.getMinValue(true) == 100.0) {
               return true;
            }
         }

         this.hitdelayfixNonliteChance.update();
         double d = this.hitdelayfixNonliteChance.sample();
         return Math.random() < d;
      }
   }

   private boolean isAlwaysFixUrgentHitsVisible() {
      return this.hitDelayFixMode.getSelectedValue().equals("OnlyAtTarget");
   }

   private boolean isHitDelayChanceVisible() {
      return this.hitDelayFixMode.getSelectedValue().equals("OnlyAtTarget");
   }


}
