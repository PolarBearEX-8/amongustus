package client.util;

import client.event.PostRenderConsumer;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GenericListener87EntryAdapter extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 1) {
         throw new AimDispatcher("commands.summon.usage", new Object[0]);
      } else {
         String text = textArray[0];
         BlockPos blockPos = iCommandSender.getBlockPos();
         Vec3 vec3 = iCommandSender.getVec3();
         double posX = vec3.xCoord;
         double posY = vec3.yCoord;
         double posZ = vec3.zCoord;
         if (textArray.length >= 4) {
            posX = computeValue(posX, textArray[1], true);
            posY = computeValue(posY, textArray[2], false);
            posZ = computeValue(posZ, textArray[3], true);
            blockPos = new BlockPos(posX, posY, posZ);
         }

         World worldObj = iCommandSender.getWorldObj();
         if (!worldObj.checkBlockPos12(blockPos)) {
            throw new InternalExceptionAdapter("commands.summon.outOfWorld", new Object[0]);
         } else {
            if ("LightningBolt".equals(text)) {
               worldObj.checkNoEntityCollision(new HUIHRVManager(worldObj, posX, posY, posZ));
               applyICommandSender(iCommandSender, this, "commands.summon.success", new Object[0]);
            } else {
               NBTTagCompound tagCompound = new NBTTagCompound();
               boolean flag = false;
               if (textArray.length >= 5) {
                  IChatComponent iChatComponent = resolveIChatComponent(iCommandSender, textArray, 4);

                  try {
                     tagCompound = DampenerEstimator.resolveNBTTagCompound(iChatComponent.getString());
                     flag = true;
                  } catch (SecondaryExceptionAdapter exception) {
                     throw new InternalExceptionAdapter("commands.summon.tagError", new Object[]{exception.getMessage()});
                  }
               }

               tagCompound.put("id", text);

               Entity entity;
               try {
                  entity = SkippingEntityWithIdManager.resolveEntityByNBTTagCompound(tagCompound, worldObj);
               } catch (RuntimeException exception2) {
                  throw new InternalExceptionAdapter("commands.summon.failed", new Object[0]);
               }

               if (entity == null) {
                  throw new InternalExceptionAdapter("commands.summon.failed", new Object[0]);
               }

               entity.applyDoubleByDouble(posX, posY, posZ, entity.rotationYaw, entity.rotationPitch);
               if (!flag && entity instanceof EntityMob) {
                  ((EntityMob)entity).resolvePostRenderConsumer(worldObj.resolveGoldenOre(new BlockPos(entity)), (PostRenderConsumer)null);
               }

               worldObj.matchesEntity(entity);

               while (entity != null && tagCompound.matchesText("Riding", 10)) {
                  Entity entity2 = SkippingEntityWithIdManager.resolveEntityByNBTTagCompound(tagCompound.getNBTTagCompound("Riding"), worldObj);
                  if (entity2 != null) {
                     entity2.applyDoubleByDouble(posX, posY, posZ, entity2.rotationYaw, entity2.rotationPitch);
                     worldObj.matchesEntity(entity2);
                     entity.setEntity8(entity2);
                  }

                  entity = entity2;
                  tagCompound = tagCompound.getNBTTagCompound("Riding");
               }

               applyICommandSender(iCommandSender, this, "commands.summon.success", new Object[0]);
            }
         }
      }
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, SkippingEntityWithIdManager.getList())
         : (textArray.length > 1 && textArray.length <= 4 ? resolveListByTextArray(textArray, 1, blockPos) : null);
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.summon.usage";
   }

   @Override
   public String getText() {
      return "summon";
   }
}
