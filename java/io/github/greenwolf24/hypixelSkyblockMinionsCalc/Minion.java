package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Minion
{
	public String name;
	public LinkedHashMap<String,Double> baseOutputs;
	public MinionLevel[] levels;
	public LinkedHashMap<String,ArrayList<String>> modOutputSteps;
}
