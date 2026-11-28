package net.raphimc.minecraftauth.responsehandler.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Generated;
import net.lenni0451.commons.httpclient.HttpResponse;

public class XblRequestException extends ApiHttpRequestException {
   public static final Map<Long, String> ERROR_CODES = new HashMap<>();
   private final long errorCode;

   public XblRequestException(HttpResponse response, long errorCode) {
      super(response, String.valueOf(errorCode), ERROR_CODES.getOrDefault(errorCode, "Unknown error"));
      this.errorCode = errorCode;
   }

   @Generated
   public long getErrorCode() {
      return this.errorCode;
   }

   static {
      ERROR_CODES.put(2148916227L, "Your account was banned by Xbox for violating one or more Community Standards for Xbox.");
      ERROR_CODES.put(
         2148916229L,
         "Your account is currently restricted and your guardian has not given you permission to play online. Login to https://account.microsoft.com/family/ and have your guardian change your permissions."
      );
      ERROR_CODES.put(2148916233L, "Your account doesn't have an Xbox profile. Please create one at https://www.xbox.com/live");
      ERROR_CODES.put(2148916234L, "Your account has not accepted Xbox's Terms of Service. Please login at https://www.xbox.com/live and accept them.");
      ERROR_CODES.put(2148916235L, "Your account is from a country where Xbox Live is not available/banned.");
      ERROR_CODES.put(2148916236L, "Your account requires proof of age. Please login to https://login.live.com/login.srf and provide proof of age.");
      ERROR_CODES.put(2148916237L, "Your account has reached the its limit for playtime. Your account has been blocked from logging in.");
      ERROR_CODES.put(2148916238L, "Your account is a child (under 18) and cannot proceed unless the account is added to a Family by an adult.");
   }
}
