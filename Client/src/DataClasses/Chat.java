package DataClasses;

import java.io.Serializable;

public class Chat implements Serializable {

	private String from;
	private String to;
	private int type;                   /*      0 chat      1 file    */
	private String content;
	private int level;                  /*      0 sender        1 receiver          */
	private int status;
	private boolean shouldIntentLeft;   /* for displaying chats */

	public Chat(String from, String to, int type, String content, int level, int status, boolean shouldIntentLeft) {
		this.from = from;
		this.to = to;
		this.type = type;
		this.content = content;
		this.level = level;
		this.status = status;
		this.shouldIntentLeft = shouldIntentLeft;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isShouldIntentLeft() {
		return shouldIntentLeft;
	}

	public void setShouldIntentLeft(boolean shouldIntentLeft) {
		this.shouldIntentLeft = shouldIntentLeft;
	}



	@Override
	public String toString() {
		return ""+this.from+"#"+this.to+"#"+this.type+"#"+this.content+"#"+this.level+"#"+this.status+"#"+this.shouldIntentLeft;
	}

	public Chat getChat(){
		String[] s = this.toString().split("#");
		return new Chat(s[0],s[1],Integer.parseInt(s[2]),s[3],Integer.parseInt(s[4]),Integer.parseInt(s[5]),Boolean.parseBoolean(s[6]));
	}

}
