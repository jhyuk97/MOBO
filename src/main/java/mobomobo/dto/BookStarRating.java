package mobomobo.dto;

public class BookStarRating {

	private String isbn;
	private int userno;
	private int age;
	private Double starRating;
	@Override
	public String toString() {
		return "BookStarRating [isbn=" + isbn + ", userno=" + userno + ", age=" + age + ", starRating=" + starRating
				+ "]";
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Double getStarRating() {
		return starRating;
	}
	public void setStarRating(Double starRating) {
		this.starRating = starRating;
	}
	
	
	
}
