package com.zer.morewaterlogging.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = {
    AbstractBannerBlock.class, AbstractButtonBlock.class, AbstractPressurePlateBlock.class, AbstractSkullBlock.class,
    AnvilBlock.class, BeaconBlock.class, BedBlock.class, BellBlock.class, BrewingStandBlock.class, CakeBlock.class,
    CandleCakeBlock.class, CarpetBlock.class, ComposterBlock.class, DaylightDetectorBlock.class, DoorBlock.class,
    DragonEggBlock.class, EnchantingTableBlock.class, EndPortalFrameBlock.class, EndRodBlock.class, FenceGateBlock.class,
    GrindstoneBlock.class, HopperBlock.class, LecternBlock.class, LeverBlock.class, PistonBlock.class,
    PistonExtensionBlock.class, PistonHeadBlock.class, ShulkerBoxBlock.class, SpawnerBlock.class, StonecutterBlock.class
})
public interface NewWaterloggable extends Waterloggable {}