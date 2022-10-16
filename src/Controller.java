import java.awt.event.*;

import javax.swing.*;

public class Controller implements ActionListener
{
	private View view;
	private Model model;
	
	public Controller(View view, Model model)
	{
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() instanceof JButton)
			view.pulisci();
		else
			view.mostraTipiBattuti(model.tipiBattuti(view.ottieniMosse()));
	}
}