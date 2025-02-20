package com.mobigen.loans.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * AuditorAware: Entity의 값이 생성 되고 변경 될 때 누가 만들었는지, 누가 수정했는지 까지 자동으로 값을 업데이트 해주는 기능
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("LOANS_MS");
    }
	
}
