package io.github.greenwolf24.hypixelSkyblockMinionsCalc;

import io.github.greenwolf24.hypixelSkyblockMinionsCalc.display.MinionSelectorPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class MainTest004 implements ActionListener, ChangeListener
{
	MinionSelectorPanel[] selectorPanels = new MinionSelectorPanel[0];
	JSpinner minionCounter;
	JFrame frame = new JFrame("MainTest003" + new Random().nextInt());
	String[] minionNames, fuelNames, transportNames;
	JPanel allMinionPanels;
	
	public MainTest004()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600,900);
		frame.setLayout(new BorderLayout());
		
		minionNames = new File("data/minions/").list();
		fuelNames = new File("data/fuel/").list();
		transportNames = new File("data/transport/").list();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));
		JLabel howMany = new JLabel("How many minions can you use?: ");
		topPanel.add(howMany);
		SpinnerNumberModel model = new SpinnerNumberModel(1,1,25,1);
		minionCounter = new JSpinner(model);
		minionCounter.addChangeListener(this);
		topPanel.add(minionCounter);
		frame.add(topPanel,BorderLayout.NORTH);
		
		allMinionPanels = new JPanel();
		allMinionPanels.setLayout(new GridLayout(5,5));
		frame.add(allMinionPanels);
		
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new MainTest004();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
	
	}
	
	private void updateMinionPanels()
	{
		allMinionPanels.removeAll();
		try
		{
			frame.remove(allMinionPanels);
			allMinionPanels.setLayout(new GridLayout(selectorPanels.length/5-1,5));
		}catch (Exception ex){}
		for(MinionSelectorPanel msp : selectorPanels)
		{
			allMinionPanels.add(new MinionSelectorPanel(minionNames,fuelNames,transportNames));
			
		}
		frame.add(allMinionPanels);
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		System.out.println("63");
		System.out.println(minionCounter.getValue());
		selectorPanels = new MinionSelectorPanel[(int)minionCounter.getValue()];
		updateMinionPanels();
		frame.pack();
		frame.repaint();
	}
}
