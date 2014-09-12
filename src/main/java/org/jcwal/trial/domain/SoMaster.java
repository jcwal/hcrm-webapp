/**
 * SoMaster.java 2011-5-31
 */
package org.jcwal.trial.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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

/**
 * <p> <b>SoMaster</b> 是销售单主表. </p>
 * 
 * @since 2011-5-31
 * @author jokeway
 * @version $Id: SoMaster.java 2329 2011-11-02 09:58:27Z wilson $
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SO_MASTER")
@TypeDefs({ @TypeDef(name = "fieldOption", typeClass = FieldOptionType.class, defaultForType = FieldOption.class) })
public class SoMaster extends AbstractAuditable<Long> {

	private static final long serialVersionUID = 1L;

	@Column(name = "SO_NO", length = 15)
	@javax.validation.constraints.Size(min = 15, max = 15)
	private String soNo;

	@Type(type = "fieldOption", parameters = { @Parameter(name = "dataParamCode", value = "so_type_option") })
	private FieldOption soType;

	@Column(name = "DEALER_NO", length = 9)
	@javax.validation.constraints.Size(min = 9, max = 9)
	private String dealerNo;

	@OneToMany(targetEntity = SoDetail.class, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "SO_NO", referencedColumnName = "SO_NO")
	private List<SoDetail> soDetails = new ArrayList<SoDetail>();

	@Column(name = "TOTAL_AMT")
	private float totalAmt;

	/**
	 * @return the soNo
	 */
	public String getSoNo() {
		return soNo;
	}

	/**
	 * @param soNo
	 *            the soNo to set
	 */
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	/**
	 * @return the soType
	 */
	public FieldOption getSoType() {
		return soType;
	}

	/**
	 * @param soType
	 *            the soType to set
	 */
	public void setSoType(FieldOption soType) {
		this.soType = soType;
	}

	/**
	 * @return the dealerNo
	 */
	public String getDealerNo() {
		return dealerNo;
	}

	/**
	 * @param dealerNo
	 *            the dealerNo to set
	 */
	public void setDealerNo(String dealerNo) {
		this.dealerNo = dealerNo;
	}

	/**
	 * @return the totalAmt
	 */
	public float getTotalAmt() {
		return totalAmt;
	}

	/**
	 * @param totalAmt
	 *            the totalAmt to set
	 */
	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	/**
	 * @return the soDetails
	 */
	public List<SoDetail> getSoDetails() {
		return soDetails == null ? new ArrayList<SoDetail>() : soDetails;
	}

	/**
	 * @param soDetails
	 *            the soDetails to set
	 */
	public void setSoDetails(List<SoDetail> soDetails) {
		this.soDetails = soDetails;
	}

	public void updateDetails() {
		if (soDetails != null) {
			List<SoDetail> removed = new ArrayList<SoDetail>();
			for (SoDetail detail : soDetails) {
				if (detail.isDeleted()) {
					removed.add(detail);
				} else if (detail.getSoMaster() == null) {
					detail.setSoMaster(this);
				}
			}
			for (SoDetail soDetail : removed) {
				soDetail.setSoMaster(null);
				soDetails.remove(soDetail);
			}
		}
	}
}
