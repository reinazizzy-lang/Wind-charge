# Wind Charge Mod

A Minecraft Fabric mod for Minecraft 1.21.11.

## Setup

1. **Install Java 21** - This mod requires Java 21
2. **Run the setup**:
   ```bash
   ./gradlew genSources
   ```

3. **Import into IDE**:
   - Eclipse: Import as existing Gradle project
   - IntelliJ: Open project and it will detect Gradle

## Development

- **Run Client**: `./gradlew runClient`
- **Run Server**: `./gradlew runServer`
- **Build JAR**: `./gradlew build`

## Project Structure

```
Wind-charge/
├── src/
│   └── main/
│       ├── java/com/windcharge/     # Main mod code
│       └── resources/               # Assets and configs
├── build.gradle                     # Build configuration
└── README.md                        # This file
```

## Next Steps

1. Add custom items to `com.windcharge.items`
2. Add custom blocks to `com.windcharge.blocks`
3. Create recipes in `src/main/resources/data/windcharge/recipes/`
4. Add textures to `src/main/resources/assets/windcharge/textures/`

## License

MIT License
