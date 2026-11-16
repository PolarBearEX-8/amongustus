package client.util;

import client.Client;
import client.ClientGlobals;
import client.module.Module;
import client.render.RenderPass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public final class MovementHandler {
   public static final PlayerMovementHistory internalArrayListEntry7 = new PlayerMovementHistory();
   public static final byte byteIndex5 = 1;
   public static final PlayerMovementHistory internalArrayListEntry = new PlayerMovementHistory();
   public static final ClientTickEventSync minecraftEntry = new ClientTickEventSync();
   public static boolean state = false;
   public static final PlayerMovementHistory internalArrayListEntry6 = new PlayerMovementHistory();
   public static final byte byteIndex = 0;
   public static final byte byteIndex4 = 2;
   public static byte byteIndex2 = 0;
   public static byte byteIndex8 = 0;
   public static byte byteIndex3 = 0;
   public static byte byteIndex1 = 0;
   public static final StepHistoryArray tertiaryIntArrayEntry = new StepHistoryArray();

   public static void processAction() {
      byteIndex2 = 0;
   }

   public static void processAction2() {
      byteIndex1 = 1;
   }

   public static void applyRunnableTask(boolean flag, RunnableTask runnableTask) {
      long value = 0x54572DFAB6F8L;
      if (!BuildManager.canExecute3() && !BuildManager.canExecute4() && !BuildManager.canExecute2() && !state) {
         if (byteIndex2 >= 1 && byteIndex8 == 0) {
            if (flag) {
               ClientGlobals.moduleManager.shadow.updateState();
               RenderPass renderPass = RenderPass.SHADOW;
               runnableTask.render(renderPass);
               ClientGlobals.moduleManager.shadow.processAction();
            } else {
               internalArrayListEntry6.applyRunnableTask(runnableTask);
            }
         }

         if (byteIndex1 >= 1) {
            if (flag) {
               boolean flag2 = byteIndex8 >= 1;
               boolean flag3 = true;
               minecraftEntry.setActive(flag3, flag2);
               RenderPass renderPass2 = RenderPass.BLUR;
               runnableTask.render(renderPass2);
               minecraftEntry.updateState();
            } else if (byteIndex8 >= 1) {
               internalArrayListEntry.applyRunnableTask(runnableTask);
            } else {
               internalArrayListEntry7.applyRunnableTask(runnableTask);
            }
         }
      }

      if (byteIndex3 >= 1) {
         RenderPass renderPass3 = RenderPass.NONE;
         runnableTask.render(renderPass3);
      }

      if (byteIndex1 == 2) {
         byteIndex1 = 0;
      }

      if (byteIndex2 == 2) {
         byteIndex2 = 0;
      }

      if (byteIndex3 == 2) {
         byteIndex3 = 0;
      }

      if (byteIndex8 == 2) {
         byteIndex8 = 0;
      }
   }

   public static void processAction3() {
      byteIndex2 = 2;
   }

   public static void processAction4() {
      byteIndex1 = 0;
   }

   public static void processBoolean(boolean flag, boolean flag2) {
      processAction8();
      if (flag) {
         processAction6();
      }

      if (flag2) {
         processAction3();
      }
   }

   public static void processAction5() {
      byteIndex8 = 1;
   }

   public static void applyScaledResolution(ScaledResolution scaledResolution, boolean flag) {
      long value = 0x47EC16D5B4A6L;
      if (!Minecraft.state3 && !BuildManager.canExecute3() && !BuildManager.canExecute4() && !BuildManager.canExecute2()) {
         tertiaryIntArrayEntry.applyRunnableTask((RunnableTask)null);
         if (((Module)ClientGlobals.moduleManager.blur).isToggled() && (ClientGlobals.moduleManager.blur.settingInScreens.isEnabled() || !flag)
            || ClientGlobals.moduleManager.blur.settingInScreens.isEnabled() && flag) {
            if (!internalArrayListEntry7.isActive()) {
               minecraftEntry.setActive(true, false);
               RenderPass renderPass = RenderPass.BLUR;
               internalArrayListEntry7.applyRenderPass(renderPass);
               tertiaryIntArrayEntry.applyRenderPassByRenderPass((RenderPass)null);
            }

            if (!internalArrayListEntry.isActive()) {
               minecraftEntry.setActive(true, true);
               RenderPass renderPass2 = RenderPass.BLUR;
               internalArrayListEntry.applyRenderPass(renderPass2);
               minecraftEntry.updateState();
               tertiaryIntArrayEntry.applyRenderPassByRenderPass((RenderPass)null);
            }

            ClientTickEventSync event = minecraftEntry;
            int value2 = (int)Client.getClient().getModuleManager().blur.settingSigma.getValue();
            event.processBoolean(flag, value2);
         }

         if (!flag) {
            if (!internalArrayListEntry6.isActive()) {
               ClientGlobals.moduleManager.shadow.updateState();
               RenderPass renderPass3 = RenderPass.SHADOW;
               internalArrayListEntry6.applyRenderPass(renderPass3);
               ClientGlobals.moduleManager.shadow.processAction();
               tertiaryIntArrayEntry.applyRenderPassByRenderPass((RenderPass)null);
            }

            if (((Module)ClientGlobals.moduleManager.shadow).isToggled()) {
               ClientGlobals.moduleManager.shadow.applyScaledResolution(scaledResolution);
            }
         }
      }
   }

   public static void processAction6() {
      byteIndex1 = 2;
   }

   public static void processAction7() {
      byteIndex3 = 1;
   }

   public static void processAction8() {
      byteIndex3 = 2;
   }

   public static boolean canExecute() {
      return byteIndex8 >= 1;
   }

   public static void processAction9() {
      byteIndex2 = 1;
   }

   public static void processAction10() {
      byteIndex8 = 2;
   }

   public static void processAction11() {
      byteIndex3 = 0;
   }

   public static void processAction12() {
      byteIndex8 = 0;
   }
}
