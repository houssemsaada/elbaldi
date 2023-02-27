/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.GUI;

import elbaldi.models.livraison;
import elbaldi.models.produit;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author houss
 */
class PanierListCell extends ListCell<produit> {

    @Override
    protected void updateItem(produit item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            // create a VBox to hold the cell content
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER_LEFT);
            vbox.setSpacing(8);
            VBox vbox2 = new VBox();
            vbox2.setAlignment(Pos.CENTER);
            vbox2.setSpacing(8);

            // create labels to display the Livraison properties
            Text idText = new Text("Ref produit: ");
            idText.setStyle("-fx-font-weight: bold");
            Text idValue = new Text(item.getRef_produit());
            HBox idbox = new HBox(idText, idValue);

            Text libelleText = new Text("Libelle:");
            libelleText.setStyle("-fx-font-weight: bold");
            Text libelleValue = new Text(item.getLibelle());
            HBox libellebox = new HBox(libelleText, libelleValue);

            Text descText = new Text("Description: ");
            descText.setStyle("-fx-font-weight: bold");
            Text descValue = new Text(item.getDescription());
            HBox descbox = new HBox(descText, descValue);

            Text prixText = new Text("Prix: ");
            prixText.setStyle("-fx-font-weight: bold");
            Text prixValue = new Text(item.getPrix_vente() + "");
            HBox  prixbox = new HBox(prixText, prixValue);

            Text quantiteText = new Text("Quantite: ");
            quantiteText.setStyle("-fx-font-weight: bold");
            Text quantiteValue = new Text(item.getQuantite() + "");
            HBox quantitebox = new HBox(quantiteText, quantiteValue);
            //for total produit 

            Float totalprod = item.getPrix_vente() * item.getQuantite();
            Text totText = new Text("Total: ");
            totText.setStyle("-fx-font-weight: bold");
            Text totValue = new Text(totalprod + "");
            HBox totbox = new HBox(totText, totValue);


            // add the labels to the VBox
            vbox.getChildren().addAll(idbox, libellebox, descbox);
            vbox2.getChildren().addAll(prixbox, quantitebox, totbox);
            Pane leftPane = new Pane(vbox);

            HBox.setHgrow(leftPane, Priority.ALWAYS);
            Pane rightPane = new Pane(vbox2);
            leftPane.setMaxWidth(450);
            leftPane.setMinWidth(450);
            HBox.setHgrow(rightPane, Priority.ALWAYS);
            
            HBox graphbox = new HBox(leftPane, rightPane);

            // set the VBox as the cell content
            setText(null);
            setGraphic(graphbox);

        }
    }
}
