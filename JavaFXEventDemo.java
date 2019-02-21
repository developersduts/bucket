package coursework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class JavaFXEventDemo extends Application {

	Tooltip t1 = new Tooltip();
	Tooltip t2 = new Tooltip();
	Tooltip t3 = new Tooltip();
	Tooltip t4 = new Tooltip();
	Tooltip t5 = new Tooltip();
	Tooltip t6 = new Tooltip();
	ArrayList<Animal> al = new ArrayList<Animal>();
	private TableView<Animal> table = new TableView<Animal>();
	private ObservableList<Animal> data = FXCollections.observableArrayList(al);
	String[] word;
	String text2 = "";
	ComboBox cb = new ComboBox<>();
	boolean b = true;
	String s;
	TextArea ta = new TextArea();

	public static void main(String[] args) {
		Application.launch(args);
	}

	public static class Animal {
		private final SimpleStringProperty animal;
		private final SimpleStringProperty weight;
		private final SimpleStringProperty lifeTime;

		private Animal(String animal1, String weight2, String lifeTime3) {
			this.animal = new SimpleStringProperty(animal1);
			this.weight = new SimpleStringProperty(weight2);
			this.lifeTime = new SimpleStringProperty(lifeTime3);
		}

		public String getAnimal() {
			return animal.get();
		}

		public String getWeight() {
			return weight.get();
		}

		public String getLifeTime() {
			return lifeTime.get();
		}

		public String toString() {
			return this.animal.getValue() + " " + this.weight.getValue() + " " + this.lifeTime.getValue() + "\n";
		}
	}

	private void SaveFileTable(ObservableList<Animal> d, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(d.toString());
			fileWriter.close();
		} catch (IOException ex) {
		}
	}

	@Override
	public void start(Stage myStage) {
		myStage.setTitle("Sorting Machine");
		final FileChooser fileChooser = new FileChooser();
		Button buttonOpen = new Button("Open");
		Button buttonSave = new Button("Save");
		Button buttonHelp = new Button("Help");
		cb.setPromptText("Color");
		t1.setText("Click to open your file");
		buttonOpen.setTooltip(t1);
		t2.setText("Click to save your file");
		buttonSave.setTooltip(t2);
		t3.setText("Click to take information about a program");
		buttonHelp.setTooltip(t3);
		t4.setText("Table in which sorting occurs");
		table.setTooltip(t4);
		cb.setItems(FXCollections.observableArrayList("Blue", "White", "Grean", "Yellow", "Black", "Red", "Orange"));
		t5.setText("Select the color for your table");
		cb.setTooltip(t5);
		VBox vbox = new VBox();
		HBox hbox = new HBox();
		HBox hbox2 = new HBox();
		cb.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if ("Grean".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #45ff2f;");
				} else if ("White".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #fffef3;");
				} else if ("Blue".equals(cb.getValue())) {
					table.setStyle("-fx-base: #2f45ff;");
				} else if ("Black".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #080001;");
				} else if ("Yellow".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #ffe92f;");
				} else if ("Red".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #fc001b ;");
				} else if ("Orange".equals(cb.getValue())) {
					table.setStyle("-fx-base:  #fc6300;");
				}
			}
		});

		buttonOpen.setStyle("-fx-base:  #00b09d;");
		buttonSave.setStyle("-fx-base:  #00b045;");
		buttonHelp.setStyle("-fx-base:  #b00013;");

		buttonHelp.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent a) {
				if (b == true) {
					s = "Ця прогама слугує для сортування данних." + "\n"
							+ "Якщо ваш файл не відображається в таблиці слід перевірити щоб він відповідав таким критеріям: "
							+ "\n" + "1. Ви вносите файл в таблицю типу txt;" + "\n"
							+ "2. В файлі знаходиться лише 3 таблиці (1.Animal, 2.Weight, 3.LifeTime);" + "\n"
							+ "3. Всі данні розділені лише одним пробілом;" + "\n"
							+ "4. Всі числові значення повинні бути трьохзначними(005,065,888);" + "\n";
					ta.setText(s);
					b = false;
				} else if (b == false) {
					b = true;
					ta.setText("");
				}
			}
		});

		buttonOpen.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(final ActionEvent a) {
				File file = fileChooser.showOpenDialog(myStage);
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						text2 = text2 + text + " ";
					}
					word = text2.split(" ");
					for (int i = 0; i < word.length - 1; i++) {
						String a1 = word[i++].trim();
						String a2 = word[i++].trim();
						String a3 = word[i].trim();
						Animal an = new Animal(a1, a2, a3);
						al.add(an);
					}
					bufferedReader.close();
				} catch (FileNotFoundException ex) {
					Logger.getLogger(JavaFXEventDemo.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(JavaFXEventDemo.class.getName()).log(Level.SEVERE, null, ex);
				}
				data = FXCollections.observableArrayList(al);
				table.setItems(data);
			}
		});

		TableColumn firstCol = new TableColumn("Animal");
		firstCol.setMinWidth(100);
		firstCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("animal"));

		TableColumn secondCol = new TableColumn("Weight");
		secondCol.setMinWidth(100);
		secondCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("weight"));

		TableColumn lastCol = new TableColumn("LifeTime");
		lastCol.setMinWidth(100);
		lastCol.setCellValueFactory(new PropertyValueFactory<Animal, String>("lifeTime"));
		table.setItems(data);
		table.getColumns().addAll(firstCol, secondCol, lastCol);

		buttonSave.setOnAction((ActionEvent event) -> {
			FileChooser fileChooser1 = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
			fileChooser1.getExtensionFilters().add(extFilter);
			File file = fileChooser1.showSaveDialog(myStage);
			if (file != null) {
				SaveFileTable(table.getItems(), file);
			}
		});

		vbox.getChildren().addAll(hbox, table, cb, ta);
		hbox.getChildren().addAll(buttonOpen, buttonSave, hbox2);
		hbox2.getChildren().add(buttonHelp);

		Scene scene = new Scene(vbox, 320, 600);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setPadding(new Insets(0, 10, 10, 0));
		hbox2.setPadding(new Insets(0, 0, 0, 163));

		myStage.setScene(scene);
		myStage.show();
	}
}