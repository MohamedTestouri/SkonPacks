package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Pack;
import tn.esprit.pidev.services.PackService;

import java.util.ArrayList;
import java.util.Collections;

public class AllPacksScreen extends Form {
    Form current;
    PackService packService = new PackService();
ArrayList<Pack> packArrayList = new ArrayList<>();
    public AllPacksScreen(Form previous) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("All Packs");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
packArrayList = packService.showMyPacks("allPacks");
        Collections.reverse(packArrayList); // To reverse the order of the arraylist
for (Pack pack : packArrayList){
MultiButton multiButton = new MultiButton();
multiButton.setTextLine1(pack.getName());
multiButton.setTextLine2(pack.getPrice()+"");
multiButton.setTextLine3(pack.getdescription());
multiButton.setUIID(pack.getId()+"");
multiButton.addActionListener(evt -> {new BuyPackScreen(current, pack).show();});
add(multiButton);
        }
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    String line3 = mb.getNameLine3();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1 ||
                            line3 != null && line3.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {    });
        getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> {        new MenuScreen().show(); });
        getToolbar().addCommandToLeftSideMenu("All Packs", null, (evt) -> {        new AllPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("My Packs", null, (evt) -> { new MyPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("My Orders", null, (evt) -> { new MyOrdersScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("Declined Packs", null, (evt) -> { new DeclinedPacksScreen(current).show(); });
        getToolbar().addCommandToLeftSideMenu("Overview Packs", null, (evt) -> { new OverviewPacksScreen(current).show(); });

    }
}
