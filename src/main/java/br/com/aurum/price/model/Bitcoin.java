package br.com.aurum.price.model;

public class Bitcoin {

	private int date;
	private Double price;
	private Double amount;
	private int tid;
	private String type;

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
