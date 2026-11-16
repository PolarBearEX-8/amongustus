package client.util;

import client.render.AuxiliaryShadersManager;
import client.render.WorldRenderer;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL20;

public class InternalLongArrayEntry {
   public int count4;
   public int count3;
   public int count2;
   public int count;
   public boolean enabled4;
   public boolean enabled2;
   public boolean enabled;
   public boolean enabled3;
   public long[] timestampArray = new long[10];
   public int count5 = 0;

   public static void applyWorldRenderer(WorldRenderer worldRenderer, int[] countArray) {
      if (worldRenderer.count2 == 0) {
         applyWorldRenderer4(worldRenderer);
      }

      InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
      if (internalLongArrayEntry.count4 == 14) {
         long value = internalLongArrayEntry.timestampArray[internalLongArrayEntry.count5];

         for (int byteValue = 12; byteValue + 1 < countArray.length; byteValue += 14) {
            countArray[byteValue] = (int)value;
            countArray[byteValue + 1] = (int)(value >> 32);
         }
      }
   }

   public static void applyIBlockState(IBlockState blockState, BlockPos blockPos, IBlockAccess iBlockAccess, WorldRenderer worldRenderer) {
      Block block = blockState.getBlock();
      int count6;
      int count7;
      if (blockState instanceof IBlockStateAdapter) {
         IBlockStateAdapter iBlockStateAdapter = (IBlockStateAdapter)blockState;
         count6 = iBlockStateAdapter.getCount2();
         count7 = iBlockStateAdapter.getCountAsIndex();
      } else {
         count6 = Block.getIdFromBlock(block);
         count7 = block.getIndex(blockState);
      }

      int count8 = AuxiliaryShadersManager.computeCount(count6, count7);
      if (count8 >= 0) {
         count6 = count8;
      }

      int count9 = ((block.processAction13() & 65535) << 16) + (count6 & 65535);
      int count10 = count7 & 65535;
      worldRenderer.internalLongArrayEntry.applyTime(((long)count10 << 32) + (long)count9);
   }

   public static void applyWorldRenderer(WorldRenderer worldRenderer, ByteBuffer buffer) {
      if (worldRenderer.count2 == 0) {
         applyWorldRenderer4(worldRenderer);
      }

      InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
      if (internalLongArrayEntry.count4 == 14) {
         long value = internalLongArrayEntry.timestampArray[internalLongArrayEntry.count5];
         int count6 = buffer.limit() / 4;

         for (int byteValue = 12; byteValue + 1 < count6; byteValue += 14) {
            int count7 = (int)value;
            int count8 = (int)(value >> 32);
            buffer.putInt(byteValue * 4, count7);
            buffer.putInt((byteValue + 1) * 4, count8);
         }
      }
   }

   public void updateState() {
      this.timestampArray[this.count5] = 0L;
      this.count5--;
   }

   public static void applyWorldRendererByWorldRenderer(WorldRenderer worldRenderer) {
      if (worldRenderer.getAuxiliaryLoggerEntry().isActive() && worldRenderer.count3 == 7 && worldRenderer.count2 % 4 == 0) {
         InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
         applyWorldRenderer4(worldRenderer);
         int count6 = worldRenderer.count2 * internalLongArrayEntry.count4;

         for (int index = 0; index < count6; index += internalLongArrayEntry.count4 * 4) {
            internalLongArrayEntry.applyWorldRenderer(worldRenderer, index);
         }
      }
   }

   public static void applyWorldRenderer(WorldRenderer worldRenderer) {
      worldRenderer.internalLongArrayEntry.updateState();
   }

   public void applyWorldRenderer(WorldRenderer worldRenderer, int count6) {
      FloatBuffer floatBuffer = worldRenderer.floatBuffer;
      IntBuffer intBuffer = worldRenderer.intBuffer;
      worldRenderer.getCount2();
      float factor = floatBuffer.get(count6 + 0 * this.count4);
      float factor2 = floatBuffer.get(count6 + 0 * this.count4 + 1);
      float factor3 = floatBuffer.get(count6 + 0 * this.count4 + 2);
      float factor4 = floatBuffer.get(count6 + 0 * this.count4 + this.count2);
      float factor5 = floatBuffer.get(count6 + 0 * this.count4 + this.count2 + 1);
      float factor6 = floatBuffer.get(count6 + 1 * this.count4);
      float factor7 = floatBuffer.get(count6 + 1 * this.count4 + 1);
      float factor8 = floatBuffer.get(count6 + 1 * this.count4 + 2);
      float factor9 = floatBuffer.get(count6 + 1 * this.count4 + this.count2);
      float factor10 = floatBuffer.get(count6 + 1 * this.count4 + this.count2 + 1);
      float factor11 = floatBuffer.get(count6 + 2 * this.count4);
      float factor12 = floatBuffer.get(count6 + 2 * this.count4 + 1);
      float factor13 = floatBuffer.get(count6 + 2 * this.count4 + 2);
      float factor14 = floatBuffer.get(count6 + 2 * this.count4 + this.count2);
      float factor15 = floatBuffer.get(count6 + 2 * this.count4 + this.count2 + 1);
      float factor16 = floatBuffer.get(count6 + 3 * this.count4);
      float factor17 = floatBuffer.get(count6 + 3 * this.count4 + 1);
      float factor18 = floatBuffer.get(count6 + 3 * this.count4 + 2);
      float factor19 = floatBuffer.get(count6 + 3 * this.count4 + this.count2);
      float factor20 = floatBuffer.get(count6 + 3 * this.count4 + this.count2 + 1);
      float factor21 = factor11 - factor;
      float factor22 = factor12 - factor2;
      float factor23 = factor13 - factor3;
      float factor24 = factor16 - factor6;
      float factor25 = factor17 - factor7;
      float factor26 = factor18 - factor8;
      float factor27 = factor22 * factor26 - factor25 * factor23;
      float factor28 = factor23 * factor24 - factor26 * factor21;
      float factor29 = factor21 * factor25 - factor24 * factor22;
      float factor30 = factor27 * factor27 + factor28 * factor28 + factor29 * factor29;
      float factor31 = (double)factor30 != 0.0 ? (float)(1.0 / Math.sqrt((double)factor30)) : 1.0F;
      float factor32 = factor27 * factor31;
      float factor33 = factor28 * factor31;
      float factor34 = factor29 * factor31;
      float factor35 = factor6 - factor;
      float factor36 = factor7 - factor2;
      float factor37 = factor8 - factor3;
      float factor38 = factor9 - factor4;
      float factor39 = factor10 - factor5;
      float factor40 = factor11 - factor;
      float factor41 = factor12 - factor2;
      float factor42 = factor13 - factor3;
      float factor43 = factor14 - factor4;
      float factor44 = factor15 - factor5;
      float factor45 = factor38 * factor44 - factor43 * factor39;
      float factor46 = factor45 != 0.0F ? 1.0F / factor45 : 1.0F;
      float factor47 = (factor44 * factor35 - factor39 * factor40) * factor46;
      float factor48 = (factor44 * factor36 - factor39 * factor41) * factor46;
      float factor49 = (factor44 * factor37 - factor39 * factor42) * factor46;
      float factor50 = (factor38 * factor40 - factor43 * factor35) * factor46;
      float factor51 = (factor38 * factor41 - factor43 * factor36) * factor46;
      float factor52 = (factor38 * factor42 - factor43 * factor37) * factor46;
      float factor53 = factor47 * factor47 + factor48 * factor48 + factor49 * factor49;
      float factor54 = (double)factor53 != 0.0 ? (float)(1.0 / Math.sqrt((double)factor53)) : 1.0F;
      float factor55 = factor47 * factor54;
      float factor56 = factor48 * factor54;
      float factor57 = factor49 * factor54;
      float factor58 = factor50 * factor50 + factor51 * factor51 + factor52 * factor52;
      float factor59 = (double)factor58 != 0.0 ? (float)(1.0 / Math.sqrt((double)factor58)) : 1.0F;
      float factor60 = factor50 * factor59;
      float factor61 = factor51 * factor59;
      float factor62 = factor52 * factor59;
      float factor63 = factor34 * factor56 - factor33 * factor57;
      float factor64 = factor32 * factor57 - factor34 * factor55;
      float factor65 = factor33 * factor55 - factor32 * factor56;
      float factor66 = factor60 * factor63 + factor61 * factor64 + factor62 * factor65 < 0.0F ? -1.0F : 1.0F;
      int count7 = (int)(factor32 * 127.0F) & 0xFF;
      int count8 = (int)(factor33 * 127.0F) & 0xFF;
      int count9 = (((int)(factor34 * 127.0F) & 0xFF) << 16) + (count8 << 8) + count7;
      intBuffer.put(count6 + 0 * this.count4 + this.count3, count9);
      intBuffer.put(count6 + 1 * this.count4 + this.count3, count9);
      intBuffer.put(count6 + 2 * this.count4 + this.count3, count9);
      intBuffer.put(count6 + 3 * this.count4 + this.count3, count9);
      int count10 = ((int)(factor55 * 32767.0F) & 65535) + (((int)(factor56 * 32767.0F) & 65535) << 16);
      int count11 = ((int)(factor57 * 32767.0F) & 65535) + (((int)(factor66 * 32767.0F) & 65535) << 16);
      intBuffer.put(count6 + 0 * this.count4 + 10, count10);
      intBuffer.put(count6 + 0 * this.count4 + 10 + 1, count11);
      intBuffer.put(count6 + 1 * this.count4 + 10, count10);
      intBuffer.put(count6 + 1 * this.count4 + 10 + 1, count11);
      intBuffer.put(count6 + 2 * this.count4 + 10, count10);
      intBuffer.put(count6 + 2 * this.count4 + 10 + 1, count11);
      intBuffer.put(count6 + 3 * this.count4 + 10, count10);
      intBuffer.put(count6 + 3 * this.count4 + 10 + 1, count11);
      float factor67 = (factor4 + factor9 + factor14 + factor19) / 4.0F;
      float factor68 = (factor5 + factor10 + factor15 + factor20) / 4.0F;
      floatBuffer.put(count6 + 0 * this.count4 + 8, factor67);
      floatBuffer.put(count6 + 0 * this.count4 + 8 + 1, factor68);
      floatBuffer.put(count6 + 1 * this.count4 + 8, factor67);
      floatBuffer.put(count6 + 1 * this.count4 + 8 + 1, factor68);
      floatBuffer.put(count6 + 2 * this.count4 + 8, factor67);
      floatBuffer.put(count6 + 2 * this.count4 + 8 + 1, factor68);
      floatBuffer.put(count6 + 3 * this.count4 + 8, factor67);
      floatBuffer.put(count6 + 3 * this.count4 + 8 + 1, factor68);
   }

   public static void applyWorldRenderer(int count6, int count7, int count8, WorldRenderer worldRenderer) {
      if (count8 != 0) {
         int count9 = worldRenderer.getAuxiliaryLoggerEntry().getCount2();
         if (count9 == 56) {
            ByteBuffer byteBuffer = worldRenderer.getByteBuffer();
            ((Buffer)byteBuffer).position(32);
            GL20.glVertexAttribPointer(PreEndHandManager.count34, 2, 5126, false, count9, byteBuffer);
            ((Buffer)byteBuffer).position(40);
            GL20.glVertexAttribPointer(PreEndHandManager.count11, 4, 5122, false, count9, byteBuffer);
            ((Buffer)byteBuffer).position(48);
            GL20.glVertexAttribPointer(PreEndHandManager.count39, 3, 5122, false, count9, byteBuffer);
            ((Buffer)byteBuffer).position(0);
            GL20.glEnableVertexAttribArray(PreEndHandManager.count34);
            GL20.glEnableVertexAttribArray(PreEndHandManager.count11);
            GL20.glEnableVertexAttribArray(PreEndHandManager.count39);
            GlStateManager.applyIndex(count6, count7, count8);
            GL20.glDisableVertexAttribArray(PreEndHandManager.count34);
            GL20.glDisableVertexAttribArray(PreEndHandManager.count11);
            GL20.glDisableVertexAttribArray(PreEndHandManager.count39);
         } else {
            GlStateManager.applyIndex(count6, count7, count8);
         }
      }
   }

   public static void applyWorldRenderer5(WorldRenderer worldRenderer) {
      if (worldRenderer.count2 == 0) {
         applyWorldRenderer4(worldRenderer);
      }
   }

   public void applyTime(long value) {
      this.count5++;
      this.timestampArray[this.count5] = value;
   }

   public static void applyWorldRenderer6(WorldRenderer worldRenderer) {
      InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
      if (internalLongArrayEntry.count4 == 14) {
         if (worldRenderer.count3 == 7 && worldRenderer.count2 % 4 == 0) {
            internalLongArrayEntry.applyWorldRenderer(worldRenderer, worldRenderer.getCount2() - 4 * internalLongArrayEntry.count4);
         }

         long value = internalLongArrayEntry.timestampArray[internalLongArrayEntry.count5];
         int count6 = worldRenderer.getCount2() - 14 + 12;
         worldRenderer.intBuffer.put(count6, (int)value);
         worldRenderer.intBuffer.put(count6 + 1, (int)(value >> 32));
      }
   }

   public static void applyWorldRenderer3(WorldRenderer worldRenderer) {
      InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
      if (internalLongArrayEntry.count4 == 14 && worldRenderer.count3 == 7 && worldRenderer.count2 % 4 == 0) {
         internalLongArrayEntry.applyWorldRenderer(worldRenderer, worldRenderer.getCount2() - 4 * internalLongArrayEntry.count4);
      }
   }

   public static boolean matchesWorldRenderer(boolean flag, WorldRenderer worldRenderer) {
      worldRenderer.internalLongArrayEntry.updateState();
      return flag;
   }

   public static void applyWorldRenderer4(WorldRenderer worldRenderer) {
      InternalLongArrayEntry internalLongArrayEntry = worldRenderer.internalLongArrayEntry;
      AuxiliaryLoggerEntry auxiliaryLoggerEntry = worldRenderer.getAuxiliaryLoggerEntry();
      internalLongArrayEntry.count4 = auxiliaryLoggerEntry.getCount2() / 4;
      internalLongArrayEntry.enabled4 = auxiliaryLoggerEntry.isActive();
      internalLongArrayEntry.enabled2 = internalLongArrayEntry.enabled4;
      internalLongArrayEntry.enabled = auxiliaryLoggerEntry.size(0);
      internalLongArrayEntry.count3 = internalLongArrayEntry.enabled4 ? auxiliaryLoggerEntry.getCount() / 4 : 0;
      internalLongArrayEntry.count2 = internalLongArrayEntry.enabled ? auxiliaryLoggerEntry.getInt(0) / 4 : 0;
      internalLongArrayEntry.count = 8;
   }

   public static void applyWorldRenderer2(WorldRenderer worldRenderer) {
      worldRenderer.internalLongArrayEntry = new InternalLongArrayEntry();
   }

   public InternalLongArrayEntry() {
      this.timestampArray[this.count5] = 0L;
   }
}
