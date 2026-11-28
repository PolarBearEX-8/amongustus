package net.raphimc.minecraftauth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.impl.security.DefaultSecureRequest;
import java.io.ByteArrayInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class JwtUtil {
   public static JwtUtil.Jwt parseSignedJwt(String jwt, PublicKey publicKey) {
      final Jws<Claims> parsedJwt = Jwts.parser().clockSkewSeconds(60L).verifyWith(publicKey).build().parseSignedClaims(jwt);
      return new JwtUtil.Jwt() {
         @Override
         public <T> T getClaim(String claimName, Class<T> requiredType) {
            return (T)((Claims)parsedJwt.getPayload()).get(claimName, requiredType);
         }
      };
   }

   public static byte[] signES256(PrivateKey privateKey, byte[] data) {
      return SIG.ES256.digest(new DefaultSecureRequest(new ByteArrayInputStream(data), null, null, privateKey));
   }

   public interface Jwt {
      <T> T getClaim(String str, Class<T> clazz);
   }
}
