package client.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public final class AuxiliaryNameManager {
   public static boolean matchesLISTManager(LISTManager lISTManager, LISTManager lISTManager2, boolean flag) {
      if (lISTManager == lISTManager2) {
         return true;
      } else if (lISTManager == null) {
         return true;
      } else if (lISTManager2 == null) {
         return false;
      } else if (!lISTManager.getClass().equals(lISTManager2.getClass())) {
         return false;
      } else if (lISTManager instanceof NBTTagCompound) {
         NBTTagCompound tagCompound = (NBTTagCompound)lISTManager;
         NBTTagCompound tagCompound2 = (NBTTagCompound)lISTManager2;

         for (String text : (Iterable<String>)(Iterable<?>)tagCompound.keySet()) {
            if (!matchesLISTManager(tagCompound.get(text), tagCompound2.get(text), flag)) {
               return false;
            }
         }

         return true;
      } else if (lISTManager instanceof NBTTagList && flag) {
         NBTTagList nBTTagList = (NBTTagList)lISTManager;
         NBTTagList nBTTagList2 = (NBTTagList)lISTManager2;
         if (nBTTagList.getInt() == 0) {
            return nBTTagList2.getInt() == 0;
         } else {
            for (int index = 0; index < nBTTagList.getInt(); index++) {
               LISTManager lISTManager3 = nBTTagList.resolveLISTManager(index);
               boolean flag2 = false;

               for (int index2 = 0; index2 < nBTTagList2.getInt(); index2++) {
                  if (matchesLISTManager(lISTManager3, nBTTagList2.resolveLISTManager(index2), flag)) {
                     flag2 = true;
                     break;
                  }
               }

               if (!flag2) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return lISTManager.equals(lISTManager2);
      }
   }

   public static GameProfile resolveGameProfile(NBTTagCompound tagCompound) {
        java.lang.Object entry = null;
      String text = null;
      String text2 = null;
      if (tagCompound.matchesText("Name", 8)) {
         text = tagCompound.resolveText("Name");
      }

      if (tagCompound.matchesText("Id", 8)) {
         text2 = tagCompound.resolveText("Id");
      }

      if (ClientBrandHelper.isEmpty(text) && ClientBrandHelper.isEmpty(text2)) {
         return null;
      } else {
         try {
            entry = UUID.fromString(text2);
         } catch (Throwable throwable) {
            entry = null;
         }

         GameProfile gameProfile = new GameProfile((UUID)(entry), text);
         if (tagCompound.matchesText("Properties", 10)) {
            NBTTagCompound nBTTagCompound = tagCompound.getNBTTagCompound("Properties");

            for (String text3 : (Iterable<String>)(Iterable<?>)nBTTagCompound.keySet()) {
               NBTTagList extendedLoggerEntry = nBTTagCompound.getExtendedLoggerEntry(text3, 10);

               for (int index = 0; index < extendedLoggerEntry.getInt(); index++) {
                  NBTTagCompound nBTTagCompound2 = extendedLoggerEntry.getNBTTagCompound(index);
                  String text4 = nBTTagCompound2.resolveText("Value");
                  if (nBTTagCompound2.matchesText("Signature", 8)) {
                     gameProfile.getProperties().put(text3, new Property(text3, text4, nBTTagCompound2.resolveText("Signature")));
                  } else {
                     gameProfile.getProperties().put(text3, new Property(text3, text4));
                  }
               }
            }
         }

         return gameProfile;
      }
   }

   public static NBTTagCompound resolveNBTTagCompound(NBTTagCompound tagCompound, GameProfile gameProfile) {
      if (!ClientBrandHelper.isEmpty(gameProfile.getName())) {
         tagCompound.put("Name", gameProfile.getName());
      }

      if (gameProfile.getId() != null) {
         tagCompound.put("Id", gameProfile.getId().toString());
      }

      if (!gameProfile.getProperties().isEmpty()) {
         NBTTagCompound tagCompound2 = new NBTTagCompound();

         for (String text : gameProfile.getProperties().keySet()) {
            NBTTagList nBTTagList = new NBTTagList();

            for (Property property : gameProfile.getProperties().get(text)) {
               NBTTagCompound tagCompound3 = new NBTTagCompound();
               tagCompound3.put("Value", property.getValue());
               if (property.hasSignature()) {
                  tagCompound3.put("Signature", property.getSignature());
               }

               nBTTagList.applyLISTManager(tagCompound3);
            }

            tagCompound2.applyText(text, nBTTagList);
         }

         tagCompound.applyText("Properties", tagCompound2);
      }

      return tagCompound;
   }
}
