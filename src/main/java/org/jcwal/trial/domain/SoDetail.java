/**
 * SoDetail.java 2011-5-31
 */
package org.jcwal.trial.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.macula.base.data.hibernate.type.FieldOptionType;
import org.macula.base.data.vo.FieldOption;
import org.macula.core.domain.AbstractAuditable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p> <b>SoDetail</b> 销售单明细. </p>
 * 
 * @since 2011-5-31
 * @author jokeway
 * @version $Id: SoDetail.java 3347 2012-07-11 06:06:12Z wzp $
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SO_DETAIL")
@TypeDefs({ @TypeDef(name = "fieldOption", typeClass = FieldOptionType.class, defaultForType = FieldOption.class) })
public class SoDetail extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = SoMaster.class)
	@JoinColumn(name = "SO_NO", referencedColumnName = "SO_NO")
	private SoMaster soMaster;

	@Type(type = "fieldOption", parameters = { @Parameter(name = "dataParamCode", value = "product_info") })
	private FieldOption product;

	private String unit;
	private float price;
	private int qty;

	/**
	 * @return the soMaster
	 */
	@JsonIgnore
	public SoMaster getSoMaster() {
		return soMaster;
	}

	/**
	 * @param soMaster
	 *            the soMaster to set
	 */
	public void setSoMaster(SoMaster soMaster) {
		this.soMaster = soMaster;
	}

	/**
	 * @return the product
	 */
	public FieldOption getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(FieldOption product) {
		this.product = product;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the qty
	 */
	public int getQty() {
		return qty;
	}

	/**
	 * @param qty
	 *            the qty to set
	 */
	public void setQty(int qty) {
		this.qty = qty;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

}
