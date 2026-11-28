package net.augustus.cosmetics;

import net.augustus.Augustus;
import net.augustus.cosmetics.model.CosmeticModelLoader;
import net.augustus.cosmetics.model.MinecraftCosmeticModelLoader;
import net.augustus.cosmetics.render.AbstractCosmeticRenderer;
import net.augustus.cosmetics.render.GlMatrixTransformBridge;
import net.augustus.cosmetics.render.MatrixTransformBridge;
import net.augustus.cosmetics.render.PlayerCosmeticRenderer;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class MinecraftBridgeImpl implements MinecraftBridge {

   @Override
   public AbstractCosmeticRenderer createCosmeticRenderer() {
      return new PlayerCosmeticRenderer();
   }

   public UUID[] getLoadedPlayerUuids() {
      if (Minecraft.getMinecraft().theWorld == null) {
         return new UUID[0];
      } else {
         List playerEntities = Minecraft.getMinecraft().theWorld.playerEntities;
         UUID[] uuids = new UUID[playerEntities.size()];

         for (int i = 0; i < uuids.length; i++) {
            uuids[i] = ((EntityPlayer)playerEntities.get(i)).getUniqueID();
         }

         return uuids;
      }
   }

   @Override
   public boolean joinServer(String str) {
      Minecraft minecraft = Minecraft.getMinecraft();
      Session session = minecraft.getSession();
      if (session == null) {
         return false;
      } else {
         try {
            minecraft.getSessionService().joinServer(session.getProfile(), session.getToken(), str);
            return true;
         } catch (Exception e) {
            return false;
         }
      }
   }

   @Override
   public String getUsername() {
      Minecraft.getMinecraft();
      return ((net.minecraft.util.Session)null).getUsername();
   }

   @Override
   public void bindTexture(int n) {
      GlStateManager.bindTexture(n);
   }

   public boolean isInWorld() {
      return Minecraft.getMinecraft().theWorld != null;
   }

   public boolean isScreenOpen(String str) {
      GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
      return currentScreen != null && currentScreen.getClass().getSimpleName().equals(str);
   }

   @Override
   public void onTick(int n) {
   }

   public boolean isNoScreenOpen() {
      return Minecraft.getMinecraft().currentScreen == null;
   }

   public boolean isMouseButtonDown(int n) {
      return Mouse.isButtonDown(n);
   }

   public MatrixTransformBridge getMatrixBridge() {
      return GlMatrixTransformBridge.getInstance();
   }

   @Override
   public UUID getPlayerUuid(boolean flag) {
      UUID uuid = null;
      Minecraft minecraft = Minecraft.getMinecraft();
      if (flag && minecraft.getRenderViewEntity() != null) {
         uuid = minecraft.getRenderViewEntity().getUniqueID();
      } else if (minecraft.getSession() != null && minecraft.getSession().getProfile() != null) {
         uuid = minecraft.getSession().getProfile().getId();
      }

      return uuid != null ? uuid : UUID.fromString("00000000-0000-0000-0000-000000000000");
   }

   @Override
   public boolean isKeyDown(int n) {
      return Keyboard.isKeyDown(n);
   }

   @Override
   public CosmeticModelLoader createModelLoader() {
      return new MinecraftCosmeticModelLoader();
   }

   @Override
   public int getScrollDelta() {
      return Augustus.getInstance().getMouseWheelTracker().accumulatedDelta;
   }




}
