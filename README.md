<p align="center">
  <img src="assets/ss.png" alt="Skid Solutions Banner" width="350" />
  <br><br>
  <a href="https://discord.gg/skidsolutions">
    <img src="https://img.shields.io/badge/Discord-Skid%20Solutions-18181b?style=for-the-badge&logo=discord&logoColor=ffffff&labelColor=09090b" alt="Discord" />
  </a>
  <img src="https://img.shields.io/badge/Version-b4.23.3-27272a?style=for-the-badge&labelColor=09090b" alt="Version b4.23.3" />
  <img src="https://img.shields.io/badge/Java-21-3f3f46?style=for-the-badge&logo=openjdk&logoColor=ffffff&labelColor=09090b" alt="Java 21" />
  <img src="https://img.shields.io/badge/Gradle-8.14.3-52525b?style=for-the-badge&logo=gradle&logoColor=ffffff&labelColor=09090b" alt="Gradle 8.14.3" />
</p>

<p align="center">
  <a href="https://discord.gg/skidsolutions">https://discord.gg/skidsolutions</a>
</p>

<p align="center">
  <img src="assets/divider.svg" alt="" width="720" />
</p>

<p align="center">
  <img src="assets/jak.jpg" alt="jak" height="150" />
  <img src="assets/hack.jpg" alt="hack" height="150" />
  <img src="assets/deobf2.png" alt="deobf2" height="150" />
  <img src="assets/deobf.jpg" alt="deobf" height="150" />
</p>

<p align="center">
  <img src="assets/divider.svg" alt="" width="720" />
</p>

## How to build

### Prerequisites
- **Java Development Kit**: JDK 8 (used via Gradle toolchain to compile and run the Minecraft 1.8.9 client)
- **Gradle Runtime**: JDK 17 or JDK 21 (Gradle wrapper 8.14.3 does not run on JDK 25+)

### Building OpenAugustus

Run from this folder:

```powershell
.\gradlew.bat clean build
```

Generated `build/libs/`:
- `openaugustus-b4.23.3.jar`

### Running

```powershell
# Run in local run/ directory (extracts natives and downloads Minecraft 1.8 assets automatically)
.\gradlew.bat runClient

# Or run using your system .minecraft directory
.\gradlew.bat runClientMinecraftDir
```

Or run `Start.java` / `StartMinecraftDirectory.java` directly from your IDE.

---

Credits to https://github.com/yxzeroo/openaugustus, all features, issues and bugs fixed on this version with our b4.20.3 internal deobfuscated version. Just compare everything.
