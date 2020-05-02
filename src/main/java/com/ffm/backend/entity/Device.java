package com.ffm.backend.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Device extends AbstractEntity {

  @Column(nullable = false, precision = 19, scale = 16)
  @NotNull
  private BigDecimal latitude;

  @Column(nullable = false, precision = 19, scale = 16)
  @NotNull
  private BigDecimal longitude;

  @Column(nullable = false, unique = true)
  @NotNull
  private String fcmToken;
}
