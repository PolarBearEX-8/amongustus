package net.optifine.render;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class ChunkVisibility {
   public static final int MASK_FACINGS = 63;
   public static final EnumFacing[][] enumFacingArrays = makeEnumFacingArrays(false);
   public static final EnumFacing[][] enumFacingOppositeArrays = makeEnumFacingArrays(true);
   private static int counter = 0;
   private static int iMaxStatic = -1;
   private static int iMaxStaticFinal = 16;
   private static World worldLast = null;
   private static int pcxLast = Integer.MIN_VALUE;
   private static int pczLast = Integer.MIN_VALUE;

   public static int getMaxChunkY(World world, Entity entity, int n) {
      int n2 = MathHelper.floor_double(entity.posX) >> 4;
      int n3 = MathHelper.floor_double(entity.posY) >> 4;
      int n4 = MathHelper.floor_double(entity.posZ) >> 4;
      Chunk chunkFromChunkCoords = world.getChunkFromChunkCoords(n2, n4);
      int n5 = n2 - n;
      int n6 = n2 + n;
      int n7 = n4 - n;
      int n8 = n4 + n;
      if (world != worldLast || n2 != pcxLast || n4 != pczLast) {
         counter = 0;
         iMaxStaticFinal = 16;
         worldLast = world;
         pcxLast = n2;
         pczLast = n4;
      }

      if (counter == 0) {
         iMaxStatic = -1;
      }

      int n9 = iMaxStatic;
      switch (counter) {
         case 0:
            n6 = n2;
            n8 = n4;
            break;
         case 1:
            n5 = n2;
            n8 = n4;
            break;
         case 2:
            n6 = n2;
            n7 = n4;
            break;
         case 3:
            n5 = n2;
            n7 = n4;
      }

      for (int i = n5; i < n6; i++) {
         for (int j = n7; j < n8; j++) {
            Chunk chunkFromChunkCoords2 = world.getChunkFromChunkCoords(i, j);
            if (!chunkFromChunkCoords2.isEmpty()) {
               ExtendedBlockStorage[] blockStorageArray = chunkFromChunkCoords2.getBlockStorageArray();

               for (int k = blockStorageArray.length - 1; k > n9; k--) {
                  ExtendedBlockStorage extendedBlockStorage = blockStorageArray[k];
                  if (extendedBlockStorage != null && !extendedBlockStorage.isEmpty()) {
                     n9 = k;
                     break;
                  }
               }

               try {
                  Map tileEntityMap = chunkFromChunkCoords2.getTileEntityMap();
                  if (!tileEntityMap.isEmpty()) {
                     for (BlockPos pos : (Iterable<BlockPos>)(tileEntityMap.keySet())) {
                        int n10 = pos.getY() >> 4;
                        if (n10 > n9) {
                           n9 = n10;
                        }
                     }
                  }
               } catch (ConcurrentModificationException e) {
               }

               ClassInheritanceMultiMap[] entityLists = chunkFromChunkCoords2.getEntityLists();

               for (int m = entityLists.length - 1; m > n9; m--) {
                  ClassInheritanceMultiMap classInheritanceMultiMap = entityLists[m];
                  if (!classInheritanceMultiMap.isEmpty() && (chunkFromChunkCoords2 != chunkFromChunkCoords || m != n3 || classInheritanceMultiMap.size() != 1)) {
                     n9 = m;
                     break;
                  }
               }
            }
         }
      }

      if (counter < 3) {
         iMaxStatic = n9;
         n9 = iMaxStaticFinal;
      } else {
         iMaxStaticFinal = n9;
         iMaxStatic = -1;
      }

      counter = (counter + 1) % 4;
      return n9 << 4;
   }

   private static EnumFacing[][] makeEnumFacingArrays(boolean flag) {
      byte b = 64;
      EnumFacing[][] facingsArr = new EnumFacing[b][];

      for (int i = 0; i < b; i++) {
         ArrayList list = new ArrayList();

         for (int j = 0; j < EnumFacing.VALUES.length; j++) {
            EnumFacing facing = EnumFacing.VALUES[j];
            if ((i & (1 << (flag ? facing.getOpposite() : facing).ordinal())) != 0) {
               list.add(facing);
            }
         }

         facingsArr[i] = (EnumFacing[])list.toArray(new EnumFacing[list.size()]);
      }

      return facingsArr;
   }

   public static void reset() {
      worldLast = null;
   }

   public static boolean getCounter() {
      return counter == 0;
   }

   public static EnumFacing[] getFacingsNotOpposite(int n) {
      int n2 = ~n & 63;
      return enumFacingOppositeArrays[n2];
   }
}
