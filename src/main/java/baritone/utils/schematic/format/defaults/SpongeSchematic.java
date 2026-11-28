package baritone.utils.schematic.format.defaults;

import baritone.utils.type.VarInt;
import baritone.utils.schematic.StaticSchematic;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

public final class SpongeSchematic extends StaticSchematic {

   public SpongeSchematic(NBTTagCompound tag) {
      this.x = tag.getInteger("Width");
      this.y = tag.getInteger("Height");
      this.z = tag.getInteger("Length");
      this.states = new IBlockState[this.x][this.z][this.y];
      Int2ObjectArrayMap int2ObjectArrayMap = new Int2ObjectArrayMap();
      NBTTagCompound compoundTag = tag.getCompoundTag("Palette");

      for (String str : (Iterable<String>)(compoundTag.getKeySet())) {
         int integer = compoundTag.getInteger(str);
         SerializedBlockState serializedBlockState = SerializedBlockState.fromString(str);
         if (serializedBlockState == null) {
            throw new IllegalArgumentException("Unable to parse palette tag");
         }

         IBlockState blockState = SerializedBlockState.getBlockState(serializedBlockState);
         if (blockState == null) {
            throw new IllegalArgumentException("Unable to deserialize palette tag");
         }

         int2ObjectArrayMap.put(integer, blockState);
      }

      byte[] byteArray = tag.getByteArray("BlockData");
      int[] ints = new int[this.x * this.y * this.z];
      int n = 0;

      for (int i = 0; i < ints.length; i++) {
         if (n >= byteArray.length) {
            throw new IllegalArgumentException("No remaining bytes in BlockData for complete schematic");
         }

         VarInt varInt = VarInt.read(byteArray, n);
         ints[i] = varInt.getValue();
         n += varInt.getSize();
      }

      for (int j = 0; j < this.y; j++) {
         for (int k = 0; k < this.z; k++) {
            for (int m = 0; m < this.x; m++) {
               int n2 = (j * this.z + k) * this.x + m;
               IBlockState state = (IBlockState)int2ObjectArrayMap.get(ints[n2]);
               if (state == null) {
                  throw new IllegalArgumentException("Invalid Palette Index " + n2);
               }

               this.states[m][k][j] = state;
            }
         }
      }
   }
}
