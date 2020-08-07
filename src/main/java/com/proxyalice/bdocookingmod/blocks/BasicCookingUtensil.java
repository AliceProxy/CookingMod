package com.proxyalice.bdocookingmod.blocks;

import com.proxyalice.bdocookingmod.init.ModTileEntityTypes;
import com.proxyalice.bdocookingmod.tileentity.CookingTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.stream.Stream;

public class BasicCookingUtensil extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.bdocooking");

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(1, 0, 15, 15, 18, 16),
            Block.makeCuboidShape(0, 6, 1, 16, 7, 2),
            Block.makeCuboidShape(0, 6, 2, 1, 7, 16),
            Block.makeCuboidShape(15, 6, 2, 16, 7, 16),
            Block.makeCuboidShape(1, 0, 2, 15, 6, 3),
            Block.makeCuboidShape(1, 0, 3, 2, 6, 15),
            Block.makeCuboidShape(14, 0, 3, 15, 6, 15),
            Block.makeCuboidShape(4, 0, 1, 12, 1, 2),
            Block.makeCuboidShape(4, 4, 1, 12, 5, 2),
            Block.makeCuboidShape(11, 1, 1, 12, 5, 2),
            Block.makeCuboidShape(4, 1, 1, 5, 5, 2),
            Block.makeCuboidShape(5, 1, 1, 11, 4, 2),
            Block.makeCuboidShape(1, 7, 2, 15, 8, 15),
            Block.makeCuboidShape(3, 8, 6, 7, 9, 10),
            Block.makeCuboidShape(2, 8, 6, 3, 12, 10),
            Block.makeCuboidShape(7, 8, 6, 8, 12, 10),
            Block.makeCuboidShape(2, 8, 10, 8, 12, 11),
            Block.makeCuboidShape(2, 8, 5, 8, 12, 6),
            Block.makeCuboidShape(9, 8, 5, 14, 9, 10),
            Block.makeCuboidShape(9, 9, 5, 14, 10, 6),
            Block.makeCuboidShape(9, 9, 9, 14, 10, 10),
            Block.makeCuboidShape(13, 9, 6, 14, 10, 9),
            Block.makeCuboidShape(9, 9, 6, 10, 10, 9)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(15, 0, 1, 16, 18, 15),
            Block.makeCuboidShape(1, 6, 0, 2, 7, 16),
            Block.makeCuboidShape(2, 6, 15, 16, 7, 16),
            Block.makeCuboidShape(2, 6, 0, 16, 7, 1),
            Block.makeCuboidShape(2, 0, 1, 3, 6, 15),
            Block.makeCuboidShape(3, 0, 14, 15, 6, 15),
            Block.makeCuboidShape(3, 0, 1, 15, 6, 2),
            Block.makeCuboidShape(1, 0, 4, 2, 1, 12),
            Block.makeCuboidShape(1, 4, 4, 2, 5, 12),
            Block.makeCuboidShape(1, 1, 4, 2, 5, 5),
            Block.makeCuboidShape(1, 1, 11, 2, 5, 12),
            Block.makeCuboidShape(1, 1, 5, 2, 4, 11),
            Block.makeCuboidShape(2, 7, 1, 15, 8, 15),
            Block.makeCuboidShape(6, 8, 9, 10, 9, 13),
            Block.makeCuboidShape(6, 8, 13, 10, 12, 14),
            Block.makeCuboidShape(6, 8, 8, 10, 12, 9),
            Block.makeCuboidShape(10, 8, 8, 11, 12, 14),
            Block.makeCuboidShape(5, 8, 8, 6, 12, 14),
            Block.makeCuboidShape(5, 8, 2, 10, 9, 7),
            Block.makeCuboidShape(5, 9, 2, 6, 10, 7),
            Block.makeCuboidShape(9, 9, 2, 10, 10, 7),
            Block.makeCuboidShape(6, 9, 2, 9, 10, 3),
            Block.makeCuboidShape(6, 9, 6, 9, 10, 7)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(1, 0, 0, 15, 18, 1),
            Block.makeCuboidShape(0, 6, 14, 16, 7, 15),
            Block.makeCuboidShape(15, 6, 0, 16, 7, 14),
            Block.makeCuboidShape(0, 6, 0, 1, 7, 14),
            Block.makeCuboidShape(1, 0, 13, 15, 6, 14),
            Block.makeCuboidShape(14, 0, 1, 15, 6, 13),
            Block.makeCuboidShape(1, 0, 1, 2, 6, 13),
            Block.makeCuboidShape(4, 0, 14, 12, 1, 15),
            Block.makeCuboidShape(4, 4, 14, 12, 5, 15),
            Block.makeCuboidShape(4, 1, 14, 5, 5, 15),
            Block.makeCuboidShape(11, 1, 14, 12, 5, 15),
            Block.makeCuboidShape(5, 1, 14, 11, 4, 15),
            Block.makeCuboidShape(1, 7, 1, 15, 8, 14),
            Block.makeCuboidShape(9, 8, 6, 13, 9, 10),
            Block.makeCuboidShape(13, 8, 6, 14, 12, 10),
            Block.makeCuboidShape(8, 8, 6, 9, 12, 10),
            Block.makeCuboidShape(8, 8, 5, 14, 12, 6),
            Block.makeCuboidShape(8, 8, 10, 14, 12, 11),
            Block.makeCuboidShape(2, 8, 6, 7, 9, 11),
            Block.makeCuboidShape(2, 9, 10, 7, 10, 11),
            Block.makeCuboidShape(2, 9, 6, 7, 10, 7),
            Block.makeCuboidShape(2, 9, 7, 3, 10, 10),
            Block.makeCuboidShape(6, 9, 7, 7, 10, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();


    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 0, 1, 1, 18, 15),
            Block.makeCuboidShape(14, 6, 0, 15, 7, 16),
            Block.makeCuboidShape(0, 6, 0, 14, 7, 1),
            Block.makeCuboidShape(0, 6, 15, 14, 7, 16),
            Block.makeCuboidShape(13, 0, 1, 14, 6, 15),
            Block.makeCuboidShape(1, 0, 1, 13, 6, 2),
            Block.makeCuboidShape(1, 0, 14, 13, 6, 15),
            Block.makeCuboidShape(14, 0, 4, 15, 1, 12),
            Block.makeCuboidShape(14, 4, 4, 15, 5, 12),
            Block.makeCuboidShape(14, 1, 11, 15, 5, 12),
            Block.makeCuboidShape(14, 1, 4, 15, 5, 5),
            Block.makeCuboidShape(14, 1, 5, 15, 4, 11),
            Block.makeCuboidShape(1, 7, 1, 14, 8, 15),
            Block.makeCuboidShape(6, 8, 3, 10, 9, 7),
            Block.makeCuboidShape(6, 8, 2, 10, 12, 3),
            Block.makeCuboidShape(6, 8, 7, 10, 12, 8),
            Block.makeCuboidShape(5, 8, 2, 6, 12, 8),
            Block.makeCuboidShape(10, 8, 2, 11, 12, 8),
            Block.makeCuboidShape(6, 8, 9, 11, 9, 14),
            Block.makeCuboidShape(10, 9, 9, 11, 10, 14),
            Block.makeCuboidShape(6, 9, 9, 7, 10, 14),
            Block.makeCuboidShape(7, 9, 13, 10, 10, 14),
            Block.makeCuboidShape(7, 9, 9, 10, 10, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();




    public BasicCookingUtensil() {
        super(Block.Properties.create(Material.IRON)
            .hardnessAndResistance(5.0f, 6.0f)
            .sound(SoundType.STONE)
            .harvestLevel(1)    // 0 = wood, 1 = stone, 2= iron, 3 = diamond
            .harvestTool(ToolType.PICKAXE)

        );
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {

        switch(state.get(FACING))
        {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // spawn lightning on rightclick
        /*
        if(!worldIn.isRemote())
        {
            ServerWorld serverWorld = (ServerWorld)worldIn;
            LightningBoltEntity lightning = new LightningBoltEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), false);
            serverWorld.addLightningBolt(lightning);
        }
        */
        if (!worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof CookingTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity)player, (CookingTileEntity)tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.COOKING_CHEST.get().create();
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving){
        if(state.getBlock() != newState.getBlock()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof CookingTileEntity) {
                InventoryHelper.dropItems(worldIn, pos, ((CookingTileEntity)te).getItems());
            }
        }
    }
}
