package net.raphimc.minecraftauth.step.bedrock;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpClient;
import net.lenni0451.commons.httpclient.requests.impl.PostRequest;
import net.raphimc.minecraftauth.responsehandler.MinecraftResponseHandler;
import net.raphimc.minecraftauth.step.AbstractStep;
import net.raphimc.minecraftauth.step.xbl.StepXblXstsToken;
import net.raphimc.minecraftauth.util.CryptUtil;
import net.raphimc.minecraftauth.util.JsonContent;
import net.raphimc.minecraftauth.util.JwtUtil;
import net.raphimc.minecraftauth.util.logging.ILogger;
import org.jetbrains.annotations.ApiStatus.Internal;

public class StepMCChain extends AbstractStep<StepXblXstsToken.XblXsts<?>, StepMCChain.MCChain> {
   public static final String MINECRAFT_LOGIN_URL = "https://multiplayer.minecraft.net/authentication";
   private static final String MOJANG_PUBLIC_KEY_BASE64 = "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAECRXueJeTDqNRRgJi/vlRufByu/2G0i2Ebt6YMar5QX/R0DIIyrJMcUpruK4QveTfJSTp3Shlq4Gk34cD/4GUWwkv0DVuzeuB+tXija7HBxii03NHDbPAD0AKnLr2wdAp";
   public static final ECPublicKey MOJANG_PUBLIC_KEY = CryptUtil.publicKeyEcFromBase64(
      "MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAECRXueJeTDqNRRgJi/vlRufByu/2G0i2Ebt6YMar5QX/R0DIIyrJMcUpruK4QveTfJSTp3Shlq4Gk34cD/4GUWwkv0DVuzeuB+tXija7HBxii03NHDbPAD0AKnLr2wdAp"
   );

   public StepMCChain(AbstractStep<?, ? extends StepXblXstsToken.XblXsts<?>> prevStep) {
      super("mcChain", (AbstractStep<?, StepXblXstsToken.XblXsts<?>>)prevStep);
   }

   protected StepMCChain.MCChain execute(ILogger logger, HttpClient httpClient, StepXblXstsToken.XblXsts<?> xblXsts) throws Exception {
      logger.info(this, "Authenticating with Minecraft Services...");
      KeyPairGenerator secp384r1 = KeyPairGenerator.getInstance("EC");
      secp384r1.initialize(new ECGenParameterSpec("secp384r1"));
      KeyPair ecdsa384KeyPair = secp384r1.generateKeyPair();
      ECPublicKey publicKey = (ECPublicKey)ecdsa384KeyPair.getPublic();
      ECPrivateKey privateKey = (ECPrivateKey)ecdsa384KeyPair.getPrivate();
      JsonObject postData = new JsonObject();
      postData.addProperty("identityPublicKey", Base64.getEncoder().encodeToString(publicKey.getEncoded()));
      PostRequest postRequest = new PostRequest("https://multiplayer.minecraft.net/authentication");
      postRequest.setContent(new JsonContent(postData));
      postRequest.setHeader("Authorization", "XBL3.0 x=" + xblXsts.getServiceToken());
      JsonObject obj = httpClient.execute(postRequest, new MinecraftResponseHandler());
      JsonArray chain = obj.getAsJsonArray("chain");
      if (chain.size() != 2) {
         throw new IllegalStateException("Invalid chain size");
      } else {
         ECPublicKey mojangJwtPublicKey = CryptUtil.publicKeyEcFromBase64(JwtUtil.parseSignedJwt(chain.get(0).getAsString(), MOJANG_PUBLIC_KEY).getClaim("identityPublicKey", String.class));
         Map<String, Object> extraData = JwtUtil.parseSignedJwt(chain.get(1).getAsString(), mojangJwtPublicKey).getClaim("extraData", Map.class);
         String xuid = (String)extraData.get("XUID");
         UUID id = UUID.fromString((String)extraData.get("identity"));
         String displayName = (String)extraData.get("displayName");
         if (!extraData.containsKey("titleId")) {
            logger.warn(this, "Minecraft chain does not contain titleId! You might get kicked from some servers");
         }

         StepMCChain.MCChain mcChain = new StepMCChain.MCChain(
            publicKey, privateKey, chain.get(0).getAsString(), chain.get(1).getAsString(), xuid, id, displayName, xblXsts
         );
         logger.info(this, "Got MC Chain, name: " + mcChain.displayName + ", uuid: " + mcChain.id + ", xuid: " + mcChain.xuid);
         return mcChain;
      }
   }

   public StepMCChain.MCChain fromJson(JsonObject json) {
      StepXblXstsToken.XblXsts<?> xblXsts = this.prevStep != null ? this.prevStep.fromJson(json.getAsJsonObject(this.prevStep.name)) : null;
      return new StepMCChain.MCChain(
         CryptUtil.publicKeyEcFromBase64(json.get("publicKey").getAsString()),
         CryptUtil.privateKeyEcFromBase64(json.get("privateKey").getAsString()),
         json.get("mojangJwt").getAsString(),
         json.get("identityJwt").getAsString(),
         json.get("xuid").getAsString(),
         UUID.fromString(json.get("id").getAsString()),
         json.get("displayName").getAsString(),
         xblXsts
      );
   }

   public JsonObject toJson(StepMCChain.MCChain mcChain) {
      JsonObject json = new JsonObject();
      json.addProperty("publicKey", Base64.getEncoder().encodeToString(mcChain.publicKey.getEncoded()));
      json.addProperty("privateKey", Base64.getEncoder().encodeToString(mcChain.privateKey.getEncoded()));
      json.addProperty("mojangJwt", mcChain.mojangJwt);
      json.addProperty("identityJwt", mcChain.identityJwt);
      json.addProperty("xuid", mcChain.xuid);
      json.addProperty("id", mcChain.id.toString());
      json.addProperty("displayName", mcChain.displayName);
      if (this.prevStep != null) {
         json.add(this.prevStep.name, this.prevStep.toJson(mcChain.xblXsts));
      }

      return json;
   }

   public static final class MCChain extends AbstractStep.StepResult<StepXblXstsToken.XblXsts<?>> {
      private final ECPublicKey publicKey;
      private final ECPrivateKey privateKey;
      private final String mojangJwt;
      private final String identityJwt;
      private final String xuid;
      private final UUID id;
      private final String displayName;
      private final StepXblXstsToken.XblXsts<?> xblXsts;
      @Internal
      long lastExpireCheckTimeMs;
      @Internal
      boolean lastExpireCheckResult;

      protected StepXblXstsToken.XblXsts<?> prevResult() {
         return this.xblXsts;
      }

      @Override
      public boolean isExpired() {
         if (this.prevResult().isExpired()) {
            return true;
         } else if (System.currentTimeMillis() - this.lastExpireCheckTimeMs < 1000L) {
            return this.lastExpireCheckResult;
         } else {
            this.lastExpireCheckTimeMs = System.currentTimeMillis();

            try {
               ECPublicKey mojangJwtPublicKey = CryptUtil.publicKeyEcFromBase64(JwtUtil.parseSignedJwt(this.mojangJwt, StepMCChain.MOJANG_PUBLIC_KEY).getClaim("identityPublicKey", String.class));
               JwtUtil.parseSignedJwt(this.identityJwt, mojangJwtPublicKey);
               this.lastExpireCheckResult = false;
            } catch (Throwable e) {
               this.lastExpireCheckResult = true;
            }

            return this.lastExpireCheckResult;
         }
      }

      @Override
      public boolean isExpiredOrOutdated() {
         return true;
      }

      @Generated
      public ECPublicKey getPublicKey() {
         return this.publicKey;
      }

      @Generated
      public ECPrivateKey getPrivateKey() {
         return this.privateKey;
      }

      @Generated
      public String getMojangJwt() {
         return this.mojangJwt;
      }

      @Generated
      public String getIdentityJwt() {
         return this.identityJwt;
      }

      @Generated
      public String getXuid() {
         return this.xuid;
      }

      @Generated
      public UUID getId() {
         return this.id;
      }

      @Generated
      public String getDisplayName() {
         return this.displayName;
      }

      @Generated
      public StepXblXstsToken.XblXsts<?> getXblXsts() {
         return this.xblXsts;
      }

      @Generated
      @Override
      public String toString() {
         return "StepMCChain.MCChain(publicKey="
            + this.getPublicKey()
            + ", privateKey="
            + this.getPrivateKey()
            + ", mojangJwt="
            + this.getMojangJwt()
            + ", identityJwt="
            + this.getIdentityJwt()
            + ", xuid="
            + this.getXuid()
            + ", id="
            + this.getId()
            + ", displayName="
            + this.getDisplayName()
            + ", xblXsts="
            + this.getXblXsts()
            + ")";
      }

      @Generated
      public MCChain(
         ECPublicKey publicKey,
         ECPrivateKey privateKey,
         String mojangJwt,
         String identityJwt,
         String xuid,
         UUID id,
         String displayName,
         StepXblXstsToken.XblXsts<?> xblXsts
      ) {
         this.publicKey = publicKey;
         this.privateKey = privateKey;
         this.mojangJwt = mojangJwt;
         this.identityJwt = identityJwt;
         this.xuid = xuid;
         this.id = id;
         this.displayName = displayName;
         this.xblXsts = xblXsts;
      }

      @Generated
      @Override
      public boolean equals(Object o) {
         if (o == this) {
            return true;
         } else if (!(o instanceof StepMCChain.MCChain)) {
            return false;
         } else {
            StepMCChain.MCChain other = (StepMCChain.MCChain)o;
            if (!other.canEqual(this)) {
               return false;
            } else {
               Object this$publicKey = this.getPublicKey();
               Object other$publicKey = other.getPublicKey();
               if (this$publicKey == null ? other$publicKey == null : this$publicKey.equals(other$publicKey)) {
                  Object this$privateKey = this.getPrivateKey();
                  Object other$privateKey = other.getPrivateKey();
                  if (this$privateKey == null ? other$privateKey == null : this$privateKey.equals(other$privateKey)) {
                     Object this$mojangJwt = this.getMojangJwt();
                     Object other$mojangJwt = other.getMojangJwt();
                     if (this$mojangJwt == null ? other$mojangJwt == null : this$mojangJwt.equals(other$mojangJwt)) {
                        Object this$identityJwt = this.getIdentityJwt();
                        Object other$identityJwt = other.getIdentityJwt();
                        if (this$identityJwt == null ? other$identityJwt == null : this$identityJwt.equals(other$identityJwt)) {
                           Object this$xuid = this.getXuid();
                           Object other$xuid = other.getXuid();
                           if (this$xuid == null ? other$xuid == null : this$xuid.equals(other$xuid)) {
                              Object this$id = this.getId();
                              Object other$id = other.getId();
                              if (this$id == null ? other$id == null : this$id.equals(other$id)) {
                                 Object this$displayName = this.getDisplayName();
                                 Object other$displayName = other.getDisplayName();
                                 if (this$displayName == null ? other$displayName == null : this$displayName.equals(other$displayName)) {
                                    Object this$xblXsts = this.getXblXsts();
                                    Object other$xblXsts = other.getXblXsts();
                                    return this$xblXsts == null ? other$xblXsts == null : this$xblXsts.equals(other$xblXsts);
                                 } else {
                                    return false;
                                 }
                              } else {
                                 return false;
                              }
                           } else {
                              return false;
                           }
                        } else {
                           return false;
                        }
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         }
      }

      @Generated
      protected boolean canEqual(Object other) {
         return other instanceof StepMCChain.MCChain;
      }

      @Generated
      @Override
      public int hashCode() {
         int PRIME = 59;
         int result = 1;
         Object $publicKey = this.getPublicKey();
         result = result * 59 + ($publicKey == null ? 43 : $publicKey.hashCode());
         Object $privateKey = this.getPrivateKey();
         result = result * 59 + ($privateKey == null ? 43 : $privateKey.hashCode());
         Object $mojangJwt = this.getMojangJwt();
         result = result * 59 + ($mojangJwt == null ? 43 : $mojangJwt.hashCode());
         Object $identityJwt = this.getIdentityJwt();
         result = result * 59 + ($identityJwt == null ? 43 : $identityJwt.hashCode());
         Object $xuid = this.getXuid();
         result = result * 59 + ($xuid == null ? 43 : $xuid.hashCode());
         Object $id = this.getId();
         result = result * 59 + ($id == null ? 43 : $id.hashCode());
         Object $displayName = this.getDisplayName();
         result = result * 59 + ($displayName == null ? 43 : $displayName.hashCode());
         Object $xblXsts = this.getXblXsts();
         return result * 59 + ($xblXsts == null ? 43 : $xblXsts.hashCode());
      }
   }
}
