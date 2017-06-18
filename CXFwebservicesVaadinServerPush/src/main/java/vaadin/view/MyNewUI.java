package vaadin.view;

import java.io.File;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;

@SpringComponent
@UIScope
public class MyNewUI extends NewUI implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "MyNewUI";
	private File file;

	public MyNewUI() {
		button.addClickListener(e -> {
			// addComponent(new Label("Thanks " + label.getValue() + ", it
			// works!"));
			doNavigate(MyNewUI2.VIEW_NAME);
		});

		comboBox.setItems("1", "2", "3");
		comboBox.addSelectionListener(e -> {
			boolean cb = checkBox.getValue();
			if (cb == true) {
				slider.setValue((double) 50);
			} else {
				String cbv = comboBox.getValue();
				if (cbv.equals("1")) {
					setImage("img1.jpg");
				} else if (cbv.equals("2")) {
					setImage("img2.jpg");
				} else {
					setImage("img3.jpg");
				}
			}
		});

	}

	@Override
	public void enter(ViewChangeEvent event) {
		setImage("img1.jpg");
	}

	private void doNavigate(String viewName) {
		getUI().getNavigator().navigateTo(viewName);
	}

	public Label getLabel() {
		return label;
	}

	private void setImage(String name) {
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		// Image as a file resource
		file = new File(basepath + "/WEB-INF/" + name);
		// show image to client
		image.setVisible(true);
		image.setSource(new FileResource(file));
	}

}
