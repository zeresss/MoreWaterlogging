package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = CakeBlock.class)
public abstract class CakeBlockMixin {

    /**
     * @since 1.0.0
     * makes candle placing on cake not cause air pocket
     */
    @ModifyArgs(method = "onUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"))
    private void onUse(Args args, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        args.set(1, args.<BlockState>get(1).with(Properties.WATERLOGGED, world.getBlockState(pos).get(Properties.WATERLOGGED)));
    }

    /**
     * @since 1.0.1
     * makes eating when candle is placed on top not cause air pocket
     */
    @ModifyArgs(method = "tryEat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldAccess;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    private static void tryEat(Args args, WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        args.set(1, args.<BlockState>get(1).with(Properties.WATERLOGGED, world.getBlockState(pos).get(Properties.WATERLOGGED)));
    }

}