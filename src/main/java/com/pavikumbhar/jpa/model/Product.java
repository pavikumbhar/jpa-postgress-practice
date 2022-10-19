package com.pavikumbhar.jpa.model;

import com.pavikumbhar.jpa.enums.Status;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="PRODUCT")
@FieldNameConstants (asEnum = true)
@EntityListeners(AuditingEntityListener.class)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "PRODUCT_ID")
	private long productId;
	
	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	@Builder.Default
	@ToString.Exclude
	@OneToMany(mappedBy ="product", fetch = FetchType.LAZY )
	private List<ProductProperty> productProperties=new ArrayList<>();

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
