/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

	OfferItemStruct offerItem = new OfferItemStruct();

	public OfferItem(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity) {
		this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
	}

	public OfferItem(String productId, BigDecimal productPrice, String productName,
			Date productSnapshotDate, String productType, int quantity,
			BigDecimal discount, String discountCause) {
		offerItem.setProductId(productId);
		//this.productId = productId;
		offerItem.setProductPrice(productPrice);
		//this.productPrice = productPrice;
		offerItem.setProductName(productName);
		//this.productName = productName;
		offerItem.setProductSnapshotDate(productSnapshotDate);
		//this.productSnapshotDate = productSnapshotDate;
		offerItem.setProductType(productType);
		//this.productType = productType;

		offerItem.setQuantity(quantity);
		//this.quantity = quantity;
		offerItem.setDiscount(discount);
		//this.discount = discount;
		offerItem.setDiscountCause(discountCause);
		//this.discountCause = discountCause;

		BigDecimal discountValue = new BigDecimal(0);
		if (discount != null)
			discountValue = discountValue.subtract(discount);
		
		offerItem.setTotalCost(productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue));
		//this.totalCost = productPrice
		//		.multiply(new BigDecimal(quantity)).subtract(discountValue);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + hashHelp(offerItem.getDiscount());
		result = prime * result + hashHelp(offerItem.getProductName());
		result = prime * result + hashHelp(offerItem.getProductPrice());
		result = prime * result + hashHelp(offerItem.getProductId());
		result = prime * result + hashHelp(offerItem.getProductType());
		result = prime * result + offerItem.getQuantity();
		result = prime * result + hashHelp(offerItem.getTotalCost());
		
		return result;
	}
	
	private int hashHelp(Object obj) {
		
		int result = ((obj == null) ? 0 : obj.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferItem other = (OfferItem) obj;
		if (offerItem.getDiscount() == null) {
			if (other.offerItem.getDiscount() != null)
				return false;
		} else if (!offerItem.getDiscount().equals(other.offerItem.getDiscount()))
			return false;
		if (offerItem.getProductName() == null) {
			if (other.offerItem.getProductName() != null)
				return false;
		} else if (!offerItem.getProductName().equals(other.offerItem.getProductName()))
			return false;
		if (offerItem.getProductPrice() == null) {
			if (other.offerItem.getProductPrice() != null)
				return false;
		} else if (!offerItem.getProductPrice().equals(other.offerItem.getProductPrice()))
			return false;
		if (offerItem.getProductId() == null) {
			if (other.offerItem.getProductId() != null)
				return false;
		} else if (!offerItem.getProductId().equals(other.offerItem.getProductId()))
			return false;
		if (offerItem.getProductType() != other.offerItem.getProductType())
			return false;
		if (offerItem.getQuantity() != other.offerItem.getQuantity())
			return false;
		if (offerItem.getTotalCost() == null) {
			if (other.offerItem.getTotalCost() != null)
				return false;
		} else if (!offerItem.getTotalCost().equals(other.offerItem.getTotalCost()))
			return false;
		return true;
	}

	/**
	 * 
	 * @param item
	 * @param delta
	 *            acceptable percentage difference
	 * @return
	 */
	public boolean sameAs(OfferItem other, double delta) {
		if (offerItem.getProductName() == null) {
			if (other.offerItem.getProductName() != null)
				return false;
		} else if (!offerItem.getProductName().equals(other.offerItem.getProductName()))
			return false;
		if (offerItem.getProductPrice() == null) {
			if (other.offerItem.getProductPrice() != null)
				return false;
		} else if (!offerItem.getProductPrice().equals(other.offerItem.getProductPrice()))
			return false;
		if (offerItem.getProductId() == null) {
			if (other.offerItem.getProductId() != null)
				return false;
		} else if (!offerItem.getProductId().equals(other.offerItem.getProductId()))
			return false;
		if (offerItem.getProductType() != other.offerItem.getProductType())
			return false;

		if (offerItem.getQuantity() != other.offerItem.getQuantity())
			return false;

		return sameAsHelp(other, delta);
	}

	private boolean sameAsHelp(OfferItem other, double delta) {
		BigDecimal max, min;
		if (offerItem.getTotalCost().compareTo(other.offerItem.getTotalCost()) > 0) {
			max = offerItem.getTotalCost();
			min = other.offerItem.getTotalCost();
		} else {
			max = other.offerItem.getTotalCost();
			min = offerItem.getTotalCost();
		}

		BigDecimal difference = max.subtract(min);
		BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

		return acceptableDelta.compareTo(difference) > 0;
	}

}
