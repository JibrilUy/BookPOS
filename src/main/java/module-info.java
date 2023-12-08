module com.example.bookpos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookpos to javafx.fxml;
    exports com.example.bookpos;
}