package com.springboot.project.service.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.springboot.project.service.domain.CartVO;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductTagVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.TagVO;
import com.springboot.project.service.product.ProductDAO;

@Repository("productDAOImpl")
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public ProductDAOImpl() {
		System.out.println("[" + getClass().getName() + " Default Constructor] Call");
	}
	
	public ProductDAOImpl(SqlSession sqlSession) {
		System.out.println("[" + getClass().getName() + " SqlSeesion set Constructor] Call");
		this.sqlSession = sqlSession;
	}
	
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("[" + getClass().getName() + ".setSqlSession] Call");
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int addProduct(ProductVO productVO) {
		System.out.println("[" + getClass().getName() + ".addProduct] Call");
		return sqlSession.insert("ProductMapper.addProduct", productVO);
	}

	@Override
	public ProductVO getProduct(int prodNo) {
		System.out.println("[" + getClass().getName() + ".getProduct] Call");
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}

	@Override
	public int updateProduct(ProductVO productVO) {
		System.out.println("[" + getClass().getName() + ".updateProduct] Call");
		return sqlSession.update("ProductMapper.updateProduct", productVO);
	}

	@Override
	public int deleteProduct(int prodNo) {
		System.out.println("[" + getClass().getName() + ".deleteProduct] Call");
		return sqlSession.delete("ProductMapper.deleteProduct", prodNo);
	}

	@Override
	public List<ProductVO> getProductList(SearchVO searchVO) {
		System.out.println("[" + getClass().getName() + ".getProductList] Call");
		return sqlSession.selectList("ProductMapper.getProductList", searchVO);
	}

	@Override
	public int getProductCount(SearchVO searchVO) {
		System.out.println("[" + getClass().getName() + ".getProductCount] Call");
		return sqlSession.selectOne("ProductMapper.getProductCount", searchVO);
	}

	@Override
	public int updateProductCount(Map<String, Integer> map) {
		System.out.println("[" + getClass().getName() + ".updateProductCount] Call");
		return sqlSession.update("ProductMapper.updateProductCount", map);
	}

	@Override
	public int addProductImage(FileVO file) {
		System.out.println("[" + getClass().getName() + ".addProductImage] Call");
		return sqlSession.insert("ProductMapper.addProductImage", file);
	}

	@Override
	public int updateAddProductImage(FileVO file) {
			System.out.println("[" + getClass().getName() + ".updateAddProductImage] Call");
			return sqlSession.insert("ProductMapper.updateAddProductImage", file);
	}

	@Override
	public List<FileVO> getProductImage(int prodNo) {
		System.out.println("[" + getClass().getName() + ".selectProductImage] Call");
		return sqlSession.selectList("ProductMapper.getProductImage", prodNo);
	}

	@Override
	public int deleteProductImage(int prodNo) {
		System.out.println("[" + getClass().getName() + ".deleteProductImage] Call");
		return sqlSession.delete("ProductMapper.deleteProductImage", prodNo);
	}

	@Override
	public List<ProductVO> getProductInfo() {
		System.out.println("[" + getClass().getName() + ".getProductInfo] Call");
		return sqlSession.selectList("ProductMapper.getProductInfo");
	}

	@Override
	public int addCart(CartVO cart) {
		System.out.println("[" + getClass().getName() + ".addCart] Call");
		return sqlSession.insert("ProductMapper.addCart" , cart);
	}

	@Override
	public int deleteCart(CartVO cart) {
		System.out.println("[" + getClass().getName() + ".deleteCart] Call");
		return sqlSession.delete("ProductMapper.deleteCart", cart);
	}

	@Override
	public int checkIsCart(CartVO cart) {
		System.out.println("[" + getClass().getName() + ".checkIsCart] Call");
		return sqlSession.selectOne("ProductMapper.checkIsCart", cart);
	}

	@Override
	public List<CartVO> getCartList(String userId) {
		System.out.println("[" + getClass().getName() + ".getCartList] Call");
		return sqlSession.selectList("ProductMapper.getCartList", userId);
	}

	@Override
	public TagVO getTag(String tagName) {
		System.out.println("[" + getClass().getName() + ".getTag] Call");
		return sqlSession.selectOne("ProductMapper.getTag", tagName);
	}

	@Override
	public int addTag(String tagName) {
		System.out.println("[" + getClass().getName() + ".addTag] Call");
		return sqlSession.insert("ProductMapper.addTag", tagName);
	}

	@Override
	public int addProductTag(ProductTagVO productTag) {
		System.out.println("[" + getClass().getName() + ".addProductTag] Call");
		return sqlSession.insert("ProductMapper.addProductTag", productTag);
	}

	@Override
	public int deleteTag(String tagName) {
		System.out.println("[" + getClass().getName() + ".deleteTag] Call");
		return sqlSession.delete("ProductMapper.deleteTag", tagName);
	}

	@Override
	public int deleteProductTag(String tagName) {
		System.out.println("[" + getClass().getName() + ".deleteProductTag] Call");
		return sqlSession.delete("ProductMapper.deleteProductTag", tagName);
	}

	@Override
	public List<TagVO> getTagFromProduct(int prodNo) {
		System.out.println("[" + getClass().getName() + ".getTagFromProduct] Call");
		return sqlSession.selectList("ProductMapper.getTagFromProduct", prodNo);
	}

	@Override
	public int addProductTagWithSeq(int tagNo) {
		System.out.println("[" + getClass().getName() + ".addProductTagWithSeq] Call");
		return sqlSession.insert("ProductMapper.addProductTagWithSeq", tagNo);
	}

	@Override
	public List<ProductVO> getWeatherRecommendProduct(int tagNo) {
		System.out.println("[" + getClass().getName() + ".getWeatherRecommendProduct] Call");
		return sqlSession.selectList("ProductMapper.getWeatherRecommendProduct", tagNo);
	}
}
