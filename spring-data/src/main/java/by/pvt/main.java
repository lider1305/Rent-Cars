package by.pvt;

import by.pvt.config.DataConfig;
import by.pvt.pojo.Brands;
import by.pvt.service.impl.BrandService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(DataConfig.class);
		BrandService brandService = context.getBean("brandService", BrandService.class);;
		Brands brand = brandService.findById(1);
		System.out.println(brand);
	}
}
