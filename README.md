# Wind Charge Auto-Jump Mod

A **client-side Fabric mod** for Minecraft 1.21.11 that automatically jumps when you're looking down and holding a Wind Charge item.

## Features

✨ **Auto-Jump Functionality**
- Automatically jumps when holding Wind Charge
- Customizable pitch threshold (how far down you need to look)
- Optional directional area restriction
- Smart cooldown to prevent spam jumps

🎛️ **Configuration GUI**
- Toggle mod on/off
- Adjust minimum pitch angle
- Set directional jump zones
- Persistent configuration saved to `config/windcharge.json`

⌨️ **Keybinds**
- `V` - Toggle mod on/off
- `B` - Open configuration screen

## Installation

1. **Install Java 21** - This mod requires Java 21
2. **Install Fabric**:
   - Download [Fabric Loader](https://fabricmc.net/use/) for 1.21.11
   - Install Fabric API mod
3. **Add Wind Charge Mod**:
   - Build the mod: `./gradlew build`
   - Place JAR in `.minecraft/mods/`

## Configuration

Press `B` in-game to open the configuration screen:

### Min Pitch
- How far down (in degrees) you need to look for auto-jump to trigger
- Range: 0-90 degrees (90 = straight down)
- Default: 45 degrees

### Area Check
- Enable/disable directional restrictions
- When enabled, only jumps when looking in a specific direction range
- Default: Disabled

### Direction Range (Min/Max Area)
- Set the directional zone where jumps trigger
- 0° = North, 90° = East, 180° = South, 270° = West
- Default: 0-360° (all directions)

## How to Use

1. Hold a Wind Charge in either hand
2. Look down at the configured angle or more
3. If area check is enabled, look in the configured direction
4. The mod will automatically jump!
5. Press `V` to toggle the mod on/off
6. Press `B` to adjust settings

## Configuration File

Settings are saved to `config/windcharge.json`:

```json
{
  "enabled": true,
  "minPitch": 45.0,
  "areaCheckEnabled": false,
  "minArea": 0.0,
  "maxArea": 360.0
}
```

## Development

- **Run Client**: `./gradlew runClient`
- **Run Server**: `./gradlew runServer` (client-side only)
- **Build JAR**: `./gradlew build`

## Project Structure

```
Wind-charge/
├── src/main/java/com/windcharge/
│   ├── client/
│   │   ├── ClientHandler.java       # Keybind registration
│   │   ├── JumpHandler.java         # Auto-jump logic
│   │   ├── WindChargeClientMod.java # Client initializer
│   │   └── screen/
│   │       └── WindChargeConfigScreen.java  # GUI
│   ├── config/
│   │   └── ModConfig.java           # Configuration management
│   └── WindChargeMod.java           # Main mod class
├── build.gradle
└── README.md
```

## License

MIT License
