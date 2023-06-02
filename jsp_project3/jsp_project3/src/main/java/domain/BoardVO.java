package domain;

public class BoardVO {
/*create table board(
bno int not null auto_increment,
title varchar(100) not null,
writer varchar(100) not null,
content text,
reg_date datetime default now(),
read_count int not null,
primary key(bno));
 * */
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private String reg_date;
	private int read_count;
	private String img_file;
	
	public BoardVO() {}
	
	//register (title, writer, content)
	public BoardVO(String title, String writer, String content) {
		this.title=title;
		this.writer=writer;
		this.content=content;
	}
	
	//list (bno, title, writer, reg_date, read_count)
	public BoardVO(int bno, String title, String writer, String reg_date, int read_count) {
		this.bno=bno;
		this.title=title;
		this.writer=writer;
		this.reg_date=reg_date;
		this.read_count=read_count;
	}
	
	//detail All
	public BoardVO(int bno, String title, String writer, String content, String reg_date, int read_count) {
		this(bno,title,writer,reg_date, read_count);
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public String getImg_file() {
		return img_file;
	}

	public void setImg_file(String img_file) {
		this.img_file = img_file;
	}
	
	
}