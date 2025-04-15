package expresscarts;

import org.jetbrains.annotations.Nullable;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MinecartItem;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class ExpressMinecartItem extends MinecartItem implements PolymerItem {
    private final Item polymerItem;

    public ExpressMinecartItem(EntityType<? extends AbstractMinecartEntity> type, Item.Settings settings,
            Item polymerItem) {
        super(type, settings);
        this.polymerItem = polymerItem;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, PacketContext context) {
        return this.polymerItem;
    }

    @Override
    public @Nullable Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return this.getPolymerItem(stack, context).getDefaultStack().get(DataComponentTypes.ITEM_MODEL);
    }

}