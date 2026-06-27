package com.windcharge.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModConfig {
    public static boolean ENABLED = true;
    public static float MIN_PITCH = 45f; // degrees looking down
    public static boolean AREA_CHECK_ENABLED = false;
    public static float MIN_AREA = 0f; // degrees
    public static float MAX_AREA = 360f; // degrees

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File CONFIG_FILE;

    public static void init() {
        CONFIG_FILE = new File("config/windcharge.json");
        CONFIG_FILE.getParentFile().mkdirs();
        load();
    }

    public static void load() {
        if (!CONFIG_FILE.exists()) {
            save();
            return;
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            if (data != null) {
                ENABLED = data.enabled;
                MIN_PITCH = data.minPitch;
                AREA_CHECK_ENABLED = data.areaCheckEnabled;
                MIN_AREA = data.minArea;
                MAX_AREA = data.maxArea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            ConfigData data = new ConfigData(
                    ENABLED,
                    MIN_PITCH,
                    AREA_CHECK_ENABLED,
                    MIN_AREA,
                    MAX_AREA
            );
            GSON.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class ConfigData {
        public boolean enabled = true;
        public float minPitch = 45f;
        public boolean areaCheckEnabled = false;
        public float minArea = 0f;
        public float maxArea = 360f;

        public ConfigData(boolean enabled, float minPitch, boolean areaCheckEnabled, float minArea, float maxArea) {
            this.enabled = enabled;
            this.minPitch = minPitch;
            this.areaCheckEnabled = areaCheckEnabled;
            this.minArea = minArea;
            this.maxArea = maxArea;
        }
    }
}
