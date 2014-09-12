/**
 * SoMasterRepository.java 2011-5-31
 */
package org.jcwal.trial.repository;

import java.util.List;

import org.jcwal.trial.domain.SoDetail;
import org.jcwal.trial.domain.SoMaster;
import org.macula.core.repository.MaculaJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p> <b>SoMasterRepository</b> 是SoMaster存取接口. </p>
 * 
 * @since 2011-5-31
 * @author jokeway
 * @version $Id: SoMasterRepository.java 2118 2011-10-19 01:09:18Z wzp $
 */
public interface SoMasterRepository extends MaculaJpaRepository<SoMaster, Long> {
	@Query("from SoMaster m left join fetch m.soDetails where m.id=:id")
	public SoMaster findOnly(@Param("id")Long id);
	
	@Query("from SoDetail d left join fetch d.soMaster where d.id=:id")
	public SoDetail findDetailOnly(@Param("id")Long id);
	
	@Query("from SoDetail d left join fetch d.soMaster")
	public List<SoDetail> findDetails();
}
