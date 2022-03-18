/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author DoAa
 */
import ContactPerson.person;
import db.Db;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RegistrationFormApplication extends Application {
     Db D;
     int whichIndexContact=0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Registration Form JavaFX Application");

        // Create the registration form grid pane
        GridPane gridPane = createRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }


    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

 // Add ID Label
        Label idLabel = new Label("ID ");
        gridPane.add(idLabel, 0, 1);

        // Add ID Text Field
        TextField idField = new TextField();
        idField.setPrefHeight(40);
        idField.setEditable(false);
        gridPane.add(idField, 1, 1);

        // Add first name Label
        Label fNameLabel = new Label("First Name ");
        gridPane.add(fNameLabel, 0, 2);

        // Add first name Text Field
        TextField fNameField = new TextField();
        fNameField.setPrefHeight(40);
        gridPane.add(fNameField, 1, 2);

        // Add middle name Label
        Label mNameLabel = new Label("Middle Name ");
        gridPane.add(mNameLabel, 0, 3);

        // Add middle name Text Field
        TextField mNameField = new TextField();
        mNameField.setPrefHeight(40);
        gridPane.add(mNameField, 1, 3);

        // Add last name Label
        Label lNameLabel = new Label("Last Name ");
        gridPane.add(lNameLabel, 0, 4);

        // Add last name Text Field
        TextField lNameField = new TextField();
        lNameField.setPrefHeight(40);
        gridPane.add(lNameField, 1, 4);

        // Add email Label
        Label emailLabel = new Label("Email ");
        gridPane.add(emailLabel, 0, 5);

        // Add email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 5);

        // Add phone Label
        Label phoneLabel = new Label("phone ");
        gridPane.add(phoneLabel, 0, 6);

        // Add phone Text Field
        TextField phoneField = new TextField();
        phoneField.setPrefHeight(40);
        gridPane.add(phoneField, 1, 6);


        // Add Submit Button
        Button New = new Button("New");
        New.setPrefHeight(40);
        New.setPrefWidth(100);
        
        Button First = new Button("First");
        First.setPrefHeight(40);
         First.setPrefWidth(100);
        
        Button prev = new Button("Prev");
        prev.setPrefHeight(40);
        prev.setPrefWidth(100);
        
        Button Next= new Button("Next");
        Next.setPrefHeight(40);
        Next.setPrefWidth(100);
        
          Button Update= new Button("Update");
       Update.setPrefHeight(40);
        Update.setPrefWidth(100);
        
        Button Last= new Button("Last");
        Last.setPrefHeight(40);
        Last.setPrefWidth(100);
        
        
        FlowPane Btns = new FlowPane(New ,First, prev , Next , Update , Last );
        gridPane.add(Btns, 0, 7, 2, 1);
        GridPane.setHalignment(Btns, HPos.CENTER);
        GridPane.setMargin(Btns, new Insets(20, 0,20,0));
        
        First.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 person firstContact = D.listPersons.firstElement();
               whichIndexContact = 0;
                idField.setText(String.valueOf(firstContact.getId()));
                fNameField.setText(firstContact.getFirstName());
                lNameField.setText(firstContact.getLastName());
                mNameField.setText(firstContact.getMiddleName());
                emailField.setText(firstContact.getEmail());
                phoneField.setText(firstContact.getPhone());
            }
        });
        
         Last.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 person firstContact = D.listPersons.lastElement();
                 whichIndexContact = D.listPersons.size()-1 ;
                idField.setText(String.valueOf(firstContact.getId()));
                fNameField.setText(firstContact.getFirstName());
                lNameField.setText(firstContact.getLastName());
                mNameField.setText(firstContact.getMiddleName());
                emailField.setText(firstContact.getEmail());
                phoneField.setText(firstContact.getPhone());
            }
        });
         
          Next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                  whichIndexContact = D.listPersons.size() == whichIndexContact + 1 ? whichIndexContact : ++whichIndexContact;
               person nextContact = D.listPersons.get(whichIndexContact);
                idField.setText(String.valueOf( nextContact.getId()));
                fNameField.setText( nextContact.getFirstName());
                lNameField.setText( nextContact.getLastName());
                mNameField.setText( nextContact.getMiddleName());
                emailField.setText( nextContact.getEmail());
                phoneField.setText( nextContact.getPhone());
            }
        });
          
          
           prev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            whichIndexContact = whichIndexContact == 0 ? whichIndexContact : --whichIndexContact;
               person nextContact = D.listPersons.get(whichIndexContact);
                idField.setText(String.valueOf( nextContact.getId()));
                fNameField.setText( nextContact.getFirstName());
                lNameField.setText( nextContact.getLastName());
                mNameField.setText( nextContact.getMiddleName());
                emailField.setText( nextContact.getEmail());
                phoneField.setText( nextContact.getPhone());
            }
        });
           
           
           
           
           
         
   New.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!idField.getText().isEmpty()) {

                    idField.setText("");
                    fNameField.setText("");
                    lNameField.setText("");
                    mNameField.setText("");
                    emailField.setText("");
                    phoneField.setText("");

                    return;
                } else if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || mNameField.getText().isEmpty()
                        || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter all fields ");
                    return;
                } else {
                    try {
                        D.Add(new person(0, fNameField.getText(), mNameField.getText(),
                                lNameField.getText(), emailField.getText(), phoneField.getText()));

                        person lastContact = D.listPersons.lastElement();
                        whichIndexContact = D.listPersons.size() - 1;
                        idField.setText(String.valueOf(lastContact.getId()));
                        fNameField.setText(lastContact.getFirstName());
                        lNameField.setText(lastContact.getLastName());
                        mNameField.setText(lastContact.getMiddleName());
                        emailField.setText(lastContact.getEmail());
                        phoneField.setText(lastContact.getPhone());

                    } catch (Exception e) {
                    }
                }

//                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Successful add contact!", "Welcome " + nameField.getText());
            }
        });
     Update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (idField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Select Contact to update it");
                } else if (fNameField.getText().isEmpty() || lNameField.getText().isEmpty() || mNameField.getText().isEmpty()
                        || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter all fields ");
                } else {

                    try {
                        D.updatePerson(new person(Integer.parseInt(idField.getText()), fNameField.getText(), mNameField.getText(),
                                lNameField.getText(), emailField.getText(), phoneField.getText()));

                    } catch (Exception e) {
                    }

                }

//                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Successful add contact!", "Welcome " + nameField.getText());
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
  public void init () throws SQLException
  {
    D =new Db(); 
    D.getAllPerson();
  }
    public static void main(String[] args) {
        launch(args);
    }
}