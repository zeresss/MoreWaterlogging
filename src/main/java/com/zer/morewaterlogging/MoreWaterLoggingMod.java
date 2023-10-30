package com.zer.morewaterlogging;

import com.zer.morewaterlogging.mixin.NewWaterloggable;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import virtuoel.statement.api.StateRefresher;

import java.util.Map;

public class MoreWaterLoggingMod implements ModInitializer {

	@Override
	public void onInitialize() {
		for (Map.Entry<RegistryKey<Block>, Block> entry : Registry.BLOCK.getEntrySet()) {
			Block block = entry.getValue();
			if (block instanceof NewWaterloggable) {
				StateRefresher.INSTANCE.addBlockProperty(block, Properties.WATERLOGGED, false);
			}
		}
		StateRefresher.INSTANCE.reorderBlockStates();
	}

}