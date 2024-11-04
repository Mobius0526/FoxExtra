package net.lrsoft.foxbladeex.hypersystem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.entity.EntityWitherSword;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects.State;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;

public class HS_advance implements ISpecialEffect {

    private static final String EffectKey = "HS_advance";

    private boolean useBlade(ItemSlashBlade.ComboSequence sequence) {
        if (sequence.useScabbard) return false;
        if (sequence == ItemSlashBlade.ComboSequence.None) return false;
        if (sequence == ItemSlashBlade.ComboSequence.Noutou) return false;
        return true;
    }

    @SubscribeEvent
    public void onUpdateItemSlashBlade(final SlashBladeEvent.OnUpdateEvent event) {
        if (!SpecialEffects.isPlayer(event.entity)) return;
        EntityPlayer player = (EntityPlayer) event.entity;
        World world = player.worldObj;
        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(event.blade);
        if (!useBlade(ItemSlashBlade.getComboSequence(tag))) return;
        if (SpecialEffects.isEffective(player, event.blade, this) == State.Effective) {

            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 100, 3));
            player.addPotionEffect(new PotionEffect(Potion.heal.getId(), 100, 1));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 100, 3));
            if (!world.isRemote) {
                boolean isBurst = (1 % 2 == 0);
                EntityWitherSword entityDrive = new EntityWitherSword(
                    world,
                    player,
                    1.0f,
                    ItemSlashBlade.RepairCount.get(tag) * 2);
                if (entityDrive != null) {
                    entityDrive.setInterval(16);
                    entityDrive.setLifeTime(35);
                    int color = isBurst ? -0xFFFAF0 : -0xFFFFFF;
                    entityDrive.setColor(color);
                    entityDrive.setBurst(isBurst);
                    world.spawnEntityInWorld(entityDrive);
                }
            }
        }
    }

    @Override
    public void register() {
        SlashBladeHooks.EventBus.register(this);
    }

    @Override
    public int getDefaultRequiredLevel() {
        return 60;
    }

    @Override
    public String getEffectKey() {
        return EffectKey;
    }

}
