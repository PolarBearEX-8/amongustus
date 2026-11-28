package net.minecraft.client.renderer;

import baritone.Baritone;
import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.augustus.modules.Module;
import net.augustus.Augustus;
import net.optifine.SmartAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import org.lwjgl.opengl.GL14;

public abstract class ChunkRenderContainer {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;
   public List renderChunks = Lists.newArrayListWithCapacity(17424);
   public boolean initialized;
   private BitSet animatedSpritesRendered;
   private final BitSet animatedSpritesCached = new BitSet();

   public abstract void renderChunkLayer(EnumWorldBlockLayer enumWorldBlockLayer);

   public void preRenderChunk(RenderChunk renderChunk) {
      BlockPos renderChunkPosition = this.getRenderChunkPosition(renderChunk);
      GlStateManager.translate((float)((double)renderChunkPosition.getX() - this.viewEntityX), (float)((double)renderChunkPosition.getY() - this.viewEntityY), (float)((double)renderChunkPosition.getZ() - this.viewEntityZ));
      Augustus.getInstance().getModuleManager().chunkAnimator.applyChunkAnimation(renderChunk);
   }

   public void addRenderChunk(RenderChunk renderChunk, EnumWorldBlockLayer enumWorldBlockLayer) {
      this.renderChunks.add(renderChunk);
      if (this.animatedSpritesRendered != null) {
         BitSet animatedSprites = renderChunk.compiledChunk.getAnimatedSprites(enumWorldBlockLayer);
         if (animatedSprites != null) {
            this.animatedSpritesRendered.or(animatedSprites);
         }
      }
   }

   private BlockPos getRenderChunkPosition(RenderChunk renderChunk) {
      if (((Module)Augustus.getInstance().getModuleManager().baritone).isToggled()
         && (Boolean)Baritone.settings().renderCachedChunks.value
         && !Minecraft.getMinecraft().isSingleplayer()
         && Minecraft.getMinecraft().theWorld.getChunkFromBlockCoords(renderChunk.getPosition()).isEmpty()) {
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         GL14.glBlendColor(0.0F, 0.0F, 0.0F, (Float)Baritone.settings().cachedChunksOpacity.value);
         GlStateManager.tryBlendFuncSeparate(32771, 32772, 1, 0);
      }

      return renderChunk.getPosition();
   }

   public void initialize(double d, double d2, double d3) {
      this.initialized = true;
      this.renderChunks.clear();
      this.viewEntityX = d;
      this.viewEntityY = d2;
      this.viewEntityZ = d3;
      if (SmartAnimations.isActive()) {
         if (this.animatedSpritesRendered != null) {
            SmartAnimations.spritesRendered(this.animatedSpritesRendered);
         } else {
            this.animatedSpritesRendered = this.animatedSpritesCached;
         }

         this.animatedSpritesRendered.clear();
      } else if (this.animatedSpritesRendered != null) {
         SmartAnimations.spritesRendered(this.animatedSpritesRendered);
         this.animatedSpritesRendered = null;
      }
   }
}
