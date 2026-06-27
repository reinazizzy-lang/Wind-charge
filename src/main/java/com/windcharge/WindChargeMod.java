package com.windcharge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafxmod.FXModLanguageProvider;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WindChargeMod.MOD_ID)
public class WindChargeMod
{
    public static final String MOD_ID = "windcharge";
    public static final Logger LOGGER = LogManager.getLogger();

    public WindChargeMod()
    {
        IEventBus modEventBus = FXModLanguageProvider.getModEventBus(MOD_ID);

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.getInstance().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Wind Charge Mod loaded!");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Client setup event");
        }
    }
}
