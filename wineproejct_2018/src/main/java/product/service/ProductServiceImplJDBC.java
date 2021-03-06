
package product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product.dao.ProductDao;
import product.vo.Product;
import product.vo.ProductPage;
import product.vo.ProductSearch;

@Service("productService")
public class ProductServiceImplJDBC implements ProductService {

	@Autowired
	private ProductDao productDao;
	int size = 10;
	int blockSize = 5;

	int searchSize = 25;

	public ProductPage getProductList(int pageNum) {
		List<Product> productList = productDao.getProductList((pageNum - 1) * size, size);
		int count = productDao.getProductCount();
		ProductPage productPage = new ProductPage(productList, pageNum, count, size, blockSize);
		return productPage;
	}

	public Product getProduct(int productId) {
		return productDao.getProduct(productId);
	}

	public List<Product> manySaleProduct() {
		List<Product> productList = productDao.manySaleProduct();
		return productList;
	} 

	public ProductPage searchWine(int pageNum, ProductSearch search) {
		List<Product> productList = productDao.searchWine((pageNum - 1) * searchSize, searchSize, search);
		int count = productDao.searchWineCount();
		ProductPage productPage = new ProductPage(productList, pageNum, count, searchSize, blockSize);
		return productPage;
	}

}
