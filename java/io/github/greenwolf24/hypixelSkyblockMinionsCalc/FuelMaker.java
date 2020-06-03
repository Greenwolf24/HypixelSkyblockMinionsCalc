package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class FuelMaker
{
	public static void main(String[] args)
	{
		/* TODO
		I am writing this before making the final equations for the calculator, and reading
		the wiki tells me that the efficiency is different for the Catalyst.  A future update
		will need to implement code for this.  In the meantime, I'm ignoring the catalyst.
		There is also a slight issue with the Solar panel, as it is only effective half the time,
		so I set the bonusEff as 0.125 instead of 0.25.  The problem is I don't know if a day
		saver on the island will make it work constantly,
		A simple workaround is to tell the user, "is you have a day saver, replace solar with
		the enchanted lava bucket"
		 */
		Fuel fuel = new Fuel();
		System.out.print("what is fuel name?: ");
		fuel.name = new Scanner(System.in).nextLine();
		System.out.print("what is the efficiency bonus percentage decimal?: ");
		fuel.effBonus = new Scanner(System.in).nextDouble();
		System.out.print("is the fuel infinite use: ");
		if(new Scanner(System.in).nextLine().toLowerCase().equals("y"))
		{
			fuel.isInfiniteTime = true;
			fuel.secondsLasts = Integer.MAX_VALUE;
		}
		else
		{
			fuel.isInfiniteTime = false;
			System.out.print("how many hours does it last?: ");
			fuel.secondsLasts = (int)(3600 * new Scanner(System.in).nextDouble());
		}
		try
		{
			Writer writer = new FileWriter("data/fuel/" + fuel.name + ".json");
			new Gson().toJson(fuel,writer);
			writer.close();
			System.out.println("saved");
		}catch (IOException ex){
			System.out.println("error 193");
			ex.printStackTrace();
		}
	}
}
