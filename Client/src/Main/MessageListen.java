package Main;

import DataClasses.Chat;

import java.io.IOException;
import java.io.ObjectInputStream;

public class MessageListen implements Runnable{

	private ObjectInputStream objectInputStream;

	public MessageListen(ObjectInputStream objectInputStream){
		this.objectInputStream = objectInputStream;
	}

	/**
	 * When an object implementing interface <code>Runnable</code> is used
	 * to create a thread, starting the thread causes the object's
	 * <code>run</code> method to be called in that separately executing
	 * thread.
	 * <p>
	 * The general contract of the method <code>run</code> is that it may
	 * take any action whatsoever.
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {

		while (true){

			try {
				Object msg = objectInputStream.readObject();

				if (msg instanceof Chat){
					System.out.println(((Chat)(msg)).getContent());
				}

			} catch (IOException e) {
				break;
//				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

}
