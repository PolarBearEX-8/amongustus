package client.util;

import client.Client;
import client.ClientGlobals;
import client.event.ClickTickListener;
import client.event.PostMotionProcessor;
import client.event.PreUpdateInterceptor;
import client.event.RenderTickListener;
import client.event.TickListener;
import client.module.Module;
import client.network.CosmeticsNetworkHandler;
import client.network.CosmeticsPayload;
import client.rotation.Rotation;
import client.rotation.RotationManager;
import client.setting.RenderSliderProperty;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;

public class SupplementalLongEntry implements MinecraftAccessor {
   public static volatile long timestamp = System.currentTimeMillis();
   public static volatile byte[] byteFlagArray = new byte[0];
   public static boolean enabled = false;
   public static volatile long timestamp2 = System.currentTimeMillis();
   public static volatile byte[] byteIndexArray = new byte[0];
   public static volatile boolean enabled2 = false;
   public static CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

   private static void sleep() {
      try {
         Thread.sleep(1L);
      } catch (InterruptedException interruptedException) {
         throw new RuntimeException(interruptedException);
      }
   }

   public static void applyIndex(int tickIndex, int extraTickFlag) {
      CosmeticsNetworkHandler.updateState3();
      CosmeticsNetworkHandler.updateState5();

      if (!copyOnWriteArrayList.isEmpty()) {
         for (Object item : copyOnWriteArrayList) {
            if (item instanceof Runnable) {
               ((Runnable)item).run();
            }
         }
         copyOnWriteArrayList.clear();
      }

      if (minecraft.theWorld != null && minecraft.thePlayer != null) {
         if (extraTickFlag == 0 || ClientGlobals.moduleManager.rotationHandler.settingRotationMode.getSelected().equals("Tick")) {
            RotationManager rotationManager = Client.getClient().getRotationManager();
            Rotation rotation = new Rotation(minecraft.thePlayer.rotationYaw, minecraft.thePlayer.rotationPitch);
            rotationManager.applyRotation(rotation, minecraft.getTimer().indexOffset - tickIndex);
            TickListener.applyRenderSliderProperty(RenderSliderProperty.EARLY);
            rotationManager.updateState();
            CosmeticsNetworkHandler.updateState3();
            CosmeticsNetworkHandler.updateState5();
         }
         ClickTickListener.updateState4();
      }

      if (CosmeticsNetworkHandler.isToggled()
         && CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()
         && minecraft.getServerData() != null
         && minecraft.thePlayer != null
         && minecraft.session != null
         && !minecraft.isFullScreen()
         && (enabled || AccountSessionManager.enabled)) {
         String serverAddress = minecraft.getServerData().text;
         String username = minecraft.session.getText1();
         String playerUuid = minecraft.thePlayer.getGameProfile().getId().toString();
         int entityId = minecraft.thePlayer.getEntityId();
         boolean offlineToken = minecraft.session.getText2() == null || minecraft.session.getText2().isEmpty();
         SessionTokenManager.applyText(serverAddress, username, playerUuid, entityId, offlineToken);
         if (enabled && Client.getClient().getInternalHashMapHolder() != null) {
            Client.getClient().getInternalHashMapHolder().applyTextByText(serverAddress);
         }
         AccountSessionManager.enabled = false;
         enabled = false;
      }

      if (Client.getClient().getModuleManager().cosmetics.isExpanded2()
         && CosmeticsNetworkHandler.isToggled()
         && CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
         CosmeticsNetworkHandler handler = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
         AuxiliaryByteArrayHolder payload = new AuxiliaryByteArrayHolder(byteIndexArray);
         handler.updatePostMotionProcessor(payload);
         Client.getClient().getModuleManager().cosmetics.setExpanded2(false);
      }

      if (!minecraft.isFullScreen()
         && minecraft.getServerData() != null
         && minecraft.thePlayer != null
         && minecraft.thePlayer.ticksExisted % 20 == 0
         && Client.getClient().getInternalHashMapHolder() != null) {
         Client.getClient().getInternalHashMapHolder().applyText(minecraft.getServerData().text);
      }

      minecraft.entityRenderer.processFloat5(1.0F, false);
      if (((Module)Client.getClient().getModuleManager().smartRaytrace).isToggled()) {
         Client.getClient().getModuleManager().smartRaytrace.processAction();
      }

      if (minecraft.theWorld != null && minecraft.thePlayer != null) {
         RenderTickListener.updateState11();
         Minecraft mcRef = minecraft;
         mcRef.movingObjectPosition = new MovingObjectPosition(minecraft.objectMouseOver);
      }

      if (minecraft.getServerData() != null && minecraft.getServerData().text.toLowerCase().contains("ngrok")) {
         CosmeticsPayload.processAction(1);
      }

      if (minecraft.objectMouseOver != null
         && minecraft.objectMouseOver.typeOfHit == MovingObjectType.ENTITY
         && minecraft.objectMouseOver.entityHit instanceof EntityLivingBase) {
         minecraft.indexOffset = minecraft.indexOffset + 1;
         minecraft.rightClickDelayTimer = 0;
      } else {
         minecraft.indexOffset = 0;
         minecraft.rightClickDelayTimer = minecraft.rightClickDelayTimer + 1;
      }

      minecraft.uNKNOWNManager.applyTextByText("gameMode");
      if (!minecraft.state && minecraft.theWorld != null) {
         minecraft.playerController.updateState2();
      }

      minecraft.uNKNOWNManager.applyText("textures");
      if (!minecraft.state) {
         minecraft.getTextureManager().updateState2();
      }

      long now = System.currentTimeMillis();
      for (Integer packetId : (Iterable<Integer>)(Iterable)SessionTokenManager.concurrentHashMap.keySet()) {
         Pair pair = (Pair)SessionTokenManager.concurrentHashMap.get(packetId);
         if (pair == null) {
            continue;
         }
         if (pair.getSecond() == null) {
            SessionTokenManager.concurrentHashMap.remove(packetId);
            continue;
         }
         if (now - (Long)pair.getSecond() > 15000L
            && CosmeticsNetworkHandler.isToggled()
            && CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter().canExecute()) {
            PreUpdateInterceptor first = (PreUpdateInterceptor)pair.getFirst();
            SessionTokenManager.concurrentHashMap.remove(packetId);
            if (first != null) {
               first.setByteFlagArray(byteIndexArray);
               CosmeticsNetworkHandler handler = CosmeticsNetworkHandler.getSimpleChannelInboundHandlerAdapter();
               handler.updatePostMotionProcessor((PostMotionProcessor)first);
               SessionTokenManager.concurrentHashMap.put(packetId, new Pair(now, pair.getFirst()));
            }
         }
      }
   }
}
