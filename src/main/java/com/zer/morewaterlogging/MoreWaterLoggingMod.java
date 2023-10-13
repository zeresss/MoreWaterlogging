package com.zer.morewaterlogging;

import com.zer.morewaterlogging.mixin.NewWaterLoggable;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import virtuoel.statement.api.StateRefresher;

import java.util.Map;

public class MoreWaterLoggingMod implements ModInitializer {

	public static final String MOD_ID = "morewaterlogging";
    public static final Logger LOGGER = LoggerFactory.getLogger("more-water-logging");

	@Override
	public void onInitialize() {
		for (Map.Entry<RegistryKey<Block>, Block> entry : Registries.BLOCK.getEntrySet()) {
			Block block = entry.getValue();
			if (block instanceof NewWaterLoggable)
				StateRefresher.INSTANCE.addBlockProperty(block, Properties.WATERLOGGED, false);
		}
		StateRefresher.INSTANCE.reorderBlockStates();
	}

}