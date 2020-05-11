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
		Item baseItem = getOrMakeItem();
		
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
		
		//boolean furnace = false;
		System.out.print("Can this minion use an Auto Smelter (y or n)?: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			Recipe r = new Recipe();
			r.inputs.put(minion.baseOutput,minion.perBaseOutput);
			System.out.print("What does the autosmelter produce?");
			Item i = getOrMakeItem();
			r.outItem = i.product_id;
			System.out.print("How many does the autosmelter make?: ");
			r.outCount = new Scanner(System.in).nextInt();
			minion.modOutput.put("Auto Smelter",r);
		}
		
		//String[] mods = {"Compacter","Super Compacter 3000"};
		for(String mod : new String[]{"Compacter","Super Compacter 3000"})
		{
			System.out.print("Can this minion use a " + mod + "(y/n)?:");
			if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
			{
				Recipe r = new Recipe();
				String modRecItem = minion.baseOutput;
				if(minion.modOutput.containsKey("Auto Smelter"))
				{
					System.out.print("does the " + mod + " need the furnace's output?: ");
					if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
					{
						minion.modRequirements.put(mod,"Auto Smelter");
						modRecItem = minion.modOutput.get("Auto Smelter").outItem;
						//r.inputs.put(furnOut.outItem);
					}
				}
				System.out.print("how many items does the " + mod + " take?: ");
				r.inputs.put(modRecItem,new Scanner(System.in).nextInt());
				System.out.print("what does the " + mod + " make");
				r.outItem = getOrMakeItem().product_id;
				System.out.println("and how many?: ");
				r.outCount = new Scanner(System.in).nextInt();
				minion.modOutput.put(mod,r);
			}
		}
		
		//Improved pseudocode
		/*
		if
			minion.modreqs.containsKey(compacter) or containsKey(super compacter 3000)
				comp + super comp impossible
		if user input comp and sc3000 possible
			if minion.modoutput.containKey(compacter) and not containKey(super)
				minion.modReq.put(super compacter,compacter)
			//TODO determine a method of recipe storage for multiple action modifications
				iron -> enc. iron -> enc. iron block
		 */
		
		//TODO AFTER this is implented, make a saver for minions
		//if saved before this is done, the minions will have to be deleted and done from scratch
		
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
	
	private static Item getOrMakeItem()
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
