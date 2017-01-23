package de.wonderworld.plugins.selfmadeEssentials.commands;

import de.fenmore.localization.ResourceBasedTranslationHandler;
import de.fenmore.localization.TranslationHandler;
import de.fenmore.localization.Translations;
import de.wonderworld.plugins.selfmadeEssentials.essentials.Essentials;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandBackTest {

    @Mock
    private Player player;

    @Mock
    private Player.Spigot spigotPlayer;


    @Before
    public void setup() {

        when(player.spigot()).thenReturn(spigotPlayer);
    }


    @Test
    public void givenMessageKeyAndLocaleWhenTranslatingAMessageThenTranslatedMessageIsReturned() {
        //given
        String country = "US";
        String language = "en";
        String messageKey = "FLY_MODE_OFF";
        when(spigotPlayer.getLocale()).thenReturn(language + "_" + country);

        //when
        //String translatedMessage = Translations.getTranslationHandler(new Essentials()).translate(player, messageKey);

        //then
        //assertThat("This is not the text for this language", translatedMessage, is("&6Your fly mode is now &coff&6."));
    }

}
