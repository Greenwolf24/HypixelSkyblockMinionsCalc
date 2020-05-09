package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

/*
This is not supposed to be the main runner
This is only going to be a way to make the json minions in an easier way
 */

import java.util.Scanner;

public class MinionMaker
{
	public static void main(String[] args)
	{
		//TODO make main() start item maker a separate method
		Item baseItem = new Item();
		System.out.print("base item id: ");
		{
			String temp = new Scanner(System.in).nextLine();
			baseItem.name = temp;
			baseItem.product_id = temp;
		}
		System.out.print("Merchant Sell Price of " + baseItem.name + ": ");
		baseItem.merchantSellVal = new Scanner(System.in).nextDouble();
		System.out.print("y or n... is this a bazaar sellable: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			baseItem.bazaar = true;
		}
		else
		{
			baseItem.bazaar = false;
		}
		//TODO at this point save item into a folder with gson json
		
		Minion minion = new Minion();
		minion.baseOutput = baseItem.product_id;
		System.out.print("how many items per break: ");
		minion.perBaseOutput = new Scanner(System.in).nextInt();
		minion.levels = new MinionLevel[11];
		for(int k = 0;k < minion.levels.length - 1;k++)
		{
			MinionLevel ml = new MinionLevel();
			System.out.println("Please enter the stats of this minion's level " + (k + 1));
			System.out.print("time per item: ");
			ml.timeBetween = new Scanner(System.in).nextDouble();
			System.out.print("item capacity: ");
			ml.capacity = new Scanner(System.in).nextInt();
			minion.levels[k] = ml;
		}
		
		//pseudocode time because im tired
		/*
		does this minion use smelter
		yes
			new recipe maker
				recipe r new recipe
				how many of minion.baseoutput is inputed
				r.inputs put minion.baseoutput, new scanner int
				string tempOutID
				what is output item?
					same as start of main() item maker
					//TODO make main() start item maker a separate method
				how many out
				r.outs put tempOutID,
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
		 */
	}
}
