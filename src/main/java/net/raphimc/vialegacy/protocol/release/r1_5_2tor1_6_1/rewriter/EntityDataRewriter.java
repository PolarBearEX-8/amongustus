package net.raphimc.vialegacy.protocol.release.r1_5_2tor1_6_1.rewriter;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import com.viaversion.viaversion.api.minecraft.entitydata.EntityData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.raphimc.vialegacy.ViaLegacy;
import net.raphimc.vialegacy.protocol.release.r1_5_2tor1_6_1.data.EntityDataIndex1_5_2;

public class EntityDataRewriter {
   public static void transform(EntityType type, List<EntityData> list) {
      for (EntityData entry : new ArrayList<>(list)) {
         EntityDataIndex1_5_2 entityDataIndex = EntityDataIndex1_5_2.searchIndex(type, entry.id());

         try {
            if (entityDataIndex != null) {
               Object value = entry.getValue();
               entry.setTypeAndValue(entityDataIndex.getOldType(), value);
               entry.setDataTypeUnsafe(entityDataIndex.getNewType());
               entry.setId(entityDataIndex.getNewIndex());
               switch (entityDataIndex.getNewType()) {
                  case BYTE:
                     entry.setValue(((Number)value).byteValue());
                     break;
                  case SHORT:
                     entry.setValue(((Number)value).shortValue());
                     break;
                  case INT:
                     entry.setValue(((Number)value).intValue());
                     break;
                  case FLOAT:
                     entry.setValue(((Number)value).floatValue());
                  case ITEM:
                  case STRING:
                  case BLOCK_POSITION:
                     break;
                  default:
                     if (Via.getConfig().logEntityDataErrors()) {
                        Logger logger = ViaLegacy.getPlatform().getLogger();
                        String str = String.valueOf(entityDataIndex.getNewType());
                        String str2 = String.valueOf(entry);
                        logger.warning("1.5.2 EntityDataRewriter: Unhandled Type: " + str + " " + str2);
                     }

                     list.remove(entry);
               }
            }
         } catch (Throwable e) {
            if (Via.getConfig().logEntityDataErrors()) {
               Logger logger2 = ViaLegacy.getPlatform().getLogger();
               Level warning = Level.WARNING;
               String str4 = type.name();
               String str5 = String.valueOf(entry);
               logger2.log(warning, "Error rewriting entity data entry for " + str4 + ": " + str5, e);
            }

            list.remove(entry);
         }
      }
   }
}
