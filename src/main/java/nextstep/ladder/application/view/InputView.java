package nextstep.ladder.application.view;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import nextstep.ladder.domain.height.Height;
import nextstep.ladder.domain.player.Player;
import nextstep.ladder.domain.player.Players;

public class InputView {

	private static final Scanner scanner = new Scanner(System.in);
	private static final String PLAYER_NAME_DELIMITER = ",";

	private InputView() {
	}

	public static Players getPlayers() {
		System.out.println("Enter the name of players. Split them using comma(,)");
		String nameString = scanner.nextLine();

		AtomicInteger index = new AtomicInteger(0);

		return Arrays.stream(nameString.split(PLAYER_NAME_DELIMITER))
			.map(name -> Player.ofNameAndPosition(name, index.get()))
			.peek(player -> index.getAndIncrement())
			.collect(collectingAndThen(toList(), Players::ofPlayers));
	}

	public static Height getHeights() {
		System.out.println("Enter the height of ladder.");
		String heightString = scanner.nextLine();
		return Height.ofHeight(heightString);
	}
}
