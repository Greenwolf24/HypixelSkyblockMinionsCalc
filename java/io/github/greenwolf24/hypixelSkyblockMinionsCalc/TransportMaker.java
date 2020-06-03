package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class TransportMaker
{
	public static void main(String[] args)
	{
		Transport transport = new Transport();
		System.out.print("what is transport name?: ");
		transport.name = new Scanner(System.in).nextLine();
		transport.target = "Merchant";
		System.out.print("what is the value multiplier (decimal format)?: ");
		transport.valueMultiplier = new Scanner(System.in).nextDouble();
		try
		{
			Writer writer = new FileWriter("data/transport/" + transport.name + ".json");
			new Gson().toJson(transport,writer);
			writer.close();
			System.out.println("saved");
		}catch (IOException ex){
			System.out.println("error 193");
			ex.printStackTrace();
		}
	}
}
