package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.*;

import java.sql.*;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button btnFirstStudent;

    @FXML
    private Button btnPrevStudent;

    @FXML
    private Button btnNextStudent;

    @FXML
    private Button btnLastStudent;

    @FXML
    private Button btnFirstCourse;

    @FXML
    private Button btnPrevCourse;

    @FXML
    private Button btnNextCourse;

    @FXML
    private Button btnLastCourse;

    @FXML
    private TextField tfID = new TextField();

    @FXML
    private TextField tfFirstName = new TextField();

    @FXML
    private TextField tfLastName = new TextField();

    @FXML
    private TextField tfMajor1 = new TextField();

    @FXML
    private TextField tfDepartment1 = new TextField();

    @FXML
    private TextField tfCRN = new TextField();

    @FXML
    private TextField tfMajor2 = new TextField();

    @FXML
    private TextField tfTitle = new TextField();

    @FXML
    private TextField tfNumber = new TextField();

    @FXML
    private TextField tfDepartment2 = new TextField();
//arraylists to store the data
    private ArrayList<String> alID = new ArrayList<>();
    private ArrayList<String> alFirstName = new ArrayList<>();
    private ArrayList<String> alLastName = new ArrayList<>();
    private ArrayList<String> alMajor1 = new ArrayList<>();
    private ArrayList<String> alMajor2 = new ArrayList<>();
    private ArrayList<String> alDepartment1 = new ArrayList<>();
    private ArrayList<String> alDepartment2 = new ArrayList<>();
    private ArrayList<String> alCRN = new ArrayList<>();
    private ArrayList<String> alTitle = new ArrayList<>();
    private ArrayList<String> alNumber = new ArrayList<>();

    @FXML
    private ComboBox<String> cbStudents = new ComboBox<>();

    @FXML
    private ComboBox<String> cbCourses = new ComboBox<>();

    @FXML
    private ImageView header = new ImageView();

    private int i = 0;
    private int j = 0;

    //stuff to run at start up
    @FXML
    void initialize() {
        header.setImage(new Image("sample/images/dtcc_logo.png"));
        try {
            Connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbStudents.getItems().setAll(alID);
        cbCourses.getItems().setAll(alCRN);
        setStudent(i);
        setCourse(j);

        btnFirstStudent.setOnAction( e -> previousStudent("First"));
        btnLastStudent.setOnAction( e -> nextStudent("Last"));
        btnNextStudent.setOnAction( e -> nextStudent("Next"));
        btnPrevStudent.setOnAction( e -> previousStudent("Prev"));

        btnFirstCourse.setOnAction( e -> previousCourse("First"));
        btnLastCourse.setOnAction( e -> nextCourse("Last"));
        btnNextCourse.setOnAction(e -> nextCourse("Next"));
        btnPrevCourse.setOnAction( e -> previousCourse("Prev"));
    }
    //connect to database and fill up arrays with the data
    private void Connect () throws SQLException {
        Connection connection = null;
        String url = "//phpmyadmin.cdgwdgkn5fuv.us-west-2.rds.amazonaws.com";
        String db_name = "csc164db_carlos";
        String user = "csc164db_carlos";
        String password = "ce3303";
        String connect = "jdbc:mysql:" + url + ":3306/" + db_name;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded");
        }
        try {
            connection = DriverManager.getConnection(connect, user,password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Connection Error");
        }
        assert connection != null;
        try(Statement s = connection.createStatement()){
                ResultSet rs = s.executeQuery("SELECT * FROM Student");
                while (rs.next()) {
                    alID.add(rs.getString(1));
                    alFirstName.add(rs.getString(2));
                    alLastName.add(rs.getString(3));
                    alMajor1.add(rs.getString(4));
                    alDepartment1.add(rs.getString(5));
                }
                rs = s.executeQuery("SELECT * FROM Courses");
                while (rs.next()) {
                    alCRN.add(rs.getString(1));
                    alTitle.add(rs.getString(2));
                    alMajor2.add(rs.getString(3));
                    alNumber.add(rs.getString(4));
                    alDepartment2.add(rs.getString(5));
                }
        } catch (SQLException e){
            System.out.println("SQL error");
        }
    }
    //methods for accessing prev or next student/course
    private void previousStudent (String m){
        if (m.contains("First")){
            i = 1;
        }
        if (!(i == 0)) {
            btnNextStudent.setDisable(false);
            btnLastStudent.setDisable(false);
            i--;
            setStudent(i);
        }
        if (i == 0){
            btnFirstStudent.setDisable(true);
            btnPrevStudent.setDisable(true);
        }
    }
    private void nextStudent (String m) {
        if (m.contains("Last")){
            i = alID.size()-2;
        }
        if (!(i == alID.size() - 1)) {
            btnFirstStudent.setDisable(false);
            btnPrevStudent.setDisable(false);
            i++;
            setStudent(i);
        }
        if (i == alID.size() - 1) {
            btnNextStudent.setDisable(true);
            btnLastStudent.setDisable(true);
        }
    }
    private void previousCourse(String m) {
        if(m.contains("First")){
            j = 1;
        }
        if (!(j == 0)) {
            btnNextCourse.setDisable(false);
            btnLastCourse.setDisable(false);
            j--;
            setCourse(j);
        }
        if (j == 0){
            btnFirstCourse.setDisable(true);
            btnPrevCourse.setDisable(true);
        }
    }
    private void nextCourse(String m) {
        if (m.contains("Last")){
            j = alCRN.size()-2;
        }
        if (!(j == alCRN.size() - 1)) {
            btnFirstCourse.setDisable(false);
            btnPrevCourse.setDisable(false);
            j++;
            setCourse(j);
        }
        if (j == alCRN.size() - 1) {
            btnNextCourse.setDisable(true);
            btnLastCourse.setDisable(true);
        }
    }
    //set course
    private void setCourse(int j){
        tfCRN.setText(alCRN.get(j));
        tfTitle.setText(alTitle.get(j));
        tfMajor2.setText(alMajor2.get(j));
        tfNumber.setText(alNumber.get(j));
        tfDepartment2.setText(alDepartment2.get(j));
    }
    //set student
    private void setStudent(int i){
        tfID.setText(alID.get(i));
        tfFirstName.setText(alFirstName.get(i));
        tfLastName.setText(alLastName.get(i));
        tfMajor1.setText(alMajor1.get(i));
        tfDepartment1.setText(alDepartment1.get(i));
    }
}
