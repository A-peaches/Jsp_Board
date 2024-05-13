package net.basket.db;


public class BasketBean {
	private int basket_id;
	private String user_id;
	private String cookie_name;
	private String cookie_img;
	private int cookie_qnt;
	private int cookie_price;
	
	public int getBasket_id() {
		return basket_id;
	}
	public void setBasket_id(int basket_id) {
		this.basket_id = basket_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCookie_img() {
		return cookie_img;
	}
	public void setCookie_img(String cookie_img) {
		this.cookie_img = cookie_img;
	}
	public int getCookie_qnt() {
		return cookie_qnt;
	}
	public void setCookie_qnt(int cookie_qnt) {
		this.cookie_qnt = cookie_qnt;
	}
	public int getCookie_price() {
		return cookie_price;
	}
	public void setCookie_price(int cookie_price) {
		this.cookie_price = cookie_price;
	}
	public String getCookie_name() {
		return cookie_name;
	}
	public void setCookie_name(String cookie_name) {
		this.cookie_name = cookie_name;
	}
	
	public String getFormat() {
	    if (cookie_name.equals("American Cookie")) {
	        return "Ame";
	    } else if (cookie_name.equals("Double Choco Cookie")) {
	        return "Double";
	    } else if (cookie_name.equals("Otmeal Cookie")) {
	        return "Otmeal";
	    }
	    return ""; // 기본값 혹은 예외 처리
	}


	
}
