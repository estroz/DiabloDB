import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class GUI extends JPanel implements ListSelectionListener {
	JDBCConnection con; 
	
	private JList jListPages;
	private DefaultListModel listModel;
	
	private static final String addPage = "Add Page";
	private JButton jAddPageButton;
	private JTextField jAddPageText;

	/*
	 * GUI Constructor
	 */
	public GUI() {
		super(new BorderLayout());
		con = new JDBCConnection();
		
		frontPageGUI();
	}
	
	/*
	 * Front Page 
	 * See ListDemo at
	 * https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
	 * 
	 */
	private void frontPageGUI() {	
		//Add pages to listModel
		listModel = new DefaultListModel();
		ArrayList<String> pages = null;
		try {
			pages = con.getAllPages();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(String s: pages) {
			listModel.addElement(s);
		}
		
		// List Stuff
		jListPages = new JList(listModel);
		jListPages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jListPages.setSelectedIndex(0);
		jListPages.addListSelectionListener(this);
		jListPages.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(jListPages);

        AddPageListener addPageListener = new AddPageListener(jAddPageButton);
		jAddPageButton = new JButton(addPage);
		jAddPageButton.setActionCommand(addPage);
		jAddPageButton.addActionListener(addPageListener);
		
		jAddPageText = new JTextField(10);
		jAddPageText.addActionListener(addPageListener);
		jAddPageText.getDocument().addDocumentListener(addPageListener);
        String name = listModel.getElementAt(
                              jListPages.getSelectedIndex()).toString();

		// Create a panel that uses BoxLayout.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
		buttonPane.add(Box.createHorizontalStrut(5));
		buttonPane.add(jAddPageText);
		buttonPane.add(jAddPageButton);
		buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		add(listScrollPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.PAGE_END);
	}
	
	//TODO: Look more into this
	/*
	 * This listener is shared by the text field and the add page button.
	 */ 
	class AddPageListener implements ActionListener, DocumentListener {
		private boolean alreadyEnabled = false;
		private JButton button;

		public AddPageListener(JButton button) {
			this.button = button;
		}

		// Required by ActionListener.
		public void actionPerformed(ActionEvent e) {
			String insertString = jAddPageText.getText();

//			// User didn't type in a unique name...
//			if (name.equals("") || alreadyInList(name)) {
//				Toolkit.getDefaultToolkit().beep();
//				employeeName.requestFocusInWindow();
//				employeeName.selectAll();
//				return;
//			}

//			int index = list.getSelectedIndex(); // get selected index
//			if (index == -1) { // no selection, so insert at beginning
//				index = 0;
//			} else { // add after the selected item
//				index++;
//			}

			listModel.insertElementAt(jAddPageText.getText(), listModel.getSize());
			// If we just wanted to add to the end, we'd do this:
			// listModel.addElement(employeeName.getText());

			// Reset the text field.
			jAddPageText.requestFocusInWindow();
			jAddPageText.setText("");

			// Select the new item and make it visible.
			jListPages.setSelectedIndex(listModel.getSize());
			jListPages.ensureIndexIsVisible(listModel.getSize());
		}

		// This method tests for string equality. You could certainly
		// get more sophisticated about the algorithm. For example,
		// you might want to ignore white space and capitalization.
		protected boolean alreadyInList(String name) {
			return listModel.contains(name);
		}

		// Required by DocumentListener.
		public void insertUpdate(DocumentEvent e) {
			enableButton();
		}

		// Required by DocumentListener.
		public void removeUpdate(DocumentEvent e) {
			handleEmptyTextField(e);
		}

		// Required by DocumentListener.
		public void changedUpdate(DocumentEvent e) {
			if (!handleEmptyTextField(e)) {
				enableButton();
			}
		}

		private void enableButton() {
			if (!alreadyEnabled) {
				button.setEnabled(true);
			}
		}

		private boolean handleEmptyTextField(DocumentEvent e) {
			if (e.getDocument().getLength() <= 0) {
				button.setEnabled(false);
				alreadyEnabled = false;
				return true;
			}
			return false;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() == false) {
			jAddPageButton.setEnabled(true);	
		}
	}
	
	
	/*
	 * Added menu bar for GUI
	 */
	private static void addMenuBar(JFrame frame) {
		//Create and set up a menu bar
        JMenuBar menuBar;
        menuBar = new JMenuBar();
        
        //First Menu drop down
        JMenu menu1;
        JMenuItem menuItemA1;
        menu1 = new JMenu("MENU #1");
        menu1.getAccessibleContext().setAccessibleDescription(
                "I don't know what this does");
        //Menu item example in drop down list
        menuItemA1 = new JMenuItem("A JMenuItem");
        menuItemA1.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu1.add(menuItemA1);
        menuBar.add(menu1);
        
        //Second Menu drop down
        JMenu menu2;
        JMenuItem menuItemB1;
        menu2 = new JMenu("MENU #2");
        menu2.getAccessibleContext().setAccessibleDescription(
                "I don't know what this does");
        //Menu item example in drop down list
        menuItemB1 = new JMenuItem("Another JMenuItem");
        menuItemB1.getAccessibleContext().setAccessibleDescription(
                "This doesn't really do anything");
        menu2.add(menuItemB1);
        menuBar.add(menu2);
        
        frame.setJMenuBar(menuBar);
	}
	
	
	/*
	 * Create the GUI
	 */
	private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DiabloDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Call method to add menu bar
        addMenuBar(frame);
        
        //Create and set up the content pane.
        JComponent newContentPane = new GUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
	
	
	/*
	 * Main
	 */
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
