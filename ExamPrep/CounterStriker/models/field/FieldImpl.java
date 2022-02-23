package CounterStriker.models.field;
//created by J.M.

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImpl implements Field {

    @Override
    public String start(Collection<Player> players) {

        List<Player> contras = players.stream().filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());

        List<Player> terrorists = players.stream().filter(p -> p instanceof Terrorist)
                .collect(Collectors.toList());

        while (contras.stream().anyMatch(Player::isAlive) && terrorists.stream().anyMatch(Player::isAlive)) {

            terrorists.stream().filter(Player::isAlive).<Consumer<? super Player>>map(terrorist -> player ->
                    player.takeDamage(terrorist.getGun().fire())).forEach(contras::forEach);

            contras.stream().filter(Player::isAlive).<Consumer<? super Player>>map(contraTerrorist -> player ->
                    player.takeDamage(contraTerrorist.getGun().fire())).forEach(terrorists::forEach);
        }
        return terrorists.stream().anyMatch(Player::isAlive) ? TERRORIST_WINS : COUNTER_TERRORIST_WINS;
    }
}
