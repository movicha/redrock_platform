package models;

public interface Task {
	
	/**
	 * Executes the task defined in the task definition.
	 */
	public void execute();
	
	/**
	 * Initializes the task and sets the task definition.
	 * 
	 * @param definition
	 */
	public void initialize(TaskDefinition definition);
	
	/**
	 * Returns the task definition associated with this task.
	 * 
	 * @return a task definition
	 */
	public TaskDefinition getTaskDefinition();
	
	/**
	 * Returns true if the task is currently in its execute() method.
	 * 
	 * @return true if task is executing, false otherwise
	 */
	boolean isExecuting();
	
	/**
	 * Callback method used to clean up resources used during the tasks execution.
	 */
	void shutdown();
	
}
