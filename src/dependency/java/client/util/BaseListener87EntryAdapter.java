package client.util;

import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;

public class BaseListener87EntryAdapter extends Listener87Entry {
   @Override
   public String getText() {
      return "effect";
   }

   public String[] getTextArray() {
      return VanillaRunnable.getVanillaRunnable().getTextArray();
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, this.getTextArray())
         : (textArray.length == 2 ? resolveList(textArray, Potion.keySet()) : (textArray.length == 5 ? resolveList(textArray, new String[]{"true", "false"}) : null));
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 2) {
         throw new AimDispatcher("commands.effect.usage", new Object[0]);
      } else {
         EntityLivingBase livingEntity = (EntityLivingBase)resolveEntity(iCommandSender, textArray[0], EntityLivingBase.class);
         if (textArray[1].equals("clear")) {
            if (livingEntity.getCollection().isEmpty()) {
               throw new InternalExceptionAdapter("commands.effect.failure.notActive.all", new Object[]{livingEntity.getText()});
            }

            livingEntity.updateState40();
            applyICommandSender(iCommandSender, this, "commands.effect.success.removed.all", new Object[]{livingEntity.getText()});
         } else {
            int count;
            try {
               count = computeCount(textArray[1], 1);
            } catch (AimDirector exception) {
               Potion potion = Potion.get(textArray[1]);
               if (potion == null) {
                  throw exception;
               }

               count = potion.indexOffset3;
            }

            int count2 = 600;
            int count3 = 30;
            int count4 = 0;
            if (count < 0 || count >= Potion.potionArray.length || Potion.potionArray[count] == null) {
               throw new AimDirector("commands.effect.notFound", new Object[]{count});
            }

            Potion potion2 = Potion.potionArray[count];
            if (textArray.length >= 3) {
               count3 = computeCount(textArray[2], 0, 1000000);
               if (potion2.isActive()) {
                  count2 = count3;
               } else {
                  count2 = count3 * 20;
               }
            } else if (potion2.isActive()) {
               count2 = 1;
            }

            if (textArray.length >= 4) {
               count4 = computeCount(textArray[3], 0, 255);
            }

            boolean flag = true;
            if (textArray.length >= 5 && "true".equalsIgnoreCase(textArray[4])) {
               flag = false;
            }

            if (count3 > 0) {
               PotionEffect potionEffect = new PotionEffect(count, count2, count4, false, flag);
               livingEntity.applyPotionEffectByPotionEffect(potionEffect);
               applyICommandSender(
                  iCommandSender,
                  this,
                  "commands.effect.success",
                  new Object[]{new BaseManagerStyleIChatComponentAdapter(potionEffect.processAction2(), new Object[0]), count, count4, livingEntity.getText(), count3}
               );
            } else {
               if (!livingEntity.checkIndexByIndex(count)) {
                  throw new InternalExceptionAdapter(
                     "commands.effect.failure.notActive",
                     new Object[]{new BaseManagerStyleIChatComponentAdapter(potion2.getText(), new Object[0]), livingEntity.getText()}
                  );
               }

               livingEntity.applyIndex5(count);
               applyICommandSender(
                  iCommandSender,
                  this,
                  "commands.effect.success.removed",
                  new Object[]{new BaseManagerStyleIChatComponentAdapter(potion2.getText(), new Object[0]), livingEntity.getText()}
               );
            }
         }
      }
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.effect.usage";
   }

   @Override
   public boolean matchesTextArray(String[] textArray, int count) {
      return count == 0;
   }
}
