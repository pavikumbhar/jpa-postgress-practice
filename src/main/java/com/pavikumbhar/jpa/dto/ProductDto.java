package com.pavikumbhar.jpa.dto;
import static com.pavikumbhar.jpa.constant.AppConstant.YYYY_MM_DD_HH_MM_SS;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pavikumbhar.jpa.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {


	private long productId;

	private String productCode;

	private Status status;
	
	@Builder.Default
	private List<ProductPropertyDto> productProperties=new ArrayList<>();

	private String createdBy;

	private String modifiedBy;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
	private LocalDateTime createdOn;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern= YYYY_MM_DD_HH_MM_SS)
	private LocalDateTime modifiedOn;
}
