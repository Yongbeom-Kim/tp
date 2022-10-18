package seedu.address.logic.commands.client.find;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.client.ClientCommand;
import seedu.address.model.Model;
import seedu.address.model.client.predicates.NameContainsKeywordsPredicate;
import seedu.address.ui.Ui;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ClientCliSyntax.PREFIX_CLIENT_NAME;

public class FindClientByNameCommand extends FindClientCommand {

    public static final String MESSAGE_FIND_CLIENT_BY_NAME_USAGE = COMMAND_WORD + ": Finds and filters clients by " +
            "keyword " +
            "from the " +
            "address book. "
            + "Parameters: "
            + PREFIX_CLIENT_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + COMMAND_FLAG + " "
            + PREFIX_CLIENT_NAME + "John";

    private final NameContainsKeywordsPredicate predicate;

    public FindClientByNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Ui ui) {
        requireNonNull(model);
        model.updateFilteredClientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClientByNameCommand // instanceof handles nulls
                && predicate.equals(((FindClientByNameCommand) other).predicate)); // state check
    }

}
