package agents;

import com.mindsmiths.ruleEngine.model.Agent;
import lombok.*;
import com.mindsmiths.ruleEngine.util.Log;

import com.mindsmiths.armory.ArmoryAPI;
import com.mindsmiths.armory.Screen;

import com.mindsmiths.armory.component.Title;

import java.io.IOException;
import java.util.List;
import com.mindsmiths.emailAdapter.NewEmail;
import com.mindsmiths.emailAdapter.EmailAdapterAPI;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class Moli extends Agent {
    public void showHelloScreen() {
        ArmoryAPI.show(
            getConnection("armory"),
            new Screen ("Welcome")
                .add(new Header("mioc_logo 3.png", true)) // trebam nekako uploadati logo sa kompa
                .add(new Title("Bok, ja sam Moli! Može anketa?"))
                .add(new Text("Hvala ti puno što mi pomažeš <3"))
                .add(new SubmitButton("welcomeStarted", "Idemo!", "askForGender")),
            new Screen ("AskForGender")
                .add(new Header("mioc_logo 3.png", true)) 
                .add(new Title("Jesi li miočanin ili miočanka?")) //trebam dodati dva gumba koji pokrecu drukcije pravilo tako da se prilagodi spol
        );
        try {
            sendEmail(List.of("marko.zelenovicc@gmail.com"), "Test title", "Test 12345");
            Log.info("Mail uspjesno poslan!");
        } catch(IOException ex) {
            Log.info("Exception! " + ex.toString());
        }
    }

    public void sendEmail(List<String> recipients, String emailTitle, String emailText) throws IOException {
        NewEmail email = new NewEmail();
        email.setRecipients(recipients);
        email.setSubject(emailTitle);
        email.setPlainText(emailText);

        EmailAdapterAPI.newEmail(email);
    }
}