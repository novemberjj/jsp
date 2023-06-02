package domain;

public class BoardVO {
	/*create table board(
	bno int not null auto_increment,
	title varchar(100) not null,
	writer varchar(100) not null,
	content text,
	reg_date datetime default now(),
	primary key(bno));
	 * */

	private int bno;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private int read_count;
	private String img_file; //생성자에는 반드시 값이 있어야 하기 때문에 set으로 설정
	
	public BoardVO() {}
	
	//register (title, writer, content)
	public BoardVO(String title, String writer, String content) {
		this.title=title;
		this.writer=writer;
		this.content=content;
	}
	//list (bno, title, writer, reg_date, read_count)
	public BoardVO(int bno, String title, String writer, String reg_date, int read_count, String img_file) {
		this.bno=bno;
		this.title=title;
		this.writer=writer;
		this.reg_date=reg_date;
		this.read_count=read_count;
		this.img_file=img_file;
	}
	
	//detail All(bno, title, writer, content, reg_date, read_count)
	public BoardVO(int bno, String title, String writer, String content, String reg_date, int read_count,String img_file) {
		this(bno, title, writer, reg_date,read_count,img_file);
		this.content=content;

	}
	
	//update (bno, title, content)
	public BoardVO(int bno, String title, String content) {
		this.bno=bno;
		this.title=title;
		this.content=content;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", reg_date=" + reg_date + ", read_count=" + read_count + ", img_file=" + img_file + "]";
	}

	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getContent() {
		return content;
	}
	public void setDetail(String content) {
		this.content = content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int lead_count) {
		this.read_count = lead_count;
	}

	public String getImg_file() {
		return img_file;
	}

	public void setImg_file(String img_file) {
		this.img_file = img_file;
	}
	
	
	
	

}