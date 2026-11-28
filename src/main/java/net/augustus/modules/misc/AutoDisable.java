package net.augustus.modules.misc;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import net.augustus.modules.Categorys;
import net.augustus.modules.Module;
import net.augustus.modules.ModuleManager;
import net.augustus.events.EventPacket;
import net.augustus.ClientManagers;
import net.augustus.settings.Setting;
import net.augustus.events.listeners.LoadWorldListener;
import net.augustus.events.EventLoadWorld;
import net.augustus.utils.TupleTriple;
import net.augustus.Augustus;
import net.augustus.events.listeners.TickListener;
import net.augustus.events.listeners.PacketListener;
import net.augustus.settings.SettingVisibilityCondition;
import net.augustus.settings.BooleanValue;
import net.augustus.settings.StringValue;
import net.augustus.settings.SettingGroup;
import net.augustus.settings.DoubleValue;
import net.augustus.utils.TimeHelper;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;

public class AutoDisable extends Module implements TickListener, LoadWorldListener, PacketListener {
   private final BooleanValue disableOnTeleport;
   private final DoubleValue minTeleportDistance;
   private final StringValue mode;
   private final DoubleValue forcedisableModulesForXMs;
   private final DoubleValue retoggleModulesAfterXMs;
   private final HashMap moduleDisableSettings;
   private boolean hasPendingRetoggles;
   private final TimeHelper forceDisableTimer;
   private final TimeHelper retoggleTimer;
   private final HashSet disabledModules;

   public AutoDisable() {
       super("AutoDisable", "AutoAusschalten", new Color(169, 224, 81), Categorys.MISC);
      this.disableOnTeleport = (BooleanValue)(new BooleanValue(this, "Disable on Teleport", false)).markVisualOnly();
      DoubleValue minTeleportDistanceSetting = new DoubleValue(this, "Min teleport distance", 50.0, 0.0, 1000.0, 1);
      BooleanValue disableOnTeleport2 = this.disableOnTeleport;
      this.minTeleportDistance = (DoubleValue)minTeleportDistanceSetting.visibleWhen(() -> disableOnTeleport2.getValue()).markVisualOnly();
      this.mode = (StringValue)(new StringValue(this, "Mode", "Simple", new String[]{"Simple", "Expert"})).markVisualOnly();
      this.forcedisableModulesForXMs = (DoubleValue)new DoubleValue(this, "Forcedisable modules for x ms", 0.0, 0.0, 5000.0, 0).visibleWhen(() -> this.isSimpleForceDisableDelayVisible()).markVisualOnly();
      this.retoggleModulesAfterXMs = (DoubleValue)new DoubleValue(this, "Retoggle modules after x ms", 0.0, 0.0, 5000.0, 0).visibleWhen(() -> this.isSimpleRetoggleDelayVisible()).markVisualOnly();
      this.moduleDisableSettings = new HashMap();
      this.hasPendingRetoggles = false;
      this.forceDisableTimer = new TimeHelper();
      this.retoggleTimer = new TimeHelper();
      this.disabledModules = new HashSet();
   }

   public void initModuleSettings(ModuleManager moduleManager) {
      if (this.moduleDisableSettings.isEmpty()) {
         for (Categorys categorys2 : Categorys.values()) {
            if (!categorys2.hidden) {
               SettingGroup settingGroup = (SettingGroup)(new SettingGroup(this, categorys2.name())).markVisualOnly();

               for (Module module : (Iterable<Module>)(moduleManager.getModulesByCategory(categorys2))) {
                  if (!(module instanceof AutoDisable)) {
                     BooleanValue booleanValue = (BooleanValue)(new BooleanValue(this, module.getModuleName(), false, settingGroup)).markVisualOnly();
                     SettingGroup settingGroup2 = (SettingGroup)new SettingGroup(this, module.getModuleName() + "Settings", settingGroup).visibleWhen(() -> this.isExpertModuleOptionVisible(booleanValue)).markVisualOnly();
                     HashMap moduleDisableSettings2 = this.moduleDisableSettings;
                     Setting setting = new DoubleValue(
                        this, module.getName() + "Forcedisable modules for x ms", 0.0, 0.0, 5000.0, 0, settingGroup2
                     ).named("Forcedisable modules for x ms").markVisualOnly();
                     DoubleValue doubleSetting = (DoubleValue)(new DoubleValue(
                           this, module.getName() + "Retoggle modules after x ms", 0.0, 0.0, 5000.0, 0, settingGroup2
                        ))
                        .markVisualOnly();
                     String str2 = "Retoggle modules after x ms";
                     moduleDisableSettings2.put(module, new TupleTriple(booleanValue, setting, doubleSetting.named(str2)));
                  }
               }
            }
         }
      }
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onDisable() {
   }

   @Override
   public void updateEventSubscriptions() {
      this.toggleEventSubscription(17);
      this.toggleEventSubscription(26);
      this.toggleEventSubscription(40);
   }

   @Override
   public void onLoadWorld(EventLoadWorld event) {
      this.hasPendingRetoggles = this.disableConfiguredModules();
   }

   @Override
   public void onPreTick() {
      if (this.hasPendingRetoggles && mc.thePlayer != null && mc.theWorld != null && !this.disabledModules.isEmpty()) {
         boolean flag = false;
         Iterator it = this.disabledModules.iterator();

         while (it.hasNext()) {
            Module module = (Module)it.next();
            int forceDisableDelayMs = this.getForceDisableDelayMs(module);
            int retoggleDelayMs = this.getRetoggleDelayMs(module);
            boolean flag2 = forceDisableDelayMs > 0;
            boolean flag3 = retoggleDelayMs > 0;
            long l = (long)forceDisableDelayMs;
            boolean flag4 = this.forceDisableTimer.reached(l);
            long l2 = (long)retoggleDelayMs;
            boolean flag5 = this.retoggleTimer.reached(l2);
            if (flag2 && flag4) {
               module.setToggleLocked(false);
            }

            if (flag3 && flag4 && flag5) {
               module.setToggled(true, false);
               it.remove();
               flag = true;
            }
         }

         if (flag) {
            Augustus.getInstance().getModuleManager().sortModules();
         }

         if (this.disabledModules.isEmpty()) {
            this.hasPendingRetoggles = false;
         }
      }
   }

   @Override
   public void onPacketReceiveNetty(EventPacket event) {
      if (event.getPacket() instanceof S08PacketPlayerPosLook) {
         PacketThreadUtil.runOnMainThreadImmediate(() -> this.handleTeleportPacket(event));
      }
   }

   private boolean disableConfiguredModules() {
      boolean flag = true;
      if (!this.hasPendingRetoggles) {
         this.disabledModules.clear();
         flag = false;
      }

      for (Module module : (Iterable<Module>)(ClientManagers.moduleManager.getModules())) {
         if (module.isToggled()) {
            TupleTriple tupleTriple = (TupleTriple)this.moduleDisableSettings.get(module);
            if (tupleTriple != null) {
               BooleanValue first = (BooleanValue)tupleTriple.getFirst();
               if (first != null && first.getValue()) {
                  int forceDisableDelayMs = this.getForceDisableDelayMs(module);
                  int retoggleDelayMs = this.getRetoggleDelayMs(module);
                  if (forceDisableDelayMs > 0 || retoggleDelayMs > 0) {
                     flag = true;
                  }

                  module.setToggled(false);
                  this.disabledModules.add(module);
                  if (forceDisableDelayMs > 0) {
                     module.setToggleLocked(true);
                  }
               }
            }
         }
      }

      this.forceDisableTimer.reset();
      this.retoggleTimer.reset();
      return flag;
   }

   private int getForceDisableDelayMs(Module module) {
      if (this.mode.getSelectedValue().equals("Simple")) {
         return (int)this.forcedisableModulesForXMs.getValue(true);
      } else {
         return (int)((DoubleValue)((TupleTriple)this.moduleDisableSettings.get(module)).second).getValue(true);
      }
   }

   private int getRetoggleDelayMs(Module module) {
      if (this.mode.getSelectedValue().equals("Simple")) {
         return (int)this.retoggleModulesAfterXMs.getValue(true);
      } else {
         return (int)((DoubleValue)((TupleTriple)this.moduleDisableSettings.get(module)).third).getValue(true);
      }
   }

   private void handleTeleportPacket(EventPacket event) {
      S08PacketPlayerPosLook packet = (S08PacketPlayerPosLook)event.getPacket();
      if (mc.thePlayer != null && mc.thePlayer.getDistance(packet.getX(), packet.getY(), packet.getZ()) > this.minTeleportDistance.getValue()) {
         boolean hasPendingRetoggles2 = this.hasPendingRetoggles;
         this.hasPendingRetoggles = this.disableConfiguredModules();
         if (this.hasPendingRetoggles && !hasPendingRetoggles2) {
            this.sendChatMessage("Disabled modules because of teleport!");
         }
      }
   }

   private boolean isExpertModuleOptionVisible(BooleanValue booleanValue) {
      return booleanValue.getValue() && this.mode.getSelectedValue().equals("Expert");
   }

   private boolean isSimpleRetoggleDelayVisible() {
      return this.mode.getSelectedValue().equals("Simple");
   }

   private boolean isSimpleForceDisableDelayVisible() {
      return this.mode.getSelectedValue().equals("Simple");
   }

}
