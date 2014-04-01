package com.coldharbour.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.coldharbour.controllers.IController;

public class RegUI {

	final private int DEFAULTH_WIDTH = 230;
	final private int DEFAULTH_HEIGHT = 230;
	final String title = "Registration";
	private Display display;
	private Shell shell;
	private Label errorLabel;
	private Text loginText;
	private Text pwdText;
	private Text repeatPwdText;
	private IController controller;

	public RegUI(IController controller) {
		this.controller = controller;
	}

	public void initialize() {
		display = Display.getDefault();
		shell = new Shell(display);
		shell.setSize(DEFAULTH_WIDTH, DEFAULTH_HEIGHT);
		shell.setText(title);

		Label loginLabel = new Label(shell, SWT.RIGHT);
		loginLabel.setText("Login");
		loginLabel.setBounds(10, 30, 70, 20);

		Label pwdLabel = new Label(shell, SWT.RIGHT);
		pwdLabel.setText("Password");
		pwdLabel.setBounds(10, 60 , 70, 20);

		Label confirmPwdLabel = new Label(shell, SWT.RIGHT);
		confirmPwdLabel.setText("Confirm");
		confirmPwdLabel.setBounds(10, 90, 70, 20);
		
		Label errorLabel = new Label(shell, SWT.CENTER);
		errorLabel.setText("ERRORERRORERRORERRORERRRO");
		errorLabel.setBounds(10, 160, 200, 30);

		loginText = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		loginText.setBounds(90, 30, 100, 20);

		pwdText = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.LEFT
				| SWT.PASSWORD);
		pwdText.setBounds(90, 60, 100, 20);
		
		repeatPwdText = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.LEFT
				| SWT.PASSWORD);
		repeatPwdText.setBounds(90, 90, 100, 20);

		Button enterButton = new Button(shell, SWT.PUSH | SWT.CENTER);
		enterButton.setText("Enjoy");
		enterButton.setBounds(40, 120, 70, 30);
		
		Button cancelButton = new Button(shell, SWT.PUSH | SWT.CENTER);
		cancelButton.setText("Cancel");
		cancelButton.setBounds(120, 120, 70, 30);
		
		enterButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				controller.register(loginText.getText(), pwdText.getText(), repeatPwdText.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cancelButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				shell.dispose();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		shell.setLayout(null);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
