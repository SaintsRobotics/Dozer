import com.saintsrobotics.framework.coroutine.Task;
import com.saintsrobotics.framework.coroutine.TaskRunner;
import com.saintsrobotics.framework.coroutine.helpers.RunContinuousTask;
import com.saintsrobotics.framework.coroutine.helpers.RunParallelTask;

public class main {

	public static void main(String[] args) {
		Task task1 = new RunContinuousTask() {
			@Override
			protected void runContinuously() {
				System.out.println("test1");
				wait.forSeconds(3);

				
			}
		};
		
		Task task2 = new RunContinuousTask() {
			@Override
			protected void runContinuously() {
				System.out.println("test2");
				wait.forSeconds(1);

				
			}
		};
		
		Task task3 = new RunContinuousTask() {
			@Override
			protected void runContinuously() {
				System.out.println("test3");

				wait.forSeconds(1);

			}
		};
		
		Task paralleltask = new RunParallelTask(task2, task3);
		
		TaskRunner test = new TaskRunner(task1, paralleltask);
		while(true) {
			test.run();
		}
		
		
	}

}
