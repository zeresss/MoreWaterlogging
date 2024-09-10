package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = PistonBlockEntity.class)
public abstract class PistonBlockEntityMixin {

    /**
     * @since 1.0.1
     * prevents removing waterlogged property
     */
    @ModifyArgs(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;with(Lnet/minecraft/state/property/Property;Ljava/lang/Comparable;)Ljava/lang/Object;"))
    private static void tick(Args args, World world, BlockPos pos, BlockState state, PistonBlockEntity blockEntity) {
        args.set(1, true);
    }

}