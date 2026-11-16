package cc.baritone.utils;

import client.util.IBlockState;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.nbt.NBTTagCompound;

public final class WidthHeightIBlockStateInternalLis extends IBlockStateInternalListen {
   public WidthHeightIBlockStateInternalLis(NBTTagCompound tagCompound) {
      this.count = tagCompound.computeCount("Width");
      this.count2 = tagCompound.computeCount("Height");
      this.count3 = tagCompound.computeCount("Length");
      this.iBlockStateArray = new IBlockState[this.count][this.count3][this.count2];
      Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
      NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Palette");

      for (String text : (Iterable<String>)(Iterable<?>)nBTTagCompound.keySet()) {
         int count = nBTTagCompound.computeCount(text);
         LocationPropertiesController locationPropertiesController = LocationPropertiesController.resolveLocationPropertiesController(text);
         if (locationPropertiesController == null) {
            throw new IllegalArgumentException("Unable to parse palette tag");
         }

         IBlockState blockState = LocationPropertiesController.resolveIBlockState(locationPropertiesController);
         if (blockState == null) {
            throw new IllegalArgumentException("Unable to deserialize palette tag");
         }

         int2ObjectArrayMap.put(count, blockState);
      }

      byte[] byteValueArray = tagCompound.resolveByteArray("BlockData");
      int[] countArray = new int[this.count * this.count2 * this.count3];
      int count2 = 0;

      for (int index = 0; index < countArray.length; index++) {
         if (count2 >= byteValueArray.length) {
            throw new IllegalArgumentException("No remaining bytes in BlockData for complete schematic");
         }

         VarIntSizeController varIntSizeController = VarIntSizeController.resolveVarIntSizeController(byteValueArray, count2);
         countArray[index] = varIntSizeController.getCount();
         count2 += varIntSizeController.getCount2();
      }

      for (int index2 = 0; index2 < this.count2; index2++) {
         for (int index3 = 0; index3 < this.count3; index3++) {
            for (int index4 = 0; index4 < this.count; index4++) {
               int count3 = (index2 * this.count3 + index3) * this.count + index4;
               IBlockState blockState2 = (IBlockState)int2ObjectArrayMap.get(countArray[count3]);
               if (blockState2 == null) {
                  throw new IllegalArgumentException("Invalid Palette Index " + count3);
               }

               this.iBlockStateArray[index4][index3][index2] = blockState2;
            }
         }
      }
   }
}
