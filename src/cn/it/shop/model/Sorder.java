package cn.it.shop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 购物车（订单）购物项的实体 @author MyEclipse Persistence Tools
 */
@Entity
public class Sorder implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7442249255970125735L;
	
	private Integer id;
	private Forder forder;     // 订单
	private Product product;   // 商品
	private String name;       // 被购买商品的名字
	private BigDecimal price;  // 被购买商品的价格
	private Integer number;    // 购买的数量

	// Constructors

	/** default constructor */
	public Sorder() {
	}

	/** minimal constructor */
	public Sorder(Integer number) {
		this.number = number;
	}

	/** full constructor */
	public Sorder(Forder forder, Product product, String name, BigDecimal price, Integer number) {
		this.forder = forder;
		this.product = product;
		this.name = name;
		this.price = price;
		this.number = number;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fid")
	public Forder getForder() {
		return this.forder;
	}

	public void setForder(Forder forder) {
		this.forder = forder;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", precision = 8)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "number", nullable = false)
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}