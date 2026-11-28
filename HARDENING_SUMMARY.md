# OpenAugustusCurated — b4.23.3 (hardened copy)

## 1. ที่มา / สิ่งที่ก็อปมา

- ฐานทั้งหมดก็อปจาก `OpenAugustusLasted2` (b4.23.3, git `24e55dd`, tag `release`) รวม `.git` ประวัติเดิมด้วย
- ไดเรกทอรีใหม่: `OpenAugustusCurated`
- ความหมายของโฟลเดอร์ที่เพิ่มเข้ามา (เป็นการ **ก็อป/ย้าย** ไม่ได้เขียนโค้ดใหม่):
  - เคยก็อป native 18 ไฟล์จาก `openaugustusLasted1` มาที่ `src/main/resources`; หลัง review เอาสำเนา LWJGL/JInput ออกจาก resource tree และคืนการดึง classifier JAR ที่มี checksum verification แล้ว
  - `reference/` — ไฟล์อ้างอิงจาก `openaugustusLasted1`, `OpenAugustusOld`, `amongustus` (docs, build report, pinned inputs ฯลฯ)
  - `hardening/OpenAugustusOld/` — verify.py / modules.json / pinned-inputs.json / expected-dependency-holds.tsv จาก `OpenAugustusOld`
  - `tools/launch/run-client.b4.20.3.reference.bat` — ตัวอย่าง launcher จาก `OpenAugustusOld` (ใช้เป็น reference เท่านั้น เพราะเป็น b4.20.3)
  - `DISABLED_SOURCES/`, `DISABLED_NATIVES/` — ไฟล์ที่ถูกคัดออกจาก build

## 2. ผลสแกนไวรัส

- Windows Defender (`MpCmdRun.exe -Scan -ScanType 3 -File ... -DisableRemediation`) สแกนทั้งโฟลเดอร์
- ผล: **Scan finished. found no threats.** (log: `SECURITY_defender_scan.log`)
- hash inventory ของไฟล์ทั้งหมด: `SECURITY_file_hashes.csv` (ใน temp ของ session; สามารถก็อปเก็บได้)

## 3. สิ่งที่ "ปิด/คัด" ไป และเหตุผล

รายการด้านล่างบันทึกการ hardening รอบแรก สถานะปัจจุบันอาจเปลี่ยนแล้ว; ให้ยึด review update ในหัวข้อ 7 เป็นหลัก

### 3.1 ระดับ Build / Gradle

| สิ่งที่ปิด | ไฟล์ | เหตุผล |
|---|---|---|
| `downloadAssets` task ถูกปิดถาวร (`onlyIf { false }`) | `build.gradle` | เดิมดึง version manifest + ไฟล์ assets จาก `piston-meta.mojang.com` / `resources.download.minecraft.net` ผ่าน HTTPS อัตโนมัติ → ให้เตรียม `run/assets` จากเครื่องตัวเองแทน |
| `extractNatives` ใช้ classifier JAR ที่ตรึงเวอร์ชันไว้จาก Maven | `build.gradle` | ปัจจุบันคืนค่าการ resolve native dependencies แบบเดิม และมี SHA-256 verification ใน `gradle/verification-metadata.xml` |
| `org.gradle.java.installations.auto-download=false` + ชี้ toolchain ไป JDK 8 ที่มีอยู่แล้ว | `gradle.properties` | ไม่ให้ Gradle โหลด JDK มาเองแบบอัตโนมัติ |
| `org.gradle.parallel=false`, `-Djava.net.preferIPv4Stack=true` | `gradle.properties` | แก้บั๊ก JvmDowngrader `ConcurrentModificationException` และปัญหา Java connect timeout |

### 3.2 โค้ดที่ถูกคัด/ปิด (fail-closed)

| สิ่งที่ปิด | ไฟล์ | เหตุผล |
|---|---|---|
| trust-all HTTPS socket factory + remote cosmetic download ทั้งหมด (`openUrlStream` throw ทันที) | `net/augustus/cosmetics/CosmeticsHttpUtil.java` | เดิมติดตั้ง `TrustAllX509TrustManager` ทำให้ TLS ไม่ตรวจสอบใบรับรอง และดึง texture/model จาก CDN ภายนอก |
| ไฟล์ `TrustAllX509TrustManager.java` ถูกย้ายออกจาก source tree | `DISABLED_SOURCES/net/augustus/cosmetics/TrustAllX509TrustManager.java.disabled` | ไม่มี caller หลังจากปิด trust-all แล้ว |
| OptiFine startup version check (`startVersionCheckThread()`) ถูก comment ออก | `net/optifine/config/Config.java` | HTTP check ตอนเริ่มโปรแกรม ไม่จำเป็นสำหรับ offline |
| `MinotarAvatarCache.preloadAccountAvatars()` ถูก comment ออก | `net/augustus/Augustus.java` | ส่งชื่อบัญชีไปยัง CDN `minotar.net` อัตโนมัติตอนโหลด config |
| `snooperEnabled` เปลี่ยนจาก `true` → `false` | `net/minecraft/client/settings/GameSettings.java` | ปิด snooping/OptiFine headers/crash upload ตามค่าเริ่มต้น |
| `disableCertificateValidation()` ทำให้เป็น no-op | `com/thealtening/auth/SSLController.java` | ปิดการ set global trust-all `HttpsURLConnection` factory |
| `switchToService()` คืนค่า MOJANG โดยไม่ reflect แก้ URL | `com/thealtening/auth/service/ServiceSwitcher.java` | ปิดการแก้ static final auth/session URL ด้วย reflection |
| `setSessionJoinUrl()` เป็น no-op | `net/augustus/ui/account/EasyMcAuthenticator.java` | ปิดการแก้ `final` field `JOIN_URL` ด้วย reflection |
| `postJsonRequest()` คืน `null` ทันที | `net/augustus/ui/account/EasyMcAuthenticator.java` | ปิด POST token ไป `api.easymc.io` |
| ปุ่ม Reconnect (SOAP `ForceTermination` ไป `http://fritz.box:49000`) ทำให้ inert | `net/augustus/ui/account/AccountManagerScreen.java` | คำสั่งนี้สามารถตัด WAN/router ได้ |
| `flushDns()` เป็น no-op | `net/augustus/utils/network/DnsCacheFlusher.java` | ปิด `cmd ipconfig /flushdns` + reflection เข้า DNS cache ของ JDK |
| `loadDLL()` เป็น no-op + ย้าย native ออกจาก resources | `net/arikia/dev/drpc/DiscordRPC.java`, `DISABLED_NATIVES/` | ไม่ extract `discord-rpc.dll/.so/.dylib` ลง temp และไม่ `System.load()` |

### 3.3 สิ่งที่ **ตั้งใจทิ้งไว้** (ไม่ใช่ช่องโหว่ในบริบทนี้)

- file I/O สำหรับเซฟโลก (`SaveHandler`, `RegionFile`, `CompressedStreamTools`), config ท้องถิ่น (`JsonFileStorageHandler`, `GameSettings`) และ asset ท้องถิ่น (`DefaultResourcePack`, `FolderResourcePack`) — จำเป็นต่อการเล่น offline
- `PluginLoader` (`URLClassLoader`) ของ JInput — ใช้โหลด plugin/native จากโฟลเดอร์ท้องถิ่น
- LAN/multiplayer, proxy checker, radio, Microsoft/Mojang login, server ping ฯลฯ ยังมีอยู่ในโค้ด **แต่ไม่ได้ถูกเรียกอัตโนมัติตอนเริ่มโปรแกรมแบบ offline** — ถ้าต้องการปิดเพิ่ม บอกได้แล้วจะคัดต่อ
- คีย์/AES ใน `ModuleConfigStorage` เป็น config ท้องถิ่น ไม่ใช่การสื่อสารเครือข่าย

## 4. การ build (ที่ทดสอบแล้ว)

ใช้ Gradle **8.14.2** ที่มีอยู่ในเครื่อง (wrapper 8.14.3 โหลดไม่ได้เพราะ network timeout ตอนทดสอบ):

```powershell
$env:JAVA_HOME='C:\path\to\jdk-21'
$env:PATH="$env:JAVA_HOME\bin;$env:PATH"
Set-Location 'OpenAugustusCurated'
gradle --no-daemon --offline classes --console=plain
```

ผลลัพธ์ที่ทดสอบจริง: `BUILD SUCCESSFUL` (ดู `BUILD_hardened_classes.log`)

## 5. Dependency verification (กัน dependency ที่ตรวจสอบไม่ได้)

- สร้าง `gradle/verification-metadata.xml` ด้วย `--write-verification-metadata sha256`
- มี checksum SHA-256 ของ dependency ที่ resolve ทั้งหมด **146 components** (รวม dependency ที่ใช้ตอนทำ fatJar)
- หลังจากมีไฟล์นี้ Gradle จะปฏิเสธartifact ที่ checksum ไม่ตรงทันที (fail-closed)
- log: `BUILD_verification_metadata.log`

## 6. ผลลัพธ์ build ที่ได้

- `classes` — `BUILD SUCCESSFUL` (`BUILD_hardened_classes.log`)
- `fatJar` — `BUILD SUCCESSFUL` (`BUILD_fatJar_online.log`, ยืนยันซ้ำแบบ offline ได้ `BUILD_fatJar_offline_verify.log`)
- ไฟล์ artifact: `build\libs\openaugustus-b4.23.3-all.jar` (283,949,798 bytes)
- SHA-256: `E2636091105DE0ABF44BD54ED063AE76E52C692EE255449D5977AA0A6900AE55`

หมายเหตุ: wrapper เดิม (`gradlew.bat`) ยังชี้ 8.14.3 พร้อม `distributionSha256Sum` — ถ้า network ถึง `services.gradle.org` ได้ จะใช้ wrapper ได้ตามเดิม (ในเครื่องนี้ wrapper โหลดไม่ได้ จึงใช้ Gradle 8.14.2 ที่แคชอยู่)

## 7. Security review update — 2026-11-28

Static source review compared the hardened changes with the checked-in baseline. The following ordinary features were restored:

- Minecraft asset downloads from the Mojang HTTPS manifest and resource hosts.
- Account avatar preloading from Minotar and the vanilla snooper default. These are normal network/telemetry behaviors, not malware; account names and gameplay diagnostics may leave the device.
- Cosmetic image/model downloads, restricted to HTTPS, the project's trusted-host list, and the platform's normal certificate validation. The trust-all TLS manager remains removed.
- EasyMC token redemption, only to `https://api.easymc.io`, with redirects disabled. This sends the token entered by the user to EasyMC when they invoke that login flow.
- Discord RPC native loading on Windows. The restored x86/x64 DLLs have valid Authenticode signatures from Discord Inc. Non-Windows Discord natives remain excluded pending equivalent provenance verification.
- DNS cache flushing on the existing server-list/disconnect UI paths. This runs the standard Windows `ipconfig /flushdns` command and clears the JVM address cache; it is not persistence or data collection.

The following remain disabled for security reasons rather than because they were identified as malware:

- Trust-all TLS and TheAltening service switching. The bundled TheAltening endpoints use plaintext HTTP, and its original code globally bypassed certificate/hostname validation.
- The account screen's router `ForceTermination` request, which can disconnect the router's WAN link.
- OptiFine's startup version check, which uses plaintext HTTP.

No malware verdict is claimed from static review. The existing Defender log reported no threats before these features were restored, so it is not a scan result for the current tree. No binaries were executed during this review. The signed Windows Discord DLLs were signature-checked locally.

One dormant item was unsafe: `de.wazed.wrapper.generation.Generator.generate()` put a TheAltening API token in a query string sent over plaintext HTTP. No call site for this generator was found in the source tree. Its method now fails closed until there is a documented HTTPS flow that does not put the token in the URL.
