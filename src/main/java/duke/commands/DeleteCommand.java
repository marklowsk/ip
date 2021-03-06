package duke.commands;

import duke.data.exceptions.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.TextUI;

import static duke.commands.Utils.parseNumberFromArgument;
import static duke.common.Messages.MESSAGE_ERROR_NOT_A_TASK_NUMBER;

public class DeleteCommand extends Command {
    public static final String DELETE_WORD = "delete";

    private String commandArgs;

    public DeleteCommand(String commandArgs) {
        this.commandArgs = commandArgs;
    }

    /**
     * Deletes a task from the TaskList object based on its task number.
     * Fails if the argument value is missing or is not a number.
     *
     * @param tasks the TaskList object that contains the list of tasks.
     * @param ui the TextUI object that that engages user input and program output.
     * @param storage the Storage object that writes/retrieves to/from file.
     * @see TaskList#deleteTask(int, TextUI, Storage)
     */
    private void deleteTask(TaskList tasks, TextUI ui, Storage storage) {
        try {
            int taskNumber = parseNumberFromArgument(commandArgs);
            tasks.deleteTask(taskNumber, ui, storage);
        } catch (DukeException e) {
            // taskNumber is an empty string, reflect error to user.
            ui.printError(e.getMessage());
        } catch (NumberFormatException e) {
            // taskNumber is not a parsable to an integer, reflect error to user.
            ui.printError(MESSAGE_ERROR_NOT_A_TASK_NUMBER);
        }
    }

    @Override
    public void execute(TaskList tasks, TextUI ui, Storage storage) {
        deleteTask(tasks, ui, storage);
    }
}
