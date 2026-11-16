package cc.baritone.utils;

import client.util.IBlockState;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;

public final class MaterialsAlphaIBlockStateInternalLis extends IBlockStateInternalListen {
   public MaterialsAlphaIBlockStateInternalLis(NBTTagCompound tagCompound) {
      String text = tagCompound.resolveText("Materials");
      if (!text.equals("Alpha")) {
         throw new IllegalStateException("bad schematic " + text);
      } else {
         this.count = tagCompound.computeCount("Width");
         this.count2 = tagCompound.computeCount("Height");
         this.count3 = tagCompound.computeCount("Length");
         byte[] byteValueArray = tagCompound.resolveByteArray("Blocks");
         byte[] byteValueArray2 = tagCompound.resolveByteArray("Data");
         byte[] byteValueArray3 = null;
         if (tagCompound.containsKey("AddBlocks")) {
            byte[] byteValueArray4 = tagCompound.resolveByteArray("AddBlocks");
            byteValueArray3 = new byte[byteValueArray4.length * 2];

            for (int index = 0; index < byteValueArray4.length; index++) {
               byteValueArray3[index * 2 + 0] = (byte)(byteValueArray4[index] >> 4 & 15);
               byteValueArray3[index * 2 + 1] = (byte)(byteValueArray4[index] >> 0 & 15);
            }
         }

         this.iBlockStateArray = new IBlockState[this.count][this.count3][this.count2];

         for (int index2 = 0; index2 < this.count2; index2++) {
            for (int index3 = 0; index3 < this.count3; index3++) {
               for (int index4 = 0; index4 < this.count; index4++) {
                  int count = (index2 * this.count3 + index3) * this.count + index4;
                  int count2 = byteValueArray[count] & 255;
                  if (byteValueArray3 != null) {
                     count2 |= byteValueArray3[count] << 8;
                  }

                  Block object = (Block)Block.iterableExtensionBridge.getObject3(count2);
                  int count3 = byteValueArray2[count] & 255;
                  this.iBlockStateArray[index4][index3][index2] = object.processAction(count3);
               }
            }
         }
      }
   }
}
