package DataClasses;

public class Chat {

	private String from;
	private String to;
	private int type;                   /*      0 chat      1 file    */
	private String content;
	private int level;                  /*      0 sender        1 receiver          */
	private int status;

	public Chat(String from, String to, int type, String content, int level,int status) {
		this.from = from;
		this.to = to;
		this.type = type;
		this.content = content;
		this.level = level;
		this.status = status;
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

	@Override
	public String toString() {
		return ""+this.type+"#"+this.level+"#"+content+"#"+this.status;
	}
}
