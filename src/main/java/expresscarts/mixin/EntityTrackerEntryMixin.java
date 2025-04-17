package expresscarts.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import expresscarts.ExpressMinecartEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.server.network.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Definition(id = "AbstractMinecartEntity", type = AbstractMinecartEntity.class)
    @Expression("? instanceof AbstractMinecartEntity")
    @WrapOperation(method = "tick()V", at = @At("MIXINEXTRAS:EXPRESSION"))
    // Don't send MoveMinecartAlongTrackS2CPacket in relation to ExpressMinecartEntity,
    // instead sending it to the client like any other entity (and, importantly, like minecarts using DefaultMinecartController).
    private boolean fakeDefaultController(Object obj, Operation<Boolean> original) {
        return !(obj instanceof ExpressMinecartEntity) && original.call(obj);
    }
}
