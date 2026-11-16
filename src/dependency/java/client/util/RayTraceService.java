package client.util;

import client.setting.CombatNumberProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RayTraceService extends Listener87Entry {
   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length >= 1 && textArray.length <= 2 ? resolveList(textArray, 0, blockPos) : null;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.spreadplayers.usage";
   }

   private int computeCount(
      WorldController worldController, double offset, World world, Random random, double posX, double posX2, double posY, double posZ, WorldController[] worldControllerArray, boolean flag
   ) throws InternalExceptionAdapter {
      boolean flag2 = true;
      double offset2 = Float.MAX_VALUE;

      int count;
      for (count = 0; count < 10000 && flag2; count++) {
         flag2 = false;
         offset2 = Float.MAX_VALUE;

         for (int index = 0; index < worldControllerArray.length; index++) {
            WorldController worldController2 = worldControllerArray[index];
            int count2 = 0;
            WorldController worldController3 = new WorldController();

            for (int index2 = 0; index2 < worldControllerArray.length; index2++) {
               if (index != index2) {
                  WorldController worldController4 = worldControllerArray[index2];
                  double offset3 = worldController2.computeValue(worldController4);
                  offset2 = Math.min(offset3, offset2);
                  if (offset3 < offset) {
                     count2++;
                     worldController3.value = worldController3.value + (worldController4.value - worldController2.value);
                     worldController3.value2 = worldController3.value2 + (worldController4.value2 - worldController2.value2);
                  }
               }
            }

            if (count2 > 0) {
               worldController3.value /= (double)count2;
               worldController3.value2 /= (double)count2;
               if ((double)worldController3.getFactor() > 0.0) {
                  worldController3.updateState();
                  worldController2.applyWorldController(worldController3);
               } else {
                  worldController2.applyRandom(random, posX, posX2, posY, posZ);
               }

               flag2 = true;
            }

            if (worldController2.checkDouble(posX, posX2, posY, posZ)) {
               flag2 = true;
            }
         }

         if (!flag2) {
            for (WorldController worldController5 : worldControllerArray) {
               if (!worldController5.matchesWorld(world)) {
                  worldController5.applyRandom(random, posX, posX2, posY, posZ);
                  flag2 = true;
               }
            }
         }
      }

      if (count >= 10000) {
         throw new InternalExceptionAdapter(
            "commands.spreadplayers.failure." + (flag ? "teams" : "players"),
            new Object[]{worldControllerArray.length, worldController.value, worldController.value2, String.format("%.2f", offset2)}
         );
      } else {
         return count;
      }
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 6) {
         throw new AimDispatcher("commands.spreadplayers.usage", new Object[0]);
      } else {
         int count = 0;
         BlockPos blockPos = iCommandSender.getBlockPos();
         double coordX = (double)blockPos.getX();
         count++;
         double offset = computeValue(coordX, textArray[0], true);
         coordX = (double)blockPos.getZ();
         count++;
         double offset2 = computeValue(coordX, textArray[1], true);
         count++;
         double offset3 = computeValue(textArray[2], 0.0);
         count++;
         double offset4 = computeValue(textArray[3], offset3 + 1.0);
         count++;
         boolean flag = matchesText(textArray[4]);
         ArrayList list = Lists.newArrayList();

         while (count < textArray.length) {
            String text = textArray[count++];
            if (FovAggregator.matchesText(text)) {
               List list2 = FovAggregator.resolveList(iCommandSender, text, Entity.class);
               if (list2.size() == 0) {
                  throw new AimStack();
               }

               list.addAll(list2);
            } else {
               EntityPlayerAdapter entityPlayerAdapter = VanillaRunnable.getVanillaRunnable().getMovingManager().resolveEntityPlayerAdapter(text);
               if (entityPlayerAdapter == null) {
                  throw new AimProvider();
               }

               list.add(entityPlayerAdapter);
            }
         }

         iCommandSender.applyCombatNumberProperty(CombatNumberProperty.AFFECTED_ENTITIES, list.size());
         if (list.isEmpty()) {
            throw new AimStack();
         } else {
            iCommandSender.applyIChatComponent(
               new BaseManagerStyleIChatComponentAdapter(
                  "commands.spreadplayers.spreading." + (flag ? "teams" : "players"), new Object[]{list.size(), offset4, offset, offset2, offset3}
               )
            );
            this.applyICommandSender(iCommandSender, list, new WorldController(offset, offset2), offset3, offset4, ((Entity)list.get(0)).worldObj, flag);
         }
      }
   }

   private double computeValue(List list, World world, WorldController[] worldControllerArray, boolean flag) {
      double offset = 0.0;
      int count = 0;
      HashMap map = Maps.newHashMap();

      for (int index = 0; index < list.size(); index++) {
         Entity entity = (Entity)list.get(index);
         WorldController worldController;
         if (flag) {
            SecondaryClientManager secondaryClientManager = entity instanceof EntityPlayer ? ((EntityPlayer)entity).getSecondaryClientManager() : null;
            if (!map.containsKey(secondaryClientManager)) {
               map.put(secondaryClientManager, worldControllerArray[count++]);
            }

            worldController = (WorldController)map.get(secondaryClientManager);
         } else {
            worldController = worldControllerArray[count++];
         }

         entity.applyDoubleByDouble(
            (double)((float)MathHelper.ceiling_double_int(worldController.value) + 0.5F),
            (double)worldController.computeCount(world),
            (double)MathHelper.ceiling_double_int(worldController.value2) + 0.5
         );
         double offset2 = Double.MAX_VALUE;

         for (int index2 = 0; index2 < worldControllerArray.length; index2++) {
            if (worldController != worldControllerArray[index2]) {
               offset2 = Math.min(worldController.computeValue(worldControllerArray[index2]), offset2);
            }
         }

         offset += offset2;
      }

      return offset / (double)list.size();
   }

   private int computeCount(List list) {
      HashSet set = Sets.newHashSet();

      for (Object entity_o : list) {
         Entity entity = (Entity)entity_o;
         if (entity instanceof EntityPlayer) {
            set.add(((EntityPlayer)entity).getSecondaryClientManager());
         } else {
            set.add((SecondaryClientManager)null);
         }
      }

      return set.size();
   }

   @Override
   public String getText() {
      return "spreadplayers";
   }

   private WorldController[] resolveWorldControllerArray(Random random, int count, double posX, double posX2, double posY, double posZ) {
      WorldController[] worldControllerArray = new WorldController[count];

      for (int index = 0; index < worldControllerArray.length; index++) {
         WorldController worldController = new WorldController();
         worldController.applyRandom(random, posX, posX2, posY, posZ);
         worldControllerArray[index] = worldController;
      }

      return worldControllerArray;
   }

   private void applyICommandSender(ICommandSender iCommandSender, List list, WorldController worldController, double offset, double offset2, World world, boolean flag) throws InternalExceptionAdapter {
      Random random = new Random();
      double offset3 = worldController.value - offset2;
      double offset4 = worldController.value2 - offset2;
      double offset5 = worldController.value + offset2;
      double offset6 = worldController.value2 + offset2;
      WorldController[] worldControllerArray = this.resolveWorldControllerArray(random, flag ? this.computeCount(list) : list.size(), offset3, offset4, offset5, offset6);
      int count = this.computeCount(worldController, offset, world, random, offset3, offset4, offset5, offset6, worldControllerArray, flag);
      double offset7 = this.computeValue(list, world, worldControllerArray, flag);
      applyICommandSender(iCommandSender, this, "commands.spreadplayers.success." + (flag ? "teams" : "players"), new Object[]{worldControllerArray.length, worldController.value, worldController.value2});
      if (worldControllerArray.length > 1) {
         iCommandSender.applyIChatComponent(
            new BaseManagerStyleIChatComponentAdapter(
               "commands.spreadplayers.info." + (flag ? "teams" : "players"), new Object[]{String.format("%.2f", offset7), count}
            )
         );
      }
   }
}
