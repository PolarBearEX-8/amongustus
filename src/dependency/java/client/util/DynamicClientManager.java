package client.util;

import client.event.PassiveListener;
import client.event.PostUpdateNotifier;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class DynamicClientManager implements PostUpdateNotifier {
   @Override
   public boolean matchesText(String text) {
      Minecraft mc = Minecraft.getMinecraft();
      Session session = mc.getSession();
      if (session == null) {
         return false;
      } else {
         try {
            mc.getMinecraftSessionService().joinServer(session.getGameProfile(), session.getText2(), text);
            return true;
         } catch (Exception exception) {
            return false;
         }
      }
   }

   public boolean isButtonDown(int count) {
      return Mouse.isButtonDown(count);
   }

   public boolean isActive() {
      return Minecraft.getMinecraft().theWorld != null;
   }

   @Override
   public UUID resolveUUID(boolean flag) {
      UUID uuid = null;
      Minecraft mc = Minecraft.getMinecraft();
      if (flag && mc.getRenderViewEntity() != null) {
         uuid = mc.getRenderViewEntity().getEntityUniqueID();
      } else if (mc.getSession() != null && mc.getSession().getGameProfile() != null) {
         uuid = mc.getSession().getGameProfile().getId();
      }

      return uuid != null ? uuid : UUID.fromString("00000000-0000-0000-0000-000000000000");
   }

   public boolean matchesTextByText(String text) {
      GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
      return currentScreen != null && currentScreen.getClass().getSimpleName().equals(text);
   }

   @Override
   public int getDWheel() {
      return Mouse.getDWheel();
   }

   public PassiveListener getPassiveListener() {
      return AuxiliaryListenerAdapter2.getAuxiliaryListenerAdapter2();
   }

   @Override
   public String getText() {
      return Minecraft.getMinecraft().getSession().getText1();
   }

   public boolean isActive3() {
      return Minecraft.getMinecraft().currentScreen == null;
   }

   @Override
   public void applyIndex(int count) {
   }

   @Override
   public FailedToLoadMaskFromManagerEntry getFailedToLoadMaskFromManagerE() {
      return new FailedToLoadMaskFromManagerEntryAdapter();
   }

   @Override
   public boolean isKeyDown(int count) {
      return Keyboard.isKeyDown(count);
   }

   @Override
   public FailedToLoadMaskFromManager getFailedToLoadMaskFromManager() {
      return new FailedToLoadMaskFromManagerAdapter();
   }

   public UUID[] getUUIDArray() {
      if (Minecraft.getMinecraft().theWorld == null) {
         return new UUID[0];
      } else {
         List itemList = Minecraft.getMinecraft().theWorld.itemList7;
         UUID[] uuidArray = new UUID[itemList.size()];

         for (int index = 0; index < uuidArray.length; index++) {
            uuidArray[index] = ((EntityPlayer)itemList.get(index)).getEntityUniqueID();
         }

         return uuidArray;
      }
   }

   @Override
   public void applyIndexByIndex(int count) {
      GlStateManager.setActiveTexture(count);
   }
}
