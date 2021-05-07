package tn.esprit.pidev.views;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Order;
import tn.esprit.pidev.entities.Pack;
import tn.esprit.pidev.services.OrderService;
import tn.esprit.pidev.utils.LoggedUser;

public class BuyPackScreen extends Form{
    Form current;
    OrderService orderService = new OrderService();
    public BuyPackScreen(Form previous, Pack pack) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Pack Details");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label nameLabel = new Label("Pack Name: #" +pack.getId()+" "+pack.getName());
        Label descriptionLabel = new Label("Description: " + pack.getdescription());
        Label priceLabel = new Label("Price: " + pack.getPrice()+ " DT");
        Button buyButton = new Button("GET IT NOW!");
        buyButton.addActionListener(evt -> {orderService.buyPack(new Order(LoggedUser.ID_LOGGED_USER,pack.getId()));
        previous.showBack();});
        addAll(nameLabel, descriptionLabel, priceLabel,buyButton); // add all component to the form
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
