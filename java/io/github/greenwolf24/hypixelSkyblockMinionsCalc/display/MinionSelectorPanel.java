package io.github.greenwolf24.hypixelSkyblockMinionsCalc.display;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

public class MinionSelectorPanel extends JPanel implements ActionListener
{
	String selectedMinon = "", selectedFuel = "", selectedTransport = "", selectedModSet = "";
	String[] modSets = {""};
	JComboBox minionSel, fuelSel, transportSel, modSetSel;
	JLabel minionLab = new JLabel("Minion: "),
			fuelLab = new JLabel("Fuel: "),
			transportLab = new JLabel("Transport: "),
			modSetLab = new JLabel("ModSet:");
	
	public MinionSelectorPanel(String[] minions,String[] fuels,String[] transports)
	{
		this.setBorder(
				BorderFactory.createLineBorder(
						new Color(40,40,40)
				)
		);
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		//this.setLayout(new SpringLayout());
		this.setMinimumSize(new Dimension(150,150));
		this.setPreferredSize(new Dimension(150,150));
		this.setMaximumSize(new Dimension(150,150));
		
		//minionLab.setPreferredSize(new Dimension(33,25));
		//minionLab.setHorizontalTextPosition(JLabel.LEFT);
		this.add(minionLab);
		minionSel = new JComboBox(minions);
		//minionSel.setPreferredSize(new Dimension(67,25));
		minionSel.addActionListener(this);
		this.add(minionSel);
		
		//fuelLab.setPreferredSize(new Dimension(33,25));
		this.add(fuelLab);
		fuelSel = new JComboBox(fuels);
		//fuelSel.setPreferredSize(new Dimension(67,25));
		fuelSel.addActionListener(this);
		this.add(fuelSel);
		
		//transportLab.setPreferredSize(new Dimension(33,25));
		this.add(transportLab);
		transportSel = new JComboBox(transports);
		//transportSel.setPreferredSize(new Dimension(67,25));
		transportSel.addActionListener(this);
		this.add(transportSel);
		
		//modSetLab.setPreferredSize(new Dimension(33,25));
		this.add(modSetLab);
		modSetSel = new JComboBox(modSets);
		//modSetSel.setPreferredSize(new Dimension(67,25));
		modSetSel.addActionListener(this);
		this.add(modSetSel);
	}
	
	public void setModSets(String[] mods)
	{
		modSets = mods;
	}
	
	/*
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("MainTest003" + new Random().nextInt());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		
		String[] minionNames = new File("data/minions/").list();
		String[] fuelNames = new File("data/fuel/").list();
		String[] transportNames = new File("data/transport/").list();
		
		MinionSelectorPanel panel = new MinionSelectorPanel(minionNames,fuelNames,transportNames);
		frame.add(panel);
		frame.setVisible(true);
	}
	*/
	
	public void actionPerformed(ActionEvent e)
	{
		selectedMinon = (String)minionSel.getSelectedItem();
		selectedFuel = (String)fuelSel.getSelectedItem();
		selectedTransport = (String)transportSel.getSelectedItem();
		selectedModSet = (String)modSetSel.getSelectedItem();
	}
}
