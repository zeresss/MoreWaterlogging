package com.zer.morewaterlogging;

import com.zer.morewaterlogging.mixin.NewWaterloggable;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.state.property.Properties;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import virtuoel.statement.api.StateRefresher;

import java.util.Map;

public class MoreWaterloggingMod implements ModInitializer {

	@Override
	public void onInitialize() {
		for (Map.Entry<RegistryKey<Block>, Block> entry : Registry.BLOCK.getEntrySet()) {
			if (entry.getValue() instanceof NewWaterloggable) {
				StateRefresher.INSTANCE.addBlockProperty(entry.getValue(), Properties.WATERLOGGED, false);
			}
		}
		StateRefresher.INSTANCE.reorderBlockStates();
	}

}