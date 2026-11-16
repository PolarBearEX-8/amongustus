package client.util;

import java.util.Objects;
import net.minecraft.item.DiamondToken;

public class AutoRegisterEncryption {
   public static DiamondToken getDiamondToken2() {
      return DiamondToken.diamondToken2;
   }

   public static DiamondToken getDiamondToken() {
      return DiamondToken.diamondToken;
   }

   public static AutoRegisterCrypto getAutoRegisterCryptoAsAutoRegisterCryp() {
      return AutoRegisterCrypto.autoRegisterCrypto;
   }

   public static AutoRegisterCrypto resolveAutoRegisterCrypto(int count, byte[] byteValueArray) {
      Object entry = null;
      Objects.requireNonNull(byteValueArray);
      entry = DiamondToken.getCountArray2();

      for (byte byteValue : byteValueArray) {
         if (((int[])entry)[byteValue & 255] != -1) {
            throw new IllegalArgumentException("Illegal base64 line separator character 0x" + Integer.toString(byteValue, 16));
         }
      }

      return count > 0 ? new AutoRegisterCrypto(false, byteValueArray, count >> 2 << 2, true, null) : AutoRegisterCrypto.autoRegisterCrypto2;
   }

   public static DiamondToken getDiamondToken3() {
      return DiamondToken.diamondToken3;
   }

   public static AutoRegisterCrypto getAutoRegisterCrypto3() {
      return AutoRegisterCrypto.autoRegisterCrypto3;
   }

   public static AutoRegisterCrypto getAutoRegisterCrypto() {
      return AutoRegisterCrypto.autoRegisterCrypto2;
   }

   private AutoRegisterEncryption() {
   }
}
