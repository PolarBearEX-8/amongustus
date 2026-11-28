package com.thealtening.auth;


import com.thealtening.auth.service.AlteningServiceType;
import com.thealtening.auth.service.ServiceSwitcher;
public final class TheAlteningAuthentication {
   private final ServiceSwitcher serviceSwitcher;
   private final SSLController sslController;
   private static TheAlteningAuthentication instance;
   private AlteningServiceType service;

   private TheAlteningAuthentication(AlteningServiceType alteningServiceType) {
       super();
      this.serviceSwitcher = new ServiceSwitcher();
      this.sslController = new SSLController();
      this.updateService(alteningServiceType);
   }

   public AlteningServiceType getService() {
      return this.service;
   }

   public static TheAlteningAuthentication mojang() {
      return withService(AlteningServiceType.MOJANG);
   }

   public void updateService(AlteningServiceType alteningServiceType) {
      if (alteningServiceType != null) {
         switch (AlteningServiceSwitch.SWITCH_MAP[alteningServiceType.ordinal()]) {
            case 1:
               this.sslController.enableCertificateValidation();
               break;
            case 2:
               this.sslController.disableCertificateValidation();
         }

         this.service = this.serviceSwitcher.switchToService(alteningServiceType);
      }
   }

   private static TheAlteningAuthentication withService(AlteningServiceType alteningServiceType) {
      if (instance == null) {
         instance = new TheAlteningAuthentication(alteningServiceType);
      } else if (instance.getService() != alteningServiceType) {
         instance.updateService(alteningServiceType);
      }

      return instance;
   }

   public static TheAlteningAuthentication theAltening() {
      return withService(AlteningServiceType.THEALTENING);
   }
}
