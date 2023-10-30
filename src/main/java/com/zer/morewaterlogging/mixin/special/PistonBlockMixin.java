package com.zer.morewaterlogging.mixin.special;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PistonBlock.class)
public abstract class PistonBlockMixin {

    /**
     * @since 1.0.0
     * makes piston waterlogged on retract
     */
    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"))
    public void onSyncedBlockEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) {
        BlockState argBlockState = args.get(1);
        if (argBlockState.isOf(Blocks.MOVING_PISTON) && world.getFluidState(args.get(0)).getFluid() == Fluids.WATER)
            args.set(1, argBlockState.with(Properties.WATERLOGGED, true));
    }

    /**
     * @since 1.0.0
     * creates waterlogged piston extension block entity on retract
     */
    @ModifyArgs(method = "onSyncedBlockEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/PistonExtensionBlock;createBlockEntityPiston(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;ZZ)Lnet/minecraft/block/entity/BlockEntity;"))
    public void onSyncedBlockEntityEvent(Args args, BlockState state, World world, BlockPos pos, int type, int data) {
        if (world.getFluidState(args.get(0)).getFluid() == Fluids.WATER)
            args.set(2, args.<BlockState>get(2).with(Properties.WATERLOGGED, true));
    }

}