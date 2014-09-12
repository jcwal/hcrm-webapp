/**
 * SoMasterServiceImpl.java 2011-6-9
 */
package org.jcwal.trial.service.impl;

import org.jcwal.trial.domain.SoMaster;
import org.jcwal.trial.repository.SoMasterRepository;
import org.jcwal.trial.service.SoMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p> <b>SoMasterServiceImpl</b> 是SoMaster服务实现类. </p>
 * 
 * @since 2011-6-9
 * @author Wilson Luo
 * @version $Id: SoMasterServiceImpl.java 2329 2011-11-02 09:58:27Z wilson $
 */
@Service
public class SoMasterServiceImpl implements SoMasterService {

	@Autowired
	private SoMasterRepository soMasterRepository;

	@Override
	@Transactional
	public SoMaster findOne(Long id) {
		return soMasterRepository.findOne(id);
	}

	@Override
	@Transactional
	public void save(SoMaster soMaster) {
		soMaster.updateDetails();
		soMasterRepository.save(soMaster);
	}

	@Override
	@Transactional
	public void delete(SoMaster soMaster) {
		soMasterRepository.delete(soMaster);
	}

}
