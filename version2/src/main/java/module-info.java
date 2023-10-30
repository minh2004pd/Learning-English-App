module com.example.version2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires freetts;
    requires voicerss.tts;
    requires javafx.media;


    opens com.example.version2 to javafx.fxml;
    exports com.example.version2;
    exports model;
}