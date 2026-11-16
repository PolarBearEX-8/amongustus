package client.util;

import client.setting.BlockNoneType;
import client.setting.OptionType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.item.GoldenLeggings;
import net.minecraft.item.IronDust;
import net.minecraft.item.SpecialItemEntry;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class NormalManager {
   public static final float factor = 1.0E-6F;

   public static IBakedModel resolveIBakedModel(ServiceStringEntry serviceStringEntry, int count) {
      ArrayList list = new ArrayList();
      EnumFacing[] enumFacingArray = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < enumFacingArray.length; index++) {
         EnumFacing enumFacing = enumFacingArray[index];
         ArrayList list3 = new ArrayList();
         list3.add(resolveLocalIntArrayEntry(enumFacing, serviceStringEntry, count));
         list2.add(list3);
      }

      return new ListenerWrapper(list, list2, true, true, serviceStringEntry, SpecialItemEntry.specialItemEntry);
   }

   public static void applyVector3f(Vector3f vector3f) {
      vector3f.setX(computeFactor(vector3f.getX()));
      vector3f.setY(computeFactor(vector3f.getY()));
      vector3f.setZ(computeFactor(vector3f.getZ()));
   }

   private static float computeFactor(float factor2) {
      return factor2 > -1.0E-6F && factor2 < 1.0E-6F ? 0.0F : (factor2 > 0.999999F && factor2 < 1.000001F ? 1.0F : factor2);
   }

   public static IBakedModel resolveIBakedModel(IBakedModel iBakedModel, IBakedModel iBakedModel2) {
      ArrayList list = new ArrayList();
      list.addAll(iBakedModel.getList());
      list.addAll(iBakedModel2.getList());
      EnumFacing[] enumFacingArray = EnumFacing.VALUES;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < enumFacingArray.length; index++) {
         EnumFacing enumFacing = enumFacingArray[index];
         ArrayList list3 = new ArrayList();
         list3.addAll(iBakedModel.resolveList(enumFacing));
         list3.addAll(iBakedModel2.resolveList(enumFacing));
         list2.add(list3);
      }

      boolean enabled = iBakedModel.isEnabled();
      boolean active = iBakedModel.isActive();
      ServiceStringEntry serviceStringEntry = iBakedModel.getServiceStringEntry();
      SpecialItemEntry specialItemEntry = iBakedModel.getSpecialItemEntry();
      return new ListenerWrapper(list, list2, enabled, active, serviceStringEntry, specialItemEntry);
   }

   public static LocalIntArrayEntry resolveLocalIntArrayEntry(EnumFacing enumFacing, ServiceStringEntry serviceStringEntry, int count) {
      Vector3f vector3f = new Vector3f(0.0F, 0.0F, 0.0F);
      Vector3f vector3f2 = new Vector3f(16.0F, 16.0F, 16.0F);
      TertiaryFloatArrayEntry tertiaryFloatArrayEntry = new TertiaryFloatArrayEntry(new float[]{0.0F, 0.0F, 16.0F, 16.0F}, 0);
      EnumFacingHolder enumFacingHolder = new EnumFacingHolder(enumFacing, count, "#" + enumFacing.getText(), tertiaryFloatArrayEntry);
      OptionType optionType = OptionType.X0_Y0;
      return new SystemFloatEntry().resolveLocalIntArrayEntry(vector3f, vector3f2, enumFacingHolder, serviceStringEntry, enumFacing, optionType, null, false, true);
   }

   public static IBakedModel resolveIBakedModel(String text, ServiceStringEntry serviceStringEntry, ServiceStringEntry serviceStringEntry2) {
      if (serviceStringEntry != null && serviceStringEntry2 != null) {
         IronDust ironDust = BuildManager.getIronDust();
         if (ironDust == null) {
            return null;
         } else {
            ResourceLocationAdapter resourceLocationAdapter = new ResourceLocationAdapter(text, "normal");
            IBakedModel iBakedModel = ironDust.resolveIBakedModel(resourceLocationAdapter);
            if (iBakedModel != null && iBakedModel != ironDust.getIBakedModel()) {
               IBakedModel iBakedModel2 = LengthManager.resolveIBakedModel(iBakedModel);
               EnumFacing[] enumFacingArray = EnumFacing.VALUES;

               for (int index = 0; index < enumFacingArray.length; index++) {
                  EnumFacing enumFacing = enumFacingArray[index];
                  applyList(iBakedModel2.resolveList(enumFacing), serviceStringEntry, serviceStringEntry2);
               }

               applyList(iBakedModel2.getList(), serviceStringEntry, serviceStringEntry2);
               return iBakedModel2;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public static IBakedModel resolveIBakedModel(String text, String text2, String text3) {
      ListenerAdapter27 listenerAdapter = BuildManager.getMc().getListenerAdapter27();
      ServiceStringEntry serviceStringEntry = listenerAdapter.resolveServiceStringEntry(text2);
      ServiceStringEntry serviceStringEntry2 = listenerAdapter.resolveServiceStringEntry(text3);
      return resolveIBakedModel(text, serviceStringEntry, serviceStringEntry2);
   }

   public static AxisAlignedBB resolveAxisAlignedBB(AxisAlignedBB boundingBox, BlockNoneType blockNoneType, BlockPos blockPos) {
      int coordX = blockPos.getX();
      int coordZ = blockPos.getZ();
      long keySalt = (long)(coordX * 3129871) ^ (long)coordZ * 116129781L;
      long value = keySalt * keySalt * 42317861L + keySalt * 11L;
      double offset = ((double)((float)(value >> 16 & 15L) / 15.0F) - 0.5) * 0.5;
      double offset2 = ((double)((float)(value >> 24 & 15L) / 15.0F) - 0.5) * 0.5;
      double offset3 = 0.0;
      if (blockNoneType == BlockNoneType.XYZ) {
         offset3 = ((double)((float)(value >> 20 & 15L) / 15.0F) - 1.0) * 0.2;
      }

      return boundingBox.expand(offset, offset3, offset2);
   }

   public static IBakedModel resolveIBakedModel(String text, int count) {
      return resolveIBakedModel(BuildManager.getMc().getListenerAdapter27().get(text), count);
   }

   private static void applyList(List list, ServiceStringEntry serviceStringEntry, ServiceStringEntry serviceStringEntry2) {
      ArrayList list2 = new ArrayList();

      for (Object element : list) {
         if (((LocalIntArrayEntry)element).getServiceStringEntry() == serviceStringEntry) {
            element = new GoldenLeggings((LocalIntArrayEntry)element, serviceStringEntry2);
         }

         list2.add(element);
      }

      list.clear();
      list.addAll(list2);
   }
}
