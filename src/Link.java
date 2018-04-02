import java.util.Random;
public class Link implements Runnable
{
	Message message;
	Random rand;

	//delay ranges
	int min,max;

	public Link(Message message)
	{
		this.message = message;
		rand = new Random();

		min = 900;
		max = 1100;


	}
	public void run() {
		//Delay in transmission
		int randomDelay = min + rand.nextInt(max -min +1);
		try {
			Thread.sleep(randomDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		message.receiver.receive(message);
	}

}
