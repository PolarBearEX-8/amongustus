package client.util;

import client.render.WorldRenderer;
import java.util.UUID;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EmojiUuidMapping implements MinecraftAccessor {
   public int count;
   public UUID uUID;
   public long timestamp2;
   public final MapEntry mapEntry = new MapEntry();
   public long timestamp;

   public void applyEntityPlayer(EntityPlayer player, double posX, double posY, double posZ) {
      EntityPlayerSP player2 = minecraft.thePlayer;
      if (this.mapEntry.getSecondaryResourceLocationEnt() != null
         && minecraft.gameSettings.indexOffset64 == 0
         && !player.equals(player2)
         && !player.matchesEntityPlayer4(player2)
         && (this.mapEntry.getCountAsIndex() == 0 || player.getEntityUniqueID() == this.uUID && System.currentTimeMillis() <= this.timestamp)) {
         SecondaryResourceLocationEntry secondaryResourceLocationEnt = this.mapEntry.getSecondaryResourceLocationEnt();
         double offset = Math.sqrt(posX * posX + posZ * posZ);
         if (offset <= 1.0) {
            return;
         }

         GlStateManager.pushMatrix();
         double offset2 = Math.atan2(posZ, posX);
         double offset3 = Math.atan2(posY, offset);
         double offset4 = 0.8;
         double offset5 = -Math.cos(offset2) * Math.cos(offset3);
         double offset6 = -Math.sin(offset2) * Math.cos(offset3);
         double offset7 = -Math.sin(offset3);
         GL11.glTranslated(posX, posY + (double)player.getHeight2() - 0.2, posZ);
         GL11.glTranslated(offset5 * offset4, offset7 * offset4, offset6 * offset4);
         GL11.glRotated((double)(-minecraft.getRenderManager().scaleFactor), 0.0, 1.0, 0.0);
         GL11.glRotated((double)minecraft.getRenderManager().scaleFactor7, 1.0, 0.0, 0.0);
         GL11.glRotated(180.0, 0.0, 0.0, 0.0);
         GL11.glScaled(-0.02666667, -0.02666667, 0.02666667);
         GL11.glTranslated(0.0, 9.374999, 0.0);
         GL11.glTranslated((double)(-secondaryResourceLocationEnt.count3), (double)(-secondaryResourceLocationEnt.count2), 0.0);
         if (player.isSprinting()) {
            GL11.glTranslated(0.0, -8.0, 0.0);
         }

         GlStateManager.applyState3();
         GlStateManager.depthMask(true);
         GlStateManager.disableBlend();
         GlStateManager.disableTexture2D();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         ResourceLocation resourceLocation = secondaryResourceLocationEnt.getResourceLocation();
         minecraft.getTextureManager().applyResourceLocation3(resourceLocation);
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
         int count2 = secondaryResourceLocationEnt.count;
         worldRenderer.getWorldRenderer((double)(-count2), (double)(-count2), 0.0).getWorldRenderer6(1.0, 1.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer((double)(-count2), (double)count2, 0.0).getWorldRenderer6(1.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer((double)count2, (double)count2, 0.0).getWorldRenderer6(0.0, 0.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         worldRenderer.getWorldRenderer((double)count2, (double)(-count2), 0.0).getWorldRenderer6(0.0, 1.0).resolveWorldRenderer(255, 255, 255, 255).updateState();
         tessellator.updateState();
         GlStateManager.enableBlend();
         GlStateManager.popMatrix();
      }
   }

   public void applyEntity(Entity entity) {
      if (entity != null) {
         if (!entity.equals(minecraft.thePlayer)) {
            UUID entityUniqueID = entity.getEntityUniqueID();
            long timestamp3 = System.currentTimeMillis();
            if (entityUniqueID == this.uUID && timestamp3 < this.timestamp2) {
               this.count++;
               if (this.count >= this.mapEntry.getCountAsIndex()) {
                  this.timestamp = timestamp3 + (long)this.mapEntry.getCount() * 1000L;
               }
            }
         } else {
            this.count = 0;
         }
      }
   }

   public void applyEntity2(Entity entity, int count2, int count3) {
      if (entity instanceof EntityPlayer) {
         UUID entityUniqueID = entity.getEntityUniqueID();
         if (!entityUniqueID.equals(this.uUID)) {
            this.count = 0;
         }

         this.uUID = entityUniqueID;
         this.timestamp2 = System.currentTimeMillis() + 2000L;
      }
   }
}
