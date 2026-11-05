package com.example.javafx_lab4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DashboardPane extends Application {
    final String fontFamily = "SFPro";
    ObservableList<String> programs = FXCollections.observableArrayList(
            "Software Engineering",
            "Artificial Intelligence",
            "Health Informatics",
            "Game Programming",
            "Information Technology"
    );


    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainShell = new BorderPane();
        mainShell.setTop(header());
        mainShell.setLeft(menu());
        mainShell.setCenter(registrationForm());

        Scene scene = new Scene(mainShell, 600, 400);
        stage.setTitle("Student Service Portal");
        stage.setScene(scene);
        stage.show();

    }

    private HBox header() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(16, 16, 16, 16));
        hBox.setStyle("-fx-background-color: #b0c4de");
        Text title = new Text("Kingsley's Student Services Lab");
        title.setFont(Font.font(fontFamily, FontWeight.BOLD, 16));
        hBox.getChildren().add(title);
        return hBox;
    }

    private VBox menu() {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(16, 16, 16, 16));
        vBox.setSpacing(16);
        vBox.setStyle("-fx-background-color: #ffebcd");
        Text text = new Text("Menu");
        text.setFont(Font.font(fontFamily, FontWeight.SEMI_BOLD, 16));
        Button dashBtn = new Button("Dashboard");
        Button registerBtn = new Button("Registration");
        Button courseBtn = new Button("Courses");
        Button helpBtn = new Button("Help");

        vBox.getChildren().addAll(text, dashBtn, registerBtn, courseBtn, helpBtn);

        return vBox;
    }

    private GridPane registrationForm() {
        GridPane regGrid = new GridPane();
        regGrid.setVgap(16);
        regGrid.setHgap(16);
        regGrid.setStyle("-fx-background-color: #fff5ee");
        regGrid.setPadding(new Insets(24, 20, 32, 20));

        //Student Id
        Label studentID = new Label("Student ID:");
        studentID.setFont(Font.font(fontFamily, FontWeight.SEMI_BOLD, 16));
        TextField studentIDField = new TextField();
        studentIDField.setPromptText("301000000");
        studentIDField.maxWidthProperty();
        regGrid.add(studentID, 0, 0);
        regGrid.add(studentIDField, 1, 0);

        //Student Name
        Label studentName = new Label("Full Name:");
        studentName.setFont(Font.font(fontFamily, FontWeight.SEMI_BOLD, 16));
        TextField studentNameField = new TextField();
        studentNameField.setPromptText("John Doe");
        regGrid.add(studentName, 0, 1);
        regGrid.add(studentNameField, 1, 1);

        //Student Email
        Label studentEmail = new Label("Email:");
        studentEmail.setFont(Font.font(fontFamily, FontWeight.SEMI_BOLD, 16));
        TextField studentEmailField = new TextField();
        studentEmailField.setPromptText("name@example.com");
        regGrid.add(studentEmail, 0, 2);
        regGrid.add(studentEmailField, 1, 2);

        //Student Program
        Label studentProgram = new Label("Program:");
        studentProgram.setFont(Font.font(fontFamily, FontWeight.SEMI_BOLD, 16));
        ComboBox<String> programDropdown = new ComboBox<>(programs);
        programDropdown.setPromptText("Select a Program");
        regGrid.add(studentProgram, 0, 3);
        regGrid.add(programDropdown, 1, 3);

        //Buttons
        HBox btnHBox = new HBox();
        Button regBtn = new Button("Register");
        Button clearBtn = new Button("Clear");
        regBtn.setOnAction(_ -> {
            String studentId = studentIDField.getText();
            String fullName = studentNameField.getText();
            String email = studentEmailField.getText();
            String program = programDropdown.getValue();

            List<String> errors = new ArrayList<>();

            if (studentId.length() != 9) {
                errors.add("Student ID must be exactly 9 characters.");
            }
            if (fullName.isEmpty()) {
                errors.add("Full name is required.");
            }
            if (!email.contains("@")) {
                errors.add("A valid email address is required.");
            }
            if (program.isEmpty()) {
                errors.add("Program selection is required.");
            }

            if (!errors.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText(String.join("\n", errors));
                alert.showAndWait();
                return;
            }

            //Registers
            List<String> studentInfo = new ArrayList<>(List.of(studentId, fullName, email, program));
            Alert success = new Alert(Alert.AlertType.INFORMATION);
            success.setTitle("Student Information");
            success.setHeaderText("Student Info");

            success.setContentText(String.join("\n", studentInfo));
            success.show();
        });

        clearBtn.setOnAction(_ -> {
            studentIDField.clear();
            studentNameField.clear();
            studentEmailField.clear();
            programDropdown.setValue(null);

            Alert clear = new Alert(Alert.AlertType.INFORMATION);
            clear.setTitle("Student Information");
            clear.setHeaderText("Student Info");
            clear.setContentText("Student info cleared!");
            clear.show();


        });
        btnHBox.setSpacing(16);
        btnHBox.getChildren().addAll(regBtn, clearBtn);
        regGrid.add(btnHBox, 0, 4);

        //Bottom Text
        Text bottomTxt = new Text("Complete the form and select Register");
        bottomTxt.setFont(Font.font(fontFamily, FontWeight.MEDIUM, 12));
        regGrid.add(bottomTxt, 0, 5);


        return regGrid;

    }

}
