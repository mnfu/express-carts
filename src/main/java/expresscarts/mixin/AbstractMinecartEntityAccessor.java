package expresscarts.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;

@Mixin(AbstractMinecartEntity.class)
public interface AbstractMinecartEntityAccessor {
    @Accessor("CUSTOM_BLOCK_ID")
    static TrackedData<Integer> getCustomBlockId() {
        throw new AssertionError();
    }

    @Accessor("CUSTOM_BLOCK_OFFSET")
    static TrackedData<Integer> getCustomBlockOffset() {
        throw new AssertionError();
    }

    @Accessor("CUSTOM_BLOCK_PRESENT")
    static TrackedData<Boolean> getCustomBlockPresent() {
        throw new AssertionError();
    }
}
