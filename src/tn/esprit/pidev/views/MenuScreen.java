package tn.esprit.pidev.views;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class MenuScreen extends Form {
Form current;
public MenuScreen(){
current = this;
setTitle("Menu");
setLayout(BoxLayout.y());

    add(new Label("Choose an option from side menu"));

//SIDE MENU
    getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {    });
    getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> {        new MenuScreen().show(); });
    getToolbar().addCommandToLeftSideMenu("All Packs", null, (evt) -> {        new AllPacksScreen(current).show(); });
    getToolbar().addCommandToLeftSideMenu("My Packs", null, (evt) -> { new MyPacksScreen(current).show(); });
    getToolbar().addCommandToLeftSideMenu("My Orders", null, (evt) -> { new MyOrdersScreen(current).show(); });
    getToolbar().addCommandToLeftSideMenu("Declined Packs", null, (evt) -> { new DeclinedPacksScreen(current).show(); });
    getToolbar().addCommandToLeftSideMenu("Overview Packs", null, (evt) -> { new OverviewPacksScreen(current).show(); });


}
}
