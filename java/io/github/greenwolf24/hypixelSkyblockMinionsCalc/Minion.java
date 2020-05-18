package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Minion
{
	public String name;
	public String baseOutput;
	public int perBaseOutput;
	public MinionLevel[] levels;
	public LinkedHashMap<String,ArrayList<Recipe>> modOutputSteps;
}
