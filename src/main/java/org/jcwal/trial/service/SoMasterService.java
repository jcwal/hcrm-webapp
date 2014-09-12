/**
 * SoMasterService.java 2011-6-9
 */
package org.jcwal.trial.service;

import org.jcwal.trial.domain.SoMaster;

/**
 * <p> <b>SoMasterService</b> 是SoMaster服务接口. </p>
 * 
 * @since 2011-6-9
 * @author Wilson Luo
 * @version $Id: SoMasterService.java 1290 2011-06-09 08:24:00Z wilson $
 */
public interface SoMasterService {

	/**
	 * @param id
	 * @return
	 */
	SoMaster findOne(Long id);

	/**
	 * @param soMaster
	 */
	void save(SoMaster soMaster);

	/**
	 * @param soMaster
	 */
	void delete(SoMaster soMaster);

}
