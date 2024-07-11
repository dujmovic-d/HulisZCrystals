package dev.huli.zcrystals.items.serverside;

import com.cobblemon.mod.common.Cobblemon;
import dev.huli.zcrystals.util.ZCrystalsServerSide;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ZRing extends SimplePolymerItem {
    PolymerModelData modelData;
    public ZRing(Settings settings, Item polymerItem) {
        super(settings, polymerItem);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player){
        return super.getPolymerItem(itemStack,player);
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player){
        this.modelData = ZCrystalsServerSide.zringModelData;
        return this.modelData.value();
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(Cobblemon.playerData.get(playerEntity).getKeyItems().contains(new Identifier("cobblemon:z_ring"))){
            playerEntity.sendMessage(Text.literal("You already have the ability to use Z-Moves!").formatted(Formatting.RED),true);
            return TypedActionResult.fail(playerEntity.getStackInHand(hand));
        }
        else {
            Cobblemon.playerData.get(playerEntity).getKeyItems().add(new Identifier("cobblemon:z_ring"));
            playerEntity.sendMessage(Text.literal("You obtained the ability to use Z-Moves!").formatted(Formatting.GREEN),true);
            playerEntity.getStackInHand(hand).decrement(1);
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }
    }}
