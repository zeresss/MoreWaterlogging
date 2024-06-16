package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CandleCakeBlock.class)
public abstract class CandleCakeBlockMixin {

    /**
     * @since 1.0.1
     * makes impossible to lit candle cake when waterlogged
     */
    @Inject(method = "canBeLit", at = @At("RETURN"), cancellable = true)
    private static void canBeLit(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
            state.isIn(
                BlockTags.CANDLE_CAKES, (statex) ->
                    !state.get(Properties.LIT) && !state.get(Properties.WATERLOGGED)
            )
        );
    }

}