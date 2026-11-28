package net.optifine.shaders;

import java.util.ArrayList;
import java.util.HashSet;
import net.optifine.config.Config;
import net.optifine.config.MatchBlock;

public class BlockAlias {
   private int blockAliasId;
   private MatchBlock[] matchBlocks;

   public int getBlockAliasId() {
      return this.blockAliasId;
   }

   public BlockAlias(int n, MatchBlock[] matchBlocks2) {
      this.blockAliasId = n;
      this.matchBlocks = matchBlocks2;
   }

   public boolean matches(int n, int n2) {
      for (int i = 0; i < this.matchBlocks.length; i++) {
         if (this.matchBlocks[i].matches(n, n2)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      return "block." + this.blockAliasId + "=" + Config.arrayToString3(this.matchBlocks);
   }

   public MatchBlock[] getMatchBlocks(int n) {
      ArrayList list = new ArrayList();

      for (int i = 0; i < this.matchBlocks.length; i++) {
         MatchBlock matchBlock = this.matchBlocks[i];
         if (matchBlock.getBlockId() == n) {
            list.add(matchBlock);
         }
      }

      return (MatchBlock[])list.toArray(new MatchBlock[list.size()]);
   }

   public int[] getMatchBlockIds() {
      HashSet set = new HashSet();

      for (int i = 0; i < this.matchBlocks.length; i++) {
         set.add(this.matchBlocks[i].getBlockId());
      }

      return Config.toPrimitive((Integer[])set.toArray(new Integer[set.size()]));
   }
}
