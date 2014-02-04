package tconstruct.blocks;

import java.util.List;

import mantle.blocks.abstracts.InventoryBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import tconstruct.TConstruct;
import tconstruct.blocks.logic.ToolForgeLogic;
import tconstruct.client.block.TableForgeRender;
import tconstruct.common.TProxyCommon;
import tconstruct.common.TRepo;
import tconstruct.library.TConstructRegistry;

public class ToolForgeBlock extends InventoryBlock
{

    public ToolForgeBlock(Material material)
    {
        super(material);
        this.func_149647_a(TConstructRegistry.blockTab);
        this.func_149711_c(2f);
        this.func_149672_a(Block.field_149777_j);
    }

    String[] textureNames = { "toolforge_iron", "toolforge_gold", "toolforge_diamond", "toolforge_emerald", "toolforge_cobalt", "toolforge_ardite", "toolforge_manyullyn", "toolforge_copper",
            "toolforge_bronze", "toolforge_tin", "toolforge_aluminum", "toolforge_alubrass", "toolforge_alumite", "toolforge_steel" };

    /* Rendering */
    @Override
    public String[] getTextureNames ()
    {
        return textureNames;
    }

    IIcon textureTop;

    @Override
    public void func_149651_a (IIconRegister iconRegister)
    {
        super.func_149651_a(iconRegister);
        textureTop = iconRegister.registerIcon("tinker:toolforge_top");
    }

    @Override
    public IIcon func_149691_a (int side, int meta)
    {
        if (side == 1)
        {
            return textureTop;
        }
        if (side == 0)
        {
            switch (meta)
            {
            case 0:
                return Blocks.iron_block.func_149691_a(side, 0);
            case 1:
                return Blocks.gold_block.func_149691_a(side, 0);
            case 2:
                return Blocks.diamond_block.func_149691_a(side, 0);
            case 3:
                return Blocks.emerald_block.func_149691_a(side, 0);
            default:
                return TRepo.metalBlock.func_149691_a(side, meta - 4);
            }
        }

        return this.icons[meta];
    }

    @Override
    public boolean func_149686_d ()
    {
        return false;
    }

    @Override
    public boolean func_149662_c ()
    {
        return false;
    }

    @Override
    public boolean isSideSolid (IBlockAccess world, int x, int y, int z, ForgeDirection side)
    {
        return side == ForgeDirection.UP;
    }

    @Override
    public int func_149645_b ()
    {
        return TableForgeRender.model;
    }

    @Override
    public boolean func_149646_a (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    public TileEntity func_149915_a (World world, int metadata)
    {
        return new ToolForgeLogic();
    }

    @Override
    public Integer getGui (World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        int md = world.getBlockMetadata(x, y, z);
        return TProxyCommon.toolForgeID;
    }

    @Override
    public Object getModInstance ()
    {
        return TConstruct.instance;
    }

    @Override
    public void func_149666_a (Item b, CreativeTabs tab, List list)
    {
        for (int iter = 0; iter < textureNames.length; iter++)
        {
            list.add(new ItemStack(b, 1, iter));
        }
    }
}