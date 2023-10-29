package com.zer.morewaterlogging.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RestartRequired;
import io.wispforest.owo.config.annotation.Sync;

import static com.zer.morewaterlogging.MoreWaterLoggingMod.MOD_ID;

@SuppressWarnings("unused")
@Modmenu(modId = MOD_ID)
@Config(name = MOD_ID, wrapperName = "MoreWaterLoggingConfig")
@Sync(Option.SyncMode.OVERRIDE_CLIENT)
public class MoreWaterLoggingConfigModel {

    @RestartRequired public boolean anvil = true;
    @RestartRequired public boolean azalea = true;
    @RestartRequired public boolean banner = true;
    @RestartRequired public boolean bed = true;
    @RestartRequired public boolean bell = true;
    @RestartRequired public boolean brewingStand = true;
    @RestartRequired public boolean button = true;
    @RestartRequired public boolean cake = true;
    @RestartRequired public boolean candleCake = true;
    @RestartRequired public boolean carpet = true;
    @RestartRequired public boolean comparator = true;
    @RestartRequired public boolean daylightDetector = true;
    @RestartRequired public boolean door = true;
    @RestartRequired public boolean dragonEgg = true;
    @RestartRequired public boolean enchantingTable = true;
    @RestartRequired public boolean endRod = true;
    @RestartRequired public boolean endPortalFrame = true;
    @RestartRequired public boolean fenceGate = true;
    @RestartRequired public boolean flowerPot = true;
    @RestartRequired public boolean grindstone = true;
    @RestartRequired public boolean hopper = true;
    @RestartRequired public boolean lectern = true;
    @RestartRequired public boolean lever = true;
    @RestartRequired public boolean piston = true;
    @RestartRequired public boolean pressurePlate = true;
    @RestartRequired public boolean redstoneDust = true;
    @RestartRequired public boolean redstoneTorch = true;
    @RestartRequired public boolean repeater = true;
    @RestartRequired public boolean skullHead = true;
    @RestartRequired public boolean stonecutter = true;
    @RestartRequired public boolean vine = true;

}