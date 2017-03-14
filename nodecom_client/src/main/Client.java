package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.java.com.challenge.node.AppNode;



public class Client {

	
	public static void main(String[] args) {
		
		int threadCount = 2;
		final ExecutorService exs = Executors.newFixedThreadPool(threadCount);
		
		int last = 0;
		

		for (int i = 0; i < threadCount; i++)
		{

			
			AppNode an = new AppNode();
			
			final Runnable jean = new NodeStarter(an, ((i+1)*(i+1))*1000, i);
			exs.execute(jean);
		}
	}

}

class NodeStarter implements Runnable
{
	
	AppNode appNode;
	long latency;
	int instanceId;
	NodeStarter(AppNode appNode, long latency, int instanceId)
	{
		this.appNode = appNode;
		this.latency = latency;
		this.instanceId = instanceId;
	}


	@Override
	public void run()
	{
		try {
			Thread.sleep(latency);
			System.out.println("Client started instance " + instanceId);
			appNode.main(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}