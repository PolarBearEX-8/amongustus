package client.util;

import client.setting.AttackEffectsExplosionNormalType;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class RayTraceRanker extends Listener87Entry {
   @Override
   public void applyICommandSender(ICommandSender iCommandSender, String[] textArray) throws InternalExceptionAdapter {
      if (textArray.length < 8) {
         throw new AimDispatcher("commands.particle.usage", new Object[0]);
      } else {
         boolean flag = false;
         AttackEffectsExplosionNormalType attackEffectsExplosionNormalType = null;

         for (AttackEffectsExplosionNormalType attackEffectsExplosionNormalType2 : AttackEffectsExplosionNormalType.values()) {
            if (attackEffectsExplosionNormalType2.isActive()) {
               if (textArray[0].startsWith(attackEffectsExplosionNormalType2.getLabelText())) {
                  flag = true;
                  attackEffectsExplosionNormalType = attackEffectsExplosionNormalType2;
                  break;
               }
            } else if (textArray[0].equals(attackEffectsExplosionNormalType2.getLabelText())) {
               flag = true;
               attackEffectsExplosionNormalType = attackEffectsExplosionNormalType2;
               break;
            }
         }

         if (!flag) {
            throw new InternalExceptionAdapter("commands.particle.notFound", new Object[]{textArray[0]});
         } else {
            String text = textArray[0];
            Vec3 vec3 = iCommandSender.getVec3();
            double offset = (double)((float)computeValue(vec3.xCoord, textArray[1], true));
            double offset2 = (double)((float)computeValue(vec3.yCoord, textArray[2], true));
            double offset3 = (double)((float)computeValue(vec3.zCoord, textArray[3], true));
            double offset4 = (double)((float)computeValue(textArray[4]));
            double offset5 = (double)((float)computeValue(textArray[5]));
            double offset6 = (double)((float)computeValue(textArray[6]));
            double offset7 = (double)((float)computeValue(textArray[7]));
            int count = 0;
            if (textArray.length > 8) {
               count = computeCount(textArray[8], 0);
            }

            boolean flag2 = false;
            if (textArray.length > 9 && "force".equals(textArray[9])) {
               flag2 = true;
            }

            World worldObj = iCommandSender.getWorldObj();
            if (worldObj instanceof WorldServer) {
               WorldServer worldServer = (WorldServer)worldObj;
               int[] countArray = new int[attackEffectsExplosionNormalType.getCount()];
               if (attackEffectsExplosionNormalType.isActive()) {
                  String[] textArray2 = textArray[0].split("_", 3);

                  for (int index = 1; index < textArray2.length; index++) {
                     try {
                        countArray[index - 1] = Integer.parseInt(textArray2[index]);
                     } catch (NumberFormatException numberFormatException) {
                        throw new InternalExceptionAdapter("commands.particle.notFound", new Object[]{textArray[0]});
                     }
                  }
               }

               worldServer.applyAttackEffectsExplosionNorm(attackEffectsExplosionNormalType, flag2, offset, offset2, offset3, count, offset4, offset5, offset6, offset7, countArray);
               applyICommandSender(iCommandSender, this, "commands.particle.success", new Object[]{text, Math.max(count, 1)});
            }
         }
      }
   }

   @Override
   public String getText() {
      return "particle";
   }

   @Override
   public List resolveList(ICommandSender iCommandSender, String[] textArray, BlockPos blockPos) {
      return textArray.length == 1
         ? resolveList(textArray, AttackEffectsExplosionNormalType.getStringArray())
         : (
            textArray.length > 1 && textArray.length <= 4
               ? resolveListByTextArray(textArray, 1, blockPos)
               : (textArray.length == 10 ? resolveList(textArray, new String[]{"normal", "force"}) : null)
         );
   }

   @Override
   public int getCount() {
      return 2;
   }

   @Override
   public String resolveText(ICommandSender iCommandSender) {
      return "commands.particle.usage";
   }
}
