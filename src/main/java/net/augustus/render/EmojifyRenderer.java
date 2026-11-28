package net.augustus.render;

import net.augustus.modules.render.EmojiRegistry;
import java.util.UUID;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EmojifyRenderer implements MC {
   private int comboHitCount;
   private long targetTimeoutMs;
   private long displayUntilMs;
   public final EmojiRegistry emojiRegistry = new EmojiRegistry();
   private UUID targetPlayerUuid;

   public void onAttackEntity(Entity entity) {
      if (entity instanceof EntityPlayer) {
         UUID uniqueID = entity.getUniqueID();
         if (!uniqueID.equals(this.targetPlayerUuid)) {
            this.comboHitCount = 0;
         }

         this.targetPlayerUuid = uniqueID;
         this.targetTimeoutMs = System.currentTimeMillis() + 2000L;
      }
   }

   public void renderEmoji(EntityPlayer player, double x, double y, double z) {
      EntityPlayerSP thePlayer = mc.thePlayer;
      if (this.emojiRegistry.getSelectedSprite() != null && mc.gameSettings.thirdPersonView == 0 && !player.equals(thePlayer) && !player.isInvisibleToPlayer(thePlayer)) {
         if (this.emojiRegistry.getComboHits() == 0 || player.getUniqueID().equals(this.targetPlayerUuid) && System.currentTimeMillis() <= this.displayUntilMs) {
            EmojiSprite selectedSprite = this.emojiRegistry.getSelectedSprite();
            double horizontalDistance = Math.sqrt(x * x + z * z);
            if (horizontalDistance <= 1.0) {
               return;
            }

            GlStateManager.pushMatrix();
            double yawRad = Math.atan2(z, x);
            double pitchRad = Math.atan2(y, horizontalDistance);
            double offsetDistance = 0.8;
            double offsetX = -Math.cos(yawRad) * Math.cos(pitchRad);
            double offsetZ = -Math.sin(yawRad) * Math.cos(pitchRad);
            double offsetY = -Math.sin(pitchRad);
            GL11.glTranslated(x, y + (double)player.getEyeHeight() - 0.2, z);
            GL11.glTranslated(offsetX * offsetDistance, offsetY * offsetDistance, offsetZ * offsetDistance);
            GL11.glRotated((double)(-mc.getRenderManager().playerViewY), 0.0, 1.0, 0.0);
            GL11.glRotated((double)mc.getRenderManager().playerViewX, 1.0, 0.0, 0.0);
            GL11.glRotated(180.0, 0.0, 0.0, 1.0);
            GL11.glScaled(-0.02666667, -0.02666667, 0.02666667);
            GL11.glTranslated(0.0, 9.374999, 0.0);
            GL11.glTranslated((double)(-selectedSprite.offsetX), (double)(-selectedSprite.offsetY), 0.0);
            if (player.isSneaking()) {
               GL11.glTranslated(0.0, -8.0, 0.0);
            }

            GlStateManager.disableLighting();
            GlStateManager.depthMask(true);
            GlStateManager.enableBlend();
            GlStateManager.enableTexture2D();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            ResourceLocation resourceLocation = selectedSprite.getResourceLocation();
            mc.getTextureManager().bindTexture(resourceLocation);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int halfSize = selectedSprite.halfSize;
            worldRenderer.pos((double)(-halfSize), (double)(-halfSize), 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
            worldRenderer.pos((double)(-halfSize), (double)halfSize, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
            worldRenderer.pos((double)halfSize, (double)halfSize, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
            worldRenderer.pos((double)halfSize, (double)(-halfSize), 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }
      }
   }

   public void onEntityHurt(Entity entity) {
      if (entity != null) {
         if (!entity.equals(mc.thePlayer)) {
            UUID uniqueID = entity.getUniqueID();
            long now = System.currentTimeMillis();
            if (uniqueID.equals(this.targetPlayerUuid) && now < this.targetTimeoutMs) {
               this.comboHitCount++;
               if (this.comboHitCount >= this.emojiRegistry.getComboHits()) {
                  this.displayUntilMs = now + (long)this.emojiRegistry.getComboTime() * 1000L;
               }
            }
         } else {
            this.comboHitCount = 0;
         }
      }
   }
}
