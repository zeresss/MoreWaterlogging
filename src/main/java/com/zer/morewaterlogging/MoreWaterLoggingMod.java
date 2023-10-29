package com.zer.morewaterlogging;

import com.zer.morewaterlogging.config.MoreWaterLoggingConfig;
import com.zer.morewaterlogging.mixin.NewWaterloggable;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.property.Properties;
import virtuoel.statement.api.StateRefresher;

import java.util.Map;

public class MoreWaterLoggingMod implements ModInitializer {

	public static final String MOD_ID = "morewaterlogging";
	public static final MoreWaterLoggingConfig CONFIG = MoreWaterLoggingConfig.createAndLoad();

	@Override
	public void onInitialize() {
		for (Map.Entry<RegistryKey<Block>, Block> entry : Registries.BLOCK.getEntrySet()) {
			Block block = entry.getValue();
			if (isEnabledWaterlogging(block)) {
				StateRefresher.INSTANCE.addBlockProperty(block, Properties.WATERLOGGED, false);
			}
		}
		StateRefresher.INSTANCE.reorderBlockStates();
	}

	/**
	 * @since 1.1.0
	 * @return is block waterlogging enabled in the config
	 */
	static public boolean isEnabledWaterlogging(Object block) {
		if (!(block instanceof NewWaterloggable)) return false;
		else if (block instanceof AbstractBannerBlock) return CONFIG.banner();
		else if (block instanceof AbstractPressurePlateBlock) return CONFIG.pressurePlate();
		else if (block instanceof AbstractSkullBlock) return CONFIG.skullHead();
		else if (block instanceof AnvilBlock) return CONFIG.anvil();
		else if (block instanceof AzaleaBlock) return CONFIG.azalea();
		else if (block instanceof ButtonBlock) return CONFIG.button();
		else if (block instanceof BedBlock) return CONFIG.bed();
		else if (block instanceof BellBlock) return CONFIG.bell();
		else if (block instanceof BrewingStandBlock) return CONFIG.brewingStand();
		else if (block instanceof CakeBlock) return CONFIG.cake();
		else if (block instanceof CandleCakeBlock) return CONFIG.candleCake();
		else if (block instanceof CarpetBlock) return CONFIG.carpet();
		else if (block instanceof ComparatorBlock) return CONFIG.comparator();
		else if (block instanceof DaylightDetectorBlock) return CONFIG.daylightDetector();
		else if (block instanceof DoorBlock) return CONFIG.door();
		else if (block instanceof DragonEggBlock) return CONFIG.dragonEgg();
		else if (block instanceof EnchantingTableBlock) return CONFIG.enchantingTable();
		else if (block instanceof EndPortalFrameBlock) return CONFIG.endPortalFrame();
		else if (block instanceof EndRodBlock) return CONFIG.endRod();
		else if (block instanceof FenceGateBlock) return CONFIG.fenceGate();
		else if (block instanceof FlowerPotBlock) return CONFIG.flowerPot();
		else if (block instanceof GrindstoneBlock) return CONFIG.grindstone();
		else if (block instanceof HopperBlock) return CONFIG.hopper();
		else if (block instanceof LecternBlock) return CONFIG.lectern();
		else if (block instanceof LeverBlock) return CONFIG.lever();
		else if (block instanceof PistonBlock || block instanceof PistonExtensionBlock || block instanceof PistonHeadBlock) return CONFIG.piston();
		else if (block instanceof RedstoneTorchBlock) return CONFIG.redstoneTorch();
		else if (block instanceof RedstoneWireBlock) return CONFIG.redstoneDust();
		else if (block instanceof RepeaterBlock) return CONFIG.repeater();
		else if (block instanceof StonecutterBlock) return CONFIG.stonecutter();
		else if (block instanceof VineBlock) return CONFIG.vine();
		else return false;
	}

}