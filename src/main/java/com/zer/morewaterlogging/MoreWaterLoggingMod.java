package com.zer.morewaterlogging;

import com.zer.morewaterlogging.mixin.NewWaterloggable;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.property.Properties;
import virtuoel.statement.api.StateRefresher;

import java.util.Map;

public class MoreWaterLoggingMod implements ModInitializer {

	@Override
	public void onInitialize() {
		for (Map.Entry<RegistryKey<Block>, Block> entry : Registries.BLOCK.getEntrySet()) {
			Block block = entry.getValue();
			if (block instanceof NewWaterloggable)
				StateRefresher.INSTANCE.addBlockProperty(block, Properties.WATERLOGGED, false);
		}
		StateRefresher.INSTANCE.reorderBlockStates();
	}

}