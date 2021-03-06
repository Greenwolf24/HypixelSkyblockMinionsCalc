package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

/*
This is not supposed to be the main runner
This is only going to be a way to make the json minions in an easier way
 */

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class MinionMaker
{
	public static void main(String[] args)
	{
		Minion minion = new Minion();
		System.out.print("what is the name of the minion (____ Minion): ");
		minion.name = new Scanner(System.in).nextLine();
		minion.baseOutputs = new LinkedHashMap<>();
		System.out.print("how many items does this minion make: ");
		int minionBaseOutCount = new Scanner(System.in).nextInt();
		for (int k = 0; k < minionBaseOutCount; k++)
		{
			System.out.println("What is this minion's base item?");
			//Item baseItem = getOrMakeItem();
			String name = getOrMakeItem().name;
			System.out.print("how many items per break (or decimal for rate of drop): ");
			minion.baseOutputs.put(name,new Scanner(System.in).nextDouble());
		}
		minion.levels = new MinionLevel[11];
		System.out.print("Does this minion use the typical storage level amounts?: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			int[] sizes = new int[]{64,192,192,384,384,576,576,768,768,960,960};
			for(int k = 0;k < minion.levels.length;k++)
			{
				if((k + 1) % 2 == 0)
				{
					minion.levels[k] = minion.levels[k - 1];
				}
				else
				{
					MinionLevel ml = new MinionLevel();
					System.out.println("Please enter the stats of this minion's level " + (k + 1) + " and " + (k + 2));
					System.out.print("time per item: ");
					ml.timeBetween = new Scanner(System.in).nextDouble();
					ml.capacity = sizes[k];
					minion.levels[k] = ml;
				}
			}
		}
		else
		{
			for (int k = 0;k < minion.levels.length;k++)
			{
				MinionLevel ml = new MinionLevel();
				System.out.println("Please enter the stats of this minion's level " + (k + 1));
				System.out.print("time per item: ");
				ml.timeBetween = new Scanner(System.in).nextDouble();
				System.out.print("item capacity: ");
				ml.capacity = new Scanner(System.in).nextInt();
				minion.levels[k] = ml;
			}
		}
		minion.modOutputSteps = new LinkedHashMap<>();
		
		System.out.print("Can this minion use AUTOSMELTER (y or n)?: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			System.out.println("please create the recipe steps for AUTOSMELTER");
			minion.modOutputSteps.put("AUTOSMELTER",makeSteps(1));
		}
		
		for(String mod : new String[]{"COMPACTER","SC3000"})
		{
			System.out.print("can this minion use a " + mod + " (y/n)?:");
			if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
			{
				if(minion.modOutputSteps.containsKey("AUTOSMELTER"))
				{
					System.out.print("does the " + mod + " require AUTOSMELTER?: ");
					if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
					{
						System.out.print("How many steps are involved?");
						int s = new Scanner(System.in).nextInt();
						System.out.println("please create the recipe steps for AUTOSMELTER_" + mod);
						minion.modOutputSteps.put("AUTOSMELTER_" + mod,makeSteps(s));
					}
					else
					{
						System.out.print("can the " + mod + " be used with AUTOSMELTER?: ");
						if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
						{
							System.out.print("How many steps are involved?");
							int s = new Scanner(System.in).nextInt();
							System.out.println("please create the recipe steps for AUTOSMELTER_" + mod);
							minion.modOutputSteps.put("AUTOSMELTER_" + mod,makeSteps(s));
						}
						else
						{
							System.out.print("How many steps are involved?");
							int s = new Scanner(System.in).nextInt();
							System.out.println("please create the recipe steps for " + mod);
							minion.modOutputSteps.put(mod,makeSteps(s));
						}
					}
				}
				else
				{
					System.out.print("How many steps are involved?");
					int s = new Scanner(System.in).nextInt();
					System.out.println("please create the recipe steps for " + mod);
					minion.modOutputSteps.put(mod,makeSteps(s));
				}
			}
		}
		
		System.out.print("can COMPACTER_SC3000 be used with this minion");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			System.out.print("How many steps are involved?");
			int s = new Scanner(System.in).nextInt();
			System.out.println("please create the recipe steps for COMPACTER_SC3000");
			minion.modOutputSteps.put("COMPACTER_SC3000",makeSteps(s));
		}
		
		saveMinion(minion);
		
		/*
		pseudocode of given seconds item calc
		THIS IS NOT THE CLASS THIS WILL BE IMPLEMENTED INTO
		THIS IS OUTDATED WITH modOutputSteps UPDATE
		But I'm keeping it here in case the math is still needed later
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
	
	private static ArrayList<String> makeSteps(int num)
	{
		ArrayList<String> steps = new ArrayList<>();
		for(int k = 0;k < num;k++)
		{
			steps.add(getOrMakeRecipeName());
		}
		return steps;
	}
	
	private static void saveMinion(Minion m)
	{
		try
		{
			Writer writer = new FileWriter("data/minions/" + m.name + ".json");
			new Gson().toJson(m,writer);
			writer.close();
			System.out.println("saved");
		}catch (IOException ex){
			System.out.println("error 98");
			ex.printStackTrace();
		}
	}
	
	private static String getOrMakeRecipeName()
	{
		System.out.print("What is the recipe output item");
		String outItem = getOrMakeItem().name;
		System.out.print("how many does this recipe make: ");
		int outAmount = new Scanner(System.in).nextInt();
		String fileBase = outItem + "x" + outAmount + "_";
		int fileBaseNum = 0;
		try
		{
			File fold = new File("data/recipes/");
			String[] files = fold.list();
			ArrayList<String> matches = new ArrayList<>();
			for(String file : files)
			{
				//System.out.println("196 FILE " + file);
				//System.out.println("197 index " + file.indexOf(fileBase));
				//System.out.println("198 subst " + file.substring(fileBase.length() + 3,fileBase.length() + 4));
				//System.out.println("199 =. " + file.substring(fileBase.length() + 3,fileBase.length() + 4).equals("."));
				//checks if the file is a . file after the recipe number.
				// Ex mushroomblock1_001.json wont be found with mushroom1_001.json
				if(file.indexOf(fileBase) == 0 && file.substring(fileBase.length() + 3,fileBase.length() + 4).equals("."))
				{
					matches.add(file);
				}
			}
			fileBaseNum = matches.size() + 1;
			for(String file : matches)
			{
				try
				{
					System.out.println(file);
					Recipe temp = new Gson().fromJson(new FileReader("data/recipes/" + file), Recipe.class);
					System.out.print("are the inputs " + temp.inputs + ": ");
					if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
					{
						String retRecipeName = file.replaceAll(".json","");
						return retRecipeName;
					}
				}catch (FileNotFoundException ex){}
			}
		}catch (NullPointerException ex) {}
		
		Recipe recipe = new Recipe();
		recipe.inputs = new LinkedHashMap<>();
		//recipe.inputs.put(minion.baseOutput,minion.perBaseOutput);
		System.out.print("how many different inputs: ");
		int recInputCount = new Scanner(System.in).nextInt();
		for(int k = 0;k < recInputCount;k++)
		{
			System.out.print("What is input item");
			String input = getOrMakeItem().name;
			System.out.print("how many " + input + " does it take:");
			recipe.inputs.put(input, new Scanner(System.in).nextInt());
		}
		recipe.outItem = outItem;
		recipe.outCount = outAmount;
		String finalSaveName = outItem + "x" + outAmount + "_" + formatRecNum(fileBaseNum);
		try
		{
			
			Writer writer = new FileWriter("data/recipes/" + finalSaveName + ".json");
			new Gson().toJson(recipe,writer);
			writer.close();
			System.out.println("saved");
		}
		catch (IOException ex){}
		return finalSaveName;
	}
	
	private static Recipe recipeNameToRecipe(String name)
	{
		try
		{
			return new Gson().fromJson(new FileReader("data/recipes/" + name + ".json"),Recipe.class);
		}catch (Exception ex)
		{
			System.err.println("An error occurred while fetching that file. Rerouting you to the getOrMakeRecipeName now");
			return recipeNameToRecipe(getOrMakeRecipeName());
		}
	}
	
	private static String formatRecNum(int i)
	{
		String s = Integer.toString(i);
		int sl = s.length();
		for(int k = 0;k < 3 - sl;k++)
		{
			s = "0" + s;
		}
		return s;
	}
	
	private static Item getOrMakeItem()
	{
		System.out.print("Enter item name: ");
		String name = new Scanner(System.in).nextLine();
		try
		{
			return new Gson().fromJson(new FileReader("data/items/" + name + ".json"),Item.class);
		}catch (IOException ex) {
			//System.out.println("error 75");
			//ex.printStackTrace();
		}
		
		Item bItem = new Item();
		bItem.name = name;
		System.out.print("Merchant Sell Price of " + bItem.name + " (0.0 for not sellable): ");
		bItem.merchantSellVal = new Scanner(System.in).nextDouble();
		System.out.print("y or n... is this a bazaar sellable: ");
		bItem.bazaar = new Scanner(System.in).nextLine().toLowerCase().equals("y");
		if(bItem.bazaar)
		{
			String maybePID = name.toUpperCase().replaceAll(" ","_");
			System.out.print("is the product ID " + maybePID + "?: ");
			if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
			{
				bItem.product_id = maybePID;
			}
			else
			{
				System.out.print("what is the product ID : ");
				bItem.product_id = new Scanner(System.in).nextLine();
			}
		}
		else
		{
			bItem.product_id = "null";
		}
		
		try
		{
			Writer writer = new FileWriter("data/items/" + bItem.name + ".json");
			new Gson().toJson(bItem,writer);
			writer.close();
			System.out.println("saved");
		}catch (IOException ex){
			System.out.println("error 193");
			ex.printStackTrace();
		}
		return bItem;
	}
}
