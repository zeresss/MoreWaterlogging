package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CandleCakeBlock.class)
public abstract class CandleCakeBlockMixin {

    @Shadow @Final public static BooleanProperty LIT;

    /**
     * @since 1.0.1
     * makes impossible to lit candle cake when waterlogged
     */
    @Inject(method = "canBeLit", at = @At("RETURN"), cancellable = true)
    private static void canBeLit(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
            state.isIn(
                BlockTags.CANDLE_CAKES, (statex) ->
                    statex.contains(LIT) && statex.contains(Properties.WATERLOGGED)
                    && !state.get(LIT) && !state.get(Properties.WATERLOGGED)
            )
        );
    }

}