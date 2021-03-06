package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItemStruct {
	// product
	private String productId;

	private BigDecimal productPrice;

	private String productName;

	private Date productSnapshotDate;

	private String productType;

	private int quantity;

	private BigDecimal totalCost;

	private String currency;

	// discount
	private String discountCause;

	private BigDecimal discount;
	
	public OfferItemStruct(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity) {
		this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
	}
	
	public OfferItemStruct(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity,
			BigDecimal discount, String discountCause) {
		this.productId = productId;
		this.productPrice = productPrice;
		this.productName = productName;
		this.productSnapshotDate = productSnapshotDate;
		this.productType = productType;

		this.quantity = quantity;
		this.discount = discount;
		this.discountCause = discountCause;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount != null)
			discountValue = discountValue.subtract(discount);

		this.totalCost = productPrice
				.multiply(new BigDecimal(quantity)).subtract(discountValue);
	}
	
	public String getProductId() {
		return productId;
	}
	
	public BigDecimal getProductPrice() {
		return productPrice;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Date getProductSnapshotDate() {
		return productSnapshotDate;
	}
	
	public String getProductType() {
		return productType;
	}

	public BigDecimal getTotalCost() {
		return totalCost;
	}

	public String getTotalCostCurrency() {
		return currency;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public String getDiscountCause() {
		return discountCause;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductSnapshotDate(Date productSnapshotDate) {
		this.productSnapshotDate = productSnapshotDate;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}

	public void setDiscountCause(String discountCause) {
		this.discountCause = discountCause;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
}
