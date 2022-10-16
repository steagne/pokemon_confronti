import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

@SuppressWarnings("serial")
public class View extends JFrame
{
	private final Model model;
	private final Controller controller;
	String[] tipi = { "Normale", "Lotta", "Volante", "Veleno", "Terra", "Roccia",
			  "Coleottero", "Spettro", "Acciaio", "Fuoco", "Acqua", "Erba",
			  "Elettro", "Psico", "Ghiaccio", "Drago", "Buio", "Folletto" };
	Dimension screenSize;
	
	private JComboBox<String> comboMossa1;
	private JComboBox<String> comboMossa2;
	private JComboBox<String> comboMossa3;
	private JComboBox<String> comboMossa4;
	private JTree treeTipiBattuti;
	private JTree treeTipiRimanenti;
	private JButton buttonPulisci;
	
	public View()
	{
		model = new Model();
		controller = new Controller(this, model);
		
		setTitle("Controlla Tipi");
		setSize(310, 576);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
		setResizable(false);
		
		comboMossa1 = new JComboBox<>();
		comboMossa1.setBounds(17, 10, 120, 28);
		comboMossa1.addItem("");
		for (int i = 0; i < 18; i++)
			comboMossa1.addItem(tipi[i]);
		comboMossa2 = new JComboBox<>();
		comboMossa2.setBounds(157, 10, 120, 28);
		comboMossa2.addItem("");
		for (int i = 0; i < 18; i++)
			comboMossa2.addItem(tipi[i]);
		comboMossa3 = new JComboBox<>();
		comboMossa3.setBounds(17, 48, 120, 28);
		comboMossa3.addItem("");
		for (int i = 0; i < 18; i++)
			comboMossa3.addItem(tipi[i]);
		comboMossa4 = new JComboBox<>();
		comboMossa4.setBounds(157, 48, 120, 28);
		comboMossa4.addItem("");
		for (int i = 0; i < 18; i++)
			comboMossa4.addItem(tipi[i]);
		treeTipiRimanenti = new JTree();
		treeTipiRimanenti.setBounds(17, 88, 120, 380);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Rimanenti");
		for (int i = 0; i < 18; i++)
			root.add(new DefaultMutableTreeNode(tipi[i]));
		treeTipiRimanenti.setModel(new DefaultTreeModel(root));
		treeTipiBattuti = new JTree();
		treeTipiBattuti.setBounds(157, 88, 120, 380);
		root = new DefaultMutableTreeNode("Battuti");
		treeTipiBattuti.setModel(new DefaultTreeModel(root));
		buttonPulisci = new JButton("Pulisci");
		buttonPulisci.setBounds(17, 480, 260, 40);

		comboMossa1.addActionListener(controller);
		comboMossa2.addActionListener(controller);
		comboMossa3.addActionListener(controller);
		comboMossa4.addActionListener(controller);
		buttonPulisci.addActionListener(controller);

		add(comboMossa1);
		add(comboMossa2);
		add(comboMossa3);
		add(comboMossa4);
		add(treeTipiBattuti);
		add(treeTipiRimanenti);
		add(buttonPulisci);
		add(new JLabel());
	}
	
	public void start() { setVisible(true); }
	public Integer[] ottieniMosse()
	{
		Integer[] mosse;
		
		mosse = new Integer[4];
		mosse[0] = comboMossa1.getSelectedIndex() - 1;
		mosse[1] = comboMossa2.getSelectedIndex() - 1;
		mosse[2] = comboMossa3.getSelectedIndex() - 1;
		mosse[3] = comboMossa4.getSelectedIndex() - 1;
		
		return mosse;
	}
	public void mostraTipiBattuti(Integer[] tipiBattuti)
	{
		DefaultMutableTreeNode rootRimanenti, rootBattuti;

		rootRimanenti = new DefaultMutableTreeNode("Rimanenti");
		rootBattuti = new DefaultMutableTreeNode("Battuti");
		for (int i = 0, j = 0; i < 18; i++)
		{
			if (j < tipiBattuti.length)
			{
				if (tipi[i].equals(tipi[tipiBattuti[j]]))
				{
					rootBattuti.add(new DefaultMutableTreeNode(tipi[tipiBattuti[j]]));
					j++;
				}
				else
					rootRimanenti.add(new DefaultMutableTreeNode(tipi[i]));
			}
			else
				rootRimanenti.add(new DefaultMutableTreeNode(tipi[i]));
		}
		treeTipiRimanenti.setModel(new DefaultTreeModel(rootRimanenti));
		treeTipiBattuti.setModel(new DefaultTreeModel(rootBattuti));
	}
	public void pulisci()
	{
		comboMossa1.setSelectedIndex(0);
		comboMossa2.setSelectedIndex(0);
		comboMossa3.setSelectedIndex(0);
		comboMossa4.setSelectedIndex(0);
	}
}