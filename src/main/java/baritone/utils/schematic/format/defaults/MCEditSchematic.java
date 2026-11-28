package baritone.utils.schematic.format.defaults;

import baritone.utils.schematic.StaticSchematic;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;

public final class MCEditSchematic extends StaticSchematic {

   public MCEditSchematic(NBTTagCompound tag) {
      String string = tag.getString("Materials");
      if (!string.equals("Alpha")) {
         throw new IllegalStateException("bad schematic " + string);
      } else {
         this.x = tag.getInteger("Width");
         this.y = tag.getInteger("Height");
         this.z = tag.getInteger("Length");
         byte[] byteArray = tag.getByteArray("Blocks");
         byte[] byteArray2 = tag.getByteArray("Data");
         byte[] bytes = null;
         if (tag.hasKey("AddBlocks")) {
            byte[] byteArray3 = tag.getByteArray("AddBlocks");
            bytes = new byte[byteArray3.length * 2];

            for (int i = 0; i < byteArray3.length; i++) {
               bytes[i * 2 + 0] = (byte)(byteArray3[i] >> 4 & 15);
               bytes[i * 2 + 1] = (byte)(byteArray3[i] >> 0 & 15);
            }
         }

         this.states = new IBlockState[this.x][this.z][this.y];

         for (int j = 0; j < this.y; j++) {
            for (int k = 0; k < this.z; k++) {
               for (int m = 0; m < this.x; m++) {
                  int n = (j * this.z + k) * this.x + m;
                  int n2 = byteArray[n] & 255;
                  if (bytes != null) {
                     n2 |= bytes[n] << 8;
                  }

                  Block objectById = (Block)Block.blockRegistry.getObjectById(n2);
                  int n3 = byteArray2[n] & 255;
                  this.states[m][k][j] = objectById.getStateFromMeta(n3);
               }
            }
         }
      }
   }
}
