package com.gym.controller.entity;

import com.gym.State;
import com.gym.entity.Category;
import com.gym.entity.Transaction;
import com.gym.utils.CategoryUtils;
import com.gym.utils.CommonUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionEntityController implements Initializable {
    @FXML
    private AnchorPane ap_description;
    @FXML
    private Button btn_delete;
    @FXML
    private Pane btn_show_description;
    @FXML
    private Button btn_update;
    @FXML
    private Label lbl_category;
    @FXML
    private Label lbl_price;
    @FXML
    private VBox wrap_transaction_entity;
    @FXML
    private TextArea ta_description;

    private Transaction transaction;
    private boolean isDescriptionOpen = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wrap_transaction_entity.getChildren().remove(ap_description);

        btn_show_description.setOnMouseClicked(event -> {
            isDescriptionOpen = !isDescriptionOpen;
            if (isDescriptionOpen) {
                wrap_transaction_entity.getChildren().add(ap_description);
                btn_show_description.setRotate(180);
            } else {
                wrap_transaction_entity.getChildren().remove(ap_description);
                btn_show_description.setRotate(0);
            }
        });

        btn_update.setOnMouseClicked(event -> {
            try {
                State.transaction = transaction;
                CommonUtils.showModal("components/modal/transaction-modal.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btn_delete.setOnMouseClicked(event -> {
            CategoryUtils.deleteCategory(this.transaction.getId());
            State.refresh.firePropertyChange("transaction", 1, 2);
        });
    }

    public void setData(Transaction transaction) {
        this.transaction = transaction;
        lbl_category.setText(transaction.getCategoryName());
        lbl_price.setText(String.valueOf(transaction.getPrice()));
        ta_description.setText(transaction.getDescription());
    }
}
