package net.lrsoft.foxbladeex;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import mods.flammpfeil.slashblade.ItemSlashBlade;
import mods.flammpfeil.slashblade.entity.EntitySakuraEndManager;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;

public class SAsakuraend_ex extends SpecialAttackBase {

    @Override
    public String toString() {
        return "sakuraend_ex";
    }

    @Override
    public void doSpacialAttack(ItemStack stack, EntityPlayer player) {
        World world = player.worldObj;

        NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);

        if (!world.isRemote) {

            final int cost = -15;
            if (!ItemSlashBlade.ProudSoul.tryAdd(tag, cost, false)) {
                ItemSlashBlade.damageItem(stack, 10, player);
            }
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 80, 2, true));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 200, 2, true));
            ItemSlashBlade blade = (ItemSlashBlade) stack.getItem();

            EntitySakuraEndManager entityDA = new EntitySakuraEndManager(world, player);
            if (entityDA != null) {
                world.spawnEntityInWorld(entityDA);
            }
        }

        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashEdge);

    }

}
