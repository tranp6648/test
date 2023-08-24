package Form;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Formain {

	private JFrame frame;
	private JPanel ContentPane;
	private Formcategory pnl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Formain window = new Formain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Formain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 784, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Manager Category");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Manager category");
		mntmNewMenuItem.addActionListener(e->{
			pnl=new Formcategory();
			ContentPane.removeAll();
			ContentPane.add(pnl,BorderLayout.CENTER);
			ContentPane.repaint();
			ContentPane.revalidate();
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Manager item");
		mnNewMenu.add(mntmNewMenuItem_1);
		frame.getContentPane().setLayout(null);
		
		 ContentPane = new JPanel();
		ContentPane.setBounds(0, 0, 770, 471);
		ContentPane.setLayout(new BorderLayout(0,0));
		ContentPane.setLayout(new BorderLayout(0,0));
		frame.getContentPane().add(ContentPane);
		
	
	}

}
