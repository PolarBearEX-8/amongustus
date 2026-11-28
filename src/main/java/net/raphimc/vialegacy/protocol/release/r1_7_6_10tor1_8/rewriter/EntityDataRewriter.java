package net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.rewriter;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.minecraft.entities.EntityTypes1_8.EntityType;
import com.viaversion.viaversion.api.minecraft.entitydata.EntityData;
import com.viaversion.viaversion.api.minecraft.item.Item;
import com.viaversion.viaversion.util.IdAndData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.raphimc.vialegacy.ViaLegacy;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.Protocolr1_7_6_10Tor1_8;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.data.EntityDataIndex1_7_6;
import net.raphimc.vialegacy.protocol.release.r1_7_6_10tor1_8.storage.ChunkTracker;

public class EntityDataRewriter {
   final Protocolr1_7_6_10Tor1_8 protocol;

   public EntityDataRewriter(Protocolr1_7_6_10Tor1_8 protocol) {
      this.protocol = protocol;
   }

   public void transform(UserConnection user, EntityType type, List<EntityData> list) {
      for (EntityData entry : new ArrayList<>(list)) {
         EntityDataIndex1_7_6 entityDataIndex = EntityDataIndex1_7_6.searchIndex(type, entry.id());

         try {
            if (entityDataIndex == null) {
               if (Via.getConfig().logEntityDataErrors()) {
                  Logger logger = ViaLegacy.getPlatform().getLogger();
                  String str = type.name();
                  String str2 = String.valueOf(entry);
                  logger.warning("Could not find valid entity data index entry for " + str + ": " + str2);
               }

               list.remove(entry);
            } else {
               Object value = entry.getValue();
               entry.setTypeAndValue(entityDataIndex.getOldType(), value);
               entry.setDataTypeUnsafe(entityDataIndex.getNewType());
               entry.setId(entityDataIndex.getNewIndex());
               if (entityDataIndex == EntityDataIndex1_7_6.ENTITY_AGEABLE_AGE) {
                  entry.setValue(Byte.valueOf((byte)((Integer)value < 0 ? -1 : 0)));
               } else if (entityDataIndex == EntityDataIndex1_7_6.ITEM_FRAME_ROTATION) {
                  entry.setValue(Integer.valueOf((Byte)value * 2).byteValue());
               } else if (entityDataIndex != EntityDataIndex1_7_6.ENDERMAN_CARRIED_BLOCK) {
                  if (entityDataIndex == EntityDataIndex1_7_6.HUMAN_SKIN_FLAGS) {
                     byte flags = (Byte)value;
                     flags = (byte)(((flags & 2) == 0) ? 127 : 126);
                     entry.setValue(flags);
                  } else {
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
                           break;
                        case ITEM:
                           this.protocol.getItemRewriter().handleItemToClient(user, (Item)value);
                        case STRING:
                        case BLOCK_POSITION:
                        case ROTATIONS:
                           break;
                        default:
                           if (Via.getConfig().logEntityDataErrors()) {
                              Logger logger2 = ViaLegacy.getPlatform().getLogger();
                              String str4 = String.valueOf(entityDataIndex.getNewType());
                              String str5 = String.valueOf(entry);
                              logger2.warning("1.7.10 EntityDataRewriter: Unhandled Type: " + str4 + " " + str5);
                           }

                           list.remove(entry);
                     }
                  }
               } else {
                  byte id = (Byte)value;
                  EntityData blockDataMeta = null;
                  Iterator data = list.iterator();

                  while (true) {
                     if (data.hasNext()) {
                        EntityData entityData = (EntityData)data.next();
                        if (entityData.id() != EntityDataIndex1_7_6.ENDERMAN_CARRIED_BLOCK_DATA.getOldIndex()) {
                           continue;
                        }

                        blockDataMeta = entityData;
                        list.remove(entityData);
                     }

                     byte datax = blockDataMeta != null ? (Byte)blockDataMeta.getValue() : 0;
                     IdAndData block = new IdAndData(id, datax);
                     ((ChunkTracker)user.get(ChunkTracker.class)).remapBlockParticle(block);
                     entry.setValue((short)(block.getId() | block.getData() << 12));
                     break;
                  }
               }
            }
         } catch (Throwable e) {
            if (Via.getConfig().logEntityDataErrors()) {
               Logger logger3 = ViaLegacy.getPlatform().getLogger();
               Level warning = Level.WARNING;
               String str7 = type.name();
               String str8 = String.valueOf(entry);
               logger3.log(warning, "Error rewriting entity data entry for " + str7 + ": " + str8, e);
            }

            list.remove(entry);
         }
      }
   }
}
