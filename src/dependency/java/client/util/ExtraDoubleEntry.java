package client.util;

import cc.baritone.utils.BaritoneManager;
import client.Client;
import client.module.Module;
import client.setting.BlockSolidType;
import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL14;

public abstract class ExtraDoubleEntry {
   public double value3;
   public double value;
   public double value2;
   public List list = Lists.newArrayListWithCapacity(17424);
   public boolean enabled;
   public BitSet bitSet2;
   public final BitSet bitSet = new BitSet();

   public abstract void applyBlockSolidType(BlockSolidType blockSolidType);

   public void applyRenderChunk(RenderChunk renderChunk) {
      BlockPos blockPos = this.resolveBlockPos(renderChunk);
      GlStateManager.translate(
         (float)((double)blockPos.getX() - this.value3), (float)((double)blockPos.getY() - this.value), (float)((double)blockPos.getZ() - this.value2)
      );
      Client.getClient().getModuleManager().chunkAnimator.updateItemArrayEntry(renderChunk);
   }

   public void applyDouble(double posX, double posY, double posZ) {
      this.enabled = true;
      this.list.clear();
      this.value3 = posX;
      this.value = posY;
      this.value2 = posZ;
      if (BitSetHolder.isActive()) {
         if (this.bitSet2 != null) {
            BitSetHolder.or(this.bitSet2);
         } else {
            this.bitSet2 = this.bitSet;
         }

         this.bitSet2.clear();
      } else if (this.bitSet2 != null) {
         BitSetHolder.or(this.bitSet2);
         this.bitSet2 = null;
      }
   }

   public void applyRenderChunk(RenderChunk renderChunk, BlockSolidType blockSolidType) {
      this.list.add(renderChunk);
      if (this.bitSet2 != null) {
         BitSet bitSet3 = renderChunk.ironShard.ordinal2(blockSolidType);
         if (bitSet3 != null) {
            this.bitSet2.or(bitSet3);
         }
      }
   }

   private BlockPos resolveBlockPos(RenderChunk renderChunk) {
      if (((Module)Client.getClient().getModuleManager().baritone).isToggled()
         && (Boolean)BaritoneManager.getSettings().renderCachedChunks.dataRef
         && !Minecraft.getMinecraft().isFullScreen()
         && Minecraft.getMinecraft().theWorld.getLoggerEntry(renderChunk.getBlockPos()).canExecute()) {
         GlStateManager.applyState14();
         GlStateManager.disableBlend();
         GL14.glBlendColor(0.0F, 0.0F, 0.0F, (Float)BaritoneManager.getSettings().cachedChunksOpacity.dataRef);
         GlStateManager.tryBlendFuncSeparate(32771, 32772, 1, 0);
      }

      return renderChunk.getBlockPos();
   }
}
