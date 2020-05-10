package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

/*
This is not supposed to be the main runner
This is only going to be a way to make the json minions in an easier way
 */

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class MinionMaker
{
	public static void main(String[] args)
	{
		Item baseItem = setBaseItem();
		
		Minion minion = new Minion();
		minion.baseOutput = baseItem.product_id;
		System.out.print("how many items per break: ");
		minion.perBaseOutput = new Scanner(System.in).nextInt();
		minion.levels = new MinionLevel[11];
		for(int k = 0;k < minion.levels.length;k++)
		{
			MinionLevel ml = new MinionLevel();
			System.out.println("Please enter the stats of this minion's level " + (k + 1));
			System.out.print("time per item: ");
			ml.timeBetween = new Scanner(System.in).nextDouble();
			System.out.print("item capacity: ");
			ml.capacity = new Scanner(System.in).nextInt();
			minion.levels[k] = ml;
		}
		
		//replacement pseudo-pseudocode
		/*
		is smelting allowed with this minion
			yes
				new recipe r
				r.inputs = new linked hash map
				r.inputs.put(minion.baseout,minion.perbaseout)
				what is made from furnace? answer -> setBaseItem() -> r.outitem
				how many are made? -> r.outCount
				minion.outMods.put("autosmelt",r)
		repeat for:
			compact, compact and sc3000, smelt and compact, smelt and sc3000
		
		//TODO AFTER this is implented, make a saver for minions
		//if saved before this is done, the minions will have to be deleted and done from scratch
		 */
		
		/*
		pseudocode of given seconds item calc
		THIS IS NOT THE CLASS THIS WILL BE IMPLEMENTED INTO
		time in seconds tis
		tis / time per item = total base items tbi
		compactedname = minion.mod.get(compact).outname
		compactedcount = (tbi / minion.mod.get("compactor").input.get(minion.baseitem) ) * minion.mod.get(compactor).outCount
		tbi = tbi % minion.mod.get("compactor").input.get(minion.baseitem)
		same for SC3000 but
		supcomp = formula 1
		compacted = formula 2
		
		more english style
		take time minion will be idle tid
		(divide by time per item (usually actionTime * 2)) * itemsPerAct for total base items tbi
		get compactor mod recipe output compname
		how many compname is tbi / minion's compactor-mod input count for base item
		tbi is changed to the remainder of the same divisor (minion.mod.get(compactor).input.get(minion.baseItem))
		this gets repeated for sc3000 but with different starter points
		 */
	}
	
	private static Item setBaseItem()
	{
		System.out.print("Enter item product ID: ");
		String pid = new Scanner(System.in).nextLine();
		try
		{
			return new Gson().fromJson(new FileReader("data/items/" + pid + ".json"),Item.class);
		}catch (IOException ex) {
			//System.out.println("error 75");
			//ex.printStackTrace();
		}
		
		Item bItem = new Item();
		bItem.name = pid;
		bItem.product_id = pid;
		System.out.print("Merchant Sell Price of " + bItem.name + ": ");
		bItem.merchantSellVal = new Scanner(System.in).nextDouble();
		System.out.print("y or n... is this a bazaar sellable: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			bItem.bazaar = true;
		}
		else
		{
			bItem.bazaar = false;
		}
		try
		{
			Writer writer = new FileWriter("data/items/" + bItem.product_id + ".json");
			new Gson().toJson(bItem,writer);
			writer.close();
			System.out.println("saved");
		}catch (IOException ex){
			System.out.println("error 98");
			ex.printStackTrace();
		}
		return bItem;
	}
}
