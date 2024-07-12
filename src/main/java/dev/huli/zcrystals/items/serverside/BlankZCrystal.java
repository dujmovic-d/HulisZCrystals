package dev.huli.zcrystals.items.serverside;

import dev.huli.zcrystals.util.ZCrystalsServerSide;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.Difficulty;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlankZCrystal extends SimplePolymerItem {
    PolymerModelData modelData;
    public BlankZCrystal(Settings settings, Item polymerItem){
        super(settings,polymerItem);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext itemUsageContext) {
        Block block = itemUsageContext.getWorld().getBlockState(itemUsageContext.getBlockPos()).getBlock();
        PlayerEntity player = Objects.requireNonNull(itemUsageContext.getPlayer());
        ItemStack heldStack = Objects.requireNonNull(itemUsageContext.getPlayer()).getMainHandStack();

        if(block == Blocks.WHEAT){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.GRASSIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.MAGMA_BLOCK){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.FIRIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.BEEHIVE){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.BUGINIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.POWDER_SNOW){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.ICIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.REINFORCED_DEEPSLATE){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.DARKINIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.BEDROCK){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.ROCKIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.DRAGON_HEAD || block == Blocks.DRAGON_WALL_HEAD){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.DRAGONIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.COPPER_BLOCK){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.ELECTRIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.MUD){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.GROUNDIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(player.getWorld().getDifficulty() == Difficulty.PEACEFUL && block == Blocks.POTATOES){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.POISONIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.SOUL_SAND){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.GHOSTIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.BAMBOO_BLOCK){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.FIGHTINIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.WET_SPONGE){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.WATERIUM_Z));
            return ActionResult.SUCCESS;
        }
        if(block == Blocks.END_GATEWAY){
            heldStack.decrement(1);
            player.giveItemStack(new ItemStack(ZCrystalsServerSide.PSYCHIUM_Z));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    @Override
    public ActionResult useOnEntity(ItemStack itemStack, PlayerEntity playerEntity, LivingEntity livingEntity, Hand hand) {

        if(livingEntity instanceof ChickenEntity){
            itemStack.decrement(1);
            livingEntity.kill();
            playerEntity.giveItemStack(new ItemStack(ZCrystalsServerSide.FLYINIUM_Z));
            return ActionResult.CONSUME;
        }
        if(livingEntity instanceof AllayEntity){
            itemStack.decrement(1);
            playerEntity.giveItemStack(new ItemStack(ZCrystalsServerSide.FAIRIUM_Z));
            return ActionResult.CONSUME;
        }
        if(livingEntity instanceof IronGolemEntity){
            itemStack.decrement(1);
            livingEntity.kill();
            playerEntity.giveItemStack(new ItemStack(ZCrystalsServerSide.STEELIUM_Z));
            return ActionResult.CONSUME;
        }
        if(playerEntity.getEntityWorld().getDifficulty() != Difficulty.PEACEFUL && livingEntity instanceof CaveSpiderEntity){
            itemStack.decrement(1);
            livingEntity.kill();
            playerEntity.giveItemStack(new ItemStack(ZCrystalsServerSide.POISONIUM_Z));
            return ActionResult.CONSUME;
        }

        return ActionResult.PASS;
    }


    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player){
        return super.getPolymerItem(itemStack,player);
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player){
        this.modelData = ZCrystalsServerSide.blankZCrystalModelData;
        return this.modelData.value();
    }
}
