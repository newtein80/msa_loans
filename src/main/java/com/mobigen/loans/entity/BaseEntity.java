package com.mobigen.loans.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Entity는 @Entity 또는 @MapperSuperclass 가 정의된 클래스만 상속받을 수 있다.
// 상속을 통해 칼럼 정보를 제공하는 역할. 등록일자, 수정일자, 등록자, 수정자와 같은 모든 테이블에 공통으로 필요한 칼럼을 효과적으로 관리
// 직접 생성(ex. new BaseEntity())를 사용하지 않는다. 추상클래스(abstract)로 생성
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어준다. @EnableJpaAuditing 과 같이 사용한다. (엔티티의 영속, 수정 이벤트를 감지)
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    @CreatedDate // 엔티티가 생성됨을 감지하고 그 시점을 createdAt 필드에 기록
    @Column(updatable = false) // 생성일자는 수정되어서는 안되므로 @Column(updatable = false) 를 적용
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate // 엔티티가 수정됨을 감지하고 그 시점을 updatedAt 필드에 기록
    @Column(insertable = false) // 수정일자는 생성될 때 insert 되면 안되므로 @Column(insertable = false) 를 적용
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
