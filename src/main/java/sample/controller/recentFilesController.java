package sample.controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import sample.util.FileData;
import sample.util.FileModule;

import java.net.URL;
import java.util.ResourceBundle;

public class recentFilesController implements Initializable {

    @FXML
    private StackPane recentsStackPane;
    @FXML
    private JFXTreeTableView<FileModule> treeView;

    private void loadFileTable(){
        JFXTreeTableColumn<FileModule,String> fileName = new JFXTreeTableColumn<>("File");
        fileName.setPrefWidth(119);
        fileName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FileModule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FileModule, String> param) {
                return param.getValue().getValue().fileName;
            }
        });

        JFXTreeTableColumn<FileModule,String> time = new JFXTreeTableColumn<>("Time");
        time.setPrefWidth(160);
        time.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FileModule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FileModule, String> param) {
                return param.getValue().getValue().time;
            }
        });

        JFXTreeTableColumn<FileModule,String> location = new JFXTreeTableColumn<>("Location");
        location.setPrefWidth(360);
        location.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FileModule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FileModule, String> param) {
                return param.getValue().getValue().location;
            }
        });

        JFXTreeTableColumn<FileModule,String> algorithm = new JFXTreeTableColumn<>("Algorithm");
        algorithm.setPrefWidth(109);
        algorithm.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<FileModule, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<FileModule, String> param) {
                return param.getValue().getValue().algorithmUsed;
            }
        });
        treeView.getColumns().setAll(fileName,time,location,algorithm);
        final TreeItem<FileModule> root = new RecursiveTreeItem<FileModule>(FileData.getInstance().fileModules, RecursiveTreeObject::getChildren);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileData.getInstance().loadFileModules();
        loadFileTable();

    }
}
