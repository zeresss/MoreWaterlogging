package com.zer.morewaterlogging.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({
    AbstractBannerBlock.class,  AbstractButtonBlock.class, AbstractPressurePlateBlock.class, AbstractSkullBlock.class,
    AnvilBlock.class, AzaleaBlock.class, BedBlock.class, BellBlock.class, BrewingStandBlock.class, CakeBlock.class,
    CandleCakeBlock.class, CarpetBlock.class, ComparatorBlock.class, DaylightDetectorBlock.class, DoorBlock.class,
    EnchantingTableBlock.class, EndPortalFrameBlock.class, EndRodBlock.class, FenceGateBlock.class,
    FlowerPotBlock.class, GrindstoneBlock.class, HopperBlock.class, LecternBlock.class, LeverBlock.class,
    PistonBlock.class, PistonExtensionBlock.class, PistonHeadBlock.class, RedstoneTorchBlock.class,
    RedstoneWireBlock.class, RepeaterBlock.class, StonecutterBlock.class, VineBlock.class
})
public interface NewWaterLoggable extends Waterloggable {}