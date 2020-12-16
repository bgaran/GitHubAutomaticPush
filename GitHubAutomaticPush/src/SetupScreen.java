import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import 
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SetupScreen extends JPanel {

	private FrameManager frameManager; // LINKED TO SWAP PANELS

	private JLabel infoLabel, filePathLabel, feedbackLabel;

	private JTextField filePathTextField;

	// radio buttons for controlling Git Command Center View
	JRadioButton cloneButton, studentButton, professorButton;
	ButtonGroup bg;

	private JFileChooser fileChooser;

	private String filePath;

	private JButton submitButton, browseButton; // TODO: implement backButton

	private JToggleButton darkModeToggleButton; // used to toggle between dark mode and light mode

	private Font bigWords, mediumWords; // used to resize the text

	private GridBagLayout gbl; // layout

	private GridBagConstraints gbc; // constraints for individual components for gbl

	/**
	 * Creates a new SetupScreen panel which serves as the setup panel for selecting
	 * user type/ current function and the file path of the repository.
	 * 
	 * @param width        - the width of the panel.
	 * @param height       - the height of the panel.
	 * @param bgColor      - the background color of the panel.
	 * @param frameManager - the frameManager which the panel will reference in
	 *                     order to swap panels.
	 * @author April Miller
	 * @Author Griffin White
	 * @author Breanna Garan
	 */
	public SetupScreen(int width, int height, Color bgColor, FrameManager frameManager) {
		setPreferredSize(new Dimension(width, height));
		setBackground(bgColor);

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);

		this.frameManager = frameManager;

		gbl = new GridBagLayout();

		gbc = new GridBagConstraints();

		this.setLayout(gbl);

		// initialize all UI elements
		infoLabel = new JLabel("Please input your file path to current project src folder.");
		filePathLabel = new JLabel("File path:");
		feedbackLabel = new JLabel("");

		filePathTextField = new JTextField("ex. H:\\workspace\\CS128 HW1\\src\\");

		submitButton = new JButton("Submit");
		browseButton = new JButton("Browse...");

		darkModeToggleButton = new JToggleButton("Dark Mode");

		//intialize radio buttons and button group
		cloneButton = new JRadioButton("Clone New Repository");
		studentButton = new JRadioButton("Student");
		professorButton = new JRadioButton("Professor");
		bg = new ButtonGroup();

		// initialize file chooser
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Select the current project's src folder:");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setPreferredSize(new Dimension(width / 2, height / 2));

		// accessibility
		submitButton.getAccessibleContext().setAccessibleDescription("Submit Button");
		browseButton.getAccessibleContext().setAccessibleDescription("Browse Files Button");
		darkModeToggleButton.getAccessibleContext().setAccessibleDescription("Dark Moe Toggle Button");
		cloneButton.getAccessibleContext().setAccessibleDescription(
				"Clone Option- Select if you are cloning " + "a repository for the first time.");
		studentButton.getAccessibleContext().setAccessibleDescription(
				"Student Option- Select if you are" + "a student working from an exisiting cloned repository.");
		professorButton.getAccessibleContext().setAccessibleDescription(
				"Professor Option- Select if you are" + "a professor working from an existing repository.");
		infoLabel.getAccessibleContext()
				.setAccessibleDescription("Please input your file path to current project src folder.");

		// tooltips
		submitButton
				.setToolTipText("<html><p width=\"500\">Click this button to confirm the current file path and UserType"
						+ "and continue to the Git Command Center screen</p></html>");
		browseButton.setToolTipText("<html><p width=\"500\">Click this button to browse your files and navigate to your"
				+ "desired file path.  If you are cloning for the first time. select the folder you would like to "
				+ "clone to.  If you are looking for an existing cloned repository, select that repository from the "
				+ "location you cloned it to.</p></html>");
		darkModeToggleButton.setToolTipText("Toggle between light mode and dark mode.");
		cloneButton.setToolTipText("Select if you are cloning " + "a repository for the first time.");
		studentButton.setToolTipText("Select if you are" + "a student working from an exisiting cloned repository.");
		professorButton.setToolTipText("Select if you are" + "a professor working from an existing repository.");

		// create fonts
		bigWords = new Font("Sans Serif", Font.PLAIN, width / 30); // Makes it so the text is easily seeable, using one
																	// 30th of the window's width for the font size
		mediumWords = new Font("Sans Serif", Font.PLAIN, width / 60);

		// set all UI elements to share this same font
		infoLabel.setFont(bigWords);
		filePathLabel.setFont(bigWords);
		feedbackLabel.setFont(bigWords);
		filePathTextField.setFont(bigWords);
		submitButton.setFont(bigWords);
		browseButton.setFont(bigWords);
		darkModeToggleButton.setFont(bigWords);
		cloneButton.setFont(bigWords);
		studentButton.setFont(bigWords);
		professorButton.setFont(bigWords);

		// initialize color of text fields to default, change color once focus gained
		filePathTextField.setForeground(Color.gray);

		// align all UI elements correctly
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		feedbackLabel.setHorizontalAlignment(JLabel.CENTER);

		// finally, add all UI elements to the SetupScreen
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0; // center
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.weighty = .25;
		this.add(infoLabel, gbc);

		// add radio buttons
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0; // center
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.add(studentButton, gbc);
		// gbc.weighty = .25;
		gbc.gridx = 1;
		this.add(professorButton, gbc);
		gbc.gridx = 2;
		this.add(cloneButton, gbc);

		// group the radio buttons together
		bg.add(cloneButton);
		bg.add(studentButton);
		bg.add(professorButton);
		studentButton.setSelected(true);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = .33;
		this.add(filePathLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = .33;
		this.add(browseButton, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.weightx = .9;
		this.add(filePathTextField, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 3;
		gbc.weightx = .25;
		this.add(submitButton, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		this.add(feedbackLabel, gbc);

		gbc.fill = GridBagConstraints.NONE;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		this.add(darkModeToggleButton, gbc);

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				frameManager.userType = getUserType();// sets the current userType in FrameManager on submit

				if (!filePathTextField.getText().equals("ex. H:\\workspace\\CS128 HW1\\src\\")
						&& !filePathTextField.getText().equals("")) { // simple checking for nothing
					filePath = filePathTextField.getText();
					// GHH.ConnectToGitHub(username, password, filePath);
					frameManager.git.projectPath = filePath;

					feedbackLabel.setText("");
					if (feedbackLabel.getText().equals("")) {
						frameManager.swapPanel(FrameManager.swappablePanel.CENTER);
					}
				} else {
					feedbackLabel.setText("Please input a file path");
				}
			}

		});

		SetupScreen setup = this; // used for the showOpenDialog method that prompts the user to choose what
									// directory to use

		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(setup) == JFileChooser.APPROVE_OPTION) {
					filePathTextField.setText("" + fileChooser.getSelectedFile());
					feedbackLabel.setText("Please select your src folder.");
				} else {
					feedbackLabel.setText("Please select your src folder.");
					// TODO check for whether or not the file path ends in /src/, if not, warn the
					// user.
				}
			}

		});

		// If default text, sets it to blank
		filePathTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (filePathTextField.getText().equals("ex. H:\\workspace\\CS128 HW1\\src\\")) {
					filePathTextField.setText("");
					setFilePathTextColor();
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if (filePathTextField.getText().equals("")) {
					filePathTextField.setText("ex. H:\\workspace\\CS128 HW1\\src\\");
					setFilePathTextColor();
				}
			}

		});

		// When darkModeToggleButton is toggled
		darkModeToggleButton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();

				if (state == ItemEvent.SELECTED) {
					// switch ON dark mode
					frameManager.isDarkMode = true;
				} else {
					// switch OFF dark mode
					frameManager.isDarkMode = false;
				}

				updateUITheme(frameManager.isDarkMode);
			}

		});
	}

	/**
	 * Updates the aesthetics of the current panel based on whether it is light or
	 * dark mode NOTE: does not update isDarkMode in frame manager
	 * 
	 * @param isDarkMode - if this is set to dark mode or not
	 * 
	 * @author Griffin White
	 * @author April Miller
	 * @author Breanna Garan
	 */
	public void updateUITheme(boolean isDarkMode) {
		setFilePathTextColor();
		if (isDarkMode) {
			// switch ON dark mode
			setBackground(Color.black);
			infoLabel.setForeground(Color.white);
			filePathLabel.setForeground(Color.white);
			feedbackLabel.setForeground(Color.white);
			filePathTextField.setBackground(Color.DARK_GRAY);
			cloneButton.setForeground(Color.white);
			studentButton.setForeground(Color.white);
			professorButton.setForeground(Color.white);
			cloneButton.setBackground(Color.black);
			studentButton.setBackground(Color.black);
			professorButton.setBackground(Color.black);
			
		} else {
			setBackground(Color.WHITE);
			infoLabel.setForeground(Color.black);
			filePathLabel.setForeground(Color.black);
			feedbackLabel.setForeground(Color.black);
			filePathTextField.setBackground(Color.LIGHT_GRAY);
			cloneButton.setForeground(Color.black);
			studentButton.setForeground(Color.black);
			professorButton.setForeground(Color.black);		
			cloneButton.setBackground(Color.white);
			studentButton.setBackground(Color.white);
			professorButton.setBackground(Color.white);
		}
	}

	/**
	 * Called every time repaint() is called, used to update UI Theme from
	 * FrameManager
	 * 
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * @author Griffin White
	 */
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;

		// update UI theme when repaint() is called
		updateUITheme(frameManager.isDarkMode);

		if (this.frameManager.isDarkMode) { // if it is in dark mode
			darkModeToggleButton.setSelected(true); // set it so the togglebutton is on
		} else {
			darkModeToggleButton.setSelected(false); // set it so the togglebutton is off
		}
	}

	/**
	 * Sets the filePathTextField text color based on whether sample text is in
	 * there and if it is in dark mode
	 * 
	 * @author Griffin White
	 */
	private void setFilePathTextColor() {
		if (filePathTextField.getText().equals("ex. H:\\workspace\\CS128 HW1\\src\\")) {
			if (frameManager.isDarkMode) {
				filePathTextField.setForeground(Color.LIGHT_GRAY);
			} else {
				filePathTextField.setForeground(Color.DARK_GRAY);
			}
		} else {
			if (frameManager.isDarkMode) {
				filePathTextField.setForeground(Color.white);
			} else {
				filePathTextField.setForeground(Color.black);
			}
		}
	}

	/**
	 * return a string that identifies the current application user type based on
	 * the radio button selection.
	 * 
	 * @return - one of three identifying strings: clone, student, or professor
	 * @author Breanna Garan
	 */
	public String getUserType() {
		if (cloneButton.isSelected()) {
			return "clone";
		} else if (studentButton.isSelected()) {
			return "student";
		} else {
			return "professor";
		}

	}
}
