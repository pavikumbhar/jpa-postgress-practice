package com.pavikumbhar.jpa.model;


import com.pavikumbhar.jpa.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="PRODUCT_PROPERTY")
@FieldNameConstants(asEnum = true)
@EntityListeners(AuditingEntityListener.class)
public class ProductProperty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_PROPERTY_ID")
	private long productPropertyId;
	
	@Column(name = "PRODUCT_PROPERTY_CODE")
	private String productPropertyCode;
	
	@Column(name="PRODUCT_ID")   
	private long productId;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUCT_ID", insertable = false, updatable = false)
	private Product product;

	@CreatedBy
	@Column(name="CREATED_BY")
	private String createdBy;

	@LastModifiedBy
	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@CreatedDate
	@Column(name="CREATED_ON")
	private LocalDateTime createdOn;

	@LastModifiedDate
	@Column(name="MODIFIED_ON")
	private LocalDateTime modifiedOn;

}


