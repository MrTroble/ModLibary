package net.hycrafthd.core;

import java.util.Map;

import net.hycrafthd.core.util.ClassObject;
import net.hycrafthd.core.util.CoreUtil;
import net.hycrafthd.core.util.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

/**
 * CommonRegistry! For both sides
 */
public class CommonRegistry {

	/**
	 * Add a new shaped crafting recipe.
	 * 
	 * @param output Itemstack instance
	 * @param obj Craftingfield object
	 */
	public static void addShapedRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapedRecipe(output, obj);
	}

	/**
	 * Add new shapeless crafting recipe.
	 * 
	 * @param output Itemstack instance
	 * @param obj Item / Block / ItemStack input instance
	 */
	public static void addShapelessRecipe(ItemStack output, Object... obj) {
		GameRegistry.addShapelessRecipe(output, obj);
	}

	/**
	 * Add new smelting recipe.
	 * 
	 * @param input Item / Block / ItemStack instance
	 * @param output ItemStack instance
	 * @param xp XP per item
	 */
	public static void addSmelting(Object input, Object output, float xp) {
		GameRegistry.addSmelting(ItemUtil.getItemStack(input), ItemUtil.getItemStack(output), xp);
	}

	/**
	 * Register a new block with default ItemBlock.
	 *
	 * @param block Block instance
	 * @param blockname Block name
	 */
	public static void registerBlock(Block block, String blockname) {
		registerBlock(block, ItemBlock.class, blockname);
	}

	/**
	 * Register a new block.
	 *
	 * @param block Block instance
	 * @param itemblock ItemBlock class
	 * @param blockname Block name
	 */
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String blockname) {
		CoreUtil.invokeMethod("Common", "registerBlock", ClassObject.forObj(block, itemblock, blockname));
	}

	/**
	 * Register a new entity.
	 *
	 * @param entityClass Entity class
	 * @param entityName Entity name
	 * @param mod Modid
	 * @param trackingRange Tracking range
	 * @param updateFrequency Update frequency
	 * @param sendsVelocityUpdates Send velocity updates
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName, String mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		CoreUtil.invokeMethod("Common", "registerEntity", ClassObject.forObj(entityClass, entityName, mod, trackingRange, updateFrequency, sendsVelocityUpdates));
	}

	/**
	 * Register a new entity.
	 *
	 * @param entityClass Entity class
	 * @param entityName Entity name
	 * @param id Entity id
	 * @param mod Modid
	 * @param trackingRange Tracking range
	 * @param updateFrequency Update frequency
	 * @param sendsVelocityUpdates Send velocity updates
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, String mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		CoreUtil.invokeMethod("Common", "registerEntity", ClassObject.forObj(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates));
	}

	/**
	 * Register a new entity with spawn eggs.
	 *
	 * @param entityClass Entity class
	 * @param entityName Entity name
	 * @param mod Modid
	 * @param trackingRange Tracking range
	 * @param updateFrequency Update frequency
	 * @param sendsVelocityUpdates Send velocity updates
	 * @param eggPrimary Egg primary color
	 * @param eggSecondary Egg secondary color
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName, String mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
		CoreUtil.invokeMethod("Common", "registerEntity", ClassObject.forObj(entityClass, entityName, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary));
	}

	/**
	 * Register a new entity with spawn eggs.
	 *
	 * @param entityClass Entity class
	 * @param entityName Entity name
	 * @param id Entity id
	 * @param mod Modid
	 * @param trackingRange Tracking range
	 * @param updateFrequency Update frequency
	 * @param sendsVelocityUpdates Send velocity updates
	 * @param eggPrimary Egg primary color
	 * @param eggSecondary Egg secondary color
	 */
	public static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, String mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int eggPrimary, int eggSecondary) {
		CoreUtil.invokeMethod("Common", "registerEntity", ClassObject.forObj(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates, eggPrimary, eggSecondary));
	}

	/**
	 * Register a new guihandler
	 * 
	 * @param mod Modid
	 * @param handler Guihandler instance
	 */
	public static void registerGuiHandler(Object mod, IGuiHandler handler) {
		NetworkRegistry.INSTANCE.registerGuiHandler(mod, handler);
	}

	/**
	 * Register a new item.
	 *
	 * @param item Item instance
	 * @param itemname Item name
	 */
	public static void registerItem(Item item, String itemname) {
		CoreUtil.invokeMethod("Common", "registerItem", ClassObject.forObj(item, itemname));
	}

	/**
	 * Register a new ore
	 * 
	 * @param obj Item / Block / ItemStack input instance
	 */
	public static void registerOre(Object obj) {
		ItemStack stack = ItemUtil.getItemStack(obj);
		OreDictionary.registerOre(stack.getItem().getUnlocalizedName().substring(5), stack);
	}

	/**
	 * Register a new tile entity.
	 *
	 * @param tileEntityClass Tile entity class
	 */
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass) {
		Map<String, Class<?>> teMappings = ObfuscationReflectionHelper.getPrivateValue(TileEntity.class, null, "field_145855_i", "nameToClassMap");
		int id = teMappings.size() + 1;
		registerTileEntity(tileEntityClass, tileEntityClass.getName() + "-" + id);
	}

	/**
	 * Register a new tile entity.
	 *
	 * @param tileEntityClass Tile entity class
	 * @param id Tile entity id
	 */
	public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id) {
		GameRegistry.registerTileEntity(tileEntityClass, id);
	}

	/**
	 * Register a new worldgenerator
	 * 
	 * @param worldgen Worldgenerator instance
	 */
	public static void registerWorldGenerator(IWorldGenerator worldgen) {
		GameRegistry.registerWorldGenerator(worldgen, 0);
	}

}
