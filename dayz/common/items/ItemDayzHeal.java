package dayz.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import dayz.common.effects.EffectBleeding;
import dayz.common.effects.EffectZombification;
import dayz.common.misc.Util;

public class ItemDayzHeal extends Item
{
    private int healAmount;
    private boolean stopBleeding;
    private boolean stopInfection;
    private int textureIndex;

    public ItemDayzHeal(int i, int amountToHeal, boolean stopBleeding, boolean stopInfection, int index)
    {
        super(i);
        maxStackSize = 1;
        healAmount = amountToHeal;
        this.stopBleeding = stopBleeding;
        this.stopInfection = stopInfection;
        this.textureIndex = index;
    }

    public int getHealAmount()
    {
        return healAmount;
    }
    
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
    	itemstack.stackSize--;
        entityplayer.heal(healAmount);
        if (stopBleeding == true)
        {
        	if (entityplayer.isPotionActive(EffectBleeding.INSTANCE))
        	{
        		entityplayer.removePotionEffect(EffectBleeding.INSTANCE.id);
        	}
        }
        if (stopInfection == true)
        {
        	if (entityplayer.isPotionActive(EffectZombification.INSTANCE))
        	{
        		entityplayer.removePotionEffect(EffectZombification.INSTANCE.id);
        	}
        }
        return itemstack;
    }

    public void registerIcons(IconRegister par1IconRegister)
    {
    	switch(this.textureIndex)
    	{
    		case 0: this.itemIcon = par1IconRegister.registerIcon(Util.ID + ":bandage"); return;
    		case 1: this.itemIcon = par1IconRegister.registerIcon(Util.ID + ":antibiotics"); return;
    	}
    }
}
