package net.minecraft.client.model;

import client.setting.MovementProperty;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.LengthManager;
import client.util.LocalIntArrayEntry;
import client.util.ResourceLocationAdapter;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.AuxiliaryBlockLeaves;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.IronDust;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class ModelManager {
   public static IBakedModel iBakedModel6 = null;
   public static IBakedModel iBakedModel10 = null;
   public static IBakedModel iBakedModel = null;
   public static IBakedModel iBakedModel2 = null;
   public static IBakedModel iBakedModel8 = null;
   public static IBakedModel iBakedModel9 = null;
   public static List list4 = null;
   public static List list3 = null;
   public static List list6 = null;
   public static List list2 = null;
   public static List list5 = null;
   public static List list = null;
   public static IBakedModel iBakedModel12 = null;
   public static IBakedModel iBakedModel4 = null;
   public static IBakedModel iBakedModel5 = null;
   public static IBakedModel iBakedModel7 = null;
   public static IBakedModel iBakedModel11 = null;
   public static IBakedModel iBakedModel3 = null;

   private static List resolveList(IBakedModel iBakedModel13) {
      return iBakedModel13 == null ? null : iBakedModel13.getList();
   }

   private static IBakedModel resolveIBakedModel(IBakedModel iBakedModel13) {
      if (iBakedModel13 == null) {
         return null;
      } else if (iBakedModel13.getList().size() > 0) {
         BuildManager.applyText3("SmartLeaves: Model is not cube, general quads: " + iBakedModel13.getList().size() + ", model: " + iBakedModel13);
         return iBakedModel13;
      } else {
         EnumFacing[] enumFacingArray = EnumFacing.VALUES;

         for (int index = 0; index < enumFacingArray.length; index++) {
            EnumFacing enumFacing = enumFacingArray[index];
            List list7 = iBakedModel13.resolveList(enumFacing);
            if (list7.size() != 1) {
               BuildManager.applyText3("SmartLeaves: Model is not cube, side: " + enumFacing + ", quads: " + list7.size() + ", model: " + iBakedModel13);
               return iBakedModel13;
            }
         }

         IBakedModel iBakedModel14 = LengthManager.resolveIBakedModel(iBakedModel13);
         List[] listArray = new List[enumFacingArray.length];

         for (int index2 = 0; index2 < enumFacingArray.length; index2++) {
            EnumFacing enumFacing2 = enumFacingArray[index2];
            List list8 = iBakedModel14.resolveList(enumFacing2);
            LocalIntArrayEntry localIntArrayEntry = (LocalIntArrayEntry)list8.get(0);
            LocalIntArrayEntry localIntArrayEntry2 = new LocalIntArrayEntry(
               (int[])localIntArrayEntry.getIndexArray().clone(), localIntArrayEntry.getCount(), localIntArrayEntry.getEnumFacing(), localIntArrayEntry.getServiceStringEntry()
            );
            int[] indexArray = localIntArrayEntry2.getIndexArray();
            int[] countArray = (int[])indexArray.clone();
            int count = indexArray.length / 4;
            System.arraycopy(indexArray, 0 * count, countArray, 3 * count, count);
            System.arraycopy(indexArray, 1 * count, countArray, 2 * count, count);
            System.arraycopy(indexArray, 2 * count, countArray, 1 * count, count);
            System.arraycopy(indexArray, 3 * count, countArray, 0 * count, count);
            System.arraycopy(countArray, 0, indexArray, 0, countArray.length);
            list8.add(localIntArrayEntry2);
         }

         return iBakedModel14;
      }
   }

   public static IBakedModel resolveIBakedModel(String text, List list7) {
      IronDust ironDust = BuildManager.getIronDust();
      if (ironDust == null) {
         return null;
      } else if (BuildManager.resolvePreUpdateProcessor(new ResourceLocation("blockstates/" + text + "_leaves.json"))
         != BuildManager.getIReloadableResourceManager()) {
         return null;
      } else if (BuildManager.resolvePreUpdateProcessor(new ResourceLocation("models/block/" + text + "_leaves.json"))
         != BuildManager.getIReloadableResourceManager()) {
         return null;
      } else {
         ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter(text + "_leaves", "normal");
         IBakedModel iBakedModel13 = ironDust.resolveIBakedModel(resourceLocationAdapter);
         if (iBakedModel13 != null && iBakedModel13 != ironDust.getIBakedModel()) {
            List list8 = iBakedModel13.getList();
            if (list8.size() == 0) {
               return iBakedModel13;
            } else if (list8.size() != 6) {
               return null;
            } else {
               for (LocalIntArrayEntry localIntArrayEntry : (Iterable<LocalIntArrayEntry>) (list8)) {
                  List list9 = iBakedModel13.resolveList(localIntArrayEntry.getEnumFacing());
                  if (list9.size() > 0) {
                     return null;
                  }

                  list9.add(localIntArrayEntry);
               }

               list8.clear();
               list7.add(text + "_leaves");
               return iBakedModel13;
            }
         } else {
            return null;
         }
      }
   }

   public static boolean matchesIBlockState(IBlockState blockState, IBlockState blockState2) {
      if (blockState == blockState2) {
         return true;
      } else {
         Block block = blockState.getBlock();
         Block block2 = blockState2.getBlock();
         return block != block2
            ? false
            : (
               block instanceof BlockLeaves
                  ? ((MovementProperty)blockState.getComparable(BlockLeaves.immutableSetEntry)).equals(blockState2.getComparable(BlockLeaves.immutableSetEntry))
                  : (
                     block instanceof AuxiliaryBlockLeaves
                        ? ((MovementProperty)blockState.getComparable(AuxiliaryBlockLeaves.immutableSetEntry))
                           .equals(blockState2.getComparable(AuxiliaryBlockLeaves.immutableSetEntry))
                        : false
                  )
            );
      }
   }

   public static IBakedModel resolveIBakedModel(IBakedModel iBakedModel13, IBlockState blockState) {
      if (!BuildManager.isActive71()) {
         return iBakedModel13;
      } else {
         List list7 = iBakedModel13.getList();
         return list7 == list4
            ? iBakedModel12
            : (
               list7 == list3
                  ? iBakedModel4
                  : (list7 == list6 ? iBakedModel5 : (list7 == list2 ? iBakedModel7 : (list7 == list5 ? iBakedModel11 : (list7 == list ? iBakedModel3 : iBakedModel13))))
            );
      }
   }

   public static void updateState() {
      ArrayList list7 = new ArrayList();
      iBakedModel6 = resolveIBakedModel("acacia", list7);
      iBakedModel10 = resolveIBakedModel("birch", list7);
      iBakedModel = resolveIBakedModel("dark_oak", list7);
      iBakedModel2 = resolveIBakedModel("jungle", list7);
      iBakedModel8 = resolveIBakedModel("oak", list7);
      iBakedModel9 = resolveIBakedModel("spruce", list7);
      list4 = resolveList(iBakedModel6);
      list3 = resolveList(iBakedModel10);
      list6 = resolveList(iBakedModel);
      list2 = resolveList(iBakedModel2);
      list5 = resolveList(iBakedModel8);
      list = resolveList(iBakedModel9);
      iBakedModel12 = resolveIBakedModel(iBakedModel6);
      iBakedModel4 = resolveIBakedModel(iBakedModel10);
      iBakedModel5 = resolveIBakedModel(iBakedModel);
      iBakedModel7 = resolveIBakedModel(iBakedModel2);
      iBakedModel11 = resolveIBakedModel(iBakedModel8);
      iBakedModel3 = resolveIBakedModel(iBakedModel9);
      if (list7.size() > 0) {
         BuildManager.applyText4("Enable face culling: " + BuildManager.resolveText(list7.toArray()));
      }
   }
}
