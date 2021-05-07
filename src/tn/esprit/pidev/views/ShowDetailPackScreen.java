package tn.esprit.pidev.views;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Pack;

public class ShowDetailPackScreen extends Form {
    Form current;
    public ShowDetailPackScreen(Form previous, Pack pack) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Pack Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label nameLabel = new Label("Pack Name: #" +pack.getId()+" "+pack.getName());
        Label descriptionLabel = new Label("Description: " + pack.getdescription());
        Label priceLabel = new Label("Price: " + pack.getPrice()+ " DT");

        addAll(nameLabel, descriptionLabel, priceLabel);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
