package com.ffm.backend.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
public abstract class AbstractEntity {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(generator = "abstract-generator")
    @Getter
    protected Long id;

    @Version
    @Column(nullable = false)
    @Getter
    private Long version;
}
